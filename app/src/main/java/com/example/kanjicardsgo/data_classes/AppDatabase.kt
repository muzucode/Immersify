package com.example.kanjicardsgo.data_classes

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.kanjicardsgo.data_classes.Deck.*
import com.example.kanjicardsgo.data_classes.Phase.Phase
import com.example.kanjicardsgo.data_classes.Phase.PhaseDao
import com.example.kanjicardsgo.data_classes.Track.Track
import com.example.kanjicardsgo.data_classes.Track.TrackDao
import com.example.kanjicardsgo.data_classes.User.User
import com.example.kanjicardsgo.data_classes.User.UserDao

@Database(entities = arrayOf(

        Deck::class,
        Card::class,
        User::class,
        Track::class,
        Phase::class), version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun deckDao(): DeckDao
    abstract fun cardDao(): CardDao
    abstract fun userDao(): UserDao
    abstract fun trackDao(): TrackDao
    abstract fun phaseDao(): PhaseDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = databaseBuilder(context, AppDatabase::class.java, "firstdb").build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}