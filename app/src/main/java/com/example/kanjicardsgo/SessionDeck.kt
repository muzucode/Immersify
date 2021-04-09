package com.example.kanjicardsgo


import java.io.Serializable
import java.util.*
import kotlin.math.roundToInt

class SessionDeck(deck: MutableList<MainActivity.Word>): Deck(deck), Serializable{
    private var active = deck.toMutableList()

    // Drops inputted card
    fun drop(word: MainActivity.Word){
        active.removeAt(active.indexOf(word))
    }

    // Load new card, calls drop() after loading
    fun newCard(): MainActivity.Word {

        // "Finished screen once zero cards in active deck"
        if(active.size == 0){
            return MainActivity.Word("Finished", "Finished")
        }

        // Create rand number
        val rand = Random()
        val rn = rand.nextInt(active.size)

        // Store the randomly selected card
        val word = active.elementAt(rn)
        // Remove the randomly selected card from the main deck
        this.drop(active.elementAt(rn))
        // Return the randomly selected card
        println(deck)
        println(active)
        return word
    }

    // Resets deck to all original cards
    fun newSession(){
        active = deck.toMutableList()
    }


}