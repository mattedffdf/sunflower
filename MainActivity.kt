package com.example.whatsbot

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("bot_prefs", MODE_PRIVATE)
        val botSwitch = findViewById<Switch>(R.id.botSwitch)

        botSwitch.isChecked = prefs.getBoolean("BOT_ENABLED", false)

        botSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("BOT_ENABLED", isChecked).apply()
        }
    }
}
