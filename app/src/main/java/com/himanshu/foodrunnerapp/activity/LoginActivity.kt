package com.himanshu.foodrunnerapp.activity

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.himanshu.foodrunnerapp.R
import com.himanshu.foodrunnerapp.util.ConnectionManager
import com.android.volley.Response
import org.json.JSONException
import org.json.JSONObject

@Suppress("DEPRECATION")
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
            etPassword = findViewById(R.id.etPassword)
            etMobileNumber = findViewById(R.id.etMobileNumber)
            btnLogin = findViewById(R.id.btnLogin)
            txtSignup = findViewById(R.id.txtSignup)
            txtForgotPassword = findViewById(R.id.txtForgotPassword)
            btnLogin.setOnClickListener {
                if (etMobileNumber.text.toString().length != 10) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Mobile number should have 10 characters",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (etPassword.text.toString().length <= 4) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Password length should be more than 4",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (ConnectionManager().checkConnectivity(this@LoginActivity)) {
                    val queue = Volley.newRequestQueue(this@LoginActivity)
                    val loginUrl = "http://13.235.250.119/v2/login/fetch_result/"
                    val loginCredential = JSONObject()
                    loginCredential.put("mobile_number", etMobileNumber.text.toString())
                    loginCredential.put("password", etPassword.text.toString())
                    val jsonObjectRequest =
                        object : JsonObjectRequest(Method.POST, loginUrl, loginCredential,
                            Response.Listener {
                                try {
                                    val data = it.getJSONObject("data")
                                    val success = data.getBoolean("success")
                                    if (success) {
                                        val info = data.getJSONObject("data")
                                        sharedPreferences.edit().clear().apply()
                                        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
                                        sharedPreferences.edit()
                                            .putString("user_id", info.getString("user_id"))
                                            .apply()
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
                                        val handler = Handler()
                                        handler.postDelayed(
                                            {
                                                val intent = Intent(
                                                    this@LoginActivity,
                                                    MainActivity::class.java
                                                )
                                                startActivity(intent)
                                            }, 2000
                                        )
                                    } else {
                                        val errorMessage = data.getString("errorMessage")
                                        Toast.makeText(
                                            this@LoginActivity,
                                            errorMessage,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        etMobileNumber.text.clear()
                                        etPassword.text.clear()
                                    }
                                } catch (e: JSONException) {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "$e",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            Response.ErrorListener {
                                Toast.makeText(
                                    this@LoginActivity,
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
                    val dialog = AlertDialog.Builder(this@LoginActivity)
                    dialog.setMessage("Internet Connection Error:")
                    dialog.setNegativeButton("Exit") { text, listener ->
                        ActivityCompat.finishAffinity(this@LoginActivity)
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

            txtForgotPassword.setOnClickListener {
                    val startForgotActivity = Intent(this@LoginActivity, ForgotActivity::class.java)
                    startActivity(startForgotActivity)

            }


            txtSignup.setOnClickListener {
                    val startSignupActivity = Intent(this@LoginActivity, SignupActivity::class.java)
                    startActivity(startSignupActivity)
                }
            }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

}