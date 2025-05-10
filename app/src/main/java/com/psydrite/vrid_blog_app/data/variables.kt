package com.psydrite.vrid_blog_app.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

//global variables that do not need to be saved in savedStateHandle
var currentUrl by mutableStateOf("")  //current webview url
var currentPage by mutableStateOf("")
var errorMessage by mutableStateOf("")