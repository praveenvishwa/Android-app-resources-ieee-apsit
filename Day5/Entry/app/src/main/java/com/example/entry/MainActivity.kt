package com.example.entry

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.*
import android.widget.*
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var providers: List<AuthUI.IdpConfig>
    private val MY_REQUEST_CODE:Int=7117


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        providers=Arrays.asList<AuthUI.IdpConfig>(

            AuthUI.IdpConfig.EmailBuilder().build(),//Email login
            AuthUI.IdpConfig.GoogleBuilder().build(),//google login
           // AuthUI.IdpConfig.GitHubBuilder().build(), if you want to add
            AuthUI.IdpConfig.PhoneBuilder().build()//Phone login

        //You can add more options to this list if you like
        )

        ShowSigninOptions()


        //if user pesses signout
        btnsignout.setOnClickListener{

            AuthUI.getInstance().signOut(this@MainActivity)
                .addOnCompleteListener {

                    btnsignout.isEnabled=false
                    ShowSigninOptions() //once logged out go back to firebase sign in screen

                }
                .addOnFailureListener {

                    e->  Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()

                }


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==MY_REQUEST_CODE){

            val response=IdpResponse.fromResultIntent(data)
            if (resultCode==Activity.RESULT_OK){
                val user=FirebaseAuth.getInstance().currentUser
                Toast.makeText(this,"Signed in as "+user!!.email,Toast.LENGTH_LONG).show()

                btnsignout.isEnabled=true

            }else{

                Toast.makeText(this,""+response!!.error!!.message,Toast.LENGTH_LONG).show()


            }
        }


    }

    private fun ShowSigninOptions(){

        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(R.style.MyTheme).
                build(),MY_REQUEST_CODE)


    }




}
