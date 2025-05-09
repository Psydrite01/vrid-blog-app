package com.psydrite.vrid_blog_app.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.psydrite.vrid_blog_app.data.formatDate
import com.psydrite.vrid_blog_app.data.model.BlogPost
import com.psydrite.vrid_blog_app.data.model.BlogViewModel
import com.psydrite.vrid_blog_app.data.model.EmbeddedBlogPost
import com.psydrite.vrid_blog_app.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlogCard(index: Int, blog: BlogPost){
    var viewModel: BlogViewModel = viewModel()
    var PostUrl = remember { mutableStateOf("") }

    LaunchedEffect(blog.id) {
        viewModel.LoadImage(blog.id, PostUrl)
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Text(
//                    (index+1).toString(),
//                    modifier = Modifier.padding(horizontal = 16.dp),
//                    fontWeight = FontWeight.SemiBold,
//                    style = MaterialTheme.typography.headlineMedium,
//                    color = MaterialTheme.colorScheme.onBackground)
                when(PostUrl.value){
                    ""->{
                        CircularProgressIndicator()
                    }else -> {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(PostUrl.value)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Post Image",
                        modifier = Modifier
                            .width(160.dp)
                            .height(90.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    }
                }
                Column (
                    modifier = Modifier.padding(3.dp)
                ){
                    Text(
                        blog.title.rendered.toString(),
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground)
                    Text(formatDate(blog.date_gmt.toString()))
                }
            }
        }
    }
}