package com.example.launchedeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import com.example.launchedeffect.ui.theme.ComposeFieldTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeFieldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scrollState = rememberScrollState()
                    val boolstate = remember { mutableStateOf(false) }
                    val countstate = remember { mutableStateOf(0) }
                    ComposeFieldTheme {
                        Column {
                            Button(
                                modifier = Modifier.wrapContentSize(),
                                onClick = { boolstate.value = !boolstate.value },
                                shape = RectangleShape
                            ) {
                                Text(text = "에러 토글 (상태 : ${boolstate.value})")
                            }

                            LE(countstate, boolstate)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun LE(c: MutableState<Int>, hasError: MutableState<Boolean>,scaffoldState: ScaffoldState = rememberScaffoldState()) {
    if(hasError.value) {
        LaunchedEffect(hasError.value) {

            scaffoldState.snackbarHostState.showSnackbar(
                message = "에러 스낵바"
            )
            c.value = c.value.toInt() + 1

        }
    }


    
    Scaffold(scaffoldState = scaffoldState) {
        Text(text = "에러 횟수 : ${c.value.toString()}")

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}