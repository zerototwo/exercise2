package com.stream

import org.apache.spark.SparkConf
import org.apache.spark.deploy.SparkSubmit
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStream_updateState {


  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("")

    val streamingContext = new StreamingContext(sparkConf,Seconds(3))
    streamingContext.sparkContext.setCheckpointDir("cp")
    val value: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(
      streamingContext,
      "hadoop11:2181",
      "lpp",
      Map("lpp" -> 3)
    )
    SparkSubmit
    val wordDStream: DStream[String] = value.flatMap(t=>t._2.split(" "))

    val mapDStream: DStream[(String, Int)] = wordDStream.map((_,1))

    val statDstream: DStream[(String, Int)] = mapDStream.updateStateByKey {

      case (seq, buffer) => {
        val sum = buffer.getOrElse(0) + seq.sum
        Option(sum)
      }
    }

    statDstream.print()

    streamingContext.start()

    streamingContext.awaitTermination()
  }
}
