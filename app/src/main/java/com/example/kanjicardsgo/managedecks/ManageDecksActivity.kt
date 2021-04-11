package com.example.kanjicardsgo.managedecks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kanjicardsgo.R
import com.example.kanjicardsgo.databinding.ActivityManageDecksBinding
import com.example.kanjicardsgo.databinding.ActivitySelectdecksBinding

class ManageDecksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageDecksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_decks)

        binding = ActivityManageDecksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCreateDeck.setOnClickListener{
            val i: Intent = Intent(this, CreateDeckActivity::class.java)
            startActivity(i)
        }
    }
}