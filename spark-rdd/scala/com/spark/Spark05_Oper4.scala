package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark05_Oper4 {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("")

    val sc = new SparkContext(conf )

    val listRdd: RDD[List[Int]] = sc.makeRDD(Array(List(1,2),List(3,4)))


    val unit: RDD[Int] = listRdd.flatMap(datas => datas)

    unit.collect().foreach(println)

  }
}
