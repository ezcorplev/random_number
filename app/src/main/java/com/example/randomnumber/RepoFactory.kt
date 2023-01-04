package com.example.randomnumber

import android.app.Application

/**
 *
 */

object RepoFactory {

    lateinit var context: Application

    val randomNumberRepo: RandomNumberRepo = RandomNumberRepoImpl
}