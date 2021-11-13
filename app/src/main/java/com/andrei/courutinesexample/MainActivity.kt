 package com.andrei.courutinesexample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

 class MainActivity : AppCompatActivity() {

    private var list: ArrayList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TAG", "OnCrate в ${Thread.currentThread().name}")

        findViewById<Button>(R.id.button).setOnClickListener {
            Toast.makeText(this, "Тра-та-та", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.buttonStart).setOnClickListener {
//            test()
//            coroutineTest()
        GlobalScope.launch (Dispatchers.IO){
            coroutineTest()
//            withContext(Dispatchers.Main){
//                Toast.makeText(this@MainActivity, "Тра-та-та", Toast.LENGTH_SHORT).show()
//                coroutineTest2()
//            }
        }
            GlobalScope.launch (Dispatchers.IO){
                coroutineTest()
            }
        }

    }

   suspend fun coroutineTest(){
        Log.d("TAG", "Метод coroutineTest начат ${Thread.currentThread().name} потоке")
       delay(2500)
        Log.d("TAG", "Метод coroutineTest идет")
       delay(2500)
        Log.d("TAG", "Метод coroutineTest закончен")
    }

     suspend fun coroutineTest2(){
         Log.d("TAG", "Метод coroutineTest2 начат ${Thread.currentThread().name} потоке")
         delay(2500)
         Log.d("TAG", "Метод coroutineTest2 идет")
         delay(2500)
         Log.d("TAG", "Метод coroutineTest2 закончен")
     }

     fun test (){

         Log.d("TAG", "Метод test начат ${Thread.currentThread().name} потоке")
         Thread.sleep(2500)
         Log.d("TAG", "Метод test идет")
         Thread.sleep(2500)
         Log.d("TAG", "Метод test закончен")
     }
}