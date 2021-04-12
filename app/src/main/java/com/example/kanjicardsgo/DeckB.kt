package com.example.kanjicardsgo

import java.io.Serializable

open class DeckB constructor(val deck: MutableList<MainActivity.Card>): Serializable {

    // Companion
    companion object{
        var count: Int = 0
    }

    // Initialization
    init {
        count++
    }

    // Extra Properties
    var id: Int = count
    var name: String = "Deck $id"


}