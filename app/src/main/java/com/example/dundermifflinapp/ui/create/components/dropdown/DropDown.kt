package com.example.dundermifflinapp.ui.create.components.dropdown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DropDown(
    isExpanded: Boolean = false,
    list: List<DropDownOption>,
    setVisibility: (Boolean) -> Unit,
    setSelectedValue: (DropDownOption) -> Unit
) {
    DropdownMenu(
        modifier = Modifier.fillMaxWidth(),
        expanded = isExpanded,
        onDismissRequest = { setVisibility(false) },
    ) {
        list.forEach {
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    setVisibility(false)
                    setSelectedValue(it)
                }
            ) {
                Text(it.label, textAlign = TextAlign.Center)
            }
        }
    }
}