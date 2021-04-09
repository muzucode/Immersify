package com.example.kanjicardsgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import com.example.kanjicardsgo.databinding.ActivitySelectdecksBinding

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

        // CLICK EVENTS
        // Button Study
        binding.buttonstudyselected.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            val sessionDeck = DeckProcessor.combineDecks(checkboxes, resources)
            intent.putExtra("SessionDeck", sessionDeck)
            startActivity(intent)
        }


    }
}