package com.andrei.courutinesexample

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NumberViewModel : ViewModel() {

    private var _city = MutableLiveData<String>()
    val city: LiveData<String>
        get() = _city

    private var _temp = MutableLiveData<Int>()
    val temp: LiveData<Int>
        get() = _temp

    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

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