package com.example.kanjicardsgo

import java.util.*
import kotlin.math.roundToInt

class SessionDeck(var deck: MutableList<MainActivity.Word>) {
    var active = deck

    // Drops inputted card
    fun drop(word: MainActivity.Word){
        println(active)
        this.active.removeAt(this.active.indexOf(word))
        println(active)
    }
    fun newCard(): MainActivity.Word {
        val rand = Random()
        val rn = rand.nextInt(active.size)

        // Store the randomly selected card
        val word = active.elementAt(rn)
        // Remove the randomly selected card from the main deck
        this.drop(active.elementAt(rn))
        // Return the randomly selected card
        return word
    }

    // Resets deck to all original cards
    fun reset(){
        this.active = deck
    }


}