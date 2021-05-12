package com.example.kanjicardsgo.track_route

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.kanjicardsgo.processors.TrackProcessor
import com.example.kanjicardsgo.data_classes.ActiveEnv
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.data_classes.Track.Track
import com.example.kanjicardsgo.databinding.ActivityAddTrackBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTrackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTrackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create view binding instance
        binding = ActivityAddTrackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "firstdb"
        ).build()

        // Instantiate trackDao
        val trackDao = db.trackDao()
        val phaseDao = db.phaseDao()



        binding.buttonEnglish1Track.setOnClickListener{
            // Create track processor
            val tproc = TrackProcessor(applicationContext)
            // Create English 1 track
            tproc.createEnglish1()
            val i: Intent = Intent(this@AddTrackActivity, SelectTrackActivity::class.java)
            startActivity(i)
        }

        binding.buttonJapanese1Track.setOnClickListener{

            // Insert track into DB
            GlobalScope.launch{
                trackDao.insertOne(Track(null, ActiveEnv.user.uid, "Japanese 1", "Japanese", "Course", 0))
            }

            val i: Intent = Intent(this, SelectTrackActivity::class.java)
            startActivity(i)
        }

    }
}