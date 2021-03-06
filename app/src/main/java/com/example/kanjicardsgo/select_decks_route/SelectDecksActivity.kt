package com.example.kanjicardsgo.select_decks_route

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.kanjicardsgo.flashcards_route.MainActivity
import com.example.kanjicardsgo.R
import com.example.kanjicardsgo.data_classes.ActiveEnv
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.databinding.ActivitySelectdecksBinding
import com.example.kanjicardsgo.managedecks.ManageDecksActivity
import com.example.kanjicardsgo.managedecks.createdeck.CreateDeckNameActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SelectDecksActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySelectdecksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectdecks)

        // Create view binding instance
        binding = ActivitySelectdecksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create database
        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "firstdb"
        ).build()

        // Instantiate trackDao
        val deckDao = db.deckDao()
        val cardDao = db.cardDao()

        // Create view variables \\
        // List of checkboxes
        val checkboxes: List<CheckBox> = listOf(
                binding.checkBox1,
                binding.checkBox2,
                binding.checkBox3,
                binding.checkBox4,
                binding.checkBox5
        )

        // Set all 5 checkboxes to GONE initially
        for(element in checkboxes){
            element.visibility = CheckBox.GONE
        }

        // Hide the 'no decks found' views initially
        binding.textViewNoDecksFound.visibility = TextView.GONE
        binding.buttonCreateADeck.visibility = Button.GONE
        binding.textViewOrOption.visibility = TextView.GONE
        binding.buttonAddExpressdeck.visibility = Button.GONE

        // Set checkboxes text and visibility based on gotten decks in track
        GlobalScope.launch {
////            // 3 sample decks with 2 cards in each
//            deckDao.insertOne(Deck(null, ActiveEnv.track.tid, "First English Deck"))
//            cardDao.insertOne(Card(null, 1, "Yooooo", "It means yo"))
//            cardDao.insertOne(Card(null, 1, "Yooooo", "It means yo"))
//
//            deckDao.insertOne(Deck(null, ActiveEnv.track.tid, "Second English Deck"))
//            cardDao.insertOne(Card(null, 2, "HIIIII", "It means HI"))
//            cardDao.insertOne(Card(null, 2, "HIIIII", "It means HI"))

            // Get decks in the track
            val trackDecks = deckDao.getAllByTrackId(ActiveEnv.track.tid)

            // Display each checkbox corresponding to a fetched deck in the DB
            if(trackDecks.isNotEmpty()){
                for(i in 0..trackDecks.size - 1){
                    runOnUiThread{
                        checkboxes[i].visibility = CheckBox.VISIBLE
                        checkboxes[i].text = trackDecks[i].name
                    }
                }
            }
            //Show 'no decks found' views if no decks found
            else {

                binding.textViewNoDecksFound.visibility = TextView.VISIBLE
                binding.buttonCreateADeck.visibility = Button.VISIBLE
                binding.textViewOrOption.visibility = TextView.VISIBLE
                binding.buttonAddExpressdeck.visibility = Button.VISIBLE

                // Assign intents to the two buttons
                binding.buttonCreateADeck.setOnClickListener{
                    val i: Intent = Intent(this@SelectDecksActivity, CreateDeckNameActivity::class.java)
                    startActivity(i)
                }
                binding.buttonAddExpressdeck.setOnClickListener{
                    val i: Intent = Intent(this@SelectDecksActivity, SelectExpressDecksActivity::class.java)
                    startActivity(i)
                }
            }

        }


        binding.buttonstudyselected.setOnClickListener{
            val allDeckIds: ArrayList<Int?> = arrayListOf()
            var anyChecked: Boolean = false
            GlobalScope.launch {

                // If checkbox is checked, add deck of the same name to allDecks master deck
                for (element in checkboxes) {
                    if (element.isChecked) {
                        // If checkbox is checked when Study! is pressed, get the deck by
                        // name and trackId and add it to the allDeckIds master
                        val checkedDeck = deckDao.getByNameAndTrackId(element.text.toString(), ActiveEnv.track.tid)
                        allDeckIds.add(checkedDeck.did)

                        anyChecked = true
                    }
                }

                // Only start next activity if there is at least one checked box, else Alert
                if(anyChecked){
                    // Add all checked decks' IDs to the passed data
                    // Cards with these native IDs will be displayed on the next activity
                    val i: Intent = Intent(this@SelectDecksActivity, MainActivity::class.java)
                    i.putIntegerArrayListExtra("deckIds", allDeckIds)
                    startActivity(i)
                }
                else{
                    runOnUiThread{
                        AlertDialog.Builder(this@SelectDecksActivity)
                                .setTitle("Oops!")
                                .setMessage("Please select at least one deck to study")
                                .setPositiveButton(R.string.ok, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show()
                    }
                }
            }
        }


        binding.buttonmanagedecks.setOnClickListener{
            val i: Intent = Intent(this, ManageDecksActivity::class.java)
            startActivity(i)
        }



    }
}