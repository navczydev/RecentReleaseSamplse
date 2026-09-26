package com.example.recentreleasesamplse

//import androidx.compose.material3.FlexibleBottomAppBar
import android.app.Activity
import android.app.NotificationManager
import android.app.StatusBarManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_USE_SYSTEM_CONTACTS_PICKER
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsPickerSessionContract
import android.provider.ContactsPickerSessionContract.EXTRA_PICK_CONTACTS_MATCH_ALL_DATA_FIELDS
import android.provider.ContactsPickerSessionContract.EXTRA_PICK_CONTACTS_SELECTION_LIMIT
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.text.selection.rememberSelectionState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.IconCompat
import androidx.photopicker.compose.ExperimentalPhotoPickerComposeApi
import com.example.recentreleasesamplse.ui.theme.RecentReleaseSamplseTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"

    @OptIn(ExperimentalPhotoPickerComposeApi::class, ExperimentalMaterial3Api::class)
    @RequiresExtension(extension = Build.VERSION_CODES.UPSIDE_DOWN_CAKE, version = 15)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getSystemService<StatusBarManager?>(StatusBarManager::class.java)

        ComponentName(
            this.applicationContext,
            MyQSTileService::class.java.name
        )

        IconCompat.createWithResource(
            applicationContext,
            R.drawable.ic_launcher_foreground,
        )
        enableEdgeToEdge()
        setContent {
            RecentReleaseSamplseTheme {
                LocalContext.current

                val notificationManager =
                    LocalContext.current.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                LiveUpdatesNotificationManager.initialize(
                    LocalContext.current.applicationContext,
                    notificationManager
                )


                /*    Scaffold(
                        modifier = Modifier
                            //.fillMaxSize()
                            .safeDrawingPadding()
                    ) { innerPadding ->*/
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
//                        ContactPickerDemo()
                    Spacer(modifier = Modifier.height(64.dp))
                    Text(
                        "Find food nearby: To discover hidden gems, " +
                                "view top-rated spots in your neighborhood, " +
                                "and get walking directions to dinner, " +
                                "please grant location access."
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    LocationPermissionScreen({}) {}
                    //ProgrammaticSelectionExample()
//                        FlexBoxFlexDemo()
                }

                // }
            }
        }
    }

}


@Composable
fun EyeDropperDemo() {
    var pickedColor by remember { mutableStateOf(Color.Black) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.getIntExtra(
                Intent.EXTRA_COLOR,
                Color.Black.value.toInt()
            )?.let { colorInt ->
                Log.d("EyeDropperDemo", "EyeDropperDemo: Picked color: $colorInt")
                pickedColor = Color(colorInt)
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(R.drawable.android17),
            ""
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("EyeDropper API Sample!", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Launch EyeDropper intent (Android 17+)
                val intent = Intent(Intent.ACTION_OPEN_EYE_DROPPER)
                launcher.launch(intent)
            }
        ) {
            Text("Pick Color 🎨 from Screen")
        }

        // Show picked color
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(pickedColor)
        )
    }
}

@Composable
fun ContactPickerDemo(modifier: Modifier = Modifier) {
    val coroutine = rememberCoroutineScope()
    var contacts = emptyList<Contact>()
    val context = LocalContext.current
    // Define the specific data fields you need
    val requestedFields = arrayListOf(
        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
        ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE,
        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE,
    )

    val contactPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Log.d("ContactPickerDemo", "Data is: ${result.data}")
            val resultUri = result.data?.data ?: return@rememberLauncherForActivityResult
            // Process the result URI in a background thread to fetch all selected contacts
            coroutine.launch {
                contacts = processContactPickerResultUri(resultUri, context)
                Log.d("TAG", "ContactPickerDemo: Contacts $contacts")
            }
        }
    }


    // Set up the intent
    val pickContactIntent = Intent(ContactsPickerSessionContract.ACTION_PICK_CONTACTS).apply {
        // Enable multi-select
        putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        // Set limit of selectable contacts
        putExtra(EXTRA_PICK_CONTACTS_SELECTION_LIMIT, 5)
        // Enable this option to only filter contacts that have all the requested data fields
        putExtra(EXTRA_PICK_CONTACTS_MATCH_ALL_DATA_FIELDS, false)
        putExtra(EXTRA_USE_SYSTEM_CONTACTS_PICKER, true)
        putStringArrayListExtra(
            ContactsPickerSessionContract.EXTRA_PICK_CONTACTS_REQUESTED_DATA_FIELDS,
            requestedFields
        )
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(R.drawable.android17),
            ""
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Pick Contact!",
            style = MaterialTheme.LocalMaterialTheme.current.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Launch the picker
                contactPickerLauncher.launch(pickContactIntent)

            }
        ) {
            Text("Pick contact 📱")
        }

        // Show picked color
        Spacer(modifier = Modifier.height(16.dp))
    }
}


// Data class representing a parsed Contact with selected details.
data class Contact(
    val lookupKey: String,
    val name: String,
    val emails: List<String>,
    val phones: List<String>
)

// Helper function to query the content resolver with the URI returned by the Contact Picker.
// Parses the cursor to extract contact details such as name, email, and phone number.
private suspend fun processContactPickerResultUri(
    sessionUri: Uri,
    context: Context
): List<Contact> = withContext(Dispatchers.IO) {
    // Define the columns we want to retrieve from the ContactPicker ContentProvider
    val projection = arrayOf(
        ContactsContract.Contacts.LOOKUP_KEY,
        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
        ContactsContract.Data.MIMETYPE, // Type of data (e.g., email or phone)
        ContactsContract.Data.DATA1, // The actual data (Phone number / Email string)
        ContactsContract.Data.DATA2, // The actual data (Phone number / Email string)
    )

    // We use `LOOKUP_KEY` as a unique ID to aggregate all contact info related to a same person
    val contactsMap = mutableMapOf<String, Contact>()

    // Note: The Contact Picker Session Uri doesn't support custom selection & selectionArgs.
    // We query the URI directly to get the results chosen by the user.
    context.contentResolver.query(sessionUri, projection, null, null, null)?.use { cursor ->
        // Get the column indices for our requested projection
        val lookupKeyIdx = cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)
        val mimeTypeIdx = cursor.getColumnIndex(ContactsContract.Data.MIMETYPE)
        val nameIdx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
        val data1Idx = cursor.getColumnIndex(ContactsContract.Data.DATA1)
        val data2Idx = cursor.getColumnIndex(ContactsContract.Data.DATA2)
        val data6Idx = cursor.getColumnIndex(ContactsContract.Data.DATA6)
        Log.d("TAG", "processContactPickerResultUri: data6Idx $data6Idx")

        while (cursor.moveToNext()) {
            val lookupKey = cursor.getString(lookupKeyIdx)
            val mimeType = cursor.getString(mimeTypeIdx)
            val name = cursor.getString(nameIdx).orEmpty()
            val data1 = cursor.getString(data1Idx) ?: ""
            val data2 = cursor.getString(data2Idx).orEmpty()
            val data6 = if (data6Idx == -1) "" else cursor.getString(data6Idx).orEmpty()
            Log.d("TAG", "processContactPickerResultUri: Data2 $data2")
            Log.d("TAG", "processContactPickerResultUri: Data6 $data6")
            Log.d("TAG", "processContactPickerResultUri: mimeType $mimeType")

            val email =
                if (mimeType == ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE) data1 else null
            val phone =
                if (mimeType == ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE) data1 else null
            val nameS =
                if (mimeType == ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE) data2 else null
            Log.d("TAG", "processContactPickerResultUri: Email $email")
            Log.d("TAG", "processContactPickerResultUri: Phone $phone")
            Log.d("TAG", "processContactPickerResultUri: Name $name")
            Log.d("TAG", "processContactPickerResultUri: NameS $nameS")
            Log.d("TAG", "===========================================")

            val existingContact = contactsMap[lookupKey]
            if (existingContact != null) {
                contactsMap[lookupKey] = existingContact.copy(
                    emails = if (email != null) existingContact.emails + email else existingContact.emails,
                    phones = if (phone != null) existingContact.phones + phone else existingContact.phones
                )
            } else {
                contactsMap[lookupKey] = Contact(
                    lookupKey = lookupKey,
                    name = name,
                    emails = if (email != null) listOf(email) else emptyList(),
                    phones = if (phone != null) listOf(phone) else emptyList()
                )
            }
        }
    }

    return@withContext contactsMap.values.toList()
}


@Preview(showBackground = true, device = "id:pixel_7_pro", showSystemUi = true)
@Composable
fun ProgrammaticSelectionExample() {
    val selectionState = rememberSelectionState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { selectionState.selectAll() },
            ) {
                Text("Select All")
            }

            Button(
                onClick = { selectionState.clear() },
                //            modifier = Modifier.disableSelectionClearOnTap()
            ) {
                Text("Clear selection")
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { selectionState.select(TextRange(0, 4)) },
                //            modifier = Modifier.disableSelectionClearOnTap()
            ) {
                Text("Range selection")
            }
            Button(
                onClick = { selectionState.extendSelectionByWord() },
                //            modifier = Modifier.disableSelectionClearOnTap()
            ) {
                Text("Extend selection")
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        SelectionContainer(state = selectionState) {
            Column {
                Text(
                    "Text content to be selected programmatically.",
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    "2nd text component",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }

        Text(text = "Selected text: ${selectionState.getSelectableTexts()}")
    }
}