package se.atte.partier

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val type: Platform.Type = Platform.Type.Android
}

actual fun getPlatform(): Platform = AndroidPlatform()