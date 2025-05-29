package com.example.recentreleasesamplse

sealed class OrderState {
    data object Confirmed : OrderState()
    data object Preparing : OrderState()
    data object Enroute : OrderState()
    data object Arriving : OrderState()
    data object Delivered : OrderState()
}