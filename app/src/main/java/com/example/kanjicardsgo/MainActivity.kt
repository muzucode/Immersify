package com.example.kanjicardsgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kanjicardsgo.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    // Class for Kanji Objects
    class Word(var eng: String, var jpn: String, var meaning:String){

    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var myrand: Random




    fun rand(num: Int): String {
        return (0..num).random().toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create variables
        var myTitle = Word("English","Japanese","Meaning")
        var myWord: Word
        myWord = Word("Hi","火","Fire")
        var attempts: Int = 0
        var passes: Int = 0
        var fails: Int = 0
        lateinit var score: String

        // read from `File`
//        var fileName: String = "./kanji_sm.csv"
//        var file: File = File(fileName)
//        val rows: List<List<String>> = csvReader().readAll(file)
//        print(rows)

        // Create view binding instance
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Update card
        fun newCard(): Unit {
            //Refresh the card once the button is clicked and pull up a random card
            //Create a pass stack, and a fail stack

            binding.kanC.text = "水"
            binding.engC.text = "Water"
            attempts += 1
        }
        // Update score
        fun updateScore(): Unit {
            score = "$passes/$attempts"
            binding.score.text = score
        }

        fun newSession(): Unit {
            passes = 0
            fails = 0
            attempts = 0
            binding.score.visibility= View.GONE
            updateScore()
        }

        // Set views' contents
        binding.kanT.text = "Kanji:"
        binding.kanC.text = "火"
        binding.engT.text = "Meaning:"
        binding.engC.text = "Fire"
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


    }


}