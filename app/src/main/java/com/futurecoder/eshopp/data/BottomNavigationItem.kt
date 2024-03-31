package com.futurecoder.eshopp.data

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    var title: Int,
    var route: String,
    var selectedIcon: ImageVector,
    var unSelectedIcon: ImageVector
)
