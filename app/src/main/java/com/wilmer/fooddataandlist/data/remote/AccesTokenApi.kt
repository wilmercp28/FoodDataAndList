package com.wilmer.fooddataandlist.data.remote

import com.wilmer.fooddataandlist.data.model.OAuthTokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OAuthTokenService {
    @FormUrlEncoded
    @POST("connect/token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("scope") scope: String
    ): Response<OAuthTokenResponse>
}

