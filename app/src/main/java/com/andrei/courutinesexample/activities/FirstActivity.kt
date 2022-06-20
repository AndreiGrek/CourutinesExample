package com.andrei.courutinesexample.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.andrei.courutinesexample.databinding.ActivityFirstBinding
import com.andrei.courutinesexample.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** На этом экране пример использования join()
 *  Имеется три корутины.
 *  В первой имитация загрузки города, которая происходит с некторой задержкой.
 *  Во второй то же самое, только для температуры.
 */

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLoad.setOnClickListener {
            binding.progress.isVisible = true
            binding.buttonLoad.isEnabled = false

            /** Первая корутина */
            val jobCity = lifecycleScope.launch {
                val city = loadCity()
                binding.tvLocation.text = city
            }
            /** Вторая корутина */
            val jobTemp = lifecycleScope.launch {
                val temp = loadTemperature()
                binding.tvTemperature.text = temp.toString()
            }
            /** Третья корутина
             * В ней мы используем метод join() для первых двух корутин.
             * Благодаря ему корутина ожидает завершения работы 1 и 2 корутины.
             * И только после их завершения идет дальше.
             */
            lifecycleScope.launch {
                jobCity.join()
                jobTemp.join()
                binding.progress.isVisible = false
                binding.buttonLoad.isEnabled = true
            }
        }

        binding.buttonCheck.setOnClickListener {
            Toast.makeText(this, "UI не блокирован", Toast.LENGTH_SHORT).show()
        }
    }

        private suspend fun loadCity(): String {
            delay(500)
            return "Moscow"
        }

        private suspend fun loadTemperature(): Int {
            delay(3000)
            return 17
        }

}