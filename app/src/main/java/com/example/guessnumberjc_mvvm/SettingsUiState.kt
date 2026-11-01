package com.example.guessnumberjc_mvvm

data class SettingsUiState(
    val maxNumberText: String = "10",
    val maxAttemptsText: String = "3",
    val error: String = ""
)