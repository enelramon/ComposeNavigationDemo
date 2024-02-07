package com.sagrd.navigationdemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var id by mutableIntStateOf(0)

    init {
        savedStateHandle.get<Int>("id")?.let {
            id = it
        }
    }
}