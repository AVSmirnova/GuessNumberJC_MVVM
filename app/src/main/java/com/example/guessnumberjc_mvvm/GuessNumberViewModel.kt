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
        firstStartGame()
    }
    fun firstStartGame(){
       startGame(
           maxNumber = _uiState.value.maxNumber,
           maxAttempts = _uiState.value.maxAttempts
       )
    }
    private fun startGame(
        maxNumber: Int,
        maxAttempts: Int
    ) {
        hiddenNumber = Random.nextInt(0, maxNumber + 1)
        Log.i("happy",hiddenNumber.toString())

        _uiState.update {
            it.copy(
                maxNumber = maxNumber,
                maxAttempts = maxAttempts,
                input = "",
                message = "",
                gameState = GameState.ACTIVE,
                attemptsLeft = maxAttempts,
                isSettingsDialogVisible = false
            )
        }
    }

    fun onInputChanged(value: String){
        val filtered = value.filter { it.isDigit() }
        _uiState.update {
            it.copy(input=filtered)
        }
    }
    //неверно, для каждого текстфилда надо делать свою очистку
    fun clearInput() {
        _uiState.update {
            it.copy(input = "",
                settings = it.settings.copy(
                    maxNumberText ="",
                    maxAttemptsText = "")
            )
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
    fun openSettings() {
        _uiState.update {
            it.copy(
                isSettingsDialogVisible = true,
                settings = SettingsUiState(
                    maxNumberText = it.maxNumber.toString(),
                    maxAttemptsText = it.maxAttempts.toString(),
                    error = ""
                )
            )
        }
    }

    fun closeSettings() {
        _uiState.update {
            it.copy(isSettingsDialogVisible = false)
        }
    }
    fun onSettingsMaxNumberChanged(value: String) {
        val filtered = value.filter { it.isDigit() }

        _uiState.update {
            it.copy(
                settings = it.settings.copy(
                    maxNumberText = filtered,
                    error = ""
                )
            )
        }
    }

    fun onSettingsMaxAttemptsChanged(value: String) {
        val filtered = value.filter { it.isDigit() }

        _uiState.update {
            it.copy(
                settings = it.settings.copy(
                    maxAttemptsText = filtered,
                    error = ""
                )
            )
        }
    }
    fun saveSettings() {
        val currentState = _uiState.value

        val maxNumber = currentState.settings.maxNumberText.toIntOrNull()
        val maxAttempts = currentState.settings.maxAttemptsText.toIntOrNull()

        if (maxNumber == null || maxNumber < 0) {
            _uiState.update {
                it.copy(
                    settings = it.settings.copy(
                        error = "Максимальное число должно быть не меньше 0"
                    )
                )
            }
            return
        }

        if (maxAttempts == null || maxAttempts < 1) {
            _uiState.update {
                it.copy(
                    settings = it.settings.copy(
                        error = "Количество попыток должно быть не меньше 1"
                    )
                )
            }
            return
        }
        startGame(maxNumber,maxAttempts)

    }



    }