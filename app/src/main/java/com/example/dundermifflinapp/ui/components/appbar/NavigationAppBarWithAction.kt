package com.example.dundermifflinapp.ui.components.appbar

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NavigationAppBarWithAction(
    title: String,
    icon: ImageVector,
    onActionClick: () -> Unit,
    onNavigationClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.background(MaterialTheme.colors.background),
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = { onActionClick() }) {
                Icon(icon, contentDescription = "Create")
            }
        },
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
fun NavigationAppBarWithActionPreview() {
    NavigationAppBarWithAction(
        title = "Sample Navbar",
        icon = Icons.Filled.Create,
        onActionClick = { println("Action Clicked") },
        onNavigationClick = { println("Navigation Clicked") }
    )
}