package com.devdroid.contactmanagerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    //for firebase
    lateinit var databaseSignup: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

            val ti_name:TextInputEditText=findViewById(R.id.ti_name)
            val ti_mail:TextInputEditText=findViewById(R.id.ti_mail)
            val ti_id:TextInputEditText=findViewById(R.id.ti_id)
            val btn_signup:Button=findViewById(R.id.btn_signup)
            btn_signup.setOnClickListener {
                val name=ti_name.text.toString()
                val mail=ti_mail.text.toString()
                val id=ti_id.text.toString()

                val usersignup=UserSignup(name,mail,id)
                databaseSignup=FirebaseDatabase.getInstance().getReference("SignupUsers")
                databaseSignup.child(id).setValue(usersignup).addOnSuccessListener {
                    ti_name.text?.clear()
                    ti_mail.text?.clear()
                    ti_id.text?.clear()
                    Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this,"Error !!!",Toast.LENGTH_SHORT).show()
                }
            }

        val tv_signup: TextView =findViewById(R.id.tv_signup)
        tv_signup.setOnClickListener {
            val intent = Intent(applicationContext, SignIn::class.java)
            startActivity(intent)
        }

    }
}