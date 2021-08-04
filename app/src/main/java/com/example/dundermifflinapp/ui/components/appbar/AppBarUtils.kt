package com.example.dundermifflinapp.ui.components.appbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MakeBackButtonAction(icon: ImageVector, backButtonAction: () -> Unit,) {
    IconButton(onClick = { backButtonAction() }) {
        Icon(icon, contentDescription = "Navigation Clicked")
    }
}