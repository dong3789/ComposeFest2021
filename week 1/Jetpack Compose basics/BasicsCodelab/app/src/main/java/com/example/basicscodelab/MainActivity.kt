package com.example.basicscodelab

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                MyApp()
                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
            }
        }
    }
}

//@Preview(showBackground = true, withDp = 300)
@Composable
fun Greeting(name: String) {
    val expanded = remember{ mutableStateOf(false) }
    val extraPadding = if( expanded.value ) 50.dp else 0.dp
    androidx.compose.material.Surface(color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 5.dp)
        ) {
        Row(modifier = Modifier.padding(24.dp)){
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)
                ) {
                Text(text = "Hello")
                Text(text = "$name!!!")
            }
            OutlinedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text( if(expanded.value) "Show less" else "Show MORE")
            }
        }

    }
}

@Preview(showBackground = true, name = "Text preview")
@Composable
fun DefaultPreview() {
    BasicsCodelabTheme {
        Greetings()
    }
}

//@Preview(showBackground = true, widthDp = 300)
@Composable
private fun MyApp() {
//    androidx.compose.material.Surface(color = MaterialTheme.colors.background) {
//        Greeting("Let's Study Compose!~")
//    }
    var shouldShowOnboarding by remember { mutableStateOf(true) }
    if( shouldShowOnboarding ){
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    }else{
        Greetings()
    }

}

@Composable
private fun Greetings(names: List<String> = List(100) { "$it" } ) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)){
        items(items = names){
            name ->
            Greeting(name = name)
        }
    }
//    Column(modifier = Modifier.padding(vertical = 4.dp)) {
//        for(name in names){
//            Greeting(name = name)
//        }
//    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Android and Basic Compose Codelab")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continueee")
            }

        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 300)
@Composable
fun OnboardingPreview() {
    BasicsCodelabTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}