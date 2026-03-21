package com.example.recentreleasesamplse

//import androidx.compose.material3.FlexibleBottomAppBar
import android.app.Activity
import android.app.NotificationManager
import android.app.StatusBarManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsPickerSessionContract
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.IconCompat
import androidx.photopicker.compose.ExperimentalPhotoPickerComposeApi
import com.example.recentreleasesamplse.ui.theme.RecentReleaseSamplseTheme


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


                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        ContactPickerDemo()
                    }

                }
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

    val context = LocalContext.current
    // Define the specific data fields you need
    val requestedFields = arrayListOf(
//        ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER,
//        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
//        ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE,
        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
        ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE
        //CONTENT_ITEM_TYPE
    )

    val contactPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Log.d("ContactPickerDemo", "Data is: ${result.data}")
            // The result data contains the Session URI
            val sessionUri = result.data?.data
            Log.d("SessionURI", "ContactPickerDemo: $sessionUri")
            sessionUri?.let { uri ->
                // Create a "Data" URI based on the specific contact you picked
                // This maintains the temporary URI permission grant
                val detailUri =
                    Uri.withAppendedPath(uri, ContactsContract.Contacts.Data.CONTENT_DIRECTORY)

                val projection = arrayOf(
                    ContactsContract.Data.MIMETYPE,
                    ContactsContract.Data.DATA1,
                    ContactsContract.Data.DISPLAY_NAME
                )

                // Query the detailUri, NOT ContactsContract.Data.CONTENT_URI
                context.contentResolver.query(detailUri, projection, null, null, null)
                    ?.use { cursor ->
                        val mimeIdx = cursor.getColumnIndex(ContactsContract.Data.MIMETYPE)
                        val dataIdx = cursor.getColumnIndex(ContactsContract.Data.DATA1)
                        val nameIdx = cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME)

                        while (cursor.moveToNext()) {
                            val mimeType = cursor.getString(mimeIdx)
                            val value = cursor.getString(dataIdx)
                            val name = cursor.getString(nameIdx)

                            when (mimeType) {
                                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE -> Log.d(
                                    "Picker",
                                    "Phone: $value"
                                )

                                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE -> Log.d(
                                    "Picker",
                                    "Email: $value"
                                )
                            }
                        }
                    }
            }
        }
    }


// Set up the intent
//    val pickContactIntent = Intent("android.intent.action.PICK_CONTACTS"/*ContactsPickerSessionContract.ACTION_PICK_CONTACTS*/).apply {
    val pickContactIntent = Intent("android.provider.action.PICK_CONTACTS"/*ContactsPickerSessionContract.ACTION_PICK_CONTACTS*/).apply {
//    val pickContactIntent = Intent(Intent.ACTION_PICK).apply {
//        data =
//        putExtra(EXTRA_USE_SYSTEM_CONTACTS_PICKER, true)
        type = ContactsContract.Contacts.CONTENT_TYPE
        // Enable multi-select
        // putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
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
        Text("Pick Contact!", style = MaterialTheme.LocalMaterialTheme.current.typography.headlineLarge)
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

private fun processSelectedContacts(sessionUri: Uri, context: Context) {
    // Define the projection (columns) you want to retrieve
    // Define the projection (columns) you want to retrieve
    val projection = arrayOf(
        ContactsContract.Data.CONTACT_ID,
        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
        ContactsContract.Data.MIMETYPE,
        ContactsContract.Data.DATA1 // Generic data column (Phone number, Email, etc.)
    )


    context.contentResolver.query(sessionUri, projection, null, null, null)?.use { cursor ->
        val mimeTypeIdx = cursor.getColumnIndex(ContactsContract.Data.MIMETYPE)
        val dataIdx = cursor.getColumnIndex(ContactsContract.Data.DATA1)
        val nameIdx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
// Safety check: if any index is -1, your projection is missing a column
        if (mimeTypeIdx == -1 || dataIdx == -1 || nameIdx == -1) {
            Log.e("ContactPicker", "Missing columns in projection!")
            return
        }
        while (cursor.moveToNext()) {
            val mimeType = cursor.getString(mimeTypeIdx)
            val dataValue = cursor.getString(dataIdx)
            val name = cursor.getString(nameIdx)

            when (mimeType) {
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE -> {
                    Log.d("ContactPicker", "Picked Phone: $dataValue for $name")
                }

                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE -> {
                    Log.d("ContactPicker", "Picked Email: $dataValue for $name")
                }
            }
        }
    }
}

