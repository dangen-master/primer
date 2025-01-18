package com.example.app1

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MathViewModel : ViewModel() {
    var number1 = mutableStateOf(0)
        private set

    var number2 = mutableStateOf(0)
        private set

    var operator = mutableStateOf("+")
        private set

    var userAnswer = mutableStateOf("")
    var correctAnswer = mutableStateOf(0)

    var backgroundColor = mutableStateOf(androidx.compose.ui.graphics.Color.White)
    var totalQuestions = mutableStateOf(0)
    var correctAnswers = mutableStateOf(0)

    var isStartButtonActive = mutableStateOf(true)

    // Генерация нового примера
    fun generateExample() {
        number1.value = Random.nextInt(10, 100)
        number2.value = Random.nextInt(10, 100)
        operator.value = listOf("+", "-", "*", "/").random()

        // Убедимся, что деление - нацело
        if (operator.value == "/") {
            while (number1.value % number2.value != 0) {
                number1.value = Random.nextInt(10, 100)
                number2.value = Random.nextInt(10, 100)
            }
        }

        correctAnswer.value = when (operator.value) {
            "+" -> number1.value + number2.value
            "-" -> number1.value - number2.value
            "*" -> number1.value * number2.value
            "/" -> number1.value / number2.value
            else -> 0
        }

        userAnswer.value = ""
        backgroundColor.value = androidx.compose.ui.graphics.Color.White
        isStartButtonActive.value = false
    }

    // Проверка ответа
    fun checkAnswer() {
        totalQuestions.value++
        if (userAnswer.value.toIntOrNull() == correctAnswer.value) {
            correctAnswers.value++
            backgroundColor.value = androidx.compose.ui.graphics.Color.Green
        } else {
            backgroundColor.value = androidx.compose.ui.graphics.Color.Red
        }
        isStartButtonActive.value = true
    }
}
