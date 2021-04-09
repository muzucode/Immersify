package com.example.kanjicardsgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kanjicardsgo.databinding.ActivityMainMenuBinding
import com.example.kanjicardsgo.databinding.ActivityStudyBinding

class StudyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        // Create view binding instance
        binding = ActivityStudyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // CLICK EVENTS
        // Button Study
        binding.buttonvocab.setOnClickListener{
            val intent = Intent(this, SelectDecksActivity::class.java)
            startActivity(intent)
        }


    }
}