package com.example.kanjicardsgo.data_classes.Deck

import androidx.room.*
import com.example.kanjicardsgo.data_classes.Track.Track
import com.example.kanjicardsgo.data_classes.User.User

// Entities

@Entity
data class Deck(
        @PrimaryKey(autoGenerate = true) val did: Int?,
        val nativeTrackId: Int?,
        val name: String
)


@Entity
data class Card(
        @PrimaryKey(autoGenerate = true) val cid: Int?,
        val nativeDeckId: Int?,
        val term: String,
        val definition: String

)

data class UserWithTracks(
        @Embedded val user: User,
        @Relation(
                parentColumn = "uid",
                entityColumn = "nativeUserId"
        )
        val tracks: List<Track>
)


// Relationships
data class TrackWithDecks(
        @Embedded val track: Track,
        @Relation(
                parentColumn = "tid",
                entityColumn = "nativeTrackId"
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





