package com.example.dundermifflinapp.ui.create.order.components.itemsdropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dundermifflinapp.data.models.OrderItem
import com.example.dundermifflinapp.ui.create.components.dropdown.DropDown
import com.example.dundermifflinapp.ui.create.components.dropdown.DropDownOption

@Composable
fun ItemDropDownComponent(
    dropDownOptions: List<DropDownOption>,
    onItemSelected: (OrderItem) -> Unit
) {
    val selectedValue = remember { mutableStateOf("") }
    val isExpanded = remember { mutableStateOf(false) }
    val setVisibility: (Boolean) -> Unit = {
        isExpanded.value = it
    }
    val setSelectedValue: (DropDownOption) -> Unit = {
        selectedValue.value = it.label
        onItemSelected(it.content as OrderItem)
    }

    ItemDropDown(
        selectedValue = selectedValue,
        options = dropDownOptions,
        isExpanded = isExpanded,
        setVisibility = setVisibility,
        setSelectedValue = setSelectedValue
    )
}

@Composable
private fun ItemDropDown(
    selectedValue: MutableState<String>,
    options: List<DropDownOption>,
    isExpanded: MutableState<Boolean>,
    setVisibility: (Boolean) -> Unit,
    setSelectedValue: (DropDownOption) -> Unit
) {
    Box {
        Column {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = selectedValue.value,
                onValueChange = { selectedValue.value = it },
                label = { Text("Item") },
            )
            DropDown(
                isExpanded = isExpanded.value,
                list = options,
                setVisibility = setVisibility,
                setSelectedValue = setSelectedValue
            )
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable { setVisibility(true) }
        )
    }
}