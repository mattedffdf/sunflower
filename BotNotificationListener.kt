package com.example.whatsbot

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.content.Intent

class BotNotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {

        if (sbn.packageName != "com.whatsapp") return

        val prefs = getSharedPreferences("bot_prefs", MODE_PRIVATE)
        if (!prefs.getBoolean("BOT_ENABLED", false)) return

        val extras = sbn.notification.extras
        val text = extras.getCharSequence("android.text")?.toString() ?: return

        if (text.trim().equals("!help", true)) {
            val i = Intent(this, BotAccessibilityService::class.java)
            i.putExtra("reply", "buongiorno")
            startService(i)
        }
    }
}
