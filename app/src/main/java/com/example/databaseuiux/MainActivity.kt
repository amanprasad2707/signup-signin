package com.example.databaseuiux

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var database:  DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signup =  findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etEmail = findViewById<TextInputEditText>(R.id.etMail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)

        signup.setOnClickListener{
            val name = etName.text.toString()
            val username = etUsername.text.toString()
            val mail = etEmail.text.toString()
            val password = etPassword.text.toString()

            val user = User(name, mail, password, username)
            database = FirebaseDatabase.getInstance().getReference("users")
            database.child(username).setValue(user).addOnSuccessListener {
                etName.text?.clear()
                etUsername.text?.clear()
                etEmail.text?.clear()
                etPassword.text?.clear()
                Toast.makeText(this,"User Registered Successfully..",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed!",Toast.LENGTH_SHORT).show()
            }
        }

        val signinText = findViewById<TextView>(R.id.tvSignin)
        signinText.setOnClickListener{
            val openSigninActivity = Intent(this,SigninActivity:: class.java)
            startActivity(openSigninActivity)
        }
    }
}