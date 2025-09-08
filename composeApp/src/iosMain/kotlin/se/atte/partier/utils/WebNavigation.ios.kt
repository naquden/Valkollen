package se.atte.partier.utils

import platform.UIKit.UIApplication
import platform.Foundation.NSURL

actual fun openUrl(url: String) {
    val nsUrl = NSURL.URLWithString(url)
    nsUrl?.let {
        UIApplication.sharedApplication.openURL(it)
    }
}
