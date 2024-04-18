package com.innawaylabs.intact.data.model

import java.time.LocalDate

data class Contact(
    val contactId: String,
    val firstName: String,
    val lastName: String,
    val profilePictureURL: String,
    val lastOnlineTimestamp: LocalDate
)
