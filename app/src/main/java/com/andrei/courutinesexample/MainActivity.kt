 package com.andrei.courutinesexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.andrei.courutinesexample.databinding.ActivityMainBinding
import kotlinx.coroutines.*

 class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btn1.setOnClickListener {
                val intent = Intent(this@MainActivity, FirstActivity::class.java)
                startActivity(intent)
            }
            btn2.setOnClickListener {

            }
            btn3.setOnClickListener {

            }
        }
    }
}