package com.example.kanjicardsgo.managedecks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.kanjicardsgo.R
import com.example.kanjicardsgo.data_classes.ActiveEnv
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.databinding.ActivityManageDecksBinding
import com.example.kanjicardsgo.managedecks.createdeck.CreateDeckActivity
import com.example.kanjicardsgo.managedecks.createdeck.CreateDeckNameActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ManageDecksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageDecksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageDecksBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Create database
        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "firstdb"
        ).build()


        // Instantiate trackDao
        val deckDao = db.deckDao()

        val numOfDecks: Int
        // Find number of decks in the track, hard limit of 5 per track
        GlobalScope.launch{
            val numOfDecks = deckDao.getAllByTrackId(ActiveEnv.track.tid).size
            // Make button unclickable if there are at least 5 decks in the track
            if(numOfDecks >= 5){
                binding.buttonCreateDeck.isClickable = false
                binding.buttonCreateDeck.text = "Deck limit reached (Max : 5)"
            }

        }

        binding.buttonCreateDeck.setOnClickListener{
            val i: Intent = Intent(this, CreateDeckNameActivity::class.java)
            startActivity(i)
        }

        


    }
}