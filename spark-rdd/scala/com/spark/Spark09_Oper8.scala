package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark09_Oper8 {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("")

    val sc = new SparkContext(conf )

    val listRdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))

    val unit: RDD[Int] = listRdd.sample(false,0.4,1)

    unit.foreach(println)
  }
}
