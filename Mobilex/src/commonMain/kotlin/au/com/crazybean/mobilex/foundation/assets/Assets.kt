package au.com.crazybean.mobilex.foundation.assets

interface Assets {
    fun loadFile(filePath: String, typeName: String? = null): String?
}
