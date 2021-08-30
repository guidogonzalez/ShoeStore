package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = ArrayList()
    }

    fun addShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
        _shoeList.value = _shoeList.value
    }

    fun clearShoes() {
        _shoeList.value?.clear()
    }
}