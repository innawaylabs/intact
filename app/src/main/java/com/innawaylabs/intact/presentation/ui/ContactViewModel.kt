package com.innawaylabs.intact.presentation.ui

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.api.services.people.v1.model.EmailAddress
import com.innawaylabs.intact.data.model.Contact
import com.innawaylabs.intact.data.network.ContactApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

class ContactViewModel : ViewModel() {
    private val _contactsState = MutableStateFlow<List<Contact>>(emptyList())
    val contactsState: StateFlow<List<Contact>> = _contactsState
    val retrofit = Retrofit.Builder()
        .baseUrl("https://your-api-url.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ContactApi::class.java)

    init {
        fetchContacts()
    }

    fun getContact(contactId: String): Contact? {
        return contactsState.value.find {contact ->
            contact.contactId == contactId
        }
    }

    fun addContact(contact: Contact) {
        val currentContacts = contactsState.value.toMutableList()
        currentContacts.add(contact)
        _contactsState.value = currentContacts
    }

    private fun fetchContacts() {
        viewModelScope.launch {
            try {
                val contactsFromApi = api.getContacts()
                val contacts = contactsFromApi.map { apiContact ->
                    Contact(
                        contactId = apiContact.contactId,
                        firstName = apiContact.firstName,
                        lastName = apiContact.lastName,
                        profilePictureURL = apiContact.profilePictureURL,
                        emailAddress = EmailAddress().setValue(apiContact.emailAddress),
                        phoneNumber = apiContact.phoneNumber,
                        lastOnlineTimestamp = LocalDate.now()
                    )
                }
                _contactsState.value = contacts
            } catch (e: Exception) {
                // Handle exception
            }
        }
        val mockContacts = listOf(
            Contact(
                contactId = "1",
                firstName = "Ravi",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/ravi.jpg",
                emailAddress = EmailAddress().setValue("ravi@gmail.com"),
                phoneNumber = "+1 234 567 8900",
                lastOnlineTimestamp = LocalDate.now()
            ),
            Contact(
                contactId = "2",
                firstName = "Rohan",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/rohan.jpg",
                emailAddress = EmailAddress().setValue("ravi@gmail.com"),
                phoneNumber = "+1 234 567 8900",
                lastOnlineTimestamp = LocalDate.now().minusDays(1)
            ),
            Contact(
                contactId = "3",
                firstName = "Suhaas",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/suhaas.jpg",
                emailAddress = EmailAddress().setValue("ravi@gmail.com"),
                phoneNumber = "+1 234 567 8900",
                lastOnlineTimestamp = LocalDate.now().minusMonths(1)
            ),
            Contact(
                contactId = "4",
                firstName = "Hima Sailaja",
                lastName = "Alapati",
                profilePictureURL = "http://www.innawaylabs.com/profiles/sailu.jpg",
                emailAddress = EmailAddress().setValue("ravi@gmail.com"),
                phoneNumber = "+1 234 567 8900",
                lastOnlineTimestamp = LocalDate.now().minusMonths(1)
            ),
            Contact(
                contactId = "5",
                firstName = "Ravi",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/ravi.jpg",
                emailAddress = EmailAddress().setValue("ravi@gmail.com"),
                phoneNumber = "+1 234 567 8900",
                lastOnlineTimestamp = LocalDate.now()
            ),
            Contact(
                contactId = "6",
                firstName = "Rohan",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/rohan.jpg",
                emailAddress = EmailAddress().setValue("ravi@gmail.com"),
                phoneNumber = "+1 234 567 8900",
                lastOnlineTimestamp = LocalDate.now().minusDays(1)
            ),
            Contact(
                contactId = "7",
                firstName = "Suhaas",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/suhaas.jpg",
                emailAddress = EmailAddress().setValue("ravi@gmail.com"),
                phoneNumber = "+1 234 567 8900",
                lastOnlineTimestamp = LocalDate.now().minusMonths(1)
            ),
            Contact(
                contactId = "8",
                firstName = "Hima Sailaja",
                lastName = "Alapati",
                profilePictureURL = "http://www.innawaylabs.com/profiles/sailu.jpg",
                emailAddress = EmailAddress().setValue("ravi@gmail.com"),
                phoneNumber = "+1 234 567 8900",
                lastOnlineTimestamp = LocalDate.now().minusMonths(1)
            ),
            Contact(
                contactId = "9",
                firstName = "Ravi",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/ravi.jpg",
                emailAddress = EmailAddress().setValue("ravi@gmail.com"),
                phoneNumber = "+1 234 567 8900",
                lastOnlineTimestamp = LocalDate.now()
            ),
            Contact(
                contactId = "10",
                firstName = "Rohan",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/rohan.jpg",
                emailAddress = EmailAddress().setValue("ravi@gmail.com"),
                phoneNumber = "+1 234 567 8900",
                lastOnlineTimestamp = LocalDate.now().minusDays(1)
            ),
            Contact(
                contactId = "11",
                firstName = "Suhaas",
                lastName = "Mandala",
                profilePictureURL = "http://www.innawaylabs.com/profiles/suhaas.jpg",
                emailAddress = EmailAddress().setValue("ravi@gmail.com"),
                phoneNumber = "+1 234 567 8900",
                lastOnlineTimestamp = LocalDate.now().minusMonths(1)
            ),
            Contact(
                contactId = "12",
                firstName = "Hima Sailaja",
                lastName = "Alapati",
                profilePictureURL = "http://www.innawaylabs.com/profiles/sailu.jpg",
                emailAddress = EmailAddress().setValue("ravi@gmail.com"),
                phoneNumber = "+1 234 567 8900",
                lastOnlineTimestamp = LocalDate.now().minusMonths(1)
            )
        )
        _contactsState.value = mockContacts
    }
}