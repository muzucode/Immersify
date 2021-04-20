package com.example.kanjicardsgo.data_classes

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kanjicardsgo.data_classes.Deck.*
import com.example.kanjicardsgo.data_classes.Track.Track
import com.example.kanjicardsgo.data_classes.Track.TrackDao
import com.example.kanjicardsgo.data_classes.User.User
import com.example.kanjicardsgo.data_classes.User.UserDao

@Database(entities = arrayOf(

        Deck::class,
        Card::class,
        User::class,
        Track::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun deckDao(): DeckDao
    abstract fun cardDao(): CardDao
    abstract fun userDao(): UserDao
    abstract fun trackDao(): TrackDao
}