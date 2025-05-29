package com.example.recentreleasesamplse

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recentreleasesamplse.ui.theme.RecentReleaseSamplseTheme

@Composable
fun OrderScreen(viewModel: OrderViewModel = viewModel()) {
    val orderState by viewModel.orderState.collectAsState()

    LaunchedEffect(orderState) {
        Log.d("OrderScreen", "OrderScreen: $orderState")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BAKLAVA) {
            LiveUpdatesNotificationManager.updateNotificationStatus(orderState)
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.android16logo),
            contentDescription = "Delivery Icon",
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Order status: ${
                when (orderState) {
                    is OrderState.Confirmed -> "Order Confirmed"
                    is OrderState.Preparing -> "Preparing your food"
                    is OrderState.Enroute -> "Food is on the way"
                    is OrderState.Arriving -> "Arriving shortly"
                    is OrderState.Delivered -> "Delivered 🎉"
                }
            }",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevOrderScreen() {
    RecentReleaseSamplseTheme {
        OrderScreen()
    }

}