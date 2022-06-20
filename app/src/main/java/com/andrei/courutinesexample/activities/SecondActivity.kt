package com.andrei.courutinesexample.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.andrei.courutinesexample.databinding.ActivityFirstBinding
import com.andrei.courutinesexample.databinding.ActivitySecondBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** На этом экране пример использования async()
 *  Имеется три корутины.
 *  В первой имитация загрузки города, которая происходит с некторой задержкой.
 *  Во второй то же самое, только для температуры.
 */

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLoad.setOnClickListener {
            binding.progress.isVisible = true
            binding.buttonLoad.isEnabled = false

            /** Первая корутина
            В переменную deferredCity вернется то значение, которое мы пропишем в последней
            строке корутины. В данном случае это строка city */
            val deferredCity = lifecycleScope.async {
                val city = loadCity()
                binding.tvLocation.text = city
                city
            }

            /** Вторая корутина
            В переменную deferredTemp вернется то значение, которое мы пропишем в последней
            строке корутины. В данном случае это строка temp */
            val deferredTemp = lifecycleScope.async {
                val temp = loadTemperature()
                binding.tvTemperature.text = temp.toString()
                temp
            }
            /** Третья корутина
             * В ней мы используем метод await() для первых двух корутин.
             * Благодаря ему корутина ожидает завершения работы 1 и 2 корутины и при этом возвращает
             * то значение, которое мы записали последним в этих корутнинах.
             * И только после их завершения третья корутина идет дальше.
             */
            lifecycleScope.launch {
                val city = deferredCity.await()
                val temp = deferredTemp.await()
                Toast.makeText(
                    this@SecondActivity,
                    "Город: $city, Температура: $temp",
                    Toast.LENGTH_LONG
                ).show()
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