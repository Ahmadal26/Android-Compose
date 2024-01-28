package com.example.counterapp

//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.counterapp.ui.theme.AppTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    Modifier
                        .fillMaxSize(), color = MaterialTheme.colorScheme.background
                )
                {

                    GameScreen()

                    Column(
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                    }

                }
            }
        }
    }


    @Composable

    fun Greeting(name: String, modifier: Modifier = Modifier) {

        Text(
            text = "Android is an Operating System ",
            modifier = modifier,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold

        )
    }


    @SuppressLint("SuspiciousIndentation")
    @Composable
    fun GameScreen(modifier: Modifier = Modifier) {
        var score by remember {
            mutableStateOf(0)
        }
        var showWrongAnswer by remember {
            mutableStateOf(false)
        }
        var showCorrectAnswer by remember {
            mutableStateOf(false)
        }
        var showNextQuestion by remember {
            mutableStateOf(false)
        }
        var showNextAnswersRow by remember {
            mutableStateOf(true)
        }
        val questions = listOf("Boiling water is 100 degrees Celsius",
            "Butterflies taste things with their wings",
            "Android is an operating System",
            "Colorblind people can see color")
        val answers = listOf(true, false, true,false)
        var currentQuestionIndex by remember {
            mutableStateOf(0)
        }
        Column(
            modifier = modifier.padding(14.dp),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = questions[currentQuestionIndex], fontSize = 20.sp)
            if (showWrongAnswer)
                AnswerResult(text = "Wrong Answer", image = R.drawable.w)
            if (showCorrectAnswer)
                AnswerResult(text = "Correct Answer", image = R.drawable.c)

            Text(text = "Score: $score", fontSize = 20.sp)
            if (showNextQuestion)
                Button(modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (currentQuestionIndex == questions.size - 1) {
                            currentQuestionIndex = 0
                            score = 0
                        } else
                            currentQuestionIndex++
                        showNextQuestion = false
                        showNextAnswersRow = true
                        showCorrectAnswer = false
                        showWrongAnswer = false

                    }) {
                    Text(text = "Next Question")
                }
            if (showNextAnswersRow)
                Row(modifier = Modifier.fillMaxWidth())
                {
                    Button(modifier = Modifier.weight(1f),
                        onClick = {
                            val isAnswerCorrect = true == answers[currentQuestionIndex]
                            if (isAnswerCorrect) {
                                score++
                                showCorrectAnswer = true
                                showNextQuestion = true
                                showNextAnswersRow = false
                                showWrongAnswer = false

                            } else {

                                showNextQuestion = false
                                showWrongAnswer = true
                            }


                            {
//                            showWrongAnswer= true
                            }
                        }) {
                        Text(text = ("True"))
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(modifier = Modifier.weight(1f),
                        onClick = {
                            val isAnswerCorrect = false == answers[currentQuestionIndex]
                            if (isAnswerCorrect) {
                                score++
                                showCorrectAnswer = true
                                showNextQuestion = true
                                showNextAnswersRow = false
                                showWrongAnswer = false


                            } else {


                                showWrongAnswer = true

                                showNextAnswersRow = true
                            }

                        }) {
                        Text(text = ("False"))
                    }

                }


        }
    }

    @Composable

    fun AnswerResult(text: String, image: Int, modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .size(200.dp)

        ) {
            Image(modifier = Modifier.fillMaxSize(), painter = painterResource(id = image), contentDescription ="" )
            Text(
                text = text,
                modifier = modifier.align(Alignment.Center)
            )
        }

    }

    @Composable
    fun Spacer() {

        Row {
            Button(onClick = { }, modifier = Modifier.width(120.dp)) {
                Text(text = "True")
            }
            Spacer(modifier = Modifier.size(50.dp))
            Button(onClick = { }, modifier = Modifier.width(120.dp)) {
                Text(text = "False")
            }
        }


    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AppTheme {
            Greeting("Android")
        }
    }
}