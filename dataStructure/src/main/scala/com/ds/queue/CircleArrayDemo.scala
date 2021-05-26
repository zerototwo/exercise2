package com.ds.queue

object CircleArrayDemo {

  def main(args: Array[String]): Unit = {

  }
}

class CircleArray(arrMaxSize:Int){

  val maxSize = arrMaxSize

  val arr = new Array[Int](maxSize)

  var front = 0

  var rear = 0

  def isEmpty():Boolean={

    front == rear
  }

  def isFull():Boolean ={
    (rear+1)% maxSize == front
  }


  def addQueue(num:Int):Unit={

    if (isFull()){
      println("队列已满")
    }
    arr(rear) = num
    rear = (rear+1)%maxSize
  }

  def getQueue():Any = {
    if (isEmpty()){
      return new Exception("队列为空")
    }

    val res = arr(front)

    front+=1

    res
  }

  def show():Unit = {
    if (isEmpty()){
      println("队列为空")
    }

    for (i<- front until front+size()){

      printf("array(%d)=%d \t",i%maxSize,arr(i%maxSize))

    }
  }


  def size():Int = {
    (rear+maxSize-front)%maxSize
  }
}
