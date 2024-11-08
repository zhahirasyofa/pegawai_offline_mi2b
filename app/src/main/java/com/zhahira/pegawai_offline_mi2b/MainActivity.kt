package com.zhahira.pegawai_offline_mi2b

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zhahira.pegawai_offline_mi2b.helper.DbHelper

class MainActivity : AppCompatActivity() {

    private lateinit var etNama : EditText
    private lateinit var etUmur : EditText
    private lateinit var btnSubmit : Button
    private lateinit var btnShowData : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etNama = findViewById(R.id.etNama)
        etUmur = findViewById(R.id.etUmur)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnShowData = findViewById(R.id.btnShowData)

        btnSubmit.setOnClickListener(){
            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DbHelper(this, null)

            // creating variables for values
            // in name and age edit texts
            val name = etNama.text.toString()
            val age = etUmur.text.toString()

            // calling method to add
            // name to our database
            db.addName(name, age)

            // Toast to message on the screen
            Toast.makeText(this, name + " added to database successfully",
                Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            etNama.text.clear()
            etUmur.text.clear()
        }

        btnShowData.setOnClickListener(){
            // creating a DBHelper class
            // and passing context to it
            val db = DbHelper(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
//            Attributes.Name(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
//            Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
//
//            // moving our cursor to next
//            // position and appending values
//            while (cursor.moveToNext()) {
//                Attributes.Name(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
//                Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
//            }

            // at last we close our cursor
            cursor.close()
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}