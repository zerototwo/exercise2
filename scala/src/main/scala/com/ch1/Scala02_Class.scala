package com.ch1

object Scala01_Class {


  def main(args: Array[String]): Unit = {

    val test :Test[User1] = new Test[Child1]

  }


}

class Person1{

}
class User1 extends Person1{

}

class Child1 extends User1{

}

class Test[+User1]{

}