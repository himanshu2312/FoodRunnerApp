package com.himanshu.foodrunnerapp.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.himanshu.foodrunnerapp.R
import com.himanshu.foodrunnerapp.util.ConnectionManager
import org.json.JSONException
import org.json.JSONObject

@Suppress("DEPRECATION")
class OtpActivity : AppCompatActivity() {
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var etOtp: EditText
    private lateinit var btnSubmit: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        etOtp = findViewById(R.id.etOtp)
        btnSubmit = findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            if (etPassword.text.toString() == etConfirmPassword.text.toString()) {
                if (ConnectionManager().checkConnectivity(this@OtpActivity)) {
                    val queue = Volley.newRequestQueue(this@OtpActivity)
                    val url = "http://13.235.250.119/v2/reset_password/fetch_result"
                    val resetCredentials = JSONObject()
                    resetCredentials.put(
                        "mobile_number",
                         intent.getStringExtra("mobile_number")
                    )
                    resetCredentials.put("password", etPassword.text.toString())
                    resetCredentials.put("otp", etOtp.text.toString())
                    val jsonObjectRequest =
                        object : JsonObjectRequest(Method.POST, url, resetCredentials,
                            Response.Listener {
                                try {
                                    if (it.getJSONObject("data").getBoolean("success")) {

                                        Toast.makeText(
                                            this@OtpActivity,
                                            it.getJSONObject("data")
                                                .getString("successMessage") + "\nRedirecting to login...",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val handler = Handler()
                                        handler.postDelayed({
                                            val intent =
                                                Intent(this@OtpActivity, LoginActivity::class.java)
                                            startActivity(intent)
                                        }, 2000)
                                        finish()
                                    } else {
                                        Toast.makeText(
                                            this@OtpActivity,
                                            it.getJSONObject("data").getString("errorMessage"),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } catch (e: JSONException) {
                                    Toast.makeText(
                                        this@OtpActivity,
                                        "$e",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            Response.ErrorListener {
                                Toast.makeText(
                                    this@OtpActivity,
                                    "Volley error occurred!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }) {
                            override fun getHeaders(): MutableMap<String, String> {
                                val header = HashMap<String, String>()
                                header["Content-Type"] = "application/json"
                                header["token"] = "56e490debc1169"
                                return header
                            }
                        }
                    queue.add(jsonObjectRequest)
                } else {
                    val dialog = AlertDialog.Builder(this@OtpActivity)
                    dialog.setMessage("Internet not found")
                    dialog.setNegativeButton("Exit") { text, listener ->
                        ActivityCompat.finishAffinity(this@OtpActivity)
                    }
                    dialog.setPositiveButton("Open Settings") { text, listener ->
                        val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                        startActivity(intent)
                        finish()
                    }
                    dialog.create()
                    dialog.show()
                }
            } else {
                Toast.makeText(
                    this@OtpActivity,
                    "Entered passwords are not same!!",
                    Toast.LENGTH_SHORT
                ).show()
                etPassword.text.clear()
                etConfirmPassword.text.clear()
            }
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this@OtpActivity, ForgotActivity::class.java)
        startActivity(intent)
        finish()
    }
}