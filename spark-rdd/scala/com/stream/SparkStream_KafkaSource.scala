package com.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStream_KafkaSource {


  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("")

    val streamingContext = new StreamingContext(sparkConf,Seconds(3))

    val fileDStream: DStream[String] = streamingContext.textFileStream("test")


    val wordDStream: DStream[String] = fileDStream.flatMap(line=>line.split(" "))

    val mapDStream: DStream[(String, Int)] = wordDStream.map((_,1))

    val wordToSum: DStream[(String, Int)] = mapDStream.reduceByKey(_+_)

    wordToSum.print()

    streamingContext.start()

    streamingContext.awaitTermination()
  }
}
