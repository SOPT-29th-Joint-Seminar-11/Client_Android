package com.example.client_android.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

fun Context.simpleDialog(msg: String, btn: String) {
    AlertDialog.Builder(this)
        .setMessage(msg)
        .setPositiveButton(btn, object: DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {

            }
        })
        .create()
        .show()
}

object DialogUtil {
}