package com.example.dundermifflinapp.ui.feed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dundermifflinapp.data.models.OrderView

@Composable
fun OrderItem(order: OrderView) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colors.background)
            .padding(vertical = 8.dp)
            .clickable {  },
        elevation = 5.dp,
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            Text(
                text = "${order.customerName.toString()}'s order",
                style = MaterialTheme.typography.body1
            )

            Column {

                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = "${order.items?.size} items",
                    style = MaterialTheme.typography.caption
                )

                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = "${order.purchaseDate}",
                    style = MaterialTheme.typography.caption
                )

                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = "${order.deliveryDate}",
                    style = MaterialTheme.typography.caption
                )

                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = "Total ${order.value}",
                    style = MaterialTheme.typography.body2
                )

            }

        }
    }
}

@Preview
@Composable
fun OrderItemPreview() {
    val order = OrderView(
        "",
        "César",
        "Dwight",
        listOf(),
        10f,
        "11/09/2021",
        "03/09/2021"
    )
    OrderItem(order = order)
}