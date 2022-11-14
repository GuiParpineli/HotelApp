package com.example.fragments.model

data class Hotel(
    val id: Long = 0,
    val name: String = "",
    val address: String = "",
    val rating: Float = 0.0F
) {
    override fun toString(): String = name
}
