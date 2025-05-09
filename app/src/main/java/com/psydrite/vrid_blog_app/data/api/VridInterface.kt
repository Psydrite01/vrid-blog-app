package com.psydrite.vrid_blog_app.data.api

import com.psydrite.vrid_blog_app.data.model.BlogPost
import com.psydrite.vrid_blog_app.data.model.EmbeddedBlogPost
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VridInterface {
    @GET("posts")
    suspend fun getBlogPosts(
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1
    ): List<BlogPost>

    @GET("posts/{id}")
    suspend fun getBlogPostImage(     //for post image
        @Path("id") id: Int,
        @Query("_embed") embed: Boolean = true
    ): EmbeddedBlogPost
}


