package com.futurecoder.eshopp.utils

import com.futurecoder.eshopp.data.OnBoardingData
import com.futurecoder.eshopp.R.string as AppString
import com.futurecoder.eshopp.R.drawable as AppDrawable

object EShoppHelper {
    fun getListOfPlayers(): List<String> {
        return arrayListOf<String>(
            "Virendra Sehwag",
            "Sachin Tendulkar",
            "Gautam Gambhir",
            "Virat Kohli",
            "Yuvraj Singh",
            "MS Dhoni",
            "Yusuf Pathan",
            "Harbhajan Singh",
            "Munaf Patel",
            "Zaheer Khan",
            "S. Sreesanth"
        )
    }

    fun getOnBoardingData(): List<OnBoardingData> {
        return listOf(
            OnBoardingData(
                AppDrawable.purchase_online,
                AppString.order,
                AppString.visit_online_shop
            ),
            OnBoardingData(
                AppDrawable.pay_online,
                AppString.pay,
                AppString.make_your_purchase
            ),
            OnBoardingData(
                AppDrawable.online_delivery,
                AppString.delivery,
                AppString.estimated_delivery
            )
        )
    }
}