package com.ezcorplev.randomnumber

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ViewModelFactory {
    fun create(context: Context): ViewModelProvider.AndroidViewModelFactory =
        ViewModelFactoryImpl(context.applicationContext as Application)
}

/**
 * Creates an instance of ViewModel depending on the modelClass provided
 * If modeClass does not exist, throws NotImplementedError with modelClass as String
 */
private class ViewModelFactoryImpl(val app: Application) : ViewModelProvider.AndroidViewModelFactory(app) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        MainViewModel::class.java -> MainViewModel(RepoFactory.randomNumberRepo, app) as T
        else -> throw NotImplementedError(modelClass.toString())
    }
}