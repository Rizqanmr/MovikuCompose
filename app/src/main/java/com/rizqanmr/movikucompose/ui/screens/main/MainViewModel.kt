package com.rizqanmr.movikucompose.ui.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _isDarkTheme = MutableLiveData<Boolean>().apply { value = false }
    val isDarkTheme: LiveData<Boolean> = _isDarkTheme

    fun performAction(action: Action) {
        when (action) {
            is Action.SwitchTheme -> _isDarkTheme.value = !(_isDarkTheme.value ?: false)
            else -> {}
        }
    }

    sealed class Action {
        object SwitchTheme : Action()
    }
}