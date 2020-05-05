package com.pratik.erostestapp.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.pratik.erostestapp.R

object AppUtils {
    private val TAG = AppUtils.javaClass.simpleName
    private var dialog: Dialog? = null


    fun showNoNetworkDialog(context: Activity) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.app_name)
        builder.setMessage(R.string.no_internet_message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Close") { dialogInterface, which ->
           context.finish()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    fun getUTF_8String(string: String): String {
        return String(string.toString().toByteArray(), Charsets.UTF_8)
    }

    fun hideLoader() {
        Log.d(TAG,"Hide Loading !!!!")
        try {
            if (dialog!! != null) {
                if (dialog!!.isShowing()) dialog!!.dismiss()
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception $e")
        }
    }

    fun showLoader(context: Context) {
        Log.d(TAG,"Show Loading !!!!")
        try {
            dialog = getProgressDialog(context)
            dialog!!.show()
        } catch (e: Exception) {
            Log.d(TAG, "Exception $e")
        }
    }

    fun getProgressDialog(context: Context): Dialog {
        if (dialog == null) {
            dialog = Dialog(context)
            dialog!!.getWindow()!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        dialog!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCancelable(false)
        val factory = LayoutInflater.from(context)
        val customPopupView: View = factory.inflate(R.layout.loading_dialog, null)
        dialog!!.setContentView(customPopupView)
        return dialog!!
    }

    fun hideKeyboard(view : View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}