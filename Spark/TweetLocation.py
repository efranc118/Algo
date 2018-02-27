import sys
import sched, time

from pyspark import SparkContext
from pyspark.streaming import StreamingContext

from pyspark.mllib.linalg import Vectors
from pyspark.mllib.clustering import StreamingKMeans

if __name__ == "__main__":
    sc = SparkContext(appName="Tweet Location")
    ssc = StreamingContext(sc, 2)

    ssc.checkpoint("file:///tmp/spark")

    def parseTrainingData(line):
        cells = line.split(",")
        return Vectors.dense([float(cells[0]), float(cells[1])])

    trainingStream = ssc.textFileStream(
        "file:///Users/evan.francis/dev/Algo/Spark/training"
    ).map(parseTrainingData)

    model = StreamingKMeans(k=2, decayFactor=1.0).setRandomCenters(2, 1.0, 0)

    print("Initial Centers: " + str(model.latestModel().centers))

    model.trainOn(trainingStream)

    ssc.start()

    scheduler = sched.scheduler(time.time, time.sleep)

    def print_cluster_centers(scheduler, model):
        print("Cluster Centers: " + str(model.latestModel().centers))
        scheduler.enter(10, 1, print_cluster_centers, (scheduler, model))

    scheduler.enter(10, 1, print_cluster_centers, (scheduler, model))

    scheduler.run()

    ssc.awaitTermination()