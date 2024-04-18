package com.innawaylabs.intact.presentation.ui

import androidx.lifecycle.ViewModel
import com.innawaylabs.intact.data.model.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

class ContactViewModel : ViewModel() {
    private val _contactsState = MutableStateFlow<List<Contact>>(emptyList())
    val contactsState: StateFlow<List<Contact>> = _contactsState

    init {
        fetchContacts()
    }

    public fun getContact(contactId: String): Contact? {
        return contactsState.value.find {contact ->
            contact.contactId == contactId
        }
    }

    private fun fetchContacts() {
        val mockContacts = listOf(
            Contact(
                contactId = "1",
                firstName = "Ravi",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/ravi.jpg",
                lastOnlineTimestamp = LocalDate.now()
            ),
            Contact(
                contactId = "2",
                firstName = "Rohan",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/rohan.jpg",
                lastOnlineTimestamp = LocalDate.now().minusDays(1)
            ),
            Contact(
                contactId = "2",
                firstName = "Suhaas",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/suhaas.jpg",
                lastOnlineTimestamp = LocalDate.now().minusMonths(1)
            ),
            Contact(
                contactId = "4",
                firstName = "Hima Sailaja",
                lastName = "Alapati",
                profilePictureURL = "http://www.innawaylabs.com/profiles/sailu.jpg",
                lastOnlineTimestamp = LocalDate.now().minusMonths(1)
            ),
            Contact(
                contactId = "5",
                firstName = "Ravi",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/ravi.jpg",
                lastOnlineTimestamp = LocalDate.now()
            ),
            Contact(
                contactId = "6",
                firstName = "Rohan",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/rohan.jpg",
                lastOnlineTimestamp = LocalDate.now().minusDays(1)
            ),
            Contact(
                contactId = "7",
                firstName = "Suhaas",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/suhaas.jpg",
                lastOnlineTimestamp = LocalDate.now().minusMonths(1)
            ),
            Contact(
                contactId = "8",
                firstName = "Hima Sailaja",
                lastName = "Alapati",
                profilePictureURL = "http://www.innawaylabs.com/profiles/sailu.jpg",
                lastOnlineTimestamp = LocalDate.now().minusMonths(1)
            ),
            Contact(
                contactId = "9",
                firstName = "Ravi",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/ravi.jpg",
                lastOnlineTimestamp = LocalDate.now()
            ),
            Contact(
                contactId = "10",
                firstName = "Rohan",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/rohan.jpg",
                lastOnlineTimestamp = LocalDate.now().minusDays(1)
            ),
            Contact(
                contactId = "11",
                firstName = "Suhaas",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/suhaas.jpg",
                lastOnlineTimestamp = LocalDate.now().minusMonths(1)
            ),
            Contact(
                contactId = "12",
                firstName = "Hima Sailaja",
                lastName = "Alapati",
                profilePictureURL = "http://www.innawaylabs.com/profiles/sailu.jpg",
                lastOnlineTimestamp = LocalDate.now().minusMonths(1)
            )
        )
        _contactsState.value = mockContacts
    }
}