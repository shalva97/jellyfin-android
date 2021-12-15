package org.jellyfin.mobile.player.qualityoptions

class QualityOptionsProvider {

    private val defaultQualityOptions = listOf(
        QualityOption(name = "4K - 120 Mbps", maxHeight = 2160, bitrate = 120000000),
        QualityOption(name = "4K - 80 Mbps", maxHeight = 2160, bitrate = 80000000),
        QualityOption(name = "1080p - 60 Mbps", maxHeight = 1080, bitrate = 60000000),
        QualityOption(name = "1080p - 40 Mbps", maxHeight = 1080, bitrate = 40000000),
        QualityOption(name = "1080p - 20 Mbps", maxHeight = 1080, bitrate = 20000000),
        QualityOption(name = "1080p - 15 Mbps", maxHeight = 1080, bitrate = 15000000),
        QualityOption(name = "1080p - 10 Mbps", maxHeight = 1080, bitrate = 10000000),
        QualityOption(name = "720p - 8 Mbps", maxHeight = 720, bitrate = 8000000),
        QualityOption(name = "720p - 6 Mbps", maxHeight = 720, bitrate = 6000000),
        QualityOption(name = "720p - 4 Mbps", maxHeight = 720, bitrate = 4000000),
        QualityOption(name = "480p - 3 Mbps", maxHeight = 480, bitrate = 3000000),
        QualityOption(name = "480p - 1.5 Mbps", maxHeight = 480, bitrate = 1500000),
        QualityOption(name = "480p - 720 kbps", maxHeight = 480, bitrate = 720000),
        QualityOption(name = "360p - 420 kbps", maxHeight = 360, bitrate = 420000),
    )

    fun getApplicableQualityOptions(videoWidth: Int, videoHeight: Int): List<QualityOption> {
        // If the aspect ratio is less than 16/9 (1.77), set the width as if it were pillarboxed
        // i.e. 4:3 1440x1080 -> 1920x1080
        val maxAllowedWidth = when {
            videoWidth / videoHeight < 16 / 9 -> videoHeight * (16 / 9)
            else -> videoWidth
        }

        val maxAllowedHeight = when {
            maxAllowedWidth >= 3800 -> 2160
            // Some 1080p videos are apparently reported as 1912
            maxAllowedWidth >= 1900 -> 1080
            maxAllowedWidth >= 1260 -> 720
            maxAllowedWidth >= 620 -> 480
            else -> 360
        }

        return defaultQualityOptions.takeLastWhile { option -> option.maxHeight <= maxAllowedHeight }
    }
}
