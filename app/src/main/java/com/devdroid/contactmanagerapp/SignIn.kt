package com.devdroid.contactmanagerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {
    lateinit var signupReference : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val ti_signin:TextInputEditText=findViewById(R.id.ti_signin)
        val btn_signin:Button=findViewById(R.id.btn_signin)

        btn_signin.setOnClickListener {
            val id=ti_signin.text.toString()
            if (id.isNotEmpty()){
                readData(id)
            }else{
                Toast.makeText(this,"please enter user name", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun readData(id: String) {
        signupReference=FirebaseDatabase.getInstance().getReference("SignupUsers")
        signupReference.child(id).get().addOnSuccessListener {
            if(it.exists()){
                //val id=it.child("id").value
               // val mail=it.child("mail").value
                //val name=it.child("name").value

                val signinintent= Intent(applicationContext,AddContact::class.java)
                startActivity(signinintent)

                Toast.makeText(this,"Congratulation !\n We find you",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"User does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }

    }

}