package com.ex.mailer

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView

class CustomDialogs {
    companion object {

        fun alert(context: Context, text: String, action: String, callback: (Boolean)->Unit){

            val dialog = AlertDialog.Builder(context, R.style.AppTheme_DialogTransparent)
            val customDialog = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null)

            customDialog.findViewById<AppCompatTextView>(R.id.alertText).text = text
            customDialog.findViewById<AppCompatButton>(R.id.alertActionButton).text = action

            dialog.setView(customDialog)
            dialog.setCancelable(true)

            val alertDialogBox = dialog.create()
            alertDialogBox.show()
            alertDialogBox.window?.setLayout(560, alertDialogBox.window?.attributes?.height!!)

            customDialog.findViewById<AppCompatButton>(R.id.alertActionButton).setOnClickListener {

                callback(true)
                alertDialogBox.cancel()
            }

        }

        fun openStore(context: Context, storeText: String, action: String, store: Store, callback: (Int)->Unit){

            val storeDialog = Dialog(context, R.style.AppTheme_Dialog_FullScreen)
            val storeLayout = LayoutInflater.from(context).inflate(R.layout.store_dialog, null)

            storeLayout.findViewById<AppCompatTextView>(R.id.storeText).text = storeText

            // store item one

            storeLayout.findViewById<AppCompatTextView>(R.id.storeDescOne).text = store.items[0].description
            storeLayout.findViewById<AppCompatTextView>(R.id.storeItemOne).text = store.items[0].action

            storeLayout.findViewById<AppCompatTextView>(R.id.storeItemOne).setOnClickListener {
                callback(1)
                storeDialog.cancel()
            }


            // store item two

            storeLayout.findViewById<AppCompatTextView>(R.id.storeDescTwo).text = store.items[1].description
            storeLayout.findViewById<AppCompatTextView>(R.id.storeItemTwo).text = store.items[1].action

            storeLayout.findViewById<AppCompatTextView>(R.id.storeItemTwo).setOnClickListener {
                callback(2)
                storeDialog.cancel()
            }

            // store item three

            storeLayout.findViewById<AppCompatTextView>(R.id.storeDescThree).text = store.items[2].description
            storeLayout.findViewById<AppCompatTextView>(R.id.storeItemThree).text = store.items[2].action

            storeLayout.findViewById<AppCompatTextView>(R.id.storeItemThree).setOnClickListener {
                callback(3)
                storeDialog.cancel()
            }

            // store item four

            storeLayout.findViewById<AppCompatTextView>(R.id.storeDescFour).text = store.items[3].description
            storeLayout.findViewById<AppCompatTextView>(R.id.storeItemFour).text = store.items[3].action

            storeLayout.findViewById<AppCompatTextView>(R.id.storeItemFour).setOnClickListener {
                callback(4)
                storeDialog.cancel()
            }

            // action button

            storeLayout.findViewById<AppCompatButton>(R.id.storeDraftButton).text = action
            storeLayout.findViewById<AppCompatButton>(R.id.storeDraftButton).setOnClickListener {
                callback(0)
                storeDialog.cancel()
            }

            // initialize and show dialog

            storeDialog.setContentView(storeLayout)

            storeDialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
            storeDialog.setCancelable(true)

            storeDialog.show()
        }
    }
}