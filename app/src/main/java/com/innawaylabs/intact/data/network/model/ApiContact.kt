package com.innawaylabs.intact.data.network.model

data class ApiContact(
    val contactId: String,
    val firstName: String,
    val lastName: String,
    val profilePictureURL: String,
    val emailAddress: String,
    val phoneNumber: String,
    val lastOnlineTimestamp: String
)