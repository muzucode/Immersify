package com.example.kanjicardsgo.signin

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.kanjicardsgo.R
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.data_classes.ActiveEnv
import com.example.kanjicardsgo.data_classes.User.User
import com.example.kanjicardsgo.databinding.ActivitySignInBinding
import com.example.kanjicardsgo.signup.SignUpActivity
import com.example.kanjicardsgo.track_route.SelectTrackActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Take in intent input from sign-up activity if it was procced before this one
        val justUser: String? = intent.getStringExtra("username")
        val justPass: String? = intent.getStringExtra("password")

        // If these are not null then set the sign in info
        if(!justUser.isNullOrBlank() && !justPass.isNullOrBlank()){
            binding.editTextUsername2.setText(justUser)
            binding.editTextPassword2.setText(justPass)
        }

        // Create database
        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "firstdb"
        ).build()

        // Instantiate userDao
        val userDao = db.userDao()

        // Sign in button
        // Check to make sure that the username and password are part of the same user
        binding.buttonSignIn.setOnClickListener{

            // Take in user and pass input
            var usernameIn: String = binding.editTextUsername2.text.toString()
            val passwordIn: String = binding.editTextPassword2.text.toString()

            // Handle sign-in attempt
            GlobalScope.launch{

                // Admin access variables
                val adminAccess = true
                userDao.insertUser(User(null,"admin","admin","admin"))
                usernameIn = "admin"





                // Try get password of user in DB
                    // If it exists, then enter app
                    // Else, show alert
                // Catch if user does not exist and show alert

                try{
                    if(adminAccess || userDao.findByName(usernameIn).password == passwordIn){
                        val i: Intent = Intent(this@SignInActivity, SelectTrackActivity::class.java)

                        // If successful login, then set environment user
                        ActiveEnv.user = userDao.findByName(usernameIn)
                        startActivity(i)
                    }
                    else{
                        // Alert if provided password does not match stored password
                        this@SignInActivity.runOnUiThread {
                            AlertDialog.Builder(this@SignInActivity)
                                    .setTitle("Oops!")
                                    .setMessage("Username and/or password are incorrect.")
                                    .setPositiveButton(R.string.ok, null)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show()
                        }
                    }
                }
                catch (e: Exception){
                    // Alert if user is not in Room database
                    this@SignInActivity.runOnUiThread {
                        // Alert if password does not match stored password
                        AlertDialog.Builder(this@SignInActivity)
                                .setTitle("Oops!")
                                .setMessage("User does not exist.")
                                .setPositiveButton(R.string.ok, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show()
                    }
                }
            }

        }

        binding.textViewSignUpHere.setOnClickListener{
            val i: Intent = Intent(this, SignUpActivity::class.java)
            startActivity(i)
        }



    }


}