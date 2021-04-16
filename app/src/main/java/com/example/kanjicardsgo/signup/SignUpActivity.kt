package com.example.kanjicardsgo.signup

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.kanjicardsgo.R
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.data_classes.User.User
import com.example.kanjicardsgo.databinding.ActivityManageDecksBinding
import com.example.kanjicardsgo.databinding.ActivitySignUpBinding
import com.example.kanjicardsgo.signin.SignInActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create database
        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "firstdb"
        ).build()

        // Instantiate userDao
        val userDao = db.userDao()



        binding.buttonSignUp.setOnClickListener{
            val userName: String = binding.editTextUsername.text.toString()
            val email: String = binding.editTextEmail.text.toString()
            val password: String = binding.editTextPassword.text.toString()
            val confirmPassword: String = binding.editTextConfirmPassword.text.toString()
            val specialChars: List<String> = listOf("!","@","#","$","%","^","&","*")
            var specialCharsPass: Boolean = false
            var alertMsgUsername: String = ""
            var alertMsgPassword: String = ""
            var alertMsgPasswordConfirm: String = ""
            val adminAccess = true

            // Check if a special character is in password
            for(i in specialChars.indices){
                if(password.contains(specialChars[i])){
                    specialCharsPass = true
                }
            }

            // Check all possible errors in signup info
            if(userName.length < 3) alertMsgUsername = "Username must be at least 3 characters in length."
            if(password.length < 8 || !specialCharsPass) alertMsgPassword = "Password must be at least 8 characters in length and contain a special character such as !, @, #, $, %, ^, &, *."
            if(confirmPassword != password) alertMsgPasswordConfirm = "Please make sure both passwords match."

            // Check if any alertmsgs have alert content and create user if there are no errors
            if(alertMsgPassword == "" && alertMsgUsername == "" && alertMsgPasswordConfirm == "" || adminAccess){
                GlobalScope.launch{
                    userDao.insertUser(User(null, userName, password, email))
                }
                // Create intent to sign in activity
                val i: Intent = Intent(this, SignInActivity::class.java)
                i.putExtra("username", userName)
                i.putExtra("password", password)
                startActivity(i)
            }
            else {
                AlertDialog.Builder(this@SignUpActivity)
                        .setTitle("Oops!")
                        .setMessage("$alertMsgPasswordConfirm  $alertMsgUsername  $alertMsgPassword")
                        .setPositiveButton(R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
            }
        }



        binding.checkBoxLang1

        binding.checkBoxLang2

        binding.checkBoxLang3
    }
}