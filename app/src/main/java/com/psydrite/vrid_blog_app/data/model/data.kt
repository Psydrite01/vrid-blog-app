package com.psydrite.vrid_blog_app.data.model

import com.google.gson.annotations.SerializedName

data class BlogPost(   //data class for blog post
    val id: Int,
    val title: Rendered,
    var date_gmt: String,
    val link: String,
    @SerializedName("_embedded")
    val embedded: Embedded
)

data class Rendered(
    val rendered: String
)

data class Embedded(
    @SerializedName("wp:featuredmedia")
    val featuredMedia: List<FeaturedMedia>?
)

data class FeaturedMedia(
    @SerializedName("source_url")
    val sourceUrl: String
)

// for cache
data class CacheData(
    val timestamp: Long,
    val blogPosts: List<BlogPost>,
    val latestPage: Int
)