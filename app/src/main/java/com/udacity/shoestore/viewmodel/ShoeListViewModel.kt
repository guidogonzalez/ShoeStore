package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShowListViewModel : ViewModel() {

    private val _shoeLists = MutableLiveData<List<Shoe>>()
    val shoeLists: LiveData<List<Shoe>>
        get() = _shoeLists
}