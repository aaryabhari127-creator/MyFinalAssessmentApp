package com.example.myassssmentapplication.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    // Change the segment to your class location: footscray / sydney / br
    @POST("sydney/auth")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("dashboard/{keypass}")
    fun getDashboard(@Path("keypass") keypass: String): Call<DashboardEnvelope>
}
