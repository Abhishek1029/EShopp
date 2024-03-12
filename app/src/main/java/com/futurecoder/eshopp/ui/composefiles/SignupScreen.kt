package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.futurecoder.eshopp.R
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomButton
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomOutlinedTextFieldWithLeadingIcon
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText
import com.futurecoder.eshopp.viewmodels.SignupViewModel

@Composable
fun SignUpComposable(
    viewModel: SignupViewModel = hiltViewModel()
) {
    SignupScreen {
        viewModel.onSignUpClick()
    }
}

@Composable
fun SignupScreen(
    onSignupBtnClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (headingText, nameTF, emailTF, passwordTF, reEnterPasswordTF, signupBtn) = createRefs()
        val topGuideline = createGuidelineFromTop(.15f)
        val chainGuidelineBottom = createGuidelineFromBottom(.3f)
        val startGuideline = createGuidelineFromStart(.06f)
        val endGuideline = createGuidelineFromEnd(.06f)
        CustomText(
            text = R.string.create_your_account,
            modifier = Modifier.constrainAs(
                headingText
            ) {
                top.linkTo(topGuideline)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
            }, fontSize = 20.sp, textStyle = FontWeight.Bold
        )

        CustomOutlinedTextFieldWithLeadingIcon(placeholderText = R.string.name,
            modifier = Modifier.constrainAs(
                nameTF
            ) {
                top.linkTo(headingText.bottom)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
            }) {
            Icon(imageVector = Icons.Default.Person, contentDescription = "Name")
        }

        CustomOutlinedTextFieldWithLeadingIcon(placeholderText = R.string.email_address,
            modifier = Modifier.constrainAs(
                emailTF
            ) {
                top.linkTo(nameTF.bottom)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
            }) {
            Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
        }

        CustomOutlinedTextFieldWithLeadingIcon(placeholderText = R.string.password,
            modifier = Modifier.constrainAs(
                passwordTF
            ) {
                top.linkTo(emailTF.bottom)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
            }) {
            Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
        }

        CustomOutlinedTextFieldWithLeadingIcon(placeholderText = R.string.re_enter_password,
            modifier = Modifier.constrainAs(
                reEnterPasswordTF
            ) {
                top.linkTo(passwordTF.bottom)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
            }) {
            Icon(imageVector = Icons.Default.Lock, contentDescription = "Re-enter Password")
        }

        CustomButton(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .padding(top = 10.dp)
                .constrainAs(
                    signupBtn
                ) {
                    top.linkTo(passwordTF.bottom)
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                },
            buttonText = R.string.create_account,
            onButtonClick = onSignupBtnClick
        )
        val verticalChain = createVerticalChain(
            nameTF,
            emailTF,
            passwordTF,
            reEnterPasswordTF,
            signupBtn,
            chainStyle = ChainStyle.Spread
        )
        constrain(verticalChain) {
            top.linkTo(headingText.bottom)
            bottom.linkTo(chainGuidelineBottom)
        }
    }
}