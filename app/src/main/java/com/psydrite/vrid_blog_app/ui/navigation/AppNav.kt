package com.psydrite.vrid_blog_app.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.psydrite.vrid_blog_app.data.currentPage
import com.psydrite.vrid_blog_app.ui.screens.BlogList
import com.psydrite.vrid_blog_app.ui.screens.BlogWebview


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNav(
    navController: NavHostController
) {
    val NavController = navController

    NavHost(
        navController = NavController,
        startDestination = "BlogList") {

        composable("BlogList") {
            currentPage = "BlogList"
            BlogList(
                goto_blog_webview = { navController.navigate("BlogWebview")}
            )
        }
        composable("BlogWebview") {
            currentPage = "BlogWebview"
            BlogWebview(
                goto_blog_list = {navController.navigate("BlogList")}
            )
        }
    }
}