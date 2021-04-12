package com.example.kanjicardsgo


import java.util.*

class SessionDeckB(deck: MutableList<MainActivity.Card>): DeckB(deck){
    private var active = deck.toMutableList()

    // Drops inputted card
    fun drop(card: MainActivity.Card){
        active.removeAt(active.indexOf(card))
    }

    // Load new card, calls drop() after loading
    fun newCard(): MainActivity.Card {

        // "Finished screen once zero cards in active deck"
        if(active.size == 0){
            return MainActivity.Card("Finished", "Finished")
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