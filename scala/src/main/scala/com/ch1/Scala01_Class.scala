package com.ch1

object Scala01_Class {


  def main(args: Array[String]): Unit = {

    test[User](new User)
  }

  def test[T<:User](t:T):Unit ={
    println(t)
  }
}

class Person{

}
class User extends Person{

}

class Child extends User{

}

