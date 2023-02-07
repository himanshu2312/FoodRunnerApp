package com.himanshu.foodrunnerapp.activity

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.CreationExtras
import com.himanshu.foodrunnerapp.R

@Suppress("DEPRECATION")
class SignupActivity : AppCompatActivity() {

    private lateinit var etUserName: EditText
    private lateinit var etUserEmail: EditText
    private lateinit var etUserMobileNumber: EditText
    private lateinit var etUserAddress: EditText
    private lateinit var etUserPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnRegister: AppCompatButton
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etUserName = findViewById(R.id.etNewName)
        etUserEmail = findViewById(R.id.etNewEmail)
        etUserAddress = findViewById(R.id.etNewDeliveryAddress)
        etUserMobileNumber = findViewById(R.id.etNewMobileNumber)
        etUserPassword = findViewById(R.id.etNewPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)
        sharedPreferences = getSharedPreferences(R.string.app_name.toString(), MODE_PRIVATE)

        btnRegister.setOnClickListener {
            val message = when {
                etUserName.text.toString().isEmpty() -> {
                    "Name field is empty"
                }
                etUserEmail.text.toString().isEmpty() -> {
                    "Email field is empty"
                }
                etUserMobileNumber.text.toString().isEmpty() -> {
                    "Mobile number field is empty"
                }
                etUserAddress.text.toString().isEmpty() -> {
                    "Address field is empty"
                }
                etUserPassword.text.toString().isEmpty() -> {
                    "Password field is empty"
                }
                etConfirmPassword.text.toString().isEmpty() -> {
                    "Confirm password field is empty"
                }
                else -> {
                    ""
                }
            }
            if (!message.isEmpty()) {
                Toast.makeText(
                    this@SignupActivity,
                    message,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (etUserPassword.text.toString() == etConfirmPassword.text.toString()) {
                    sharedPreferences.edit().clear().apply()
                    sharedPreferences.edit().putString("user_name", etUserName.text.toString())
                        .apply()
                    sharedPreferences.edit().putString("user_email", etUserEmail.text.toString())
                        .apply()
                    sharedPreferences.edit()
                        .putString("user_address", etUserAddress.text.toString())
                        .apply()
                    sharedPreferences.edit()
                        .putString("user_number", etUserMobileNumber.text.toString()).apply()
                    sharedPreferences.edit()
                        .putString("user_password", etUserPassword.text.toString())
                        .apply()
                    Toast.makeText(
                        this@SignupActivity,
                        "Registration success!!\nRedirecting to login....",
                        Toast.LENGTH_SHORT
                    ).show()
                    etUserName.text.clear()
                    etUserEmail.text.clear()
                    etUserMobileNumber.text.clear()
                    etUserAddress.text.clear()
                    etUserPassword.text.clear()
                    etConfirmPassword.text.clear()
                    val handler = Handler()
                    handler.postDelayed(
                        {
                            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                            startActivity(intent)
                        }, 2000
                    )
                } else {
                    Toast.makeText(
                        this@SignupActivity,
                        "Entered passwords are not same!!",
                        Toast.LENGTH_SHORT
                    ).show()
                    etUserPassword.text.clear()
                    etConfirmPassword.text.clear()
                }
            }
        }

    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    override fun onBackPressed() {
        val intent = Intent(this@SignupActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}