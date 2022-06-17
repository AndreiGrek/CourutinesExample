package com.andrei.courutinesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.andrei.courutinesexample.databinding.ActivityFirstBinding
import com.andrei.courutinesexample.databinding.ActivityThirdBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** На этом экране пример использования async(), но вместе с ViewModel.
 *  В остальном, всё то же самое, что и в предыдущих примерах.
 */
class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    private val numberViewModel: NumberViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()

        binding.buttonLoad.setOnClickListener {
            numberViewModel.calculate()
        }
        binding.buttonCheck.setOnClickListener {
            Toast.makeText(this, "UI не блокирован", Toast.LENGTH_SHORT).show()
        }
        binding.buttonClear.setOnClickListener {
            numberViewModel.clear()
        }
    }

    private fun observeViewModel() {
        numberViewModel.city.observe(this) {
            binding.tvLocation.text = it
        }
        numberViewModel.temp.observe(this) {
            binding.tvTemperature.text = it.toString()
        }
        numberViewModel.progress.observe(this) {
            binding.progress.isVisible = it
            binding.buttonLoad.isEnabled = !it
        }
    }
}