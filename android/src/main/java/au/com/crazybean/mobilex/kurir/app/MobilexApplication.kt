package au.com.crazybean.mobilex.kurir.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
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

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
