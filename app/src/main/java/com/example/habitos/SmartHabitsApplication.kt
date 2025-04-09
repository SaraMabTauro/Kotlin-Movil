package com.example.habitos

import android.app.Application

@HiltAndroidApp
class SmartHabitsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannel(this)
    }
}