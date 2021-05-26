package com.spark

import java.sql.DriverManager

import org.apache.spark.rdd.{JdbcRDD, RDD}
import org.apache.spark.{SparkConf, SparkContext}

object Spark19_Mysql {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("")

    val sc = new SparkContext(conf )

    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://127.0.0.1:3306/lpp"
    val userName = "root"
    val passWd = "123456"

    val sql = "select * from user where uid>=? and uid<=?"

    val unit = new JdbcRDD(
      sc,
      () => {
        Class.forName(driver)
        DriverManager.getConnection(url, userName, passWd)
      },
      sql,
      1,
      3,
      2,
      (rs) => {
        println(rs.getString(1) + "," + rs.getString(2))
      }

    )
    unit.collect()


  }
}
