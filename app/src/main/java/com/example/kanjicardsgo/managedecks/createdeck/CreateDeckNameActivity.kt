package com.example.kanjicardsgo.managedecks.createdeck

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kanjicardsgo.R
import com.example.kanjicardsgo.databinding.ActivityCreateDeckBinding
import com.example.kanjicardsgo.databinding.ActivityCreateDeckNameBinding

class CreateDeckNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateDeckNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create view binding instance
        binding = ActivityCreateDeckNameBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.buttonConfirmDeckName.setOnClickListener(){

            val deckName: String = binding.editTextCreateDeckName.text.toString()

            // Make sure deck name is not null or less than 3 chars
            if(deckName.length > 2){
                val i: Intent = Intent(this, CreateDeckActivity::class.java)
                i.putExtra("deckName", deckName);
                startActivity(i)
            }
            else {
                AlertDialog.Builder(this@CreateDeckNameActivity)
                    .setTitle("Oops!")
                    .setMessage("Deck name must be at least 3 characters in length.")
                    .setPositiveButton(R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }



        }

    }
}