package com.example.kanjicardsgo.track_route

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kanjicardsgo.databinding.ActivitySelectTrackBinding

class SelectTrackActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectTrackBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create view binding instance
        binding = ActivitySelectTrackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddTrack.setOnClickListener{

        }
    }
}