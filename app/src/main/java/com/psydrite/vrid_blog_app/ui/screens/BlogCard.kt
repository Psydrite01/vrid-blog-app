package com.psydrite.vrid_blog_app.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.psydrite.vrid_blog_app.data.formatDate
import com.psydrite.vrid_blog_app.data.model.BlogPost
import com.psydrite.vrid_blog_app.R


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlogCard(blog: BlogPost){
    Row (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            shape = RoundedCornerShape(16.dp)
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
                Row (
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                ){
                    Spacer(Modifier.width(8.dp))
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(blog.embedded.featuredMedia?.get(0)?.sourceUrl.toString())
                            .crossfade(true)
                            .build(),
                        contentDescription = "Post Image",
                        placeholder = painterResource(id = R.drawable.loadingplaceholder),
                        modifier = Modifier
                            .width(105.dp)
                            .height(90.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        contentScale = ContentScale.FillHeight
                    )
                }
                Column (
                    modifier = Modifier.padding(16.dp)
                ){
                    Text(
                        blog.title.rendered.toString(),
                        lineHeight = MaterialTheme.typography.titleMedium.fontSize * 1.3f,  //for consistent height across devices
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground)
                    Spacer(Modifier.height(8.dp))
                    Text(
                        formatDate(blog.date_gmt.toString()),
                        color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }
}