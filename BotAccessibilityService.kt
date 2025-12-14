package com.example.whatsbot

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class BotAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val reply = intent?.getStringExtra("reply") ?: return START_NOT_STICKY
        sendMessage(reply)
        return START_NOT_STICKY
    }

    private fun sendMessage(message: String) {
        val root = rootInActiveWindow ?: return
        val inputs = root.findAccessibilityNodeInfosByViewId("com.whatsapp:id/entry")
        if (inputs.isEmpty()) return

        val input = inputs[0]
        input.performAction(AccessibilityNodeInfo.ACTION_FOCUS)

        val args = android.os.Bundle()
        args.putCharSequence(
            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
            message
        )
        input.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args)

        val send =
            root.findAccessibilityNodeInfosByViewId("com.whatsapp:id/send")
        if (send.isNotEmpty()) {
            send[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
        }
    }

    override fun onInterrupt() {}
}
