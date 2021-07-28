package com.example.eshop.ui.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.eshop.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialoge_progress.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class BaseActivity : AppCompatActivity() {


    private lateinit var mProgressDialog: Dialog

    private var doubleBackToExitPressedOnce = false


     fun showErrorSnackBar(message: String, errorMessage: Boolean) {
         val snackBar =
             Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
         val snackBarView = snackBar.view

         if (errorMessage) {
             snackBarView.setBackgroundColor(
                 ContextCompat.getColor(
                     this@BaseActivity,
                     R.color.colorSnackBarError
                 )
             )
         }else{
             snackBarView.setBackgroundColor(
                 ContextCompat.getColor(
                     this@BaseActivity,
                     R.color.colorSnackBarSuccess
                 )
             )
         }
         snackBar.show()
     }

    fun showProgressDialog(text: String) {
        mProgressDialog = Dialog(this)

        mProgressDialog.setContentView(R.layout.dialoge_progress)

        mProgressDialog.tv_progress_text.text = text

        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        //Start the dialog and display it on screen.
        mProgressDialog.show()
    }

    fun hideProgressDialog() {
        mProgressDialog.dismiss()
    }


    fun doubleBackToExit() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true

        Toast.makeText(this, resources.getString(R.string.please_click_back_again_to_exit), Toast.LENGTH_SHORT).show()


        CoroutineScope(Dispatchers.Main).launch {
            delay(2000L)
            doubleBackToExitPressedOnce = false
        }
    }
}