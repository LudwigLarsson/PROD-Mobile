package com.example.finalprodproject.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

object KeyboardUtils {

    @JvmStatic
    fun hideKeyboard(fragment: Fragment) {
        with(fragment) {
            view?.let { hideKeyboard(it, activity!!) }
        }
    }

    @JvmStatic
    fun hideKeyboard(activity: Activity) {
        with(activity) {
            hideKeyboard(currentFocus ?: View(this), applicationContext)
        }
    }

    @JvmStatic
    private fun hideKeyboard(view: View, context: Context) {
        with(context) {
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}