package xyz.mobcoder.testapp.di

import dagger.Component
import xyz.mobcoder.testapp.ui.MainActivity
import xyz.mobcoder.testapp.di.modules.AppModule
import xyz.mobcoder.testapp.di.modules.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}