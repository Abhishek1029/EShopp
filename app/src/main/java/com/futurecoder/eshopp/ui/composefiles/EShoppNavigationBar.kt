package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import com.futurecoder.eshopp.data.BottomNavigationItem
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText

@Composable
fun EShoppNavigationBar(
    bottomNavList: List<BottomNavigationItem>,
    navBackStackEntry: NavBackStackEntry?,
    navBarState: MutableState<Boolean>,
    openScreen: (String) -> Unit
) {
    val currentRoute = navBackStackEntry?.destination?.route
    AnimatedVisibility(
        visible = navBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        NavigationBar {
            bottomNavList.forEach { bottomNavItem ->
                NavigationBarItem(
                    onClick = {
                        openScreen(bottomNavItem.route)
                    }, label = {
                        CustomText(text = bottomNavItem.title)
                    },
                    selected = currentRoute == bottomNavItem.route,
                    icon = {
                        Icon(
                            imageVector = if (currentRoute == bottomNavItem.route) bottomNavItem.selectedIcon else bottomNavItem.unSelectedIcon,
                            contentDescription = stringResource(id = bottomNavItem.title)
                        )
                    })
            }
        }
    }
}