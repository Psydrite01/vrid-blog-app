package com.psydrite.vrid_blog_app.data.model

data class BlogPost(   //data class for blog post
    val id: Int,
    val title: Rendered,
    var date_gmt: String,
    val link: String
)

data class Rendered(
    val rendered: String
)