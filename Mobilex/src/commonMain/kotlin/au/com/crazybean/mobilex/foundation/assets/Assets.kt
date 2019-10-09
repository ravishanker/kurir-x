package au.com.crazybean.mobilex.foundation.assets

expect open class Assets {
    fun loadFile(filePath: String, typeName: String? = null): String?
}
