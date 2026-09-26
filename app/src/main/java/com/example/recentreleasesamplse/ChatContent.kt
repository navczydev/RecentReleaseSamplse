package com.example.recentreleasesamplse

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("ComposeModifierMissing")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatIntentScreen(
    onConfirm: () -> Unit = {},
    onMinimize: () -> Unit = {}
) {
    val mainViewModel = viewModel<MainViewModel>()
    val notificationData = remember(mainViewModel) { mainViewModel.getMeNotificcationsList() }

    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .semantics { testTagsAsResourceId = true },
            shape = RoundedCornerShape(0.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("TopBar")
                    Spacer(Modifier.weight(1f))
                    TextButton(onClick = onMinimize) {
                        Text("Minimize")
                    }
                }
                NotificationView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    notificationData = notificationData
                )
                IntentContent(onConfirm = onConfirm)
            }
        }
    }
}

fun Context.getActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

@Composable
fun NotificationView(notificationData: NotificationData, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            NotificationView(context = context)
        },
        update = { notificationView ->
            notificationView.setNotificationData(notificationData)
        }
    )
}


@Composable
private fun IntentContent(
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top = 40.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "App icon",
            modifier = Modifier.size(32.dp)
        )

        Text(
            text = "Subtitle text goes here....",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 22.sp,
                lineHeight = 28.sp
            ),
            modifier = Modifier
                .padding(vertical = 16.dp)
        )

        Text(
            text = "Descr.....",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
            ),
            modifier = Modifier
                .padding(bottom = 24.dp)
        )

        Button(onClick = onConfirm) {
            Text("Confirm")
        }
    }
}

@Preview
@Composable
private fun Preview_ChatIntentScreen_Theme() {
    MaterialTheme {
        ChatIntentScreen(

        )
    }
}

@Preview
@Composable
private fun Preview_ChatIntentScreen_Locale() {
    MaterialTheme {
        ChatIntentScreen()
    }
}