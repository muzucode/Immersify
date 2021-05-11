package com.example.kanjicardsgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.room.Room
import com.example.kanjicardsgo.data_classes.ActiveEnv
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.data_classes.Phase.Phase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PhaseMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase_menu)

        // Create database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "firstdb"
        ).build()

        // Instantiate trackDao
        val trackDao = db.trackDao()
        val phaseDao = db.phaseDao()

        // Save phase buttons to a list
        val phaseButtons: List<Button> = listOf(
            findViewById(R.id.buttonPhase1),
            findViewById(R.id.buttonPhase2),
            findViewById(R.id.buttonPhase3),
            findViewById(R.id.buttonPhase4),
            findViewById(R.id.buttonPhase5),
            findViewById(R.id.buttonPhase6),
            findViewById(R.id.buttonPhase7),
            findViewById(R.id.buttonPhase8),
            findViewById(R.id.buttonPhase9),
            findViewById(R.id.buttonPhase10),
        )

        // Set all phase buttons as gone visibility
        for(button in phaseButtons){
            button.visibility = Button.GONE
        }

        GlobalScope.launch{


            val gottenPhases: List<Phase> = phaseDao.getAllPhasesInTrack(ActiveEnv.track.tid)

            runOnUiThread{
                for(i in 0..gottenPhases.size-1){
                    // Set phase button name
                    phaseButtons[i].text = gottenPhases[i].name
                    // Set phase button visibility
                    phaseButtons[i].visibility = Button.VISIBLE
                }
            }

        }


        // Get number of phases in the track
        // Get names of phases for each button
        // For loop each button and show buttons = phaseNumber.size
    }
}