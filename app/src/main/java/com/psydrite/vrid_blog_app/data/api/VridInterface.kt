package com.psydrite.vrid_blog_app.data.api

import com.psydrite.vrid_blog_app.data.model.BlogPost
import retrofit2.http.GET
import retrofit2.http.Query

interface VridInterface {
    @GET("posts")
    suspend fun getBlogPosts(
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1,
        @Query("_embed") embed: Boolean = true    //for images
    ): List<BlogPost>
}


