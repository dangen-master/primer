package com.example.app1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app1.ui.theme.App1Theme
import kotlin.random.Random
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App1Theme {
                MathApp()
            }
        }
    }
}



@Composable
fun MathApp(mathViewModel: MathViewModel = viewModel()) {
    val number1 = mathViewModel.number1.value
    val number2 = mathViewModel.number2.value
    val operator = mathViewModel.operator.value
    val userAnswer = mathViewModel.userAnswer.value
    val backgroundColor = mathViewModel.backgroundColor.value
    val totalQuestions = mathViewModel.totalQuestions.value
    val correctAnswers = mathViewModel.correctAnswers.value
    val isStartButtonActive = mathViewModel.isStartButtonActive.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Статистика
        Text(
            text = "Итого решено: $totalQuestions\nПравильно: $correctAnswers\nПроцент: ${
                if (totalQuestions > 0) String.format("%.2f", correctAnswers * 100.0 / totalQuestions) else "0.00"
            }%",
            fontSize = 18.sp
        )

        // Пример
        Text(
            text = "$number1 $operator $number2 = ?",
            fontSize = 32.sp,
            modifier = Modifier.padding(16.dp)
        )

        // Поле ввода ответа
        OutlinedTextField(
            value = userAnswer,
            onValueChange = { mathViewModel.userAnswer.value = it },
            label = { Text("Введите ответ") },
            modifier = Modifier.fillMaxWidth()
        )

        // Кнопки
        Column {
            Button(
                onClick = { mathViewModel.generateExample() },
                enabled = isStartButtonActive,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("СТАРТ")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { mathViewModel.checkAnswer() },
                enabled = userAnswer.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ПРОВЕРКА")
            }
        }
    }
}


