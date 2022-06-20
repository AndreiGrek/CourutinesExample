package com.andrei.courutinesexample.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/** ViewModel для SixActivity
 * То же самое, только здесь миграция с LiveData на Flow.
 */
class FlowViewModel : ViewModel(){

    /** По сути, разница небольшая.
     * У MutableStateFlow нет параметризации, но есть значение по умолчанию.
     * У StateFlow нет геттера, но у значения вызываем метод asStateFlow().
     * Больше измененией никаких
     */
    private var _city = MutableStateFlow("Минск")
    val city: StateFlow<String> = _city.asStateFlow()

    private var _temp = MutableStateFlow(8)
    val temp: StateFlow<Int> = _temp.asStateFlow()

    private var _progress = MutableStateFlow(false)
    val progress: StateFlow<Boolean> = _progress.asStateFlow()

    fun calculate() {
        /** Третья корутина */
        viewModelScope.launch {

            /** Первая корутина */
            val deferredCity = viewModelScope.async {
                loadCity()
            }

            /** Вторая корутина */
            val deferredTemp = viewModelScope.async {
                loadTemperature()
            }

            _progress.value = true

            /** Если мы сперва хотим дождаться завершения обоиз корутин, а потом присвоить их
             *  значение, то я решил здесь испольщвать join(), а потом await().
             *  Не знаю, можно ли так, но работает.
             */
//            deferredCity.join()
//            deferredTemp.join()
            _city.value = deferredCity.await()
            _temp.value = deferredTemp.await()
            _progress.value = false
        }
    }

    fun clear(){
        _city.value = ""
        _temp.value = 0
    }

    private suspend fun loadCity(): String {
        delay(500)
        return "Moscow"
    }

    private suspend fun loadTemperature(): Int {
        Log.d("logq", "Метод запущен")
        delay(3000)
        return 17
    }
}