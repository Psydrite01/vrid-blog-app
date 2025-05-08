package com.psydrite.vrid_blog_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.getValue
import kotlin.jvm.java

interface VridInterface {
    @GET("posts")
    suspend fun getBlogPosts(
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1
    ): List<BlogPost>
}


object VridRetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://blog.vrid.in/wp-json/wp/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: VridInterface by lazy {
        retrofit.create(VridInterface::class.java)
    }
}


class BlogRepository {
    suspend fun fetchPosts(page: Int = 1): List<BlogPost> {
        return VridRetrofitInstance.api.getBlogPosts(page = page)
    }
}