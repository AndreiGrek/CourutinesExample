 package com.andrei.courutinesexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andrei.courutinesexample.activities.*
import com.andrei.courutinesexample.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            /** launch() and Job */
            btn1.setOnClickListener {
                val intent = Intent(this@MainActivity, FirstActivity::class.java)
                startActivity(intent)
            }

            /** async() and Deferred */
            btn2.setOnClickListener {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(intent)
            }
            /** Пример с ViewModel */
            btn3.setOnClickListener {
                val intent = Intent(this@MainActivity, ThirdActivity::class.java)
                startActivity(intent)
            }
            /** Exception Handler */
            btn4.setOnClickListener {
                val intent = Intent(this@MainActivity, FourthActivity::class.java)
                startActivity(intent)
            }
            /** Cancelling Coroutines */
            btn5.setOnClickListener {
                val intent = Intent(this@MainActivity, FifthActivity::class.java)
                startActivity(intent)
            }
            /** Cancelling Coroutines */
            btn6.setOnClickListener {
                val intent = Intent(this@MainActivity, SixActivity::class.java)
                startActivity(intent)
            }
        }
    }
}