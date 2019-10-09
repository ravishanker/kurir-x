package au.com.crazybean.mobilex.foundation.assets

import au.com.crazybean.mobilex.foundation.logger.Logger

actual open class Assets {
    actual fun loadFile(filePath: String, typeName: String?): String? {
        Logger.e("Mobilex: Android app should override Assets implementation!")
        return null
    }
}
