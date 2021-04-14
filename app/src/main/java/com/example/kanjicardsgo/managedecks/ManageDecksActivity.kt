package com.example.kanjicardsgo.managedecks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kanjicardsgo.R
import com.example.kanjicardsgo.databinding.ActivityManageDecksBinding
import com.example.kanjicardsgo.managedecks.createdeck.CreateDeckActivity
import com.example.kanjicardsgo.managedecks.createdeck.CreateDeckNameActivity

class ManageDecksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageDecksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_decks)

        binding = ActivityManageDecksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCreateDeck.setOnClickListener{
            val i: Intent = Intent(this, CreateDeckNameActivity::class.java)
            startActivity(i)
        }
    }
}