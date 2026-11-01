package com.example.guessnumberjc_mvvm


import android.graphics.Color.LTGRAY
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guessnumberjc_mvvm.components.AppButton
import com.example.guessnumberjc_mvvm.components.AppImage
import com.example.guessnumberjc_mvvm.components.AppTextField
import com.example.guessnumberjc_mvvm.components.Header
import com.example.guessnumberjc_mvvm.components.SettingsDialog
import com.example.guessnumberjc_mvvm.ui.theme.GrayBlue
import com.example.guessnumberjc_mvvm.ui.theme.Pink40
import com.example.guessnumberjc_mvvm.ui.theme.PurpleGrey80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessNumberScreen(
    viewModel: GuessNumberViewModel = viewModel()
 )
{   val uiState by viewModel.iuState.collectAsStateWithLifecycle()
    Scaffold(
       // containerColor = GrayBlue,
        topBar = {
            TopAppBar(
                title = {
                    Text("Угадай число")
                },

                actions = {
                    IconButton(onClick = viewModel::openSettings) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Настройки"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GrayBlue,
                    scrolledContainerColor = Color(0xFFE87A99),
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                )
            )
        }

    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            when(uiState.gameState){

                GameState.ACTIVE -> {
                    Header(uiState.maxNumber)
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

                    Text("Осталось попыток: ${uiState.attemptsLeft}")
                }

                GameState.WIN -> {
                    Text(
                        text = uiState.message,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    AppImage(
                        imageResource = R.drawable.win,
                        description = "Победа"
                    )

                    AppButton(onPress = viewModel::firstStartGame,
                        btnText ="Играть снова",
                        isWin = true)

                    Spacer(modifier = Modifier.height(12.dp))
                }

                GameState.LOSE -> {
                    Text(
                        text = uiState.message,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    AppImage(
                        imageResource = R.drawable.lose,
                        description = "Проигрыш"
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    AppButton(onPress = viewModel::firstStartGame,
                        btnText ="Попробовать снова",
                        isWin = true
                    )
                }
            }
            if (uiState.isSettingsDialogVisible) {
                SettingsDialog(
                    settings = uiState.settings,
                    onMaxNumberChanged = viewModel::onSettingsMaxNumberChanged,
                    onMaxAttemptsChanged = viewModel::onSettingsMaxAttemptsChanged,
                    onSaveClick = viewModel::saveSettings,
                    onCancelClick = viewModel::closeSettings,
                    onClearClick = viewModel::clearInput
                )
            }

        }

    }




}