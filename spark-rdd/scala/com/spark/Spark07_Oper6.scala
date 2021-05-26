package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark07_Oper6 {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("")

    val sc = new SparkContext(conf )

    val listRdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))

    val unit: RDD[(Int, Iterable[Int])] = listRdd.groupBy(i=>i%2)


    unit.collect().foreach(println)

  }
}
