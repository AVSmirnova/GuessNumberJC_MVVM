package com.example.guessnumberjc_mvvm

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class GuessNumberViewModel : ViewModel() {

    private val _uiState= MutableStateFlow(GuessUiState())
    val iuState: StateFlow<GuessUiState> = _uiState.asStateFlow()

    private var hiddenNumber = -1


    init {
        startGame()
    }
    fun startGame(){
        hiddenNumber = Random.nextInt(0,_uiState.value.maxNumber+1)
        Log.i("happy",hiddenNumber.toString())
        _uiState.update {
            GuessUiState(
                gameState = GameState.ACTIVE,
                attemptsLeft = it.maxAttempts
            )
        }
    }

    fun onInputChanged(value: String){
        val filtered = value.filter { it.isDigit() }
        _uiState.update {
            it.copy(input=filtered)
        }
    }
    fun clearInput() {
        _uiState.update {
            it.copy(input = "")
        }
    }

    fun checkAnswer() {
        val currentState = _uiState.value

        if(currentState.gameState!= GameState.ACTIVE){
            return
        }
         if(currentState.input.isBlank()){
             _uiState.update {
                 it.copy(message = "Введите число!")
             }
             return

         }
        val userNumber = currentState.input.toIntOrNull()

       if(userNumber==null){
           _uiState.update {
               it.copy(message = "Введите число!")
           }
           return
       }
        if(userNumber==hiddenNumber){
            _uiState.update{
                it.copy(gameState = GameState.WIN,
                        message ="Верно, вы выиграли!")

            }
        }
        else{
            val remainingAttempts = currentState.attemptsLeft - 1

            if (remainingAttempts <= 0) {
                _uiState.update {
                    it.copy(
                        message = "Попытки закончились. Вы проиграли.\n Загаданное число $hiddenNumber",
                        gameState = GameState.LOSE,
                        attemptsLeft = 0
                    )
                }
            } else {
                val hint = if (hiddenNumber > userNumber) {
                    "Нужно число больше"
                } else {
                    "Нужно число меньше"
                }

                _uiState.update {
                    it.copy(
                        message = hint,
                        attemptsLeft = remainingAttempts
                    )
                }
            }
        }

    }


}