package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.futurecoder.eshopp.data.Address
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomButton
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomTextFieldWithPlaceholder
import com.futurecoder.eshopp.viewmodels.AddressViewModel
import com.futurecoder.eshopp.R.string as AppString

private const val TAG = "AddAddressScreen"

@Composable
fun AddAddressScreen(
    addressViewModel: AddressViewModel = hiltViewModel(),
    onAddressAdded: () -> Unit
) {
    val currentAddress by addressViewModel.addressState
    AddAddress(
        currentAddress,
        onAddressChange = {
            addressViewModel.onAddressChange(it)
        },
        onCityChange = {
            addressViewModel.onCityChange(it)
        },
        onCountryChange = {
            addressViewModel.onCountryChange(it)
        },
        onStateChange = {
            addressViewModel.onStateChange(it)
        },
        onPostalCodeChange = {
            addressViewModel.onPostalCodeChange(it)
        },
        onProceedClick = {
            addressViewModel.addAddress()
            onAddressAdded()
        }
    )
}

@Composable
fun AddAddress(
    currentAddress: Address? = null,
    onAddressChange: (String) -> Unit,
    onCityChange: (String) -> Unit,
    onCountryChange: (String) -> Unit,
    onStateChange: (String) -> Unit,
    onPostalCodeChange: (String) -> Unit,
    onProceedClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        val (shippingAddressText, addressText, addressTF, cityText, cityTF, countryText,
            countryTF, stateText, stateTF, postalCodeText, postalCodeTF, proceedBtn) = createRefs()

        val topGuideline = createGuidelineFromTop(.15f)
        val startGuideline = createGuidelineFromStart(.06f)
        val endGuideline = createGuidelineFromEnd(.06f)
        val guidelineFromCenter = createGuidelineFromStart(.5f)
        CustomText(
            text = AppString.add_shipping_address,
            modifier = Modifier.constrainAs(shippingAddressText) {
                top.linkTo(topGuideline)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            },
            fontSize = 20.sp, textStyle = FontWeight.ExtraBold
        )
        CustomText(
            text = AppString.address,
            modifier = Modifier.constrainAs(addressText) {
                top.linkTo(shippingAddressText.bottom, 30.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            },
            fontSize = 15.sp,
            textStyle = FontWeight.Medium
        )
        CustomTextFieldWithPlaceholder(
            receivedText = currentAddress?.address ?: "",
            placeholderText = AppString.address_ex,
            modifier = Modifier.constrainAs(addressTF) {
                top.linkTo(addressText.bottom, 5.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black
            ), onTextChange = onAddressChange
        )
        CustomText(
            text = AppString.city,
            modifier = Modifier.constrainAs(cityText) {
                top.linkTo(addressTF.bottom, 10.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            },
            fontSize = 15.sp,
            textStyle = FontWeight.Medium
        )
        CustomTextFieldWithPlaceholder(
            placeholderText = AppString.city_ex,
            receivedText = currentAddress?.city ?: "",
            modifier = Modifier.constrainAs(cityTF) {
                top.linkTo(cityText.bottom, 5.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }, colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black
            ), onTextChange = onCityChange
        )
        CustomText(
            text = AppString.country,
            modifier = Modifier.constrainAs(countryText) {
                top.linkTo(cityTF.bottom, 10.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            },
            fontSize = 15.sp,
            textStyle = FontWeight.Medium
        )
        CustomTextFieldWithPlaceholder(
            placeholderText = AppString.country_ex,
            receivedText = currentAddress?.country ?: "",
            modifier = Modifier.constrainAs(countryTF) {
                top.linkTo(countryText.bottom, 5.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }, colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black
            ), onTextChange = onCountryChange
        )

        CustomText(
            text = AppString.state,
            modifier = Modifier.constrainAs(stateText) {
                top.linkTo(countryTF.bottom, 10.dp)
                start.linkTo(startGuideline)
                end.linkTo(guidelineFromCenter)
                width = Dimension.fillToConstraints
            },
            fontSize = 15.sp,
            textStyle = FontWeight.Medium
        )
        CustomTextFieldWithPlaceholder(
            placeholderText = AppString.state_ex,
            receivedText = currentAddress?.state ?: "",
            modifier = Modifier.constrainAs(stateTF) {
                top.linkTo(stateText.bottom, 5.dp)
                start.linkTo(startGuideline)
                end.linkTo(guidelineFromCenter, 2.dp)
                width = Dimension.fillToConstraints
            }, colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black
            ), onTextChange = onStateChange
        )

        CustomText(
            text = AppString.postal_code,
            modifier = Modifier.constrainAs(postalCodeText) {
                top.linkTo(countryTF.bottom, 10.dp)
                start.linkTo(guidelineFromCenter, 2.dp)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            },
            fontSize = 15.sp,
            textStyle = FontWeight.Medium
        )
        CustomTextFieldWithPlaceholder(
            placeholderText = AppString.postal_ex,
            receivedText = currentAddress?.postalCode ?: "",
            modifier = Modifier.constrainAs(postalCodeTF) {
                top.linkTo(postalCodeText.bottom, 5.dp)
                start.linkTo(guidelineFromCenter, 2.dp)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLength = 6,
            onTextChange = onPostalCodeChange
        )

        CustomButton(
            buttonText = AppString.proceed,
            modifier = Modifier.constrainAs(proceedBtn) {
                top.linkTo(postalCodeTF.bottom, 20.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }, btnTextFontSize = 16.sp, btnTextColor = Color.White
        ) {
            onProceedClick()
        }
    }
}
