package com.example.kanjicardsgo.data_classes.Book

import androidx.room.*

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    suspend fun getAll(): List<Book>

    @Query("SELECT * FROM book WHERE bid IN (:bookId)")
    suspend fun getById(bookId: Int?): Book

    @Insert
    suspend fun insertOne(book: Book)


//    @Query("SELECT * FROM book WHERE bid IN (:bookIds)")
//    suspend fun loadAllByIds(bookIds: IntArray): List<Book>
//
//    @Insert
//    suspend fun insertAll(vararg books: Book)
//
//    @Delete
//    suspend fun delete(book: Book)
//
//    @Transaction
//    @Query("SELECT * FROM User")
//    fun getBookWithDecks(): List<BookWithDecks>
//
//    @Transaction
//    @Query("SELECT * FROM User")
//    fun getDeckWithCards(): List<DeckWithCards>


//    @Query("SELECT bid FROM book")
//    suspend fun getAll(): List<Book>


}

@Dao
interface DeckDao {

    @Query("SELECT * FROM deck WHERE did IN (:deckId)")
    suspend fun getById(deckId: Int?): Deck

    @Query("SELECT * FROM deck WHERE name IN (:deckName)")
    suspend fun getByName(deckName: String?): Deck

    @Query("SELECT * FROM deck")
    suspend fun getAll(): List<Deck>

    @Query("SELECT MAX(did) FROM deck")
    suspend fun getMaxId(): Int?

    @Query("SELECT * FROM deck WHERE nativeBookId IN (:bookId)")
    suspend fun getDecksByBook(bookId: Int?): List<Deck>

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

    @Insert
    suspend fun insertOne(card: Card)

    @Insert
    suspend fun insertAll(cards: List<Card>)

}