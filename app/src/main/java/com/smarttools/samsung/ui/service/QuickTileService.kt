package com.smarttools.samsung.service

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.widget.Toast
import com.smarttools.samsung.utils.SamsungSystemHelper

class QuickTileService : TileService() {

    override fun onClick() {
        super.onClick()
        val tile = qsTile ?: return

        tile.state = Tile.STATE_ACTIVE
        tile.updateTile()

        // Déclencher le nettoyage en 1 clic
        SamsungSystemHelper.clearRam(applicationContext)
        Toast.makeText(applicationContext, "SmartTools : RAM Optimisée !", Toast.LENGTH_SHORT).show()

        tile.state = Tile.STATE_INACTIVE
        tile.updateTile()
    }
}
