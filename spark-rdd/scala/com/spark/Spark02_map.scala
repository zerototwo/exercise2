package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_map {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("")

    val sc = new SparkContext(conf )

    val listRdd: RDD[Int] = sc.makeRDD(1 to 10)


//    val value = listRdd.mapPartitions(datas => {
//      datas.map(_ * 2)
//    })

    val indexRdd = listRdd.mapPartitionsWithIndex {
      case (num, datas) => {
        datas.map((_,"分区号："+num))
      }
    }
    indexRdd.collect().foreach(println)

//    value.foreach(println)
   // listRdd.map(x=>2*x).collect().foreach(println)

  }
}
