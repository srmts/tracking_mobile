package org.traccar.client

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("api/devices")
    fun postRequest(@Body body: PostData): Call<ResponseBody>
}
