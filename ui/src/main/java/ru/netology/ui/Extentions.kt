package ru.netology.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable

fun Fragment.snack(msg: String) {
    Snackbar.make(this.requireView(), msg, Snackbar.LENGTH_SHORT).show()
}

@Suppress("DEPRECATION")
inline fun <reified T : Serializable> Bundle.getObject(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, T::class.java)
    } else {
        getSerializable(key) as? T
    }
}


fun Int.toAmount(currency: String) : String{
    return "$this $currency"
}