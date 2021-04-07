package com.example.kanjicardsgo

open class Deck constructor(val deck: MutableList<MainActivity.Word>) {

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