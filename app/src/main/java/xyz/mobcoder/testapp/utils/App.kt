package xyz.mobcoder.testapp.utils

import android.app.Application
import xyz.mobcoder.testapp.di.AppComponent
import xyz.mobcoder.testapp.di.DaggerAppComponent
import xyz.mobcoder.testapp.di.modules.AppModule
import xyz.mobcoder.testapp.di.modules.NetworkModule

class App : Application() {

    lateinit var injector: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
        injector = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
    }

    companion object {
        private var INSTANCE: App? = null
        @JvmStatic
        fun get(): App = INSTANCE!!
    }

}