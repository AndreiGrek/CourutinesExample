package com.example.lambdas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ThirdActivity : AppCompatActivity() {

    private val LOG = "LOGQ"
    var list = mutableListOf(1, 2, 3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val filteredList = list.filter { it > 1 }
        val mapList = list.map { it * 2 }
        Log.d(LOG, "list: $list")
        Log.d(LOG, "filteredList: $filteredList")
        Log.d(LOG, "list: $list")
        Log.d(LOG, "mapList: $mapList")
    }
}