package com.example.kotlinbuttonnavi.ui.setting

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.kotlinbuttonnavi.R
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.fragment_setting.view.*

class SettingFragment : Fragment() {
    private val TAG = "SettingFragment"

    private lateinit var settingViewModel: SettingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: start")
        settingViewModel = ViewModelProviders.of(this).get(SettingViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        val textView: TextView = view.findViewById(R.id.text_setting)
        settingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val dataStore: SharedPreferences = this.requireActivity().getSharedPreferences("DataStore", Context.MODE_PRIVATE)

        //ToDo:設定画面で都市設定
        //ToDo:文字入力完了ボタンをGboardに設定
        view.button_setCity.setOnClickListener{
            Log.d(TAG, "onCreateView: button click")
            //設定postcodeデータの保存
            val stringText = editText_City.text.toString()
            val editor = dataStore.edit()
            editor.putString("Input",stringText)
            editor.apply()
            Log.d(TAG, "onCreateView: set PostCode data")
            //ホームフラグメントへ移動
            view.findNavController().navigate(R.id.navigation_home)
        }

        Log.d(TAG, "onCreateView: end")
        return view
    }

}