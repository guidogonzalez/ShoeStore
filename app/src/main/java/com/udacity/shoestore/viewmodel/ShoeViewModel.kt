package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoeLists = MutableLiveData<MutableList<Shoe>>()
    val shoeLists: LiveData<MutableList<Shoe>>
        get() = _shoeLists

    fun addShoe(shoe: Shoe) {

        _shoeLists.value?.add(shoe)
    }
}