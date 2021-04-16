package com.example.kanjicardsgo.data_classes.Book

import androidx.room.*
import com.example.kanjicardsgo.data_classes.User.User

// Entities
@Entity
data class Book(
        @PrimaryKey(autoGenerate = true) val bid: Int?,
        val nativeUserId: Int?

)


@Entity
data class Deck(
        @PrimaryKey(autoGenerate = true) val did: Int?,
        val nativeBookId: Int?,
        val name: String
)


@Entity
data class Card(
        @PrimaryKey(autoGenerate = true) val cid: Int?,
        val nativeDeckId: Int?,
        val term: String,
        val definition: String

)

data class UserWithBooks(
        @Embedded val deck: User,
        @Relation(
                parentColumn = "uid",
                entityColumn = "nativeUserId"
        )
        val books: List<Book>
)

// Relationships
data class BookWithDecks(
        @Embedded val book: Book,
        @Relation(
                parentColumn = "bid",
                entityColumn = "nativeBookId"
        )
        val decks: List<Deck>
)

data class DeckWithCards(
        @Embedded val deck: Deck,
        @Relation(
                parentColumn = "did",
                entityColumn = "nativeDeckId"
        )
        val cards: List<Card>
)





