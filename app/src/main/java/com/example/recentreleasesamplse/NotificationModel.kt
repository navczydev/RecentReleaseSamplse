package com.example.recentreleasesamplse

import androidx.compose.runtime.Immutable
import java.io.Serializable

@Immutable
public data class NotificationModel(
    val title: String,
    val details: String? = null,
    // GB notification url follow this pattern:[URL_LABEL]:[URL_ADDRESS]. Example url- [Google]: www.google.com
    // Current url implementation support only this pattern.
    val urlLink: String? = null,
    val linkLabel: String = "",
    val position: Int = -1,
    val role: String = "",
    val name: String = "",
) : Serializable {

}


/**
 * <h1>NotificationData</h1>
 * This is Data Structure of the Notification Model
 * @author  Skander Jabouzi
 * @version 1.0
 * @since   2020-10-30
 */

/**
 * The notification data is the data structure that holds a list of notification model
 * If there's more then one notification then the icon will be one not from the Notification Types
 * @param notificationModels List of NotificationModel
 * @see NotificationModel
 */
@Immutable
public data class NotificationData(val notificationModels: List<NotificationModel>) :
    java.io.Serializable {

    /**
     * Add the notifications one NotificationModel to the custom view
     * @param notificationModel, NotificationModel
     * @see NotificationModel
     * @return Nothing
     */
    public constructor(notificationModel: NotificationModel) : this(
        listOf(notificationModel)
    )

}