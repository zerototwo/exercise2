package com.stream

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStream_MyReceiver {


  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("")

    val streamingContext = new StreamingContext(sparkConf,Seconds(3))

    val value: ReceiverInputDStream[String] = streamingContext.receiverStream(new MyReceiver("hadoop11",9998))

    val wordDStream: DStream[String] = value.flatMap(line=>line.split(" "))

    val mapDStream: DStream[(String, Int)] = wordDStream.map((_,1))

    val wordToSum: DStream[(String, Int)] = mapDStream.reduceByKey(_+_)

    wordToSum.print()

    streamingContext.start()

    streamingContext.awaitTermination()
  }
}

class MyReceiver(host:String,port:Int) extends Receiver[String](StorageLevel.MEMORY_ONLY){

  var socket:java.net.Socket = null


  def receive():Unit={

    socket = new java.net.Socket(host,port)
    val reader: BufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream,"UTF-8"))
    var line:String = null
    while ((line = reader.readLine())!=null){

      if ("END".equals(line)){
        return
      }else{
      this.store(line)

      }

    }
  }
  override def onStart(): Unit = {

    new Thread(new Runnable {
      override def run(): Unit = {

        receive()
      }
    }).start()

  }

  override def onStop(): Unit = {

    if (socket !=null) {
      socket.close()
    }
  }
}