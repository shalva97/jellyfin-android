package org.jellyfin.mobile.player.qualityoptions

data class QualityOption(
    val name: String,
    val maxHeight: Int,
    val bitrate: Int,
)
