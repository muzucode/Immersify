package com.example.kanjicardsgo.managedecks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.kanjicardsgo.R
import com.example.kanjicardsgo.SelectDecksActivity
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.data_classes.Book.Book
import com.example.kanjicardsgo.data_classes.Book.Card
import com.example.kanjicardsgo.data_classes.Book.Deck
import com.example.kanjicardsgo.data_classes.User.User
import com.example.kanjicardsgo.databinding.ActivityCreateDeckBinding
import com.example.kanjicardsgo.databinding.ActivityMainBinding
import com.example.kanjicardsgo.databinding.ActivitySelectdecksBinding
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

        // Create database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "deck"
        ).build()

        // Instantiate deckDao
        val deckDao = db.deckDao()
        val cardDao = db.cardDao()

        // Instantiate qDeck and qCards, the temporary deck under construction
        lateinit var qDeck: Deck
        val qCards: MutableList<Card> = mutableListOf()


        GlobalScope.launch{
            // Insert qDeck into database
            deckDao.insertOne(Deck(null,26,"q"))
            // Save qDeck instance to a variable
            qDeck = deckDao.getByName("q")
        }

        binding.buttonAddCard.setOnClickListener{
            // Set new term and def
            val newTerm = binding.editTextTerm.text.toString()
            val newDefinition = binding.editTextDefinition.text.toString()

            // Add to qCards
            qCards.add(Card(null, qDeck.did, newTerm, newDefinition))
            println(qDeck)
            println(qCards)

            // Reset flashcard
            binding.editTextTerm.setText("")
            binding.editTextDefinition.setText("")
        }

        binding.buttonPublish.setOnClickListener{
            // Convert mutable list to list
            Collections.unmodifiableList(qCards)

            // Save deck name
            val newName = binding.editTextDeckName.text.toString()

            GlobalScope.launch {
                // Insert all cards in q to database
                cardDao.insertAll(qCards)
                // Update qDeck to have desired name
                deckDao.updateName(Deck(qDeck.did,26,newName))

                var allCards = cardDao.getAll()
                println(allCards)
                var newDeck = deckDao.getByName(newName)
                println(newDeck)
            }

            // Send back to select decks activity
            val i = Intent(this, SelectDecksActivity::class.java)
            startActivity(i)
        }



        // lateinit var cardsGotten:List<Card>
        // create lateinit var qDeckId: Deck on start

        // title
        // flashcard term
        // flashcard definition

        // Publish onClick
        // --> check if deck database has a deck with name, error if not
        // --> check if deck has any cards added (thus, related to it)
        // --> if there are no cards related to it

        // Add onClick
        // --> check if both fields are filled out
        // --> check if








    }
}