package com.psydrite.vrid_blog_app.data.repository

import com.psydrite.vrid_blog_app.data.api.VridRetrofitInstance
import com.psydrite.vrid_blog_app.data.model.BlogPost
import com.psydrite.vrid_blog_app.data.model.EmbeddedBlogPost


class BlogRepository {
    suspend fun fetchPosts(page: Int = 1): List<BlogPost> {
        return VridRetrofitInstance.api.getBlogPosts(page = page)
    }

    suspend fun fetchImage(id: Int) : EmbeddedBlogPost{
        return VridRetrofitInstance.api.getBlogPostImage(id)
    }
}