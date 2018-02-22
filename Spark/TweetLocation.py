import sys
from pyspark import SparkContext
from pyspark.streaming import StreamingContext

if __name__ == "__main__":
    sc = SparkContext(appName="Tweet Location")
    ssc = StreamingContext(sc, 2)

    ssc.checkpoint("file:///tmp/spark")

    lines = ssc.socketTextStream(sys.argv[1], int(sys.argv[2]))