package com.example.kanjicardsgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import com.example.kanjicardsgo.databinding.ActivitySelectdecksBinding
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.Serializable

class SelectDecksActivity : AppCompatActivity(){


    private lateinit var binding: ActivitySelectdecksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectdecks)


        // Create view binding instance
        binding = ActivitySelectdecksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create variables \\
        // List of checkboxes
        val checkboxes: List<CheckBox> = listOf(
                binding.checkBox1,
                binding.checkBox2,
                binding.checkBox3,
                binding.checkBox4,
                binding.checkBox5
        )
        // Mutable List of Words (Master Session Deck)
        val sessionDeckData = mutableListOf<MainActivity.Word>()



        // BELOW: PrepareSession Interface
        // Create a session deck to be passed to the next activity
        fun createSessionDeck(): SessionDeck{
            val sessionDeck = SessionDeck(sessionDeckData)
            // Returns Session Deck instance
            return sessionDeck
        }

        // Read every checked CSV to master deckData
        fun readCSV(i: Int){
            // Get R.id of checked deck
            var rawResID = resources.getIdentifier("jlpt_n${i+1}", "raw", getPackageName());

            // Add all entries from R csv to deckData
            csvReader().open(resources.openRawResource(rawResID)) {
                readAllAsSequence().forEach { row ->
                    var word = MainActivity.Word(row.elementAt(0), row.elementAt(1))
                    sessionDeckData.add(word)
                }
            }
        }

        // Find all checked boxes
        fun getChecked(){
            lateinit var csvPaths: MutableList<String>

            for (i in 0..4) {
                if(checkboxes[i].isChecked)
                    println("Deck $i, JLPT N${i+1} has been added to the session deck")
                    readCSV(i)
            }
        }



//        getChecked()





        // CLICK EVENTS
        // Button Study
        binding.buttonstudyselected.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            getChecked()
            val sessionDeck = createSessionDeck()
            intent.putExtra("SessionDeck", sessionDeck)
            startActivity(intent)
        }


    }
}