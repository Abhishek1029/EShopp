@file:OptIn(ExperimentalMaterial3Api::class)

package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.futurecoder.eshopp.data.Address
import com.futurecoder.eshopp.ui.composefiles.customwidgets.BasicDialog
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText
import com.futurecoder.eshopp.ui.theme.EShoppTheme
import com.futurecoder.eshopp.utils.generateActualAddress
import com.futurecoder.eshopp.viewmodels.AddressViewModel
import com.futurecoder.eshopp.R.string as AppString

@Composable
fun AddressScreen(
    addressViewModel: AddressViewModel = hiltViewModel(),
    onEditClick: (Long) -> Unit,
    onAddManuallyClick: () -> Unit
) {
    var showDeleteDialog by remember {
        mutableStateOf(false)
    }
    val addressList = addressViewModel.addressSF.collectAsStateWithLifecycle()
    if (showDeleteDialog) {
        BasicDialog(
            headingText = stringResource(id = AppString.delete_address),
            subHeadingText = stringResource(id = AppString.do_you_really_want_to_delete),
            onDialogDismiss = {
                showDeleteDialog = false
            },
            onNegativeButtonClick = {
                showDeleteDialog = false
            }) {
            addressViewModel.deleteAddress(addressViewModel.addressIdToDelete)
            showDeleteDialog = false
        }
    }
    Address(
        addressList = addressList.value,
        onEditClick = onEditClick,
        onDeleteClick = {
            addressViewModel.addressIdToDelete = it
            showDeleteDialog = true
        },
        onAddManuallyClick = onAddManuallyClick
    )
}


@Composable
fun Address(
    addressList: List<Address> = emptyList(),
    onEditClick: (Long) -> Unit,
    onDeleteClick: (Long) -> Unit,
    onAddManuallyClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            onClick = {
            }, modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 15.dp, top = 3.dp, end = 15.dp, bottom = 3.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ), shape = RectangleShape, elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp,
                pressedElevation = 10.dp
            )
        ) {
            CustomText(
                text = AppString.fetch_current_address,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                fontSize = 18.sp,
                textStyle = FontWeight.Medium,
                textAlignment = TextAlign.Center
            )
        }

        CustomText(
            text = AppString.or,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .wrapContentHeight(Alignment.CenterVertically)
                .wrapContentWidth(Alignment.CenterHorizontally),
            fontSize = 18.sp,
            textAlignment = TextAlign.Center
        )

        ElevatedCard(
            onClick = {
                onAddManuallyClick()
            }, modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 15.dp, top = 3.dp, end = 15.dp, bottom = 3.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ), shape = RectangleShape, elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp,
                pressedElevation = 10.dp
            )
        ) {
            CustomText(
                text = AppString.add_address_manually,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                fontSize = 18.sp,
                textStyle = FontWeight.Medium,
                textAlignment = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        CustomText(
            text = AppString.saved_addresses,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(Alignment.CenterVertically)
                .background(Color.LightGray)
                .padding(
                    start = 15.dp,
                    top = 15.dp,
                    bottom = 15.dp
                ),
            fontSize = 16.sp
        )

        LazyColumn(
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(addressList) { address ->
                AddressItem(
                    address,
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick
                )
            }
        }
    }
}

@Composable
fun AddressItem(
    address: Address,
    onEditClick: (Long) -> Unit,
    onDeleteClick: (Long) -> Unit
) {

    Column {
        CustomText(
            text = address.generateActualAddress(),
            fontSize = 16.sp,
            textColor = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            CustomText(text = AppString.edit, modifier = Modifier.clickable {
                onEditClick(address.id)
            }, textColor = Color.Magenta, fontSize = 18.sp)
            CustomText(text = AppString.delete, modifier = Modifier.clickable {
                onDeleteClick(address.id)
            }, textColor = Color.Magenta, fontSize = 18.sp)
        }
    }
}

@Preview
@Composable
fun AddressScreenPreview() {
    EShoppTheme {
        AddressScreen(
            onEditClick = {}
        ) {

        }
    }
}