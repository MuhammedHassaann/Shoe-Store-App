package com.muhammedhassaan.shoestore.ui.shoedetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhammedhassaan.shoestore.model.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoesList = MutableLiveData<MutableList<Shoe>>()
    val shoesList: LiveData<MutableList<Shoe>> get() = _shoesList

    val shoeName = MutableLiveData<String>()

    val shoeCompany = MutableLiveData<String>()

    val shoeSize = MutableLiveData<Int>()

    val shoeDescription = MutableLiveData<String>()

    init {
        shoeSize.value = 42
    }

    private fun getShoeData(): Shoe {
        return Shoe(
            name = shoeName.value ?: "",
            size = shoeSize.value ?: 0,
            company = shoeCompany.value ?: "",
            description = shoeDescription.value ?: ""
        )
    }

    fun addShoe() {
        if (_shoesList.value == null) {
            _shoesList.value = mutableListOf(getShoeData())
        } else {
            _shoesList.value?.add(getShoeData())
        }
    }

}