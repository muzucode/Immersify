package com.example.kanjicardsgo.data_classes

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kanjicardsgo.data_classes.Book.*
import com.example.kanjicardsgo.data_classes.User.User
import com.example.kanjicardsgo.data_classes.User.UserDao

@Database(entities = arrayOf(
        Book::class,
        Deck::class,
        Card::class,
        User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun deckDao(): DeckDao
    abstract fun cardDao(): CardDao
    abstract fun userDao(): UserDao
}