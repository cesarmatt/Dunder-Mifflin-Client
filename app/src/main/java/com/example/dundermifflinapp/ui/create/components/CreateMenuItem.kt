package com.example.dundermifflinapp.ui.create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dundermifflinapp.ui.create.Action
import com.example.dundermifflinapp.ui.create.MenuAction

@ExperimentalMaterialApi
@Composable
fun CreateMenuItem(menuAction: MenuAction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { menuAction.action?.invoke() }
            .background(MaterialTheme.colors.surface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp).weight(1f),
            text = menuAction.actionLabel.text
        )
        Icon(
            modifier = Modifier.padding(horizontal = 8.dp),
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = "Navigation Clicked"
        )
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun CreateMenuItemPreview() {
    val createMenuAction = MenuAction(Action.ACTION_CREATE_CUSTOMER) {}
    CreateMenuItem(menuAction = createMenuAction)
}