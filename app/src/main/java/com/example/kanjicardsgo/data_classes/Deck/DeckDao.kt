package com.example.kanjicardsgo.data_classes.Deck

import androidx.room.*
import com.example.kanjicardsgo.data_classes.Track.Track


@Dao
interface DeckDao {

    @Query("SELECT * FROM deck WHERE did IN (:deckId)")
    suspend fun getById(deckId: Int?): Deck

    @Query("SELECT * FROM deck WHERE name IN (:deckName)")
    suspend fun getByName(deckName: String?): Deck

    @Query("SELECT * FROM deck WHERE nativeTrackId IN (:trackId)")
    suspend fun getAllByTrackId(trackId: Int?): List<Deck>

    @Query("SELECT * FROM deck WHERE name IN (:deckName) AND nativeTrackId IN (:trackId)")
    suspend fun getByNameAndTrackId(deckName: String, trackId: Int?): Deck

    @Query("SELECT * FROM deck")
    suspend fun getAll(): List<Deck>

    @Query("SELECT MAX(did) FROM deck")
    suspend fun getMaxId(): Int?

    @Update
    suspend fun updateName(deck: Deck)

    @Insert
    suspend fun insertOne(deck: Deck)

    @Insert
    suspend fun insertAll(decks: List<Deck>)


}

@Dao
interface CardDao {

    @Query("SELECT * FROM card WHERE cid IN (:cardId)")
    suspend fun getById(cardId: Long): Card

    @Query("SELECT * FROM card")
    suspend fun getAll(): List<Card>

    @Query("SELECT * FROM card WHERE nativeDeckId IN (:deckId)")
    suspend fun getByDeckId(deckId: Int?): List<Card>

    @Insert
    suspend fun insertOne(card: Card)

    @Insert
    suspend fun insertAll(cards: List<Card>)



}