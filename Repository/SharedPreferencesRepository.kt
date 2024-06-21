package com.example.clienttest.UI.Repository

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.clienttest.UI.Contract.MainContract
import com.example.clienttest.UI.MainActivity
import com.example.clienttest.UI.data.Acount

class SharedPreferencesRepository (private val context: Context ): MainContract.SharedPreferencesRepository {
    val sharedPreferences= context.getSharedPreferences("acount",Context.MODE_PRIVATE)
    val editor=sharedPreferences.edit()

    override fun getData():Acount {

        val name=sharedPreferences.getString("name","")!!
        val phone=sharedPreferences.getString("phone","")!!
        val address=sharedPreferences.getString("address","")!!
        val floor=sharedPreferences.getString("floor","")!!
        val house_number=sharedPreferences.getString("house_number","")!!
        return Acount(name, phone, address, floor, house_number)
    }

    override fun updateData(acount: Acount) {
        editor.putString("name",acount.name)
        editor.putString("phone",acount.phone)
        editor.putString("address",acount.address)
        editor.putString("floor",acount.floor)
        editor.putString("house_number",acount.house_number)
        editor.commit()
    }
}