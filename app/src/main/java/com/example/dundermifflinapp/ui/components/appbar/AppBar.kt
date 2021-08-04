package com.example.dundermifflinapp.ui.components.appbar

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppBar(
    title: String,
    onActionClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.background(MaterialTheme.colors.background),
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = { onActionClick() }) {
                Icon(Icons.Filled.Add, contentDescription = "Create")
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Preview
@Composable
fun AppBarPreview() {
    AppBar(
        title = "Sample app bar",
        onActionClick = { println("Action Clicked") }
    )
}