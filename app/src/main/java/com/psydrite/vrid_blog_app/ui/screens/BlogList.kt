package com.psydrite.vrid_blog_app.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.psydrite.vrid_blog_app.data.GlobalBlogList
import com.psydrite.vrid_blog_app.data.currentPage
import com.psydrite.vrid_blog_app.data.isDataFetching
import com.psydrite.vrid_blog_app.data.model.BlogViewModel
import kotlinx.coroutines.flow.distinctUntilChanged

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlogList(
    goto_blog_webview: () -> Unit,
    viewModel: BlogViewModel = viewModel()
){
    val listState = rememberLazyListState()

    LaunchedEffect(currentPage) {    // load the data first time on entering
        viewModel.loadPosts(1)
    }

    LaunchedEffect(listState) {   // trigger next page load when reaching the end
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .distinctUntilChanged()
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex != null) {
                    if (lastVisibleItemIndex >= GlobalBlogList.lastIndex - 3) {  // -3 so that loading is faster
                        viewModel.loadPosts(GlobalBlogList.size / 10 + 1)
                    }
                }
            }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 5.dp)
            .background(MaterialTheme.colorScheme.background),
    ){
        Spacer(Modifier.height(50.dp))
        Text(
            "Blog Posts",
            modifier = Modifier
                .padding(start = 5.dp),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground)
        Spacer(Modifier.height(20.dp))

        LazyColumn(state = listState) {
            if (GlobalBlogList.isEmpty() && !isDataFetching){
                item {
                    Text("Oops, No data found")
                }
            }
            else{
                itemsIndexed(GlobalBlogList) { index, blog ->
                    BlogCard(blog, goto_blog_webview)
                }
            }
            if (isDataFetching){
                item {
                    DataFetchingComposable()
                }
            }
        }
    }
}