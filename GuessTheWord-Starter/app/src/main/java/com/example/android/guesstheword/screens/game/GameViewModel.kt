package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private val TAG = GameViewModel::class.java.simpleName

    init {
        Log.i(TAG, "GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "GameViewModel destroyed!")
    }
}