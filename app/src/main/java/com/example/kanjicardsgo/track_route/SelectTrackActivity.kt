package com.example.kanjicardsgo.track_route

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.kanjicardsgo.MainMenuActivity
import com.example.kanjicardsgo.data_classes.ActiveEnv
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.data_classes.Track.Track
import com.example.kanjicardsgo.databinding.ActivitySelectTrackBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SelectTrackActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectTrackBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create view binding instance
        binding = ActivitySelectTrackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "firstdb"
        ).build()

        // Instantiate trackDao
        val trackDao = db.trackDao()

        // Save list of all the bound track buttons
        val tracks: List<Button> =
            listOf(
                binding.buttonTrack1,
                binding.buttonTrack2,
                binding.buttonTrack3,
                binding.buttonTrack4,
                binding.buttonTrack5,
            )

        // Set the track placeholders to gone visibility
        for(element in tracks){
            element.visibility = Button.GONE
        }

        // Create 4 tracks with ActiveEnv user ID
        GlobalScope.launch {
//            trackDao.insertOne(Track(null, ActiveEnv.userId, "English 1", "English", "Course", 0))
//            trackDao.insertOne(Track(null, ActiveEnv.userId, "Japanese 1", "English","Course", 0))

            // Get all tracks for the ActiveEnv user
            val userTracks = trackDao.getAllByUserId(ActiveEnv.userId)

            // Display all tracks as buttons
            runOnUiThread{
                for(i in 0..userTracks.size-1){
                    tracks[i].visibility = Button.VISIBLE
                    tracks[i].text = userTracks[i].name
                    tracks[i].setOnClickListener{

                        // Set global track Id
                        ActiveEnv.trackId = userTracks[i].tid

                    // Event to navigate to main menu
                    val intent: Intent = Intent(this@SelectTrackActivity, MainMenuActivity::class.java)
                    startActivity(intent)
                    }
                }
            }
        }




        // 1.) getAllByUserId







        binding.buttonAddTrack.setOnClickListener{
            val i: Intent = Intent(this, AddTrackActivity::class.java)
            startActivity(i)
        }






    }
}