package com.example.kanjicardsgo.data_classes

import com.example.kanjicardsgo.data_classes.Track.Track
import com.example.kanjicardsgo.data_classes.User.User

class ActiveEnv {

    companion object{
        lateinit var user: User
        lateinit var track: Track

    }

}