package com.example.recentreleasesamplse

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    fun getMeNotificcationsList(): NotificationData {
        val notif1 = NotificationModel(
            title = "Notification test # 1",
            details = "The same holds true of the crowds who, because of ignorance, " +
                    "fail to practice sport by and for themselves Since the new " +
                    "socialist society is based on partnership and not on a wage system, " +
                    "natural socialist rules do not apply to domestic servants because " +
                    "they render services rather than production",
            urlLink = "[Google] https://www.google.ca "
        )
        val notif2 = NotificationModel(
            title = "Notification test # 2",
            details = "Deprivation of the means of fulfilment compromises freedom because, " +
                    "in attempting to satisfy basic needs, one would be subject to the " +
                    "interference of outside forces in one's basic interests",
            urlLink = "https://google.com"
        )
        val notif3 = NotificationModel(
            title = "Notification test # 3",
            details = "To dispense with the natural role of woman in maternity, " +
                    "nurseries replacing mothers, is a start in dispensing " +
                    "with the human society and transforming it into a merely " +
                    "biological society with an artificial way of life\n" +
                    "\n" +
                    "Consequently, ethical standards have become confused\n" +
                    "\n" +
                    "Nations whose nationalism is destroyed are subject to ruin\n" +
                    "\n" +
                    "Therefore, drafting a constitution or conducting a plebiscite on it " +
                    "is a mockery\n" +
                    "\n" +
                    "Physically, the representative cannot transmit to others how his body " +
                    "and morale benefit from sport\n" +
                    "\n" +
                    "If a test were carried out to discover whether the natural propensity of " +
                    "the child is towards its mother or the nursery\n" +
                    "\n" +
                    "The ﬂourishing society is that in which the individual grows naturally " +
                    "within the family and the family within society",
            urlLink = "[Google]: https://www.google.ca"
        )
        return NotificationData(listOf(notif1, notif2, notif3))
    }

    fun getSingleNotification(): NotificationModel {
        return NotificationModel(
            title = "Testing Alert",
            details = "This a testing alert to test if it works",
            urlLink = "https://www.manulife.ca",
            linkLabel = "Manulife"
        )
    }

    fun getNotificationWithCustomDrawable(id: Int): NotificationModel {
        return NotificationModel(
            "Testing Alert",
            "This a testing alert to test if it works"
        )
    }
}