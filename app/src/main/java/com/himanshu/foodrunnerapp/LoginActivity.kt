package com.himanshu.foodrunnerapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var etMobileNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var txtForgotPassword: TextView
    private lateinit var txtSignup: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(R.string.app_name.toString(), MODE_PRIVATE)
        val startMainActivity = Intent(this@LoginActivity, MainActivity::class.java)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            startActivity(startMainActivity)
        }
        else {
            setContentView(R.layout.activity_login)
            etPassword=findViewById(R.id.etPassword)
            etMobileNumber=findViewById(R.id.etMobileNumber)
            btnLogin = findViewById(R.id.btnLogin)
            txtSignup=findViewById(R.id.txtSignup)
            txtForgotPassword=findViewById(R.id.txtForgotPassword)

            btnLogin.setOnClickListener {
                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
                startActivity(startMainActivity)
            }

            txtForgotPassword.setOnClickListener {
                 val startForgotActivity=Intent(this@LoginActivity,ForgotActivity::class.java)
                 startActivity(startForgotActivity)
            }

            txtSignup.setOnClickListener {
                 val startSignupActivity=Intent(this@LoginActivity,SignupActivity::class.java)
                  startActivity(startSignupActivity)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (sharedPreferences.getBoolean("isLoggedIn",false)){
            finish()
        }
    }
}