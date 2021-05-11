package com.example.kanjicardsgo.select_decks_route

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import androidx.room.Room
import com.example.kanjicardsgo.flashcards_route.MainActivity
import com.example.kanjicardsgo.R
import com.example.kanjicardsgo.data_classes.ActiveEnv
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.data_classes.Deck.Card
import com.example.kanjicardsgo.data_classes.Deck.Deck
import com.example.kanjicardsgo.databinding.ActivitySelectExpressDecksBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SelectExpressDecksActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectExpressDecksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create view binding instance
        binding = ActivitySelectExpressDecksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val checkboxes: List<CheckBox> = listOf(
            binding.checkBox1,
            binding.checkBox2,
            binding.checkBox3,
            binding.checkBox4,
            binding.checkBox5
        )

        // Create database
        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "firstdb"
        ).build()

        // Instantiate deckDao
        val deckDao = db.deckDao()
        val cardDao = db.cardDao()

        // Create list of express decks
        val expressDeckIds: ArrayList<Int?> = arrayListOf<Int?>()

        // Add deck ids 7000, 7001, 7002, 7003, 7004 depending on checked boxes
        GlobalScope.launch{
//            // 5 sample decks with 2 cards in each
            deckDao.insertOne(Deck(7000, ActiveEnv.track.tid, "First English Deck"))
            cardDao.insertOne(Card(null, 7000, "Yooooo", "It means yo"))
            cardDao.insertOne(Card(null, 7000, "Yooooo", "It means yo"))

            deckDao.insertOne(Deck(7001, ActiveEnv.track.tid, "Second English Deck"))
            cardDao.insertOne(Card(null, 7001, "HIIIII", "It means HI"))
            cardDao.insertOne(Card(null, 7001, "HIIIII", "It means HI"))

            deckDao.insertOne(Deck(7002, ActiveEnv.track.tid, "First English Deck"))
            cardDao.insertOne(Card(null, 7002, "Yooooo", "It means yo"))
            cardDao.insertOne(Card(null, 7002, "Yooooo", "It means yo"))

            deckDao.insertOne(Deck(7003, ActiveEnv.track.tid, "Second English Deck"))
            cardDao.insertOne(Card(null, 7003, "HIIIII", "It means HI"))
            cardDao.insertOne(Card(null, 7003, "HIIIII", "It means HI"))

            deckDao.insertOne(Deck(7004, ActiveEnv.track.tid, "Second English Deck"))
            cardDao.insertOne(Card(null, 7004, "HIIIII", "It means HI"))
            cardDao.insertOne(Card(null, 7004, "HIIIII", "It means HI"))
        }

        binding.buttonAddExpressDecksToList.setOnClickListener{
            var anyChecked: Boolean = false
            // If checkbox is checked, add the corresponding 7000-based express deck id
            var count: Int = 0
            for(box in checkboxes){
                if(box.isChecked){
                    expressDeckIds.add(7000 + count)
                    anyChecked = true
                }
                count +=1
            }

            // Only start next activity if there is at least one checked box, else Alert
            if(anyChecked){

                // Pass selected deck ids to the main activity
                println(expressDeckIds)
                val i: Intent = Intent(this, MainActivity::class.java)
                i.putIntegerArrayListExtra("deckIds", expressDeckIds)
                startActivity(i)

            }
            else {
                runOnUiThread{
                    AlertDialog.Builder(this)
                            .setTitle("Oops!")
                            .setMessage("Please select at least one deck to study")
                            .setPositiveButton(R.string.ok, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show()
                }
            }
        }
    }
}