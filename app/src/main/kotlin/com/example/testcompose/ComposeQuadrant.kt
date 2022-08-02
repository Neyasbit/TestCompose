package com.example.testcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testcompose.ui.theme.TestComposeTheme

@Composable
fun Content() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        MainContainer()
    }
}

@Composable
fun MainContainer() {

    Column {
        Row(modifier = Modifier.weight(1f)) {
            ContentColumn(
                rowScope = this,
                backgroundColor = Color.Green,
                tittleName = "Text composable",
                description = "Displays text and follows Material Design guidelines."
            )
            ContentColumn(
                rowScope = this,
                backgroundColor = Color.Yellow,
                tittleName = "Image composable",
                description = "Creates a composable that lays out and draws a given Painter class object."
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            ContentColumn(
                rowScope = this,
                backgroundColor = Color.Cyan,
                tittleName = "Row composable",
                description = "A layout composable that places its children in a horizontal sequence."
            )
            ContentColumn(
                rowScope = this,
                backgroundColor = Color.LightGray,
                tittleName = "Column composable",
                description = "A layout composable that places its children in a vertical sequence."
            )
        }

    }
}

@Composable
fun ContentColumn(
    rowScope: RowScope,
    backgroundColor: Color,
    tittleName: String,
    description: String
) {

    rowScope.apply {
        Column(
            modifier = Modifier
                .weight(1f)
                .background(color = backgroundColor)
                .fillMaxHeight()
                .padding(all = 16.dp),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = tittleName,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(text = description, textAlign = TextAlign.Justify)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeQuadrantPreview() {
    TestComposeTheme {
        Content()
    }
}
