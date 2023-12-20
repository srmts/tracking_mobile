package org.traccar.client

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //local
//    private const val BASE_URL = "https://seven-facts-like.loca.lt"
//    private const val USERNAME = "coe@pnm.co.id"
//    private const val PASSWORD = "P@ssword123PNM"

    //deploy
    private const val BASE_URL = "http://10.50.74.7:8082"
    private const val USERNAME = "rnd_coe@pnm.co.id"
    private const val PASSWORD = "P@ssw0rd123PNM"

    private val okHttpClient: OkHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(AuthInterceptor(USERNAME, PASSWORD))
            .build()
    }

    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

class AuthInterceptor(private val username: String, private val password: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val credentials = Credentials.basic(username, password)
        val request = chain.request().newBuilder()
            .addHeader("Authorization", credentials)
            .build()
        return chain.proceed(request)
    }
}
