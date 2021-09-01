package com.udacity.shoestore.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.BR
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    var shoe: Shoe? = null

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        shoe = Shoe("", 0.0, "", "", ArrayList())
        _shoeList.value = ArrayList()
    }

    fun addShoe() {
        shoe?.size = shoeSizeObservable.shoeSize.toDouble()
        shoe?.let { shoe ->
            _shoeList.value?.add(shoe)
            _shoeList.value = _shoeList.value
        }
    }

    fun clearShoes() {
        _shoeList.value?.clear()
    }

    val shoeSizeObservable = ShoeSizeObservable()

    inner class ShoeSizeObservable : BaseObservable() {

        var shoeSize: String = "0.0"

        @Bindable
        fun getNewSize(): String {
            return shoeSize
        }

        fun setNewSize(size: String) {
            if (shoeSize != size) {
                shoeSize = size

                notifyPropertyChanged(BR.newSize)
            }
        }
    }
}