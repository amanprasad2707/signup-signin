package com.example.databaseuiux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


        val name = intent.getStringExtra(SigninActivity.KEY2)
        val mail = intent.getStringExtra(SigninActivity.KEY1)
        val userId = intent.getStringExtra(SigninActivity.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tvWelcome)
        val mailText = findViewById<TextView>(R.id.tvMail)
        val idText = findViewById<TextView>(R.id.tvUnique)

        welcomeText.text = "Welcome $name"
        mailText.text = "Mail: $mail"
        idText.text = "UserId: $userId"



    }
}