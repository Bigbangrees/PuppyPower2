package com.example.puppypower.viewmodels

import android.app.Application
import com.example.puppypower.database.PuppyDatabase

class PuppyApplication : Application() {
    val database: PuppyDatabase by lazy {
        PuppyDatabase.getDatabase(this)
    }
}