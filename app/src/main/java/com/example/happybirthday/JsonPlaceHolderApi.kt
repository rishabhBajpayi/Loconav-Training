package com.example.happybirthday

import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {
        @GET("/posts")
        fun getPosts(): Call<MutableList<Post>>
}