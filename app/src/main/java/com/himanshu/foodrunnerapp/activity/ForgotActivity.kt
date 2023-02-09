package com.himanshu.foodrunnerapp.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.widget.EditText
import android.widget.Toast
import com.himanshu.foodrunnerapp.R
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.himanshu.foodrunnerapp.util.ConnectionManager
import org.json.JSONException
import org.json.JSONObject

class ForgotActivity : AppCompatActivity() {
    private lateinit var etRegisteredMobileNumber: EditText
    private lateinit var etRegisteredEmail: EditText
    private lateinit var btnNext: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        etRegisteredEmail=findViewById(R.id.etRegisteredEmail)
        etRegisteredMobileNumber=findViewById(R.id.etRegisteredMobileNumber)
        btnNext=findViewById(R.id.btnNext)
        btnNext.setOnClickListener {
            if (etRegisteredMobileNumber.text.toString().length != 10) {
                Toast.makeText(
                    this@ForgotActivity,
                    "Mobile number should have 10 characters",
                    Toast.LENGTH_SHORT
                ).show()

            }
            else if (ConnectionManager().checkConnectivity(this@ForgotActivity)) {
                val queue = Volley.newRequestQueue(this@ForgotActivity)
                val otpUrl = "http://13.235.250.119/v2/forgot_password/fetch_result"
                val otpCredential = JSONObject()
                otpCredential.put("mobile_number", etRegisteredMobileNumber.text.toString())
                otpCredential.put("email", etRegisteredEmail.text.toString())
                val jsonObjectRequest =
                    object : JsonObjectRequest(
                        Method.POST, otpUrl, otpCredential,
                        Response.Listener {
                            try {
                                val data = it.getJSONObject("data")
                                val success = data.getBoolean("success")
                                if (success) {
                                    Toast.makeText(
                                        this@ForgotActivity,
                                        "OTP is sent to our registered email!!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val handler = Handler()
                                    handler.postDelayed(
                                        {
                                            val intent = Intent(
                                                this@ForgotActivity,
                                                MainActivity::class.java
                                            )
                                            startActivity(intent)
                                        }, 2000
                                    )

                                } else {
                                    val errorMessage = data.getString("errorMessage")
                                    Toast.makeText(
                                        this@ForgotActivity,
                                        errorMessage,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } catch (e: JSONException) {
                                Toast.makeText(
                                    this@ForgotActivity,
                                    "$e",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        Response.ErrorListener {
                            Toast.makeText(
                                this@ForgotActivity,
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
                val dialog = AlertDialog.Builder(this@ForgotActivity)
                dialog.setMessage("Internet Connection Error:")
                dialog.setNegativeButton("Exit") { text, listener ->
                    ActivityCompat.finishAffinity(this@ForgotActivity)
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
    override fun onBackPressed() {
        val intent = Intent(this@ForgotActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}