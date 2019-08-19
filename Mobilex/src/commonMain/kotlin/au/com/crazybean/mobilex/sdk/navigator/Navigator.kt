package au.com.crazybean.mobilex.sdk.navigator

interface Navigator {
    /**
     * Navigate to a new module with argument
     * If requestCode is -1, it will be ignored
     */
    fun navigate(arguments: Arguments, requestCode: Int = -1)
}
