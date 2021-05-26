package com.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStream_FileDataSource {


  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("")

    val streamingContext = new StreamingContext(sparkConf,Seconds(5))

    val value: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(
      streamingContext,
      "hadoop11:2181",
      "lpp",
      Map("lpp" -> 3)
    )
    value

    val wordDStream: DStream[String] = value.flatMap(t=>t._2.split(" "))

    val mapDStream: DStream[(String, Int)] = wordDStream.map((_,1))

    val wordToSum: DStream[(String, Int)] = mapDStream.reduceByKey(_+_)

    wordToSum.print()

    streamingContext.start()

    streamingContext.awaitTermination()
  }
}
