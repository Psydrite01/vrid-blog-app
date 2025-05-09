package com.psydrite.vrid_blog_app.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.psydrite.vrid_blog_app.data.model.BlogPost

var GlobalBlogList by mutableStateOf<List<BlogPost>>(emptyList())
var LatestPage by mutableStateOf(0)   //store the latest page that is fetched

var isDataFetching by mutableStateOf(false)
var currentUrl by mutableStateOf("")  //current webview url

var currentPage by mutableStateOf("")