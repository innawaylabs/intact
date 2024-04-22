package com.innawaylabs.intact.data.model

import com.google.api.services.people.v1.model.EmailAddress
import java.time.LocalDate

data class Contact(
    val contactId: String,
    val firstName: String,
    val lastName: String,
    val profilePictureURL: String,
    val emailAddress: EmailAddress,
    val phoneNumber: String,
    val lastOnlineTimestamp: LocalDate
)
