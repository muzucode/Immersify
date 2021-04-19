package com.example.kanjicardsgo.track_route

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kanjicardsgo.R
import com.example.kanjicardsgo.databinding.ActivityAddTrackBinding
import com.example.kanjicardsgo.databinding.ActivitySelectTrackBinding

class AddTrackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTrackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_track)
    }
}