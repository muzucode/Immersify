package com.example.kanjicardsgo.flashcards_route

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.data_classes.Deck.Card
import com.example.kanjicardsgo.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create view binding instance
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create database
        val db = AppDatabase.getInstance(this)

        // Instantiate trackDao
        val cardDao = db.cardDao()

        // Set score as INVISIBLE by default
        binding.score.visibility = TextView.INVISIBLE

        // Get designated deck IDs from prior activity
        val selectedDeckIds = intent.getIntegerArrayListExtra("deckIds")
        var sessionCards: MutableList<Card> = mutableListOf()
        var sessionCardsInit: List<Card> = listOf()

        // Generate random number between 0 and # of cards in sessionCards
        var rand = Random()
        var randNum: Int = 0
        var attempts: Int = 0
        var passes: Int = 0
        var fails: Int = 0
        lateinit var score: String

        // Get all passed cards and save to sessionCards variable
        GlobalScope.launch{
            // If deckIds are not null,
            if (selectedDeckIds != null) {
                println(selectedDeckIds)
                for(id in selectedDeckIds){
                    // Get all cards and save to sessionCards
                    val fetchedCards: List<Card> = cardDao.getByDeckId(id)
                    sessionCards.addAll(fetchedCards)
                    // Save an initial version of sessionCards that is immutable
                    sessionCardsInit = sessionCards.toList()
                    println("DECK ID IS $id")
                }
            }
        }


        // Update card
        fun newCard(): Unit {

            // Display score after the initial pass/fail
            binding.score.visibility = TextView.VISIBLE


            // Generate random number between 0 and # of cards in sessionCards
            if(sessionCards.size >= 2){
                randNum = rand.nextInt(sessionCards.size-1)
                println("The size of the sessionCards deck is: ${sessionCards.size}")
            }
            else {
                randNum = 0
            }

            // Set term and definition to randomly selected card
            // Remove card from sessionCards deck
            if(sessionCards.isNotEmpty()){
                binding.cardTerm.text = sessionCards[randNum].term
                binding.cardDefinition.text = sessionCards[randNum].definition
                sessionCards.removeAt(randNum)
            }
            else {
                binding.cardTerm.text = "End of deck"
                binding.cardDefinition.text = "End of deck"
            }

            attempts += 1
        }


        // Update score
        fun updateScore(): Unit {
            score = "$passes/$attempts"
            binding.score.text = score
        }


        fun newSession(): Unit {

            // Set mutable sessionCards variable to original version for a deck reset
            sessionCards = sessionCardsInit.toMutableList()

            // Load new card (the initial card)
            newCard()

            passes = 0
            fails = 0
            attempts = 0

            // Update score with 0s
            updateScore()

            // Hide score marker on new game until pass/fail clicked
            binding.score.visibility = TextView.INVISIBLE

        }


        // Set views' contents
        binding.buttonpass.setOnClickListener{
            newCard()
            passes += 1
            updateScore()
        }
        binding.buttonfail.setOnClickListener{
            newCard()
            fails += 1
            updateScore()
        }
        binding.buttonunsure.setOnClickListener{
            newCard()
            fails += 1
            updateScore()
        }
        binding.buttonrestart.setOnClickListener{
            newSession()
        }

    }


}