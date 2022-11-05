package com.projemanag.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.projemanag.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignInActivity : BaseActivity() {
    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_sign_in)

        auth=FirebaseAuth.getInstance()


        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        btn_sign_in.setOnClickListener{
            signinregister()
        }
        setupActionBar()
    }

    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(toolbar_sign_in_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        toolbar_sign_in_activity.setNavigationOnClickListener { onBackPressed() }
    }
    private fun signinregister()
    {
        val email: String = et_email_in.text.toString().trim { it <= ' ' }
        val password: String = et_password_in.text.toString().trim { it <= ' ' }
       if(validateForm(email, password))
       {
           showProgressDialog(resources.getString(R.string.please_wait))
           auth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener(this) { task ->
                   hideProgressDialog()
                   if (task.isSuccessful) {
                       // Sign in success, update UI with the signed-in user's information

                       val user = auth.currentUser
                            val intent=Intent(this,MainActivity::class.java)
                       startActivity(intent)
                   } else {
                       // If sign in fails, display a message to the user.

                       Toast.makeText(baseContext, "Authentication failed.",
                           Toast.LENGTH_SHORT).show()

                   }
               }
       }
    }
    private fun validateForm( email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter email.")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter password.")
                false
            }
            else -> {
                true
            }
        }
    }
}