package com.himanshu.foodrunnerapp.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.Display.Mode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.util.findColumnIndexBySuffix
import com.himanshu.foodrunnerapp.R

class ProfileFragment : Fragment() {

    private lateinit var txtUserName: TextView
    private lateinit var txtUserAddress: TextView
    private lateinit var txtUserMobileNumber: TextView
    private lateinit var txtUserEmail: TextView
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        txtUserAddress=view.findViewById(R.id.txtUserDeliveryAddress)
        txtUserEmail=view.findViewById(R.id.txtUserEmail)
        txtUserName=view.findViewById(R.id.txtUserName)
        txtUserMobileNumber=view.findViewById(R.id.txtUserMobileNumber)
        sharedPreferences = (activity as Context).getSharedPreferences(R.string.app_name.toString(),
            AppCompatActivity.MODE_PRIVATE
        )
        txtUserAddress.text=sharedPreferences.getString("user_address",txtUserAddress.text.toString())
        txtUserName.text=sharedPreferences.getString("user_name",txtUserAddress.text.toString())
        txtUserEmail.text=sharedPreferences.getString("user_email",txtUserAddress.text.toString())
        txtUserMobileNumber.text=sharedPreferences.getString("user_number",txtUserAddress.text.toString())
        return view
    }

}