package com.example.email

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sendEmailbtn.setOnClickListener{

            val message=messageman.text.toString().trim()
            val recipent=recipentman.text.toString().trim()
            val subject=subjectman.text.toString().trim()


            //agar subject aur email apne choice ka rakhna hai to wo upar val me store kardo
            //user se input lene ki bajay

            //now send all this data as parameters to a function

            SendEmail(message,recipent,subject)

        }

    }


    private fun SendEmail(message: String,recipent: String,subject: String){

        val mIntent=Intent(Intent.ACTION_SEND)
        mIntent.data=Uri.parse("mailto")
        mIntent.type="text/plain"

        /*Recipent is put as an array because you might want to send emails to multiple persons
        so enter comma between each different email
         */
        //putting recipent in intent
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipent))

        //putting subject in intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT,subject)

        //putting message in intent
        mIntent.putExtra(Intent.EXTRA_TEXT,message)


        try {

            //agar sab thik h to email bhej hi dete hai
            startActivity(Intent.createChooser(mIntent,"Choose Email Client..."))

        }
        catch (e: Exception){

            //jaise kuch empty rehgaya ho to

            Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
        }


    }



}
