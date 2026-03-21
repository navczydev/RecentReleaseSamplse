package com.example.recentreleasesamplse

//import androidx.compose.material3.FlexibleBottomAppBar
import android.R.attr.type
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
//                            .verticalScroll(rememberScrollState())
                    ) {
//                        EyeDropperDemo()
                        ContactPickerDemo()
                        /*  OneLineListItem()
                          TwoLineListItem()
                          ThreeLineListItemWithOverlineAndSupporting()
                          ThreeLineListItemWithExtendedSupporting()
                          ClickableListItemSample()
                          ClickableListItemWithClickableChildSample()
                          SingleSelectionListItemSample()
                          MultiSelectionListItem()
                          ListItemWithModeChangeOnLongClickSample()
                          SingleSelectionSegmentedListItem()
                          MultiSelectionSegmentedListItem()
                          SegmentedListItemWithExpansion()*/
                    }

                    /* var shouldShow by remember {
                         mutableStateOf(false)
                     }
                     val state = rememberEmbeddedPhotoPickerState(
                         onSelectionComplete = {
                             Log.d(TAG, "onCreate: Selection done!")
                         }
                     )
                     val updateVisibility = remember {
                         mutableStateOf(true)
                     }

                     Log.d(TAG, "onCreate: ${state.isReady}, ${updateVisibility.value}")
                     *//*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*//*
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(64.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // This box will not be visible
                        // but will still occupy 200.dp x 200.dp of space.
                        Box(modifier = Modifier.border(
                            width = 16.dp,
                            color = Color.Blue
                        )) {
                            Box(
                                modifier = Modifier
                                    .size(200.dp)
                                    .background(Color.Red)
                                    .border(
                                        width = 8.dp,
                                        color = Color.Cyan
                                    )
                                    .visible(false)
                                    .background(Color.Yellow)
                                    .border(
                                        width = 14.dp,
                                        color = Color.Green
                                    )
                            )
                        }
                        Text("Invisible text!",
                            modifier = Modifier
                                    .alpha(0f))
                        Button(onClick = {
                            shouldShow = !shouldShow
                        }, modifier = Modifier.alpha(0f)) {
                            Text("Update")
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        AnimatedVisibility(shouldShow) {
                            Text("Text under AnimatedVisibility!")
                        }
                        DynamicPositionTooltipSample()
                        PlainTooltipWithCaretRightOfAnchor()
                        var passwordHidden by rememberSaveable { mutableStateOf(true) }
                        SecureTextField(
                            state = rememberTextFieldState(),
                            label = { Text("Enter password") },
                            textObfuscationMode =
                                if (passwordHidden) TextObfuscationMode.RevealLastTyped
                                else TextObfuscationMode.Visible,
                            trailingIcon = {
                                // Provide localized description for accessibility services
                                val description =
                                    if (passwordHidden) "Show password" else "Hide password"
                                TooltipBox(
                                    positionProvider =
                                        TooltipDefaults.rememberTooltipPositionProvider(
                                            TooltipAnchorPosition.Above
                                        ),
                                    tooltip = { PlainTooltip { Text(description) } },
                                    state = rememberTooltipState(),
                                ) {
                                    IconButton(onClick = { passwordHidden = !passwordHidden }) {
                                        val visibilityIcon =
                                            if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                                        Icon(
                                            imageVector = visibilityIcon,
                                            contentDescription = description
                                        )
                                    }
                                }
                            },
                        )
                        // OrderScreen()
                        *//* if (updateVisibility.value) {
                             EmbeddedPhotoPicker(
                                 modifier = Modifier.fillMaxHeight(0.7f),
                                 state = state
                             )
                         }

                         OutlinedButton(
                             onClick = {
                                 state.onSelectionComplete()
                                 updateVisibility.value = false
                                 Log.d(TAG, "onCreate: ${state.selectedMedia}")
                             },
                             modifier = Modifier.fillMaxWidth()
                         ) {
                             Text("Done")
                         }

                         OutlinedButton(
                             onClick = {
                                 Log.d(TAG, "onCreate: Add tile")
                                 statusBarService?.requestAddTileService(
                                     componentName,
                                     "Quick settings..",
                                     icon.toIcon(context),
                                     mainExecutor
                                 ) { result ->
                                     Log.d(TAG, "onCreate: Result is $result")
                                 }
                             },
                             modifier = Modifier.fillMaxWidth()
                         ) {
                             Text("Request to add tile")
                         }*//*
                    }*/
                }
            }
        }
    }

}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var updateText by remember { mutableStateOf("") }
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            updateText = "Button Clicked ${Random(seed = 5).nextInt()}"
        }) {
            Text(text = "Click Me")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = updateText)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecentReleaseSamplseTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true)
