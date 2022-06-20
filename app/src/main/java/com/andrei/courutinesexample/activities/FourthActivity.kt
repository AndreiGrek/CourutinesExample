package com.andrei.courutinesexample.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.andrei.courutinesexample.R
import kotlinx.coroutines.*

/** Пример обработки ошибок.
 * Здесь три корутины. Третья завершится раньше всех, так как delay(1000).
 * Но мы чисто для примера в нее всунули ошибку.
 * Если мы не обработаем её с помощью exceptionHandler, то приложение будет крашить.
 * Если обработаем, то третья корутина передаст ошибку родительской джобе и все остальные корутины прервутся.
 * При этом краша не произойдет, но 1 и 2 корутины выполнены не будут.
 *
 * Но (ВАЖНО!) если мы будем использовать SupervisorJob() вместо обычной Job(), то
 * ошибка обработается, но при этом 1 и 2 корутины продолжат свою работу как ни в чем не бывало.
 */
class FourthActivity : AppCompatActivity() {

    private val parentJob = SupervisorJob()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d(LOG_TAG, "Словлена абосцаная ошибка: $throwable")
    }
    private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob + exceptionHandler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)
        method()
    }

    private fun method() {
        val childJob1 = coroutineScope.launch {
            delay(3000)
            Log.d(LOG_TAG, "first coroutine finished")
        }
        val childJob2 = coroutineScope.launch {
            delay(2000)
            Log.d(LOG_TAG, "second coroutine finished")
        }
        val childJob3 = coroutineScope.launch {
            delay(1000)
            error()
            Log.d(LOG_TAG, "third coroutine finished")
        }
    }

    private fun error() {
        throw RuntimeException()
    }

    companion object {
        private const val LOG_TAG = "MainViewModel"
    }
}