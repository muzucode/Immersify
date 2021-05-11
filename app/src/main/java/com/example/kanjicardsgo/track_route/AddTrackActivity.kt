package com.example.kanjicardsgo.track_route

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.kanjicardsgo.data_classes.ActiveEnv
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.data_classes.Phase.Phase
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

            // Insert track into DB
            GlobalScope.launch{
                // Insert track and generate primary key id, set name to "q"
                trackDao.insertOne(Track(null, ActiveEnv.user.uid, "q", "English", "Course", 0))
                // Get generated id from "q"-named track
                val trackId: Int? = trackDao.getByName("q").tid
                // Set track's name to original name, have to designate the primary key here!
                trackDao.updateTrackName(Track(trackId, ActiveEnv.user.uid, "English 1", "English", "Course", 0))
                // Insert phase with corresponding nativeTrackId
                phaseDao.insertOne(Phase(null, trackId, "Alphabet", 0, 0.0))
                phaseDao.insertOne(Phase(null, trackId, "Numbers", 1, 0.0))
                phaseDao.insertOne(Phase(null, trackId, "Family", 2, 0.0))
                phaseDao.insertOne(Phase(null, trackId, "Tenses I", 3, 0.0))

                val i: Intent = Intent(this@AddTrackActivity, SelectTrackActivity::class.java)
                startActivity(i)
            }



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