@Composable
fun CenteredSliderSample() {
    val sliderState =
        rememberSliderState(
            valueRange = 0f..100f,
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
            value = 50f
        )
    val interactionSource = remember { MutableInteractionSource() }
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(text = "Value is: %.2f".format(sliderState.value))
        Slider(
            state = sliderState,
            interactionSource = interactionSource,
            thumb = { SliderDefaults.Thumb(interactionSource = interactionSource) },
            track = { SliderDefaults.CenteredTrack(sliderState = sliderState) }
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun OverflowingVerticalFloatingToolbarSample() {
    Scaffold(
        content = { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                LazyColumn(
                    state = rememberLazyListState(),
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val list = (0..25).map { it.toString() }
                    items(count = list.size) {
                        Text(
                            text = list[it],
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
                VerticalFloatingToolbar(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = -ScreenOffset),
                    expanded = true,
                    leadingContent = { },
                    trailingContent = {
                        AppBarColumn(
                            overflowIndicator = { menuState ->
                                IconButton(
                                    onClick = {
                                        if (menuState.isExpanded) {
                                            menuState.dismiss()
                                        } else {
                                            menuState.show()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.MoreVert,
                                        contentDescription = "Localized description"
                                    )
                                }
                            }
                        ) {
                            clickableItem(
                                onClick = { *//* doSomething() *//* },
                                icon = {
                                    Icon(
                                        Icons.Filled.Download,
                                        contentDescription = "Localized description"
                                    )
                                },
                                label = "Download"
                            )
                            clickableItem(
                                onClick = { *//* doSomething() *//* },
                                icon = {
                                    Icon(
                                        Icons.Filled.Favorite,
                                        contentDescription = "Localized description"
                                    )
                                },
                                label = "Favorite"
                            )
                            clickableItem(
                                onClick = { *//* doSomething() *//* },
                                icon = {
                                    Icon(
                                        Icons.Filled.Add,
                                        contentDescription = "Localized description"
                                    )
                                },
                                label = "Add"
                            )
                            clickableItem(
                                onClick = { *//* doSomething() *//* },
                                icon = {
                                    Icon(
                                        Icons.Filled.Person,
                                        contentDescription = "Localized description"
                                    )
                                },
                                label = "Person"
                            )
                            clickableItem(
                                onClick = { *//* doSomething() *//* },
                                icon = {
                                    Icon(
                                        Icons.Filled.ArrowUpward,
                                        contentDescription = "Localized description"
                                    )
                                },
                                label = "ArrowUpward"
                            )
                        }
                    },
                    content = {
                        FilledIconButton(
                            modifier = Modifier.height(64.dp),
                            onClick = { *//* doSomething() *//* }
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Localized description")
                        }
                    }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun HorizontalFloatingToolbarWithFabSample() {
    var expanded by rememberSaveable { mutableStateOf(true) }
    val vibrantColors = FloatingToolbarDefaults.vibrantFloatingToolbarColors()
    Scaffold { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    // Apply a floatingToolbarVerticalNestedScroll Modifier to the Column to toggle
                    // the expanded state of the HorizontalFloatingToolbar.
                    .floatingToolbarVerticalNestedScroll(
                        expanded = expanded,
                        onExpand = { expanded = true },
                        onCollapse = { expanded = false }
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = remember { LoremIpsum().values.first().take(800) })
            }
            HorizontalFloatingToolbar(
                expanded = expanded,
                floatingActionButton = {
                    // Match the FAB to the vibrantColors. See also StandardFloatingActionButton.
                    FloatingToolbarDefaults.VibrantFloatingActionButton(
                        onClick = { *//* doSomething() *//* },
                    ) {
                        Icon(Icons.Filled.Add, "Localized description")
                    }
                },
                modifier =
                    Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = -ScreenOffset, y = -ScreenOffset),
                colors = vibrantColors,
                content = {
                    IconButton(onClick = { *//* doSomething() *//* }) {
                        Icon(Icons.Filled.Person, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { *//* doSomething() *//* }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { *//* doSomething() *//* }) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { *//* doSomething() *//* }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Localized description")
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true)
@Composable
fun LoadingIndicatorSample() {
    Column(
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        LoadingIndicator()
        LoadingIndicator(
            polygons = LoadingIndicatorDefaults.IndeterminateIndicatorPolygons.take(2)
        )
        ContainedLoadingIndicator()
        ContainedLoadingIndicator(
            containerColor = Color.Cyan
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview(showBackground = true)
fun FilledSplitButtonSample() {
    var checked by remember { mutableStateOf(false) }
    var checked1 by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        SplitButtonLayout(
            leadingButton = {
                SplitButtonDefaults.LeadingButton(
                    onClick = { *//* Do Nothing *//* },
                ) {
                    Icon(
                        Icons.Filled.Edit,
                        modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                        contentDescription = "Localized description",
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("My Button")
                }
            },
            trailingButton = {
                SplitButtonDefaults.TrailingButton(
                    checked = checked,
                    onCheckedChange = { checked = it },
                ) {
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Localized description"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.size(16.dp))
        SplitButtonLayout(
            leadingButton = {
                SplitButtonDefaults.ElevatedLeadingButton(
                    onClick = { *//* Do Nothing *//* },
                ) {
                    Icon(
                        Icons.Filled.Edit,
                        modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                        contentDescription = "Localized description",
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("My Button")
                }
            },
            trailingButton = {
                SplitButtonDefaults.ElevatedTrailingButton(
                    checked = checked1,
                    onCheckedChange = { checked1 = it },
                ) {
                    val rotation: Float by
                    animateFloatAsState(
                        targetValue = if (checked1) 180f else 0f,
                        label = "Trailing Icon Rotation"
                    )
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        modifier =
                            Modifier
                                .size(SplitButtonDefaults.TrailingIconSize)
                                .graphicsLayer {
                                    this.rotationZ = rotation
                                },
                        contentDescription = "Localized description"
                    )
                }
            })
        SplitButtonLayout(
            spacing = 8.dp,
            leadingButton = {
                SplitButtonDefaults.OutlinedLeadingButton(
                    onClick = { *//* Do Nothing *//* },
                ) {
                    Icon(
                        Icons.Filled.Edit,
                        modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                        contentDescription = "Localized description",
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("My Button")
                }
            },
            trailingButton = {
                SplitButtonDefaults.OutlinedTrailingButton(
                    checked = checked1,
                    onCheckedChange = { checked1 = it },
                ) {
                    val rotation: Float by
                    animateFloatAsState(
                        targetValue = if (checked1) 180f else 0f,
                        label = "Trailing Icon Rotation"
                    )
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        modifier =
                            Modifier
                                .size(SplitButtonDefaults.TrailingIconSize)
                                .graphicsLayer {
                                    this.rotationZ = rotation
                                },
                        contentDescription = "Localized description"
                    )
                }
            })
    }

}

// Replace the speed dial and any usage of stacked small FABs
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true)
@Composable
fun FloatingActionButtonMenuSample() {
    val listState = rememberLazyListState()
    val fabVisible by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }

    Box {
        LazyColumn(state = listState) {
            for (index in 0 until 5) {
                item {
                    Text(
                        text = "Item - $index",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    )
                }
            }
        }

        val items =
            listOf(
                Icons.Filled.Snooze to "Snooze",
                Icons.Filled.Archive to "Archive",
                Icons.AutoMirrored.Filled.Label to "Label",
            )

        var fabMenuExpanded by rememberSaveable { mutableStateOf(false) }

        BackHandler(fabMenuExpanded) { fabMenuExpanded = false }

        FloatingActionButtonMenu(
            modifier = Modifier.align(Alignment.BottomEnd),
            expanded = fabMenuExpanded,
            button = {
                ToggleFloatingActionButton(
                    modifier =
                        Modifier
                            .semantics {
                                traversalIndex = -1f
                                stateDescription = if (fabMenuExpanded) "Expanded" else "Collapsed"
                                contentDescription = "Toggle menu"
                            }
                            .animateFloatingActionButton(
                                visible = fabVisible || fabMenuExpanded,
                                alignment = Alignment.BottomEnd
                            ),
                    checked = fabMenuExpanded,
                    containerSize = ToggleFloatingActionButtonDefaults.containerSizeLarge(),
                    onCheckedChange = { fabMenuExpanded = !fabMenuExpanded }
                ) {
                    val imageVector by remember {
                        derivedStateOf {
                            if (checkedProgress > 0.5f) Icons.Filled.Close else Icons.Filled.Add
                        }
                    }
                    Icon(
                        painter = rememberVectorPainter(imageVector),
                        contentDescription = null,
                        modifier = Modifier.animateIcon({ checkedProgress })
                    )
                }
            }
        ) {
            items.forEachIndexed { i, item ->
                FloatingActionButtonMenuItem(
                    modifier =
                        Modifier.semantics {
                            isTraversalGroup = true
                            // Add a custom a11y action to allow closing the menu when focusing
                            // the last menu item, since the close button comes before the first
                            // menu item in the traversal order.
                            if (i == items.size - 1) {
                                customActions =
                                    listOf(
                                        CustomAccessibilityAction(
                                            label = "Close menu",
                                            action = {
                                                fabMenuExpanded = false
                                                true
                                            }
                                        )
                                    )
                            }
                        },
                    onClick = { fabMenuExpanded = false },
                    icon = { Icon(item.first, contentDescription = null) },
                    text = { Text(text = item.second) },
                )
            }
        }
    }
}

// ElevatedButton - Example
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun ExitAlwaysBottomAppBarSpacedEvenly() {
    val scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            FlexibleBottomAppBar(
                horizontalArrangement = Arrangement.SpaceEvenly,
                contentPadding = PaddingValues(horizontal = 0.dp),
                scrollBehavior = scrollBehavior,
                content = {
                    IconButton(onClick = { *//* doSomething() *//* }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }

                    FilledIconButton(
                        modifier = Modifier.width(56.dp),
                        onClick = { *//* doSomething() *//* }
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { *//* doSomething() *//* }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val list = (0..75).map { it.toString() }
                items(count = list.size) {
                    Text(
                        text = list[it],
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    )
}

*/
/** A sample for a [FlexibleBottomAppBar] with an overflow behavior when the content doesn't fit. *//*
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun BottomAppBarWithOverflow() {
    FlexibleBottomAppBar(
        contentPadding = PaddingValues(horizontal = 96.dp),
        horizontalArrangement = BottomAppBarDefaults.FlexibleFixedHorizontalArrangement,
    ) {
        AppBarRow(
            overflowIndicator = { menuState ->
                IconButton(
                    onClick = {
                        if (menuState.isExpanded) {
                            menuState.dismiss()
                        } else {
                            menuState.show()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Localized description"
                    )
                }
            }
        ) {
            clickableItem(
                onClick = { *//* doSomething() *//* },
                icon = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                },
                label = "ArrowBack"
            )
            clickableItem(
                onClick = { *//* doSomething() *//* },
                icon = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Localized description"
                    )
                },
                label = "ArrowForward"
            )
            clickableItem(
                onClick = { *//* doSomething() *//* },
                icon = { Icon(Icons.Filled.Add, contentDescription = "Localized description") },
                label = "Add"
            )
            clickableItem(
                onClick = { *//* doSomething() *//* },
                icon = { Icon(Icons.Filled.Check, contentDescription = "Localized description") },
                label = "Check"
            )
            clickableItem(
                onClick = { *//* doSomething() *//* },
                icon = { Icon(Icons.Filled.Edit, contentDescription = "Localized description") },
                label = "Edit"
            )
            clickableItem(
                onClick = { *//* doSomething() *//* },
                icon = {
                    Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                },
                label = "Favorite"
            )
        }
    }
}*/

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
        Text("EyeDropper API Sample!", style = MaterialTheme.typography.headlineLarge)
        Text("EyeDropper API Sample!", style = MaterialTheme.LocalMaterialTheme.current.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Launch the picker
                contactPickerLauncher.launch(pickContactIntent)

            }
        ) {
            Text("Pick Color 🎨 from Screen")
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

