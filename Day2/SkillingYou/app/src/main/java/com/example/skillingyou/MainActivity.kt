package com.example.skillingyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    fun dusraactivity(view: View){


        startActivity(Intent(this, SecondAcivity::class.java))

    }


    fun teesraactivity(view: View){


        startActivity(Intent(this, ThirdActivity::class.java))

    }


}
