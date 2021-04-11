package com.example.kanjicardsgo

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.kanjicardsgo.databinding.ActivitySelectdecksBinding
import com.example.kanjicardsgo.managedecks.ManageDecksActivity


class SelectDecksActivity : AppCompatActivity(){



    private lateinit var binding: ActivitySelectdecksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectdecks)

        // Create view binding instance
        binding = ActivitySelectdecksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create view variables \\
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
            if(!DeckProcessor.isEmpty(checkboxes)){
                val intent = Intent(this, MainActivity::class.java)
                val sessionDeck = DeckProcessor.combineDecks(checkboxes, resources)
                intent.putExtra("SessionDeck", sessionDeck)
                startActivity(intent)
            }

            else {
                AlertDialog.Builder(this@SelectDecksActivity)
                    .setTitle("No decks selected")
                    .setMessage("Please select at least one deck to study.")
                    .setPositiveButton(R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        }


        binding.buttonmanagedecks.setOnClickListener{
            val i: Intent = Intent(this, ManageDecksActivity::class.java)
            startActivity(i)
        }



    }
}