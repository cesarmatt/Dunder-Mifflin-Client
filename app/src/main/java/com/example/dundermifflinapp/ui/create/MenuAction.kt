package com.example.dundermifflinapp.ui.create

class MenuAction(
    val label: String,
    val action: (() -> Unit?)? = null,
) {
    companion object {
        const val ACTION_CREATE_ORDER = "Create new order"
        const val ACTION_CREATE_CUSTOMER = "Create new customer"
    }
}