package com.example.databaseuiux

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SigninActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    companion object{
        const val KEY1 = "com.example.databaseuiux.SigninActivity.mail"
        const val KEY2 = "com.example.databaseuiux.SigninActivity.name"
        const val KEY3 = "com.example.databaseuiux.SigninActivity.id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val signinButton = findViewById<Button>(R.id.btnSignin)
        val username = findViewById<TextInputEditText>(R.id.etUsername)

        signinButton.setOnClickListener{
            // take reference till node "users"
            val usernameString = username.text.toString()
            if (usernameString.isNotEmpty()){
                readData(usernameString)
            }else{
                Toast.makeText(this,"Please enter username..",Toast.LENGTH_SHORT).show()
            }
        }
    }
//    onCreate Method over
    private fun readData(username: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        databaseReference.child(username).get().addOnSuccessListener {
            // if user exist or not
            if(it.exists()){
                // welcome user in your app, with intent and also pass
                val email = it.child("email").value
                val name = it.child("name").value
                val userId = it.child("uniqueId").value
                val intentWelcome = Intent(this,WelcomeActivity:: class.java)
                intentWelcome.putExtra(KEY2,name.toString())
                intentWelcome.putExtra(KEY1,email.toString())
                intentWelcome.putExtra(KEY3,userId.toString())
                startActivity(intentWelcome)

            }else{
                Toast.makeText(this, "User does not exist..",Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener{
            Toast.makeText(this,"Failed!, Can't connect to Database",Toast.LENGTH_SHORT).show()
        }
    }
}