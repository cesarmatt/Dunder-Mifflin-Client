package com.example.dundermifflinapp.ui.create.order.components.ordervaluebanner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OrderValueBannerComponent(orderValue: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFF2A3C5F))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Total US$$orderValue",
                color = Color.White,
                fontSize = 30.sp
            )
        }
    }
}

@Preview
@Composable
fun OrderValueBannerComponent() {
    OrderValueBannerComponent(100f)
}