package com.example.kanjicardsgo.processors

import android.content.Context
import androidx.room.Room
import com.example.kanjicardsgo.R
import com.example.kanjicardsgo.data_classes.ActiveEnv
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.data_classes.Phase.Phase
import com.example.kanjicardsgo.data_classes.Section.Section
import com.example.kanjicardsgo.data_classes.Track.Track
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TrackProcessor(appContext: Context) {

    // Create database
    private val db = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java, "firstdb"
    ).build()

    // Instantiate trackDao
    private val trackDao = db.trackDao()
    private val phaseDao = db.phaseDao()
    private val sectionDao = db.sectionDao()

    fun createEnglish1(): Unit{
        GlobalScope.launch{
            // Insert track and generate primary key id, set name to "q"
            trackDao.insertOne(Track(null, ActiveEnv.user.uid, "q", "English", "Course", 0))
            // Get generated id from "q"-named track
            val trackId: Int? = trackDao.getByName("q").tid
            // Set track's name to original name, have to designate the primary key here!
            trackDao.updateTrackName(Track(trackId, ActiveEnv.user.uid, "English 1", "English", "Course", 0))
            // Insert phase with corresponding nativeTrackId
            val insertPhasesList: List<Phase> = listOf(
                Phase(null, trackId, "Alphabet", 0, 0.0, R.drawable.ic_baseline_30fps_24),
                Phase(null, trackId, "Numbers", 1, 0.0, R.drawable.ic_baseline_30fps_24),
                Phase(null, trackId, "Family", 2, 0.0, R.drawable.ic_baseline_30fps_24),
                Phase(null, trackId, "Tenses I", 3, 0.0, R.drawable.ic_baseline_30fps_24)
            )
            // Insert all phases
            phaseDao.insertAll(insertPhasesList)
            // Insert all sections by phase name
            sectionDao.insertOne(Section(null, "Alphabet", "Vowels", 0.0))
            sectionDao.insertOne(Section(null, "Alphabet", "Consonants", 0.0))
            sectionDao.insertOne(Section(null, "Alphabet", "The alphabet", 0.0))
            sectionDao.insertOne(Section(null, "Alphabet", "Sounds", 0.0))

        }

    }
}