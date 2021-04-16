package com.example.kanjicardsgo.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kanjicardsgo.MainMenuActivity
import com.example.kanjicardsgo.R
import com.example.kanjicardsgo.databinding.ActivitySignInBinding
import com.example.kanjicardsgo.databinding.ActivitySignUpBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Take in intent input from sign-up activity
        val justUser: String? = intent.getStringExtra("username")
        val justPass: String? = intent.getStringExtra("password")

        // If these are not null then set the sign in info
        if(!justUser.isNullOrBlank() && !justPass.isNullOrBlank()){
            binding.editTextUsername2.setText(justUser)
            binding.editTextPassword2.setText(justPass)
        }

        // Sign in button
        // Check to make sure that the username and password are part of the same user
        binding.buttonSignIn.setOnClickListener{
            val i: Intent = Intent(this, MainMenuActivity::class.java)
            startActivity(i)
        }




    }


}