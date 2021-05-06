package com.example.mycinema.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ThirdViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ThirdViewModel::class.java)) {
            return ThirdViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}