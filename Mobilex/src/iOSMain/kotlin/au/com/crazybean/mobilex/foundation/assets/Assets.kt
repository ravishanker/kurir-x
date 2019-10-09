package au.com.crazybean.mobilex.foundation.assets

import au.com.crazybean.mobilex.foundation.logger.Logger
import platform.Foundation.*

actual open class Assets {
    actual fun loadFile(filePath: String, typeName: String?): String? {
        var path = filePath
        var type = typeName

        filePath.split(".").takeIf { it.size > 1 }?.let { segments ->
            path = segments[0]
            type = segments[1]
        }

        return NSBundle.mainBundle.pathForResource(path, type)?.takeIf { it.isNotBlank() }?.let { resPath ->
            try {
                NSString.stringWithContentsOfFile(resPath, NSUTF8StringEncoding, null).toString()
            } catch (throwable: Exception) {
                Logger.e(throwable)
                null
            }
        }
    }
}
