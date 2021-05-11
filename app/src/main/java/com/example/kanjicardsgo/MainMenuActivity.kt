package com.example.kanjicardsgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kanjicardsgo.data_classes.ActiveEnv
import com.example.kanjicardsgo.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        // Create view binding instance
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Set course name
        binding.textViewCourseTitle.text = ActiveEnv.track.name

        // CLICK EVENTS
        // Button Study
        binding.buttonstudy.setOnClickListener{
            val intent = Intent(this, StudyActivity::class.java)
            startActivity(intent)
        }

        binding.buttonlearn.setOnClickListener{
            val i = Intent(this, PhaseMenuActivity::class.java)
            startActivity(i)
        }

    }
}