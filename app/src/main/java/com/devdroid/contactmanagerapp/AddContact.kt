package com.devdroid.contactmanagerapp

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddContact : AppCompatActivity() {

    lateinit var AddContactdatabase:DatabaseReference
    lateinit var dialogAdd: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        val text_name:TextInputEditText=findViewById(R.id.text_name)
        val text_email:TextInputEditText=findViewById(R.id.text_email)
        val btn_add:Button=findViewById(R.id.btn_add)

        dialogAdd=Dialog(this)
        dialogAdd.setContentView(R.layout.custom_dialog_addcontact)
        dialogAdd.window?.setBackgroundDrawable(getDrawable(R.drawable.bg_alartbox))

        val button:Button=dialogAdd.findViewById(R.id.button)
        button.setOnClickListener {
            dialogAdd.dismiss()
        }

        btn_add.setOnClickListener {
            val name=text_name.text.toString()
            val email=text_email.text.toString()

            val addmanager=addManagers(name,email)
            AddContactdatabase=FirebaseDatabase.getInstance().getReference("AddManagers")
            AddContactdatabase.child(name).setValue(addmanager).addOnSuccessListener {
                text_name.text?.clear()
                text_email.text?.clear()

                dialogAdd.show()

               // Toast.makeText(this,"Contact Added",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed to Add",Toast.LENGTH_SHORT).show()
            }
        }


    }
}