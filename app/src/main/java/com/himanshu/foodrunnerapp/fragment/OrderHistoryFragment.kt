package com.himanshu.foodrunnerapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.himanshu.foodrunnerapp.R


class OrderHistoryFragment : Fragment() {
    private lateinit var progressBarLayout: RelativeLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order_history, container, false)
        progressBarLayout=view.findViewById(R.id.progressBarLayout)


        return view
    }

}