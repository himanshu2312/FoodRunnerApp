package com.himanshu.foodrunnerapp.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.CreationExtras
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.himanshu.foodrunnerapp.R
import com.himanshu.foodrunnerapp.util.ConnectionManager
import org.json.JSONException
import org.json.JSONObject

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
            } else if (etUserMobileNumber.text.toString().length != 10) {
                Toast.makeText(
                    this@SignupActivity,
                    "Mobile number should have 10 characters",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (etUserPassword.text.toString().length <= 4) {
                Toast.makeText(
                    this@SignupActivity,
                    "Password length should be more than 4",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (etUserPassword.text.toString() != etConfirmPassword.text.toString()) {
                Toast.makeText(
                    this@SignupActivity,
                    "Entered passwords are not same!!",
                    Toast.LENGTH_SHORT
                ).show()
                etUserPassword.text.clear()
                etConfirmPassword.text.clear()
            } else if (ConnectionManager().checkConnectivity(this@SignupActivity)) {
                val queue = Volley.newRequestQueue(this@SignupActivity)
                val registerUrl = "http://13.235.250.119/v2/register/fetch_result"
                val registerCredential = JSONObject()
                registerCredential.put("name", etUserName.text.toString())
                registerCredential.put("mobile_number", etUserMobileNumber.text.toString())
                registerCredential.put("password", etUserPassword.text.toString())
                registerCredential.put("address", etUserAddress.text.toString())
                registerCredential.put("email", etUserEmail.text.toString())
                val jsonObjectRequest =
                    object : JsonObjectRequest(
                        Method.POST, registerUrl, registerCredential,
                        Response.Listener {
                            try {
                                val data = it.getJSONObject("data")
                                val success = data.getBoolean("success")
                                if (success) {
                                    val info = data.getJSONObject("data")
                                    sharedPreferences.edit().clear().apply()
                                    sharedPreferences.edit()
                                        .putString("name", info.getString("name"))
                                        .apply()
                                    sharedPreferences.edit()
                                        .putString("email", info.getString("email"))
                                        .apply()
                                    sharedPreferences.edit()
                                        .putString("address", info.getString("address"))
                                        .apply()
                                    sharedPreferences.edit()
                                        .putString("mobile_number", info.getString("mobile_number"))
                                        .apply()
                                    sharedPreferences.edit()
                                        .putString("password", info.getString("password"))
                                        .apply()
                                    Toast.makeText(
                                        this@SignupActivity,
                                        "Registration success!!\nRedirecting to Home....",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val handler = Handler()
                                    handler.postDelayed(
                                        {
                                            val intent = Intent(
                                                this@SignupActivity,
                                                MainActivity::class.java
                                            )
                                            startActivity(intent)
                                        }, 2000
                                    )

                                } else {
                                    val errorMessage = data.getString("errorMessage")
                                    Toast.makeText(
                                        this@SignupActivity,
                                        errorMessage,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    etUserEmail.text.clear()
                                    etUserMobileNumber.text.clear()
                                    etUserPassword.text.clear()
                                    etConfirmPassword.text.clear()
                                }
                            } catch (e: JSONException) {
                                Toast.makeText(
                                    this@SignupActivity,
                                    "$e",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        Response.ErrorListener {
                            Toast.makeText(
                                this@SignupActivity,
                                "volley error occurred!!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    ) {
                        override fun getHeaders(): MutableMap<String, String> {
                            val header = HashMap<String, String>()
                            header["Content-Type"] = "application/json"
                            header["token"] = "56e490debc1169"
                            return header
                        }
                    }
                queue.add(jsonObjectRequest)
            } else {
                val dialog = AlertDialog.Builder(this@SignupActivity)
                dialog.setMessage("Internet Connection Error:")
                dialog.setNegativeButton("Exit") { text, listener ->
                    ActivityCompat.finishAffinity(this@SignupActivity)
                }
                dialog.setPositiveButton("Open Settings") { text, listener ->
                    val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                    startActivity(intent)
                    finish()
                }
                dialog.create()
                dialog.show()
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