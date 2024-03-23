package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.futurecoder.eshopp.R
import com.futurecoder.eshopp.ui.composefiles.customwidgets.BasicDialog
import com.futurecoder.eshopp.R.string as AppString
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText
import com.futurecoder.eshopp.ui.theme.EShoppTheme
import com.futurecoder.eshopp.utils.EShoppConstants.APP_VERSION
import com.futurecoder.eshopp.viewmodels.ProfileViewModel

@ExperimentalMaterial3Api
@Composable
fun ProfileComposable(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onAddressCardClick: () -> Unit,
    onUserLogout: () -> Unit
) {
    ProfileScreen(
        profileViewModel,
        onAddressCardClick = onAddressCardClick
    ) {
        profileViewModel.logoutUser()
        onUserLogout()
    }
}

@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    onAddressCardClick: () -> Unit,
    onLogoutClick: () -> Unit
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    if (showDialog) {
        BasicDialog(headingText = stringResource(id = AppString.logout),
            subHeadingText = stringResource(id = AppString.are_you_sure_to_logout),
            negativeButtonTextColor = Color.Red,
            onDialogDismiss = {
                showDialog = false
            },
            onNegativeButtonClick = {
                showDialog = false
            }) {
            showDialog = false
            onLogoutClick()
        }
    }

    ConstraintLayout(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (profileIcon, nameText, emailText, addressCard, logoutCard, appVersionText) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.profile_icon),
            contentDescription = "Profile Icon",
            modifier = Modifier
                .size(90.dp)
                .constrainAs(profileIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )

        CustomText(
            text = profileViewModel.getCurrentUser()?.displayName ?: "",
            textStyle = FontWeight.Bold, fontSize = 16.sp,
            modifier = Modifier.constrainAs(nameText) {
                start.linkTo(profileIcon.end, 16.dp)
                top.linkTo(parent.top, 16.dp)
            }
        )

        CustomText(
            text = profileViewModel.getCurrentUser()?.email ?: "",
            textStyle = FontWeight.Bold, fontSize = 16.sp,
            modifier = Modifier.constrainAs(emailText) {
                start.linkTo(profileIcon.end, 16.dp)
                top.linkTo(nameText.bottom, 5.dp)
            }
        )

        ElevatedCard(
            onClick = {
                onAddressCardClick()
            }, modifier = Modifier
                .height(56.dp)
                .padding(3.dp)
                .constrainAs(addressCard) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(profileIcon.bottom, 20.dp)
                    width = Dimension.fillToConstraints
                },
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ), shape = RectangleShape, elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp,
                pressedElevation = 10.dp
            )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(10.dp))
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Address",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.size(15.dp))
                CustomText(
                    text = AppString.address,
                    fontSize = 18.sp,
                    textStyle = FontWeight.Bold
                )
            }
        }

        ElevatedCard(
            onClick = {
                showDialog = true
            }, modifier = Modifier
                .height(56.dp)
                .padding(3.dp)
                .constrainAs(logoutCard) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(appVersionText.top, 5.dp)
                    width = Dimension.fillToConstraints
                },
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White,
                contentColor = Color.Red
            ), shape = RectangleShape, elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp,
                pressedElevation = 10.dp
            )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(10.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = "Logout",
                    tint = Color.Red
                )
                Spacer(modifier = Modifier.size(15.dp))
                CustomText(
                    text = R.string.logout,
                    fontSize = 18.sp,
                    textStyle = FontWeight.Bold
                )
            }
        }

        CustomText(
            text = AppString.app_version,
            dynamicString = APP_VERSION,
            fontSize = 16.sp,
            textStyle = FontWeight.Bold,
            modifier = Modifier
                .padding(3.dp)
                .constrainAs(appVersionText) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, 10.dp)
                }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ProfileScreenPreview() {
    EShoppTheme {
        ProfileComposable(
            onAddressCardClick = {

            }
        ) {

        }
    }
}