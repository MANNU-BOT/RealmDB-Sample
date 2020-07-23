package com.example.realmdb

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        save.setOnClickListener {
            saveData()
        }
        realm.commitTransaction()

        show.setOnClickListener {
            readData()
        }

    }


    private fun saveData() {
        realm.executeTransactionAsync ({
            val student = it.createObject(Patient::class.java)
            student.comment = one.text.toString()
            student.severity= two.text.toString().toInt()
        },{
            Log.d("msg","On Success: Data Written Successfully!")
            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
            readData()
        },{
            Log.d("msg","On Error: Error in saving Data!")
            Toast.makeText(this,"Wrong",Toast.LENGTH_SHORT).show()

        })
    }

    private fun readData() {
        val students = realm.where(Patient::class.java).findAll()
        var response=""
        students.forEach {
            response = response + "Name: ${it.comment}, Age: ${it.severity}" +"\n"
        }
        data.text= response
    }
}

