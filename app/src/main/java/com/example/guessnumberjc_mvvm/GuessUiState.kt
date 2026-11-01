package com.example.guessnumberjc_mvvm

data class GuessUiState(
    val input: String = "",
    val message: String = "",
    val gameState: GameState = GameState.ACTIVE,
    val attemptsLeft: Int = 3,
    val maxNumber: Int = 15,
    val maxAttempts: Int = 3,
    val isSettingsDialogVisible: Boolean = false,
    val settings: SettingsUiState = SettingsUiState()
) {
    val canCheck: Boolean
        get() = input.isNotBlank() && gameState == GameState.ACTIVE
}