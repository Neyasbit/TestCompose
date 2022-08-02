package com.example.testcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun Content() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.secondary) {
        MainContainer()
    }
}

@Composable
private fun MainContainer() {
    AboutContainer()
    InfoContainer()
}

@Composable
private fun AboutContainer() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_android),
            contentDescription = stringResource(id = R.string.avatar_desc),
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = stringResource(R.string.full_name),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
        Text(text = stringResource(R.string.description), fontSize = 16.sp)
    }
}

@Composable
private fun InfoContainer() {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {

        InfoCard(stringResource(R.string.number), R.drawable.ic_phone)
        InfoCard(textContent = stringResource(R.string.email), icon = R.drawable.ic_email)
        InfoCard(textContent = stringResource(R.string.social), icon = R.drawable.ic_people)
    }
}

@Composable
private fun InfoCard(textContent: String, icon: Int) {
    Divider(
        modifier = Modifier
            .height(2.dp)
            .fillMaxWidth(),
        color = Color.Blue
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(32.dp)
        )
        Text(
            text = textContent,
            fontSize = 16.sp,
            modifier = Modifier.padding(end = 8.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BusinessCardPreview() {
    Content()
}
