package com.zerodev.adminkasremaja.root

import android.app.Application

class App : Application() {

    companion object{

        val server : String = "http://192.168.43.73/kas/"
    }

    override fun onCreate() {
        super.onCreate()
    }
}