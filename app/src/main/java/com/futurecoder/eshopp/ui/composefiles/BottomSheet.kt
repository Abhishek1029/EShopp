package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.futurecoder.eshopp.R
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomButton
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomOutlinedTextBox
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText
import com.futurecoder.eshopp.viewmodels.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayBottomSheet(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onDismissClicked: (Boolean) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheet(onDismissRequest = {
        onDismissClicked(false)
    }, sheetState = sheetState, dragHandle = { BottomSheetDefaults.DragHandle() }) {
        BottomLayout(
            onLoginButtonClicked = {
                loginViewModel.onLoginClick()
            }
        )
    }
}

@Composable
fun BottomLayout(
    onLoginButtonClicked: () -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (headingText, divider, emailTF, passwordTF, loginBtn) = createRefs()
        val startGuideline = createGuidelineFromStart(.03f)
        val endGuideline = createGuidelineFromEnd(.03f)
        val topGuideline = createGuidelineFromTop(.01f)
        CustomText(
            text = R.string.login_to_continue,
            modifier = Modifier.constrainAs(
                headingText
            ) {
                top.linkTo(topGuideline)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
            }, fontSize = 18.sp, textStyle = FontWeight.ExtraBold
        )

        Divider(
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(
                    divider
                ) {
                    top.linkTo(headingText.bottom)
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                }
        )
        CustomOutlinedTextBox(textLabel = R.string.email_address,
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(
                    emailTF
                ) {
                    top.linkTo(divider.bottom)
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                })
        CustomOutlinedTextBox(textLabel = R.string.password,
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(
                    passwordTF
                ) {
                    top.linkTo(emailTF.bottom)
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                })
        CustomButton(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .padding(top = 10.dp)
                .constrainAs(
                    loginBtn
                ) {
                    top.linkTo(passwordTF.bottom)
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                },
            buttonText = R.string.login,
            onLoginButtonClick = onLoginButtonClicked
        )
    }
}