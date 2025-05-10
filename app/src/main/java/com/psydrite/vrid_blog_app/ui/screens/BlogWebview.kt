package com.psydrite.vrid_blog_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.psydrite.vrid_blog_app.data.currentUrl

@Composable
fun BlogWebview(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 5.dp)
            .background(MaterialTheme.colorScheme.background),
    ) {
        Spacer(Modifier.height(50.dp))
        Text(
            "Blog Web view",
            modifier = Modifier
                .padding(start = 5.dp),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(20.dp))
        WebPageViewer(currentUrl)
    }
}