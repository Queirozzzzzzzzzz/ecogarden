package com.queirozzzzzzzzzz.estufasemestufa.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Endpoints {
    @FormUrlEncoded
    @POST("/api/v1/auth/signin")
    suspend fun login(@FieldMap params: Map<String, String>): Response<ResponseBody>

    @FormUrlEncoded
    @POST("/api/v1/auth/signup")
    suspend fun signup(@FieldMap params: Map<String, String>): Response<ResponseBody>

    @GET("/api/v1/data")
    suspend fun getNewData(): Response<ResponseBody>

    @GET("/api/v1/status")
    suspend fun checkLogin(): Response<ResponseBody>
}