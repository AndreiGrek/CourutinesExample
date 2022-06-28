package com.example.lambdas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button


class MainActivity : AppCompatActivity() {

    private val LOG = "LOGQ"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_1).setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }

        Log.d(LOG, simpleFunction(2).toString())
        Log.d(LOG, lambdaFunction.invoke(3).toString())

        /** Простой пример номер 1
        Тип переменной "number" можно не указывать */
        Log.d(LOG, "Первый пример: ${highOrderFunction1 { number: Int -> number * 5 }}")
        Log.d(LOG, "Первый пример: ${highOrderFunction1 { number -> number * 5 }}")
        Log.d(LOG, "Первый пример: ${highOrderFunction1 { number: Int -> number * 22 }}")

        /** Простой пример номер 2
        Лямбду можно вынести за фигцрные скобки */
        Log.d(LOG, "Второй пример: ${highOrderFunction2(10, { number -> number.toString() })}")
        Log.d(LOG, "Второй пример: ${highOrderFunction2(10) { number -> number.toString() }}")

        /** Простой пример номер 3
        Возвращаем Unit */
        highOrderFunction3 { number -> Log.d("LOGQ", number.toString()) }
    }

    // Простая функция
    private fun simpleFunction(param: Int): Int {
        return param * 2
    }

    // Простая лямбда
    private val lambdaFunction = { param: Int -> param * 2 }

    /** Простой пример номер 1
     * Рассмотрим её поподробней.
     * "Функция highOrderFunction1" принимает функцию "number" в качестве параметра.
     * Здесь мы прописываем только сигнатуру функции "number", в которой говорим, что она принимает Int
     * и возвращаем Int.
     * В return мы передаем функции "number" какой-то аргумент.
     * Реализацию функции "number" мы прописываем в момент вызова функции highOrderFunction1 в onCreate.
     * Например, highOrderFunction1 { number: Int -> number * 5 }. */
    private fun highOrderFunction1(number: (Int) -> Int): Int {
        return number(5)
    }

    // Простой пример номер 2
    private fun highOrderFunction2(value: Int, number: (Int) -> String): String {
        return number(5 * value)
    }

    // Простой пример номер 3
    private fun highOrderFunction3(number: (Int) -> Unit) {
        number(99)
    }
}