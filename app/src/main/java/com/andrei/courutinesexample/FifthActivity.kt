package com.andrei.courutinesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

/** Пример отмены корутины.
 * Есть две корутины. В первой идет рандомная работа в течение 10 секунд.
 * Во второй корутине на 3 секунде вызывается метод cancel() для первой корутины.
 * Но этот метод не отменяет корутину, а лишь присваивает ей флаг isAcive = false.
 * Чтобы отменить первую корутину, нам нужно вызвать в ней метод ensureActive(), который
 * проверяет, активна ли она. Ниже есть более подробные комментарии по этому методу.
 * Также есть коллбэк-метод invokeOnCompletion(), который вызывается, когда корутина была завершена
 */
class FifthActivity : AppCompatActivity() {
    private val parentJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)
        method()
    }

    private fun method() {

        val job = coroutineScope.launch(Dispatchers.Default) {
            Log.d(LOG_TAG, "Started")
            val before = System.currentTimeMillis()
            var count = 0
            for (i in 0 until 10_000_000) {
                for (j in 0 until 20) {
                    /** Метод ensureActive() проверяет, активна ои корутина
                     * По усти, его можно заменить кодом:
                     * if(isActive){
                     * count++
                     * } else {
                     * throw CancellationException()
                     * }
                     */
                    ensureActive()
                    count++
                }
            }
            Log.d(LOG_TAG, "Finished: ${System.currentTimeMillis() - before}")
        }
        job.invokeOnCompletion {
            Log.d(LOG_TAG, "Coroutine was cancelled. $it")
        }
        coroutineScope.launch {
            delay(3000)
            job.cancel()
        }
    }

    companion object {
        private const val LOG_TAG = "MainViewModel"
    }
}