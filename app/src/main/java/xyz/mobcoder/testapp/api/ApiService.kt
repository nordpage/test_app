package xyz.mobcoder.testapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.mobcoder.testapp.api.models.Plan

interface ApiService {

    @GET("project/{hash}")
    suspend fun getPlan(@Path("hash") hash: String) : Response<Plan>
}