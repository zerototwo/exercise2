package com.ds.queue

import scala.io.StdIn

object ArrayQueueDemo {

  def main(args: Array[String]): Unit = {
    val queue = new ArrayQueue(3)

    var key = ""

//    while (true){
//
//      println()
//      println("请选择菜单")
//      println("show:显示队列")
//      println("add :添加数据")
//      println("get :获取数据")
//      println("exit:退出程序")
//
//      key  = StdIn.readLine()
//      key match {
//        case "show" =>queue.show()
//        case "add" =>
//          println("请输入一个数")
//          val num  = StdIn.readInt()
//          queue.addQueue(num)
//        case "get"=>{
//          val unit = queue.getQueue()
//          if (unit.isInstanceOf[Exception]){
//            println(unit.asInstanceOf[Exception].getMessage)
//          }{
//
//          println(unit)
//          }
//        }
//      }
//
//    }

  }
}

class ArrayQueue(arrMaxSize:Int){

  val maxSize  = arrMaxSize
  val arr  = new Array[Int](maxSize)


  var front  = -1
  var rear = -1

  def isFull():Boolean={
    rear == maxSize -1
  }

  def isEmpty():Boolean = {

    rear == front
  }

  //添加
  def addQueue(num:Int):Unit={

    if (isFull()){
      println("队列已满")
    }

    rear+=1
    arr(rear) = num

  }

  //遍历
  def show():Unit = {
    if (isEmpty()) {
      println("队列为空")
    }

    println("队列遍历:")
    for (i<- front+1 to rear) {

      printf("array(%d)=%d \t",i,arr(i))
    }
  }

  def getQueue():Any = {
    if (isEmpty()){
      throw new Exception("队列为空")
    }

    front+= 1

    arr(front)
  }



}
