package com.futurecoder.eshopp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.futurecoder.eshopp.ui.composefiles.DashboardScreen
import com.futurecoder.eshopp.ui.composefiles.DisplayBottomSheet
import com.futurecoder.eshopp.ui.composefiles.EShoppAppScreen
import com.futurecoder.eshopp.ui.composefiles.SearchScreen
import com.futurecoder.eshopp.ui.composefiles.SplashScreen
import com.futurecoder.eshopp.ui.theme.EShoppTheme
import com.futurecoder.eshopp.viewmodels.DashboardViewModel
import com.futurecoder.eshopp.utils.DashboardDestination
import com.futurecoder.eshopp.utils.SearchDestination
import com.futurecoder.eshopp.utils.SplashDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EShoppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                    ) { paddingValues ->
                        EShoppAppScreen(
                            modifier = Modifier.padding(paddingValues)
                        )
                    }
                }
            }
        }
    }
}