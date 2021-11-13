package com.andrei.lambdaexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG", lambdaTest(2).toString())
       var lambdaTest2 =  {param: Int -> param*2}
        Log.d("TAG",  lambdaTest2.invoke(3).toString())
    }

    fun lambdaTest(param: Int): Int {
        return  param*2
    }





}