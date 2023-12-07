package com.bestswlkh0310.sexyalarimi.service

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.bestswlkh0310.sexyalarimi.server.HttpClient.api
import com.bestswlkh0310.sexyalarimi.server.dto.AlarmDto
import com.bestswlkh0310.sexyalarimi.util.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SexyNotificationListenerService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        val packageName: String = sbn?.packageName ?: "Null"
        val extras = sbn?.notification?.extras

        val title = extras?.getString(Notification.EXTRA_TITLE)?: ""
        val text = extras?.getString(Notification.EXTRA_TEXT)?: ""
        val bigText = extras?.getString(Notification.EXTRA_BIG_TEXT)?: ""
        val infoText = extras?.getString(Notification.EXTRA_INFO_TEXT)?: ""
        val subText = extras?.getString(Notification.EXTRA_SUB_TEXT)?: ""
        val summaryText = extras?.getString(Notification.EXTRA_SUMMARY_TEXT)?: ""

        val alarmDto = AlarmDto(
            title = title,
            text = text,
            roomName = subText,
            packageName = packageName
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.addAlarm(alarmDto)
                Log.d(TAG, "SexyNotificationListenerService ${response} - onNotificationPosted() called")
            } catch (e: Exception) {
                Log.d(TAG, "SexyNotificationListenerService ${e.message} - onNotificationPosted() called")
            }
        }

        Log.d(
            TAG, "onNotificationPosted:\n" +
                    "PackageName: $packageName\n" +
                    "Title: $title\n" +
                    "Text: $text\n" +
                    "BigText: $bigText\n" +
                    "InfoText: $infoText\n" +
                    "SubText: $subText\n" +
                    "SummaryText: $summaryText\n"
        )
    }
}