package com.stone.mvixml.model

import com.stone.mvixml.APIServicesss.ApiService
import retrofit2.Response
import javax.inject.Inject

class MyRepository @Inject constructor (private val apiService: ApiService) {
    suspend fun getpostt():List<Posts>{
     return   apiService.getposts()
    }
    suspend fun postd(posts: Posts):List<Posts>{
        return apiService.postdata(posts)
    }
}
