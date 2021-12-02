package com.example.client_android.util

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.client_android.R

fun Context.simpleDialog(msg: String, btn: String) {

    val layoutInflater = LayoutInflater.from(this)
    val view = layoutInflater.inflate(R.layout.dialog_simple, null)

    val alertDialog = AlertDialog.Builder(this)
        .setView(view)
        .create()

    val tvMessage = view.findViewById<TextView>(R.id.tv_dialog_reserve_msg)
    val btnConfirm = view.findViewById<Button>(R.id.btn_dialog_reserve_ok)

    tvMessage.text = msg
    btnConfirm.text = btn
    btnConfirm.setOnClickListener {
        alertDialog.dismiss()
    }

    // radius 적용을 위한 background 적용
    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

    alertDialog.show()

}

object DialogUtil {
}