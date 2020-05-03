package com.pratik.erostestapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.airbnb.lottie.LottieAnimationView
import com.pratik.erostestapp.R
import java.lang.Exception

object AppUtils {
    private val TAG = AppUtils.javaClass.simpleName
    private var dialog: Dialog? = null

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
}