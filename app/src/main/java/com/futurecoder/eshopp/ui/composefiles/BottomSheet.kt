package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.futurecoder.eshopp.R
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomAnnotatedText
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomButton
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomOutlinedTextBox
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomOutlinedTextFieldWithLeadingIcon
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText
import com.futurecoder.eshopp.viewmodels.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayBottomSheet(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onDismissClicked: (Boolean) -> Unit,
    onSignUpTextClicked: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheet(onDismissRequest = {
        onDismissClicked(false)
    }, sheetState = sheetState, dragHandle = { BottomSheetDefaults.DragHandle() }) {
        BottomLayout(
            onSignUpTextClicked = onSignUpTextClicked,
            onLoginButtonClicked = {
                loginViewModel.onLoginClick()
            }
        )
    }
}

@Composable
fun BottomLayout(
    onSignUpTextClicked: () -> Unit,
    onLoginButtonClicked: () -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (headingText, divider, emailTF, passwordTF, loginBtn, signupText) = createRefs()
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
        CustomOutlinedTextFieldWithLeadingIcon(placeholderText = R.string.email_address,
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(
                    emailTF
                ) {
                    top.linkTo(divider.bottom)
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                }, leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = stringResource(id = R.string.email_address)
                )
            })
        CustomOutlinedTextFieldWithLeadingIcon(placeholderText = R.string.password,
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(
                    passwordTF
                ) {
                    top.linkTo(emailTF.bottom)
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                }, leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(id = R.string.password)
                )
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
            onButtonClick = onLoginButtonClicked
        )

        ClickableText(text = buildAnnotatedString {
            append("Don't have an account? ")
            withStyle(style = SpanStyle(color = Color.Red)) {
                append("Sign Up")
            }
        }, modifier = Modifier
            .padding(top = 10.dp)
            .constrainAs(
                signupText
            ) {
                top.linkTo(loginBtn.bottom)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
            }, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            onClick = {
                onSignUpTextClicked()
            })

        /* CustomAnnotatedText(
             annotatedText = returnAnnotatedString(context = context),
             modifier = Modifier
                 .fillMaxWidth(.5f)
                 .padding(top = 10.dp)
                 .constrainAs(
                     signupText
                 ) {
                     top.linkTo(loginBtn.bottom)
                     start.linkTo(startGuideline)
                     end.linkTo(endGuideline)
                 }
         )*/
    }
}