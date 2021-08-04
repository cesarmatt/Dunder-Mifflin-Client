package com.example.dundermifflinapp.ui.components.appbar

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NavigationAppBar(
    title: String,
    onNavigationClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.background(MaterialTheme.colors.background),
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = {
            MakeBackButtonAction(icon = Icons.Filled.ArrowBack) {
                onNavigationClick()
            }
        }
    )
}

@Preview
@Composable
fun NavigationAppBarPreview() {
    NavigationAppBar(
        title = "Sample Navbar",
        onNavigationClick = { println("Navigation Clicked") }
    )
}