package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark06_Oper5 {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("")

    val sc = new SparkContext(conf )

    val listRdd: RDD[Int] = sc.makeRDD(1 to 16, 4)

    val unit: RDD[Array[Int]] = listRdd.glom()

    unit.collect().foreach(println)


  }
}
