package com.example.testcompose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testcompose.ui.theme.TestComposeTheme

@Composable
fun Content() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        MainContainer()
    }
}

@Composable
fun MainContainer() {
    Greeting(name = "Test")
}

@Composable
fun Greeting(name: String) {
    Surface(color = Color.Green) {
        Text(text = "Hi, my name is $name!", modifier = Modifier.padding(all = 24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestComposeTheme {
        Content()
    }
}