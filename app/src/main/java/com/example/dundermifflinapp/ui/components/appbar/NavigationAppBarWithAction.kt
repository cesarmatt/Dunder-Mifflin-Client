package com.example.dundermifflinapp.ui.components.appbar

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NavigationAppBarWithAction(
    title: String,
    onActionClick: () -> Unit,
    onNavigationClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.background(MaterialTheme.colors.background),
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = { onActionClick() }) {
                Icon(Icons.Filled.Add, contentDescription = "Create")
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = {
            MakeBackButtonAction {
                onNavigationClick()
            }
        }
    )
}

@Composable
private fun MakeBackButtonAction(backButtonAction: () -> Unit) {
    return IconButton(onClick = { backButtonAction() }) {
        Icon(Icons.Filled.ArrowBack, contentDescription = "Back button")
    }
}

@Preview
@Composable
fun NavigationAppBarWithActionPreview() {
    NavigationAppBarWithAction(
        title = "Sample Navbar",
        onActionClick = { println("Action Clicked") },
        onNavigationClick = { println("Navigation Clicked") }
    )
}