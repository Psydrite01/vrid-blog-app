package com.psydrite.vrid_blog_app.data.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.psydrite.vrid_blog_app.data.errorMessage
import com.psydrite.vrid_blog_app.data.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

@HiltViewModel
class BlogViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BlogRepository,
    application: Application
): ViewModel() {
    // var declarations:
    private val _blogList = MutableStateFlow<List<BlogPost>>(
        savedStateHandle.get<List<BlogPost>>("blog_list") ?: emptyList()
    )
    val blogList = _blogList.asStateFlow()


    var latestPage: Int
        get() = savedStateHandle.get<Int>("latest_page") ?: 0
        set(value) {
            savedStateHandle["latest_page"] = value
        }


    private val _isDataFetching = MutableStateFlow(false)
    var isDataFetching= _isDataFetching.asStateFlow()

    //functions for blogList:
    private fun updateSavedState(blogList: List<BlogPost>, page: Int) {
        savedStateHandle["blog_list"] = blogList
        savedStateHandle["latest_page"] = page
    }

    fun updateBlogList(newBlogList: List<BlogPost>) {
        _blogList.value = newBlogList
        updateSavedState(newBlogList, latestPage)
        saveToCache(_blogList.value)

    }
    fun addtoBlogList(newBlogList: List<BlogPost>) {
        _blogList.value = _blogList.value + newBlogList
        updateSavedState(_blogList.value, latestPage)
        saveToCache(_blogList.value)
    }

    //caching part:
    private val cacheDirectory = File(application.cacheDir, "blog_cache")
    private val cacheLifetime = 60 * 60 * 1000 // 1 hr

    init {
        //if no cache directory, create
        if (!cacheDirectory.exists()){
            cacheDirectory.mkdirs()
        }
        //else load data
        else{
            loadFromCache()
        }
    }

    //caching functions:
    private fun saveToCache(blogs: List<BlogPost>) {
        viewModelScope.launch {
            try {
                val file = File(cacheDirectory, "blog_cache.json")
                val data = CacheData(
                    timestamp = System.currentTimeMillis(),
                    blogPosts = blogs,
                    latestPage = latestPage
                )
                val json = Gson().toJson(data)
                file.writeText(json)
            } catch (e: Exception){
                Log.e("BlogViewModel", "Error saving to cache", e)
            }
        }
    }

    private fun loadFromCache(){
        viewModelScope.launch {
            try {
                val cacheFile = File(cacheDirectory, "blog_cache.json")
                if (cacheFile.exists() && cacheFile.isFile){
                    val json = cacheFile.readText()
                    val data = Gson().fromJson(json, CacheData::class.java)
                    if (System.currentTimeMillis() - data.timestamp < cacheLifetime){
                        updateBlogList(data.blogPosts)   //if not expired, load from cache
                        latestPage = data.latestPage
                    }
                    else{
                        loadPosts()
                    }
                }
            }catch (e: Exception){
                Log.e("BlogViewModel", "Error loading from cache", e)
            }
        }
    }

    fun loadPosts(page: Int = 1) {
        if (latestPage >= page) return                // return if already loaded

        var templist = emptyList<BlogPost>()
        viewModelScope.launch {
            _isDataFetching.value = true
            try {
                templist = repository.fetchPosts(page)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }finally {
                if (page<=1){   //first page
                    updateBlogList(templist)
                }else{     //other pages
                    addtoBlogList(templist)
                }
                if (templist.isNotEmpty()){
                    latestPage = page          //update latest page
                    errorMessage = ""          //reset var on successful loading
                }
                _isDataFetching.value = false
            }
        }
    }
}
