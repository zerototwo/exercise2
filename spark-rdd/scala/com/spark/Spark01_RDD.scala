package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("")

    val sc = new SparkContext(conf )

    val listRdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    val value = sc.parallelize(Array(1, 2, 3, 45))

    listRdd.saveAsTextFile("output")
    //listRdd.collect().foreach(println)
  }
}
