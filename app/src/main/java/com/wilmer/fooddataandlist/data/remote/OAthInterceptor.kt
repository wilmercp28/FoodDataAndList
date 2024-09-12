package com.wilmer.fooddataandlist.data.remote

import android.util.Log
import okhttp3.HttpUrl
import java.util.SortedMap
import okhttp3.Interceptor
import okhttp3.Response
import java.net.URLEncoder
import java.util.Base64
import java.util.UUID
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class OAuthInterceptor(
    private val consumerKey: String,
    private val consumerSecret: String,
    private val accessToken: String?,
    private val accessSecret: String?
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val method = request.method
        val url = request.url

        val oauthTimestamp = (System.currentTimeMillis() / 1000).toString()
        val oauthNonce = UUID.randomUUID().toString().replace("-", "")
        val oauthVersion = "1.0"
        val oauthSignatureMethod = "HMAC-SHA1"

        val queryParams = url.queryParameterNames.associateWith { url.queryParameter(it).orEmpty() }

        val params = sortedMapOf(
            "oauth_consumer_key" to consumerKey,
            "oauth_nonce" to oauthNonce,
            "oauth_signature_method" to oauthSignatureMethod,
            "oauth_timestamp" to oauthTimestamp,
            "oauth_version" to oauthVersion
        )
        accessToken?.let { params["oauth_token"] = it }

        val allParams = sortedMapOf<String, String>().apply {
            putAll(queryParams)
            putAll(params)
        }

        val signatureBaseString = createSignatureBaseString(method, url, allParams)
        val signingKey = createSigningKey(consumerSecret, accessSecret)
        val oauthSignature = createSignature(signatureBaseString, signingKey)

        val finalParams = allParams.apply { put("oauth_signature", oauthSignature) }

        val urlWithParams = HttpUrl.Builder()
            .scheme(url.scheme)
            .host(url.host)
            .apply {
                // Add path segments
                addPathSegments(url.pathSegments.joinToString("/"))

                // Add new query parameters
                finalParams.forEach { (key, value) -> addQueryParameter(key, value) }
            }
            .build()

        val newRequest = request.newBuilder().url(urlWithParams).build()

        return chain.proceed(newRequest)
    }

    private fun createSignatureBaseString(
        method: String,
        url: HttpUrl,
        params: SortedMap<String, String>
    ): String {
        val normalizedUrl = "${url.scheme}://${url.host}${url.encodedPath}"

        val encodedParams = params.map {
            "${URLEncoder.encode(it.key, "UTF-8")}=${URLEncoder.encode(it.value, "UTF-8")}"
        }.joinToString("&")

        return "$method&${URLEncoder.encode(normalizedUrl, "UTF-8")}&${URLEncoder.encode(encodedParams, "UTF-8")}"
    }

    private fun createSigningKey(consumerSecret: String, accessSecret: String?): String {
        return "${URLEncoder.encode(consumerSecret, "UTF-8")}&${URLEncoder.encode(accessSecret ?: "", "UTF-8")}"
    }

    private fun createSignature(baseString: String, signingKey: String): String {
        val mac = Mac.getInstance("HmacSHA1")
        val keySpec = SecretKeySpec(signingKey.toByteArray(), "HmacSHA1")
        mac.init(keySpec)
        val rawHmac = mac.doFinal(baseString.toByteArray())
        return Base64.getEncoder().encodeToString(rawHmac)
    }
}
