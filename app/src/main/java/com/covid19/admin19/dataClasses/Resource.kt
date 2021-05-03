package com.covid19.admin19.dataClasses

data class Resource(
    var id: String,
    var Name: String,
    val city: String,
    val state: String,
    val provider: String,
    val contact: String,
    val address: String,
    val verifier: String,
    val more: String,
    val time: String
)
