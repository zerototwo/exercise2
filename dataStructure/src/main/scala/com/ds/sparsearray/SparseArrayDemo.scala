package com.ds.sparsearray

import scala.collection.mutable.ArrayBuffer

object SparseArrayDemo {


  def main(args: Array[String]): Unit = {

    val rows = 11
    val cols = 11
    val chessMap = Array.ofDim[Int](rows, cols)

    chessMap(1)(2) = 1 //黑子
    chessMap(2)(3) = 2 //篮子

    for(row<-chessMap){

      for (item<-row){
        printf("%d ",item)
      }
      println()
    }

    val nodes: ArrayBuffer[Node] = ArrayBuffer[Node]()
    nodes.append(new Node(rows,cols,0))

    for (i<-0 until chessMap.length){
      for (j<- 0 until chessMap(1).length){

        if (chessMap(i)(j) !=0){
          nodes.append(new Node(i,j,chessMap(i)(j)))
        }
      }
    }

    println("稀疏数组")

    for (node <- nodes){

      printf("%d , %d , %d",node.row,node.col,node.value)
      println()
    }


    val arr = nodes(0)

    val array = Array.ofDim[Int](arr.row,arr.col)

    for (i<- 1 until nodes.length){

      val node1 = nodes(i)

      array(node1.row)(node1.col) = node1.value
    }

    for(row<-array){

      for (item<-row){
        printf("%d ",item)
      }
      println()
    }

  }
}

class Node(val row:Int,val col:Int,val value:Int)