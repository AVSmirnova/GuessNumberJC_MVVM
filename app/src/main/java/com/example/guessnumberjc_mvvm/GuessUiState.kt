package com.example.guessnumberjc_mvvm

data class GuessUiState(
    val input: String = "",
    val message: String = "",
    val gameState: GameState = GameState.ACTIVE,
    val attempts: Int = 0
) {
    val canCheck: Boolean
        get() = input.isNotBlank() && gameState == GameState.ACTIVE
}