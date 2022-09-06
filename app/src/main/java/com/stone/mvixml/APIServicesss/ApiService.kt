package com.stone.mvixml.APIServicesss

import com.stone.mvixml.model.Posts
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    companion object{
        const val BASE_URL="https://jsonplaceholder.typicode.com/"
    }
@GET("posts")
suspend fun getposts():List<Posts>
@POST("posts")
suspend fun postdata(@Body posts: Posts):List<Posts>
}