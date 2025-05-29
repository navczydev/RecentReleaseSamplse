package com.example.recentreleasesamplse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class OrderViewModel : ViewModel() {

    val orderState: StateFlow<OrderState> = getOrderStatusFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = OrderState.Confirmed
    )


    private fun getOrderStatusFlow(): Flow<OrderState> = flow {

        delay(5_000)

        emit(OrderState.Preparing)

        delay(10_000)

        emit(OrderState.Enroute)

        delay(15_000)

        emit(OrderState.Arriving)

        delay(20_000)

        emit(OrderState.Delivered)
    }
}