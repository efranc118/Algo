import sys
from pyspark import SparkContext
from pyspark.streaming import StreamingContext

if __name__ == "__main__":
    sc = SparkContext(appName="Stream Summing Test")
    ssc = StreamingContext(sc, 2)

    ssc.checkpoint("file:///tmp/spark")

    lines = ssc.socketTextStream(sys.argv[1], int(sys.argv[2]))

    values = lines.map(lambda x: ("k", int(x)))

    def countWords(newValues, lastSum):
        if lastSum is None:
            lastSum = 0
        return sum(newValues, lastSum)

    count = lines.flatMap(lambda line: line.split(" "))\
        .map(lambda word: (word, 1))\
        .updateStateByKey(countWords)

    count.pprint()

    ssc.start()
    ssc.awaitTermination()