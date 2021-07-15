package com.example.dundermifflinapp.ext

import androidx.fragment.app.Fragment

fun Fragment.popBackStack() {
    activity?.supportFragmentManager?.popBackStack()
}