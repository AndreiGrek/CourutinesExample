package com.example.lambdas.classes

import android.util.Log

open class Animal

data class Dog(val id: Int) : Animal()

class Cat : Animal()

/** Пример параметризированного класса */
class Person<T>(private val something: T) {

    fun someFun() {
        Log.d("LOGQ", "Человек  $something")
    }
}