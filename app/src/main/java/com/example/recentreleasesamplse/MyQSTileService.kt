package com.example.recentreleasesamplse

import android.graphics.drawable.Icon
import android.service.quicksettings.Tile
import android.service.quicksettings.Tile.STATE_INACTIVE
import android.service.quicksettings.TileService
import android.util.Log

class MyQSTileService : TileService() {
    var clicks = 0
    private val TAG = "MyQSTileService"

    // Called when the user adds your tile.
    override fun onTileAdded() {
        super.onTileAdded()
        Log.d(TAG, "onTileAdded: ")
    }

    // Called when your app can update your tile.
    override fun onStartListening() {
        super.onStartListening()
        Log.d(TAG, "onStartListening: ")
        val tile = qsTile
        val state = tile.state
        qsTile.label = tile.label
        qsTile.contentDescription = tile.label
        qsTile.state = if (state == STATE_INACTIVE) Tile.STATE_ACTIVE else STATE_INACTIVE
        qsTile.icon = tile.icon
        qsTile.updateTile()
    }

    // Called when your app can no longer update your tile.
    override fun onStopListening() {
        super.onStopListening()
        Log.d(TAG, "onStopListening: ")
    }

    // Called when the user taps on your tile in an active or inactive state.
    override fun onClick() {
        super.onClick()
        Log.d(TAG, "onClick: ")
//        showDialog()
//        startActivityAndCollapse()
        clicks++
        qsTile.state = if (clicks % 2 == 0) Tile.STATE_ACTIVE else STATE_INACTIVE
        qsTile.label = "Clicked $clicks times"
        qsTile.contentDescription = qsTile.label
        qsTile.updateTile()
    }

    // Called when the user removes your tile.
    override fun onTileRemoved() {
        super.onTileRemoved()
        Log.d(TAG, "onTileRemoved: ")
    }
}

data class StateModel(val enabled: Boolean, val label: String, val icon: Icon)
