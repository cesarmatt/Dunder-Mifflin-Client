package com.example.dundermifflinapp.ui.create.order.components.itemselector

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dundermifflinapp.data.models.OrderItem

@Composable
fun ItemSelectorTileComponent(
    item: ItemCheckboxState,
    setSelectedValue: (OrderItem?, Boolean) -> Unit,
) {
    val checkboxState = remember {
        mutableStateOf(
            ItemCheckboxState(item.isChecked, item.content)
        )
    }

    val checkedState = remember { mutableStateOf(false) }

    ItemSelectorTile(
        itemState = checkboxState,
        setSelectedValue = setSelectedValue,
        checkedState = checkedState
    )
}

@Composable
fun ItemSelectorTile(
    itemState: MutableState<ItemCheckboxState>,
    setSelectedValue: (OrderItem?, Boolean) -> Unit,
    checkedState: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            modifier = Modifier.padding(horizontal = 8.dp),
            checked = checkedState.value,
            colors = CheckboxDefaults.colors(Color(0xFF2A3C5F)),
            onCheckedChange = {
                checkedState.value = it
                setSelectedValue(itemState.value.content, it)
            }
        )

        Text(text = itemState.value.content?.name ?: "")

        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = "Value: ${itemState.value.content?.value.toString()}"
        )
    }
}

@Preview
@Composable
fun ItemSelectorTilePreview() {
    val item = remember {
        mutableStateOf(ItemCheckboxState(false, OrderItem("1", "Item 1", 10f)))
    }
    val setSelectedValue: (OrderItem?, Boolean) -> Unit = { _,_ ->
        println("checked/unchecked")
    }
    val checkedState = remember { mutableStateOf(false) }
    ItemSelectorTile(
        itemState = item,
        setSelectedValue = setSelectedValue,
        checkedState = checkedState
    )
}