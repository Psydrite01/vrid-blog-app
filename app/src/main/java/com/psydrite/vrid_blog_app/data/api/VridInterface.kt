package com.psydrite.vrid_blog_app.data.api

import android.content.Context
import com.psydrite.vrid_blog_app.R
import com.psydrite.vrid_blog_app.data.model.BlogPost
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.coroutines.coroutineContext
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

    private lateinit var retrofit: Retrofit
    lateinit var api: VridInterface
        private set

    fun init(context: Context) {
        val baseUrl = context.getString(R.string.blog_api)
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(VridInterface::class.java)
    }
}




class BlogRepository {
    suspend fun fetchPosts(page: Int = 1): List<BlogPost> {
        return VridRetrofitInstance.api.getBlogPosts(page = page)
    }
}