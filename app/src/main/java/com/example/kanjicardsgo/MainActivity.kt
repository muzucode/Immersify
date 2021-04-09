package com.example.kanjicardsgo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kanjicardsgo.databinding.ActivityMainBinding
import org.apache.commons.io.IOUtils
import java.io.InputStream
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.Serializable


class MainActivity : AppCompatActivity() {

    // Class for Kanji Objects
    class Word(var jpn: String, var meaning:String) : Serializable{

    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create view binding instance
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Called when the user taps the Send button */




        // Create variables
        var myTitle = Word("Japanese","Meaning")
        var myWord: Word
        myWord = Word("火","Fire")

        var attempts: Int = 0
        var passes: Int = 0
        var fails: Int = 0
        lateinit var score: String
        val kanjiData = mutableListOf<Word>()

//        Debug Vars
//        val deck_1: Deck = Deck(mutableListOf<Word>())
//        val deck_2: Deck = Deck(mutableListOf<Word>())
//        val deck_3: Deck = Deck(mutableListOf<Word>())
//
//        println(deck_1.id)
//        println(deck_3.id)
//        println(Deck.count)
//        println("HELLO THERE!")


        // Add kanji from res to kanjiData list
        csvReader().open(resources.openRawResource(R.raw.kanji_list)) {
            readAllAsSequence().forEach { row ->
                var newWord = Word(row.elementAt(0),row.elementAt(1))
                kanjiData.add(newWord)

            }
        }





        // Create SessionDeck from intent passed over
        var myDeck: SessionDeck = getIntent().getSerializableExtra("SessionDeck") as SessionDeck;

        // Update card
        fun newCard(): Unit {

            // Load kanjiData at start ===================
            // Create sessionDeck =================
            // Randomly load card from sessionDeck ==============
            // Remove loaded card from sessionDeck after loading
            // Randomly load another card in the sessionDeck
            // Update views with kanjiData card contents
            var newWord = myDeck.newCard()

            binding.kanC.text = newWord.jpn
            binding.engC.text = newWord.meaning

            attempts += 1
        }
        // Update score
        fun updateScore(): Unit {
            score = "$passes/$attempts"
            binding.score.text = score
        }

        fun newSession(): Unit {

            // Reset the session deck to contain all cards
            myDeck.newSession()
            // Load a new card
            var newWord = myDeck.newCard()

            binding.kanC.text = newWord.jpn

            binding.engC.text = newWord.meaning


            passes = 0
            fails = 0
            attempts = 0
            binding.score.visibility= View.GONE
            updateScore()
        }

        // Set views' contents

        binding.buttonpass.setOnClickListener{
            newCard()
            passes += 1
            updateScore()
            binding.score.visibility= View.VISIBLE
        }
        binding.buttonfail.setOnClickListener{
            newCard()
            fails += 1
            updateScore()
            binding.score.visibility= View.VISIBLE
        }
        binding.buttonunsure.setOnClickListener{
            newCard()
            fails += 1
            updateScore()
            binding.score.visibility= View.VISIBLE
        }
        binding.buttonrestart.setOnClickListener{
            newSession()
        }


        // Create inputstream from raw res file and convert to string
        val textfile: InputStream = resources.openRawResource(R.raw.my_file)
        val theString: String = IOUtils.toString(textfile, "UTF-8")
        Log.d("myString", theString)





    }


}