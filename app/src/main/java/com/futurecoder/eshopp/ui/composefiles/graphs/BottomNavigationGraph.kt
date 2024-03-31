package com.futurecoder.eshopp.ui.composefiles.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

fun NavGraphBuilder.bottomNavigationGraph() {
    navigation(
        route = "BottomNav",
        startDestination = "Home"
    ){

    }
}