package com.example.dundermifflinapp.ui.create.order.components.itemselector

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dundermifflinapp.data.models.OrderItem

@Composable
fun ItemSelectorComponent(
    items: List<ItemCheckboxState>,
    onItemSelected: (OrderItem) -> Unit,
    onItemDeselected: (OrderItem) -> Unit
) {
    val setSelectedValue: (OrderItem?, Boolean) -> Unit = { item, isChecked ->
        if (item != null) {
            if (isChecked) {
                onItemSelected(item)
            } else {
                onItemDeselected(item)
            }
        }
    }

    ItemSelector(
        items = items,
        setSelectedValue = setSelectedValue
    )
}

@Composable
private fun ItemSelector(
    items: List<ItemCheckboxState>,
    setSelectedValue: (OrderItem?, Boolean) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            "Select the products",
            modifier = Modifier.padding(vertical = 4.dp)
        )
        items.forEach { item ->
            ItemSelectorTileComponent(
                item = item,
                setSelectedValue = setSelectedValue
            )
        }
    }
}

@Preview
@Composable
fun ItemSelectorPreview() {
    val setSelectedValue: (OrderItem?, Boolean) -> Unit = { _,_ ->
        println("checked/unchecked")
    }

    val item = OrderItem("1", "Item 1", 10f)
    val items = listOf(ItemCheckboxState(false, item))

    ItemSelector(items = items, setSelectedValue = setSelectedValue)
}