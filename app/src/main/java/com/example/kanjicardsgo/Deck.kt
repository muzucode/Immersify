package com.example.kanjicardsgo

import java.io.Serializable

open class Deck constructor(val deck: MutableList<MainActivity.Word>): Serializable {

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