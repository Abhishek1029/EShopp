package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
    SignupScreen(
        onNameChange = {
            viewModel.onNameChange(it)
        },
        onEmailChange = {
            viewModel.onEmailChange(it)
        },
        onPasswordChange = {
            viewModel.onPasswordChange(it)
        }
    ) {
        viewModel.onSignUpClick()
    }
}

@Composable
fun SignupScreen(
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignupBtnClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (appIcon, spacerBtwIconText, headingText, spacerBtwTextName, nameTF, emailTF, passwordTF, reEnterPasswordTF, signupBtn) = createRefs()
        val topGuideline = createGuidelineFromTop(.1f)
        val chainGuidelineBottom = createGuidelineFromBottom(.2f)
        val startGuideline = createGuidelineFromStart(.06f)
        val endGuideline = createGuidelineFromEnd(.06f)
        Image(
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = "Signup Icon",
            modifier = Modifier
                .size(85.dp)
                .clip(CircleShape)
                .constrainAs(
                    appIcon
                ) {
                    top.linkTo(topGuideline)
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                }
        )

        Spacer(modifier = Modifier
            .size(40.dp)
            .constrainAs(
                spacerBtwIconText
            ) {
                top.linkTo(appIcon.bottom)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
            })

        CustomText(
            text = R.string.create_your_account,
            modifier = Modifier
                .constrainAs(
                    headingText
                ) {
                    top.linkTo(spacerBtwIconText.bottom)
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    width = Dimension.fillToConstraints
                }, fontSize = 20.sp, textStyle = FontWeight.Bold, textAlignment = TextAlign.Center
        )

        Spacer(modifier = Modifier
            .size(10.dp)
            .constrainAs(
                spacerBtwTextName
            ) {
                top.linkTo(headingText.bottom)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
            })

        CustomOutlinedTextFieldWithLeadingIcon(
            placeholderText = R.string.name,
            modifier = Modifier.constrainAs(
                nameTF
            ) {
                top.linkTo(spacerBtwTextName.bottom)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }, onTextFieldValueChange = onNameChange
        ) {
            Icon(imageVector = Icons.Default.Person, contentDescription = "Name")
        }

        CustomOutlinedTextFieldWithLeadingIcon(
            placeholderText = R.string.email_address,
            modifier = Modifier.constrainAs(
                emailTF
            ) {
                top.linkTo(nameTF.bottom)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }, onTextFieldValueChange = onEmailChange
        ) {
            Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
        }

        CustomOutlinedTextFieldWithLeadingIcon(
            placeholderText = R.string.password,
            modifier = Modifier.constrainAs(
                passwordTF
            ) {
                top.linkTo(emailTF.bottom)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }, onTextFieldValueChange = onPasswordChange
        ) {
            Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
        }

        CustomOutlinedTextFieldWithLeadingIcon(
            placeholderText = R.string.re_enter_password,
            modifier = Modifier.constrainAs(
                reEnterPasswordTF
            ) {
                top.linkTo(passwordTF.bottom)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }, onTextFieldValueChange = onPasswordChange
        ) {
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
            top.linkTo(spacerBtwTextName.bottom)
            bottom.linkTo(chainGuidelineBottom)
        }
    }
}