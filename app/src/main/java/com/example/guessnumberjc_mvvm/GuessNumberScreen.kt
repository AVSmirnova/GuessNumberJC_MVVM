package com.example.guessnumberjc_mvvm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guessnumberjc_mvvm.components.AppButton
import com.example.guessnumberjc_mvvm.components.AppImage
import com.example.guessnumberjc_mvvm.components.AppTextField
import com.example.guessnumberjc_mvvm.components.Header

@Composable
fun GuessNumberScreen(
    viewModel: GuessNumberViewModel = viewModel()
 )
{   val uiState by viewModel.iuState.collectAsStateWithLifecycle()
    Scaffold{ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            when(uiState.gameState){

                GameState.ACTIVE -> {
                    Header(10)
                        AppTextField(
                            message = uiState.input,
                            onBtnClick = viewModel::onInputChanged,
                            onClearClick = viewModel::clearInput
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        AppButton(
                            onPress = viewModel::checkAnswer,
                            isWin = uiState.canCheck,
                            btnText = "Проверить"
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(text = uiState.message)

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Осталось попыток: ${uiState.attempts}")
                }

                GameState.WIN -> {
                    Text(
                    text = "Победа!",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))
                    AppImage(
                        imageResource = R.drawable.win,
                        description = "Победа"
                    )

                AppButton(onPress = viewModel::startGame,
                    btnText ="Играть снова",
                    isWin = true)

                    Spacer(modifier = Modifier.height(12.dp))
            }

                GameState.LOSE -> {
                Text(
                    text = "Проигрыш",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))
                    AppImage(
                        imageResource = R.drawable.lose,
                        description = "Проигрыш"
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                AppButton(onPress = viewModel::startGame,
                    btnText ="Попробовать снова",
                    isWin = true)
            }
            }

        }

    }




}