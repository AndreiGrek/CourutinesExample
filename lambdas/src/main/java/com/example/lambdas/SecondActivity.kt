package com.example.lambdas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lambdas.classes.Animal
import com.example.lambdas.classes.Cat
import com.example.lambdas.classes.Dog
import com.example.lambdas.classes.Person

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val animal = Animal()
        val cat = Cat()
        val dog = Dog(10)

        /** При создании объекта person, мы можем передавать в конструктор всё, что угодно, так как
         класс параметризован */
        val person1 = Person(10)
        val person2 = Person("Вася")
        val person3 = Person(cat)

        genericFunction(1)
        genericFunction("Вася")
        genericFunction(1.0)
        genericFunction(cat)
        genericFunction(dog)

        genericInFunction(animal)
        genericInFunction(cat)
        genericInFunction(dog)

        genericFun(animal)
        genericFun(cat)
        genericFun(dog)

        person1.someFun()
        person2.someFun()
        person3.someFun()

    }

    /** Пример параметризированной функции
     * В неё можно запихнуть всё, что угодно, для чего можно вызвать toString()
     */
    private fun <T> genericFunction (someThing: T){
        Log.d("LOGQ", someThing.toString())
    }

    /** Функция ниже параметризирована параметром Т, который наследуется от Animal
     По сути, функция тождественна функции genericFun(animal: Animal) */
    private fun <T : Animal> genericInFunction (someThing: T){
        Log.d("LOGQ", someThing.toString())
    }

    private fun genericFun(animal: Animal){
        Log.d("LOGQ", animal.toString())
    }

}