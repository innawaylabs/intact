@file:OptIn(ExperimentalMaterial3Api::class)

package com.innawaylabs.intact.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.api.services.people.v1.model.EmailAddress
import com.innawaylabs.intact.R
import com.innawaylabs.intact.data.model.Contact
import com.innawaylabs.intact.ui.theme.IntactTheme
import java.time.LocalDate
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        setContent {
            IntactTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun ContactsListScreen(navController: NavController, viewModel: ContactViewModel) {
    val contacts = viewModel.contactsState.collectAsState().value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(contacts) { contact ->
                ContactCard(contact, navController)
            }
        }
        FloatingActionButton(
            onClick = { navController.navigate("addContact") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Add contact")
        }
    }
}

@Composable
fun ContactCard(contact: Contact, navController: NavController) {
    val context = LocalContext.current

    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable {
            navController.navigate("contactDetail/${contact.contactId}")
            Toast
                .makeText(
                    context,
                    "Clicked on ${contact.firstName} ${contact.lastName}",
                    Toast.LENGTH_SHORT
                )
                .show()
        }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp)
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.androidparty),
                contentDescription = stringResource(id = R.string.profile_picture_description)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = contact.firstName + " " + contact.lastName)
                Text(text = "Last online: ${contact.lastOnlineTimestamp}")
            }
        }
    }
}

@Composable
fun ContactDetailsScreen(navController: NavController, viewModel: ContactViewModel, contactId: String) {
    val contact = remember {
        viewModel.getContact(contactId)
    }
    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(
            title = { contact?.let { Text(text = "${contact.firstName} ${contact.lastName}")} },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button_description)
                    )
                }
            }
        )
        Image(
            modifier = Modifier
                .width(240.dp)
                .height(240.dp)
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.androidparty),
            contentDescription = stringResource(id = R.string.profile_picture_description)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "${contact?.firstName} ${contact?.lastName}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "ravikumar.mandala@gmail.com")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Ph. +1 650 284 6502")
    }
}

@Composable
fun AppNavigation(viewModel: ContactViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "contactsList") {
        composable("contactsList") {
            ContactsListScreen(navController = navController, viewModel = viewModel)
        }
        composable("contactDetail/{contactId}") { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("contactId")?.let { contactId ->
                ContactDetailsScreen(
                    navController = navController,
                    viewModel = viewModel,
                    contactId = contactId
                )
            }
        }
        composable("addContact") {
            AddContactScreen(navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
fun AddContactScreen(navController: NavHostController, viewModel: ContactViewModel) {
    var firstName = "Ravi"
    var lastName = "Mandala"
    var email = "ravi@gmail.com"
    var phoneNumber = "+1 650 284 6502"

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = "Add Contact") },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button_description)
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text(text = "First Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text(text = "Last Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email Address") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text(text = "Phone Number") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.addContact(
                    Contact(
                        UUID.randomUUID().toString(),
                        firstName,
                        lastName,
                        "",
                        EmailAddress().setValue(email),
                        phoneNumber,
                        LocalDate.now()
                    )
                )
                navController.navigateUp()
            }
        ) {
            Text(text = "Save Contact")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppNavigation() {
    IntactTheme {
        AppNavigation(ContactViewModel())
    }
}