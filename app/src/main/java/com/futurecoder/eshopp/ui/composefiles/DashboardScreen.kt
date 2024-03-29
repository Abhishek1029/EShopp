package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.futurecoder.eshopp.R
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomOutlinedTextFieldWithLeadingIcon
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText
import com.futurecoder.eshopp.ui.theme.EShoppTheme

@Composable
fun DashboardScreen(
    onProfileIconClicked: (Boolean) -> Unit,
    onSearchBarClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (locationText, profileImage, searchBox) = createRefs()
        val startGuideline = createGuidelineFromStart(.01f)
        val endGuideline = createGuidelineFromEnd(.01f)
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = stringResource(id = R.string.profile),
            modifier = Modifier
                .clickable {
                    onProfileIconClicked(true)
                }
                .constrainAs(
                    profileImage
                ) {
                    end.linkTo(endGuideline)
                    top.linkTo(parent.top)
                }
        )

        CustomText(text = R.string.delivery_in,
            fontSize = 18.sp,
            modifier = Modifier.constrainAs(
                locationText
            ) {
                top.linkTo(parent.top)
                start.linkTo(startGuideline)
            })
        CustomOutlinedTextFieldWithLeadingIcon(
            modifier = Modifier
                .height(60.dp)
                .padding(top = 10.dp)
                .clickable {
                    onSearchBarClick()
                }
                .constrainAs(searchBox) {
                    top.linkTo(locationText.bottom)
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    width = Dimension.fillToConstraints
                },
            placeholderText = R.string.search,
            enabled = false,
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Black,
                disabledLabelColor = Color.Black,
                disabledPlaceholderColor = Color.Black,
                disabledLeadingIconColor = Color.Black,
                disabledContainerColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    EShoppTheme {
        DashboardScreen({

        }) {

        }
    }
}