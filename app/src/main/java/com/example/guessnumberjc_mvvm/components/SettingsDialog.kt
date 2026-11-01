package com.example.guessnumberjc_mvvm.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guessnumberjc_mvvm.SettingsUiState

@Composable
fun SettingsDialog(
    settings: SettingsUiState,
    onMaxNumberChanged: (String) -> Unit,
    onMaxAttemptsChanged: (String) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    onClearClick: () ->Unit
) {
    AlertDialog(
        onDismissRequest = onCancelClick,
        title = {
            Text("Настройки игры")
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AppTextField(
                    message = settings.maxNumberText,
                    onBtnClick = onMaxNumberChanged,
                    placeholderText = "Максимальное число",
                    onClearClick = { onClearClick()}
                     )
                AppTextField(
                    message = settings.maxAttemptsText,
                    onBtnClick = onMaxAttemptsChanged,
                    placeholderText = "Максимум попыток",
                    onClearClick = { onClearClick()}
                )


                if (settings.error.isNotEmpty()) {
                    Text(
                        text = settings.error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = onSaveClick) {
                Text("Сохранить")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelClick) {
                Text("Отмена")
            }
        }
    )
}