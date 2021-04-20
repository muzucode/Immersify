package com.example.kanjicardsgo.managedecks.createdeck

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.kanjicardsgo.SelectDecksActivity
import com.example.kanjicardsgo.data_classes.ActiveEnv
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.data_classes.Deck.Card
import com.example.kanjicardsgo.data_classes.Deck.Deck
import com.example.kanjicardsgo.databinding.ActivityCreateDeckBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class CreateDeckActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateDeckBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Create view binding instance
        binding = ActivityCreateDeckBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Get deck name created on previous activity
        val newName = intent.getStringExtra("deckName").toString();


        // Create database
        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "firstdb"
        ).build()


        // Instantiate deckDao
        val deckDao = db.deckDao()
        val cardDao = db.cardDao()


        // Instantiate qDeck and qCards, the temporary deck under construction
        lateinit var qDeck: Deck
        val qCards: MutableList<Card> = mutableListOf()



        // Coroutine - Insert a deck, init qDeck
        GlobalScope.launch{
            // Insert qDeck into database
            deckDao.insertOne(Deck(null, ActiveEnv.track.tid, "q"))
            // Save qDeck instance to a variable
            qDeck = deckDao.getByName("q")
        }

        // Add card button event
        binding.buttonAddCard.setOnClickListener{
            // Save new term and definition
            val newTerm = binding.editTextTerm.text.toString()
            val newDefinition = binding.editTextDefinition.text.toString()

            // Add to qCards staging cardset
            // Check to make sure both fields are filled out
            if(newTerm != "" && newDefinition != ""){
                qCards.add(Card(null, qDeck.did, newTerm, newDefinition))
                println(qDeck)
                println(qCards)

                // Reset flashcard
                binding.editTextTerm.setText("")
                binding.editTextDefinition.setText("")
                fun updateCardCount(){
                    binding.textViewFlashcardCount.text = "Number of cards: ${qCards.size}"
                }
                updateCardCount()
            }
            else {
                if(newTerm == "" || newDefinition == ""){
                    println("Please be sure to fill out both fields in the flashcard!")
                }
            }



        }

        // Publish deck button event
        binding.buttonPublish.setOnClickListener{
            // Convert mutable list to list
            qCards.toList()

            // Make sure cards list is not null
            if(qCards.size > 0){
                // Coroutine - Insert qCards cardset into Card db & update deck name
                GlobalScope.launch {
                    // Insert all cards in q to database
                    cardDao.insertAll(qCards)
                    // Update qDeck to have desired name
                    deckDao.updateName(Deck(qDeck.did, ActiveEnv.track.tid, newName))

                    // Print all the cards in Card db
                    var allCards = cardDao.getAll()
                    println(allCards)
                    // Print the deck with the name they inputted
                    var newDeck = deckDao.getByName(newName)
                    println(newDeck)
                }

                // Start SelectDecks activity
                val i = Intent(this, SelectDecksActivity::class.java)
                startActivity(i)
            }
            else {
                println("Decks must contain at least one flashcard.")
            }
        }
    }

}