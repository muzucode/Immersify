package com.example.kanjicardsgo.learn_route

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.room.Room
import com.example.kanjicardsgo.R
import com.example.kanjicardsgo.data_classes.AppDatabase
import com.example.kanjicardsgo.data_classes.Section.Section
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SectionsMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sections_menu)

        // Get phaseName intent
        val phaseName: String? = intent.getStringExtra("phaseName")

        // Create database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "firstdb"
        ).build()

        val sectionDao = db.sectionDao()

        GlobalScope.launch{
            // Create list of section name textviews
            val sectionNameTextViewList: List<TextView> = listOf(
                findViewById<TextView>(R.id.textViewSection1),
                findViewById<TextView>(R.id.textViewSection2),
                findViewById<TextView>(R.id.textViewSection3),
                findViewById<TextView>(R.id.textViewSection4),
                findViewById<TextView>(R.id.textViewSection5),
                )

            // Get sections in the phase based on phaseName intent extra
            val gottenSections: List<Section> = sectionDao.getAllByPhaseName(phaseName)

            // Assign gotten section names
            for(i in 0..gottenSections.size - 1){
                sectionNameTextViewList[i].text = gottenSections[i].name
            }
        }



    }
}