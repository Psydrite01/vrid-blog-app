package com.psydrite.vrid_blog_app.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.psydrite.vrid_blog_app.data.model.BlogPost

var GlobalBlogList by mutableStateOf<List<BlogPost>>(emptyList())
var LatestPage by mutableStateOf(0)   //store the latest page that is fetched