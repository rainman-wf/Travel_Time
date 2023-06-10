package ru.netology.ui

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.snack(msg: String) {
    Snackbar.make(this.requireView(), msg, Snackbar.LENGTH_SHORT).show()
}