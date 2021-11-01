package xyz.mobcoder.testapp.viewmodel.repositories

import xyz.mobcoder.testapp.api.ApiService

class MainRepository constructor(private val apiService: ApiService) {

    suspend fun getPlan(hash: String) = apiService.getPlan(hash)

}