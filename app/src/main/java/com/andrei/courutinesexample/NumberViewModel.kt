package com.andrei.courutinesexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NumberViewModel: ViewModel() {

    private var _something = MutableLiveData<String>()
    val something : LiveData<String>
    get() = _something

}