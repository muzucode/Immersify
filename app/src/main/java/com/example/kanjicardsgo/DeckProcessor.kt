package com.example.kanjicardsgo

import android.content.res.Resources
import android.widget.CheckBox
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

class DeckProcessor {

    companion object{

        fun combineDecks(checkboxes: List<CheckBox>, resources: Resources): SessionDeck {

            var sessionDeckData = mutableListOf<MainActivity.Word>()

            fun createSessionDeck(sessionDeckData: MutableList<MainActivity.Word>): SessionDeck{
                val sessionDeck = SessionDeck(sessionDeckData)
                // Returns Session Deck instance
                return sessionDeck
            }

            // i is locally passed
            fun readCSV(i: Int){
                // Get R.id of checked deck
                var rawResID = resources.getIdentifier("jlpt_n${i+1}", "raw", BuildConfig.APPLICATION_ID);

                // Add all entries from R csv to deckData
                csvReader().open(resources.openRawResource(rawResID)) {
                    readAllAsSequence().forEach { row ->
                        var word = MainActivity.Word(row.elementAt(0), row.elementAt(1))
                        sessionDeckData.add(word)
                    }
                }
            }

            // Find all checked boxes
            fun getCheckedAndRead(){
                for (i in 0..4) {
                    if(checkboxes[i].isChecked){
                        println("Deck ${i}, JLPT N${4-i+1} has been added to the session deck")
                        readCSV(i)
                    }
                }
            }

            getCheckedAndRead()
            val sessionDeck: SessionDeck = createSessionDeck(sessionDeckData)

            return sessionDeck
        }


    }


}