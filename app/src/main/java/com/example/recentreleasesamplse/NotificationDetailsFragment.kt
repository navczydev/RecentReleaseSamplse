package com.example.recentreleasesamplse

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.recentreleasesamplse.databinding.FragmentNotificationDetailsBinding

/**
 * <h1>NotificationDetailsFragment</h1>
 * This is the DialogFragment used
 * to display the notification details
 * it's for internal package use only
 * and therefore bot exposed
 * @author  Skander Jabouzi
 * @version 1.0
 * @since   2020-10-30
 */

internal class NotificationDetailsFragment : DialogFragment() {

//    private var notificationsAdapter = NotificationsAdapter()
    private lateinit var binding: FragmentNotificationDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentNotificationDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val toolbar = binding.dialogToolbar
        toolbar.setNavigationIcon(R.drawable.outline_arrow_back_24)
        toolbar.setNavigationOnClickListener { dismiss() }
        val bundle = arguments
        val detailsList: NotificationData = bundle?.getSerializable(DETAILS) as NotificationData
//        notificationsAdapter.notifications = detailsList.notificationModels
//        notificationsAdapter.notifyDataSetChanged()
//        binding.notificationsRecyclerView.adapter = notificationsAdapter
        return view
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    companion object {
        const val TAG = "NotificationDetailsFragment"
        var DETAILS = "details"
    }
}