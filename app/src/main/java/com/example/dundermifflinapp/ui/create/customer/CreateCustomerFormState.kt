package com.example.dundermifflinapp.ui.create.customer

import androidx.lifecycle.MutableLiveData

class CreateCustomerFormState(
    val name: MutableLiveData<String> = MutableLiveData(""),
    val address: MutableLiveData<String> = MutableLiveData(""),
    val email: MutableLiveData<String> = MutableLiveData("")
)