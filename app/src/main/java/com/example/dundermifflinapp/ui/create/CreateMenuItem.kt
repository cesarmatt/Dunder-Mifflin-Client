package com.example.dundermifflinapp.ui.create

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun CreateMenuItem(menuAction: MenuAction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = menuAction.label
        )
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun CreateMenuItemPreview() {
    val createMenuAction = MenuAction("Action") {}
    CreateMenuItem(menuAction = createMenuAction)
}