package com.example.recentreleasesamplse


import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.recentreleasesamplse.NotificationDetailsFragment.Companion.DETAILS
import com.example.recentreleasesamplse.databinding.NotificationViewBinding


/**
 * <h1>NotificationView</h1>
 * A custom view that displays the notfication infos
 * @author  Skander Jabouzi
 * @version 1.0
 * @since   2020-10-30
 */

/**
 * After initialisation either by layout or programmatically it's possible
 * to use two params:
 * @param notificationdColor, the background color of the view
 * @param textColor, the notification text color
 * It's possible to override the action programmatically via a closure
 * @return Nothing.
 */
public class NotificationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val DEFAULT_STYLE_ATTR = R.attr.notificationViewStyle
    private val DEFAULT_STYLE_RES = R.style.Widget_LibTheme_NotificationView

    private var notificationdColor: Int = 0
    private var textColor: Int = 0
    public var onClick: (() -> Unit)? = null
    private lateinit var notificationData: NotificationData
    public var binding: NotificationViewBinding
    private var viewContext = context

    init {
        binding = NotificationViewBinding.inflate(LayoutInflater.from(context), this, true)
        Log.d(TAG, "Context: $context")
        attrs?.let {
            Log.d(TAG, "Attrs not null: ")
            val styledAttributes = context.obtainStyledAttributes(
                it,
                R.styleable.NotificationView,
                DEFAULT_STYLE_ATTR,
                DEFAULT_STYLE_RES
            )
            notificationdColor = styledAttributes.getInt(
                R.styleable.NotificationView_notificationdColor,
                R.color.notificationBackground
            )
            textColor =
                styledAttributes.getInt(R.styleable.NotificationView_textColor, R.color.black)
            styledAttributes.recycle()
            binding.notificationView.setBackgroundColor(notificationdColor)
            binding.notificationTitle.setTextColor(textColor)
            binding.notificationDetails.setTextColor(textColor)
        }
        setDetailsClickListener()
        //setDetailsClickListener(context)
    }

    /**
     * Add the notifications list to the custom view
     * @param data, NotificationData
     * @see NotificationData
     * @return Nothing
     */
    fun setNotificationData(data: NotificationData) {
        if (data.notificationModels.isNotEmpty()) {
            notificationData = data
            binding.notificationTitle.text = getNotificationTitle()
        } else {
            this.visibility = View.GONE
        }
    }

    /**
     * Add one notification to the custom view
     * @param model, NotificationModel
     * @see NotificationModel
     * @return Nothing
     */
    public fun setNotificationData(model: NotificationModel) {
        val data = NotificationData(model)
        notificationData = data
        binding.notificationTitle.text = getNotificationTitle()
    }

    /**
     * It's possible to hide the notification details button and not to aloow any action
     * @return Nothing
     */
    public fun hideDetailsButton() {
        binding.notificationDetails.visibility = View.GONE
    }

    /**
     * It's possible to hide the notification icon
     * @return Nothing
     */
    public fun hideIcon() {
        binding.notificationIcon.visibility = View.GONE
    }

    /**
     * FOR PRIVATE USE ONLY
     * Get the notification title from the notification data usecase
     * if more than one notification model is available then a cutom title
     * will be shown: "You have x alerts"
     * @return Nothing
     */
    private fun getNotificationTitle(): String {
        return if (notificationData.notificationModels.size > 1)
            String.format(
                context.getString(R.string.notification_title),
                notificationData.notificationModels.size
            )
        else notificationData.notificationModels[0].title
    }

    /**
     * FOR PRIVATE USE ONLY
     * Set the notification action usecase
     * if the custom action is set then it will be triggered otherwise the default
     * Dialog Fragment will be shown
     * @return Nothing
     */
    private fun setDetailsClickListener(context: Context) = binding.notificationDetails.setOnClickListener {
        if (onClick != null) {
            onClick?.invoke()
        } else {
            try {
                val notificationDetailsFragment = NotificationDetailsFragment()
                val bundle = Bundle()
                bundle.putSerializable(DETAILS, notificationData)
                notificationDetailsFragment.arguments = bundle
                Log.d(TAG, "setDetailsClickListener: Context is $context")
                unwrap(it.context)
                /* val mContext =
                     when (context) {

                         is ContextThemeWrapper -> {
                             Log.d(TAG, "ContextThemeWrapper case")
                             val finalCtx: Context = (context as ContextThemeWrapper).baseContext
                             Log.d(TAG, "setDetailsClickListener: Final $finalCtx")
                             finalCtx
                         }

                         is ContextWrapper -> {
                             Log.d("NotificationView", "ContextWrapper####")
                             (context as ContextWrapper).baseContext
                         }

                         else -> {
                             Log.d("NotificationView", "Default Context")
                             context
                         }
                     }*/
                Log.d("NotificationView", "mContext = $context")
                val fragmentManager = (context as? AppCompatActivity)?.supportFragmentManager
                when {
                    fragmentManager != null -> {
                        Log.d("NotificationView", "FragmentManager is not null")
                        notificationDetailsFragment.show(fragmentManager, TAG)
                    }

                    else -> {
                        Log.e("NotificationView", "FragmentManager is null")
                    }
                }
            }catch (e: Exception) {
                Log.e("NotificationView", "Error showing notification details fragment", e)
            }
        }
    }

    private fun setDetailsClickListener() = binding.notificationDetails.setOnClickListener {
        if (onClick != null) {
            onClick?.invoke()
        } else {
            try {
                val notificationDetailsFragment = NotificationDetailsFragment()
                val bundle = Bundle()
                bundle.putSerializable(DETAILS, notificationData)
                notificationDetailsFragment.arguments = bundle
                Log.d(TAG, "setDetailsClickListener: Context is $context")
                // val fCtx = unwrap(context)
                val fragmentManager = (context as? AppCompatActivity)?.supportFragmentManager
                when {
                    fragmentManager != null -> {
                        Log.d("NotificationView", "FragmentManager is not null")
                        notificationDetailsFragment.show(fragmentManager, TAG)
                    }

                    else -> {
                        Log.e("NotificationView", "FragmentManager is null")
                    }
                }
            }catch (e: Exception) {
                Log.e("NotificationView", "Error showing notification details fragment", e)
            }
        }
    }

}

fun unwrap(context: Context): AppCompatActivity? {
    var context: Context? = context
    while (context !is AppCompatActivity && context is ContextWrapper) {
        context = context.baseContext
    }
    Log.d(TAG, "unwrap: $context")
    return context as? AppCompatActivity
}

private const val TAG = "NotificationView"