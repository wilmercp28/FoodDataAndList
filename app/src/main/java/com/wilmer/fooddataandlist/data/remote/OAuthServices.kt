package com.wilmer.fooddataandlist.data.remote

import com.wilmer.fooddataandlist.BuildConfig
import com.wilmer.fooddataandlist.data.model.AccessTokenResponse
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Headers

interface OAuthService {
    @FormUrlEncoded
    @POST("connect/token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("scope") scope: String = "basic"
    ): retrofit2.Response<AccessTokenResponse>
}

suspend fun getToken() {
    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val credentials = Credentials.basic(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
            val request = chain.request()
                .newBuilder()
                .header("Authorization", credentials)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build()
            chain.proceed(request)
        }
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://oauth.fatsecret.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val oAuthService = retrofit.create(OAuthService::class.java)

    val response = oAuthService.getAccessToken()

    if (response.isSuccessful) {
        response.body()?.let { accessTokenResponse ->
            println("Access Token: ${accessTokenResponse.accessToken}")
        }
    } else {
        println("Error: ${response.errorBody()?.string()}")
    }
}


private fun okHttpClient() = OkHttpClient().newBuilder()
    .addInterceptor { chain ->
        val credentials = Credentials.basic(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
        val request = chain.request()
            .newBuilder()
            .header("Authorization", credentials)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .build()
        chain.proceed(request)
    }
    .build()
