package com.psydrite.vrid_blog_app.data.model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psydrite.vrid_blog_app.data.GlobalBlogList
import com.psydrite.vrid_blog_app.data.isDataFetching
import com.psydrite.vrid_blog_app.data.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class BlogViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _blogList = MutableStateFlow<List<BlogPost>>(emptyList())
    val blogList = _blogList.asStateFlow()
    var latestPage: Int
        get() = savedStateHandle.get<Int>("latest_page") ?: 0
        set(value) {
            savedStateHandle["latest_page"] = value
        }

    private val repository = BlogRepository()

    fun updateBlogList(newBlogList: List<BlogPost>) {
        _blogList.value = newBlogList
    }
    fun addtoBlogList(newBlogList: List<BlogPost>) {
        _blogList.value += newBlogList
    }

    fun loadPosts(page: Int = 1) {
        if (page == 1) {
            GlobalBlogList = emptyList()   // clear the list if first page load
        }
        if (latestPage >= page) return                // return if already loaded

        viewModelScope.launch {
            isDataFetching = true
            try {
                GlobalBlogList += repository.fetchPosts(page)
            } catch (e: Exception) {
                Log.d("BlogViewModel", "err:${e.toString()}}")
            }finally {
                if (page<=1){
                    updateBlogList(GlobalBlogList)
                }else{
                    addtoBlogList(GlobalBlogList)
                }
                latestPage = page
                isDataFetching = false
            }
        }
    }
}
