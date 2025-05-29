package com.example.recentreleasesamplse

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onFirstVisible
import androidx.compose.ui.layout.onVisibilityChanged
import com.example.recentreleasesamplse.ui.theme.RecentReleaseSamplseTheme

class MediaItem(val id: Int) {
    fun play() {}

    fun pause() {}
}

val mediaItems = listOf(
    MediaItem(0),
    MediaItem(1),
    MediaItem(2),
    MediaItem(3),
    MediaItem(4),
    MediaItem(5),
    MediaItem(6),
    MediaItem(7),
    MediaItem(8),
    MediaItem(9),
)

@Composable
fun FirstVisibleItemImpressionSample(modifier: Modifier = Modifier) {

    RecentReleaseSamplseTheme {
        LazyColumn {
            items(mediaItems) {
                MediaRow(
                    it,
                    modifier = Modifier.onFirstVisible(
                        minDurationMs = 1000
                    ) {
                        Log.d(TAG, "FirstVisibleItemImpressionSample: ")
                    })
            }
        }
    }
}

@Composable
fun MediaRow(mediaItem: MediaItem, modifier: Modifier = Modifier) {
    Column {
        Text(text = "Item id is ${mediaItem.id}", modifier = modifier)
    }
}

@Composable
fun OnVisibilityChangeSample(modifier: Modifier = Modifier) {

    RecentReleaseSamplseTheme {
        Column(modifier = Modifier.onVisibilityChanged {
            Log.d(TAG, "OnVisibilityChangeSample: ")
        }) {
            Text("Log Impression sample")
        }
    }
}

private const val TAG = ""