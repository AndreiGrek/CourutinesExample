package com.andrei.courutinesexample.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.andrei.courutinesexample.viewmodels.FlowViewModel
import com.andrei.courutinesexample.databinding.ActivitySixBinding
import kotlinx.coroutines.flow.collect
/** То же самое, что и SixActivity, но с миграцией с LiveData на Flow. */
class SixActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySixBinding

    private val flowViewModel: FlowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySixBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()

        binding.buttonLoad.setOnClickListener {
            flowViewModel.calculate()
        }
        binding.buttonCheck.setOnClickListener {
            Toast.makeText(this, "UI не блокирован", Toast.LENGTH_SHORT).show()
        }
        binding.buttonClear.setOnClickListener {
            flowViewModel.clear()
        }
    }

    /** По сути, разница небольшая.
     * Сперва вызываем lifecycleScope.launchWhenStarted {}
     * Затем вместо observe() вызываем collect {}
     * Больше измененией никаких.
     */
    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            flowViewModel.city.collect {
                binding.tvLocation.text = it
            }
        }

        lifecycleScope.launchWhenStarted {
            flowViewModel.temp.collect {
                binding.tvTemperature.text = it.toString()
            }
        }

        lifecycleScope.launchWhenStarted {
            flowViewModel.progress.collect {
                binding.progress.isVisible = it
                binding.buttonLoad.isEnabled = !it
            }
        }
    }
}