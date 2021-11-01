package xyz.mobcoder.testapp.di.modules

import dagger.Module
import dagger.Provides
import xyz.mobcoder.testapp.api.ApiClient
import xyz.mobcoder.testapp.api.ApiService
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : ApiService {
        return ApiClient.apiService
    }
}