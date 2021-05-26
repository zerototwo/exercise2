package com.ch1

import com.ch1.SeasonEm.SeasonEm

object Scala_methos {

  def main(args: Array[String]): Unit = {

    for(i<-0 to 100){
      println(i)
    }
  }

  def getMidEle[A](l:List[A])= {
    l(l.length/2)
  }
}

object SeasonEm extends Enumeration{
  type SeasonEm = Value
  val spring,summer,winter,autumn = Value
}
class EnglishClass[A,B,C](val classSeason:A,val className:B,val classType:C)




