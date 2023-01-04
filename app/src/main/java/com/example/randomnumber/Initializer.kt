package com.example.randomnumber

import android.app.Application

object Initializer {

    fun init(application: Application) {
        RepoFactory.context = application
    }
}