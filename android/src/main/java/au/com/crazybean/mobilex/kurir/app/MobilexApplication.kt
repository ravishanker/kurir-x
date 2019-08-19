package au.com.crazybean.mobilex.kurir.app

import android.app.Application
import au.com.crazybean.mobilex.kurir.dependency.Dependency

class MobilexApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            println(throwable)
            throwable.printStackTrace()
            throwable?.cause?.printStackTrace()
        }

        // Register dependencies
        Dependency.register(this)
    }
}
