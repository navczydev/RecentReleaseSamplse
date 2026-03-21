package com.example.recentreleasesamplse

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SegmentedListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.UUID

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)

@Composable
fun OneLineListItem() {
    Column {
        HorizontalDivider()
        ListItem(
            headlineContent = { Text("One line list item with icon") },
            leadingContent = {
                Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
            },
        )
        HorizontalDivider()
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)


@Composable
fun TwoLineListItem() {
    Column {
        HorizontalDivider()
        ListItem(
            headlineContent = { Text("Two line list item") },
            supportingContent = { Text("Secondary text") },
            trailingContent = { Text("Trailing content") },
            leadingContent = {
                Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
            },
        )
        HorizontalDivider()
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)


@Composable
fun ThreeLineListItemWithOverlineAndSupporting() {
    Column {
        HorizontalDivider()
        ListItem(
            headlineContent = { Text("Three line list item") },
            overlineContent = { Text("OVERLINE") },
            supportingContent = { Text("Secondary text") },
            leadingContent = {
                Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
            },
            trailingContent = { Text("Trailing content") },
        )
        HorizontalDivider()
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)


@Composable
fun ThreeLineListItemWithExtendedSupporting() {
    Column {
        HorizontalDivider()
        ListItem(
            headlineContent = { Text("Three line list item") },
            supportingContent = { Text("Secondary text that\nspans multiple lines") },
            leadingContent = {
                Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
            },
            trailingContent = { Text("Trailing content") },
        )
        HorizontalDivider()
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)


@Composable
fun ClickableListItemSample() {
    Column {
        HorizontalDivider()
        Text("ListItem Click....", style = MaterialTheme.typography.titleMedium)
        repeat(3) { index ->
            var count by rememberSaveable { mutableIntStateOf(0) }
            ListItem(
                onClick = { count++ },
                leadingContent = { Icon(Icons.Default.Home, contentDescription = null) },
                trailingContent = { Text("$count") },
                supportingContent = { Text("Additional info") },
                content = { Text("Item ${index + 1}") },
            )

            HorizontalDivider()
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)


@Composable
fun ClickableListItemWithClickableChildSample() {
    Column {
        val context = LocalContext.current
        HorizontalDivider()
        Text("ListItem Click & Child Click....", style = MaterialTheme.typography.titleMedium)
        newsItems.forEachIndexed { index, item ->
            ListItem(
                onClick = {
                    Toast.makeText(context, "Clicked on the list item", Toast.LENGTH_SHORT).show()
                },
                leadingContent = { Icon(Icons.Default.Home, contentDescription = null) },
                trailingContent = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Clicked on the trailing icon", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Localized description")
                    }
                },
                supportingContent = { Text("The trailing icon has a separate click action") },
                content = { Text(item.headline) },
            )

            HorizontalDivider()
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)


@Composable
fun SingleSelectionListItemSample() {
    Column(Modifier.selectableGroup()) {
        HorizontalDivider()
        Text("ListItem single selection....", style = MaterialTheme.typography.titleMedium)
        var selectedIndex: Int? by rememberSaveable { mutableStateOf(null) }
        repeat(3) { index ->
            val selected = selectedIndex == index
            ListItem(
                selected = selected,
                onClick = { selectedIndex = if (selected) null else index },
                leadingContent = { RadioButton(selected = selected, onClick = null) },
                trailingContent = { Icon(Icons.Default.Favorite, contentDescription = null) },
                supportingContent = { Text("Additional info") },
                content = { Text("Item ${index + 1}") },
            )

            HorizontalDivider()
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)


@Composable
fun MultiSelectionListItem() {
    Column {
        HorizontalDivider()
        Text("ListItem multi selection....", style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()
        repeat(3) { index ->
            var checked by rememberSaveable { mutableStateOf(false) }
            ListItem(
                checked = checked,
                onCheckedChange = { checked = it },
                leadingContent = { Checkbox(checked = checked, onCheckedChange = null) },
                trailingContent = { Icon(Icons.Default.Favorite, contentDescription = null) },
                supportingContent = { Text("Additional info") },
                content = { Text("Item ${index + 1}") },
            )
            HorizontalDivider()
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)


@Composable
fun ListItemWithModeChangeOnLongClickSample() {
    Column {
        HorizontalDivider()
        Text("ListItem mode change on long click....", style = MaterialTheme.typography.titleMedium)
        var inClickMode by rememberSaveable { mutableStateOf(true) }
        val counts = rememberSaveable { mutableStateListOf(0, 0, 0) }
        val checked = rememberSaveable { mutableStateListOf(false, false, false) }

        repeat(3) { idx ->
            if (inClickMode) {
                ListItem(
                    onClick = { counts[idx]++ },
                    onLongClick = {
                        checked[idx] = true
                        inClickMode = false
                    },
                    leadingContent = { Icon(Icons.Default.Home, contentDescription = null) },
                    trailingContent = { Text("${counts[idx]}") },
                    supportingContent = { Text("Long-click to change interaction mode.") },
                    content = { Text("Item ${idx + 1}") },
                )
            } else {
                ListItem(
                    checked = checked[idx],
                    onCheckedChange = { checked[idx] = it },
                    onLongClick = {
                        inClickMode = true
                        checked.clear()
                        checked.addAll(listOf(false, false, false))
                    },
                    leadingContent = { Checkbox(checked = checked[idx], onCheckedChange = null) },
                    trailingContent = { Icon(Icons.Default.Favorite, contentDescription = null) },
                    supportingContent = { Text("Long-click to change interaction mode.") },
                    content = { Text("Item ${idx + 1}") },
                )
            }

            HorizontalDivider()
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)


@Composable
fun SingleSelectionSegmentedListItem() {
    val count = 3
    val colors =
        ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    Column(
        modifier = Modifier.selectableGroup(),
        verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap),
    ) {
        var selectedIndex: Int? by rememberSaveable { mutableStateOf(null) }
        Text("SegmentedListItem single selection....", style = MaterialTheme.typography.titleMedium)
        repeat(count) { idx ->
            val selected = selectedIndex == idx
            SegmentedListItem(
                selected = selected,
                onClick = { selectedIndex = if (selected) null else idx },
                colors = colors,
                shapes = ListItemDefaults.segmentedShapes(index = idx, count = count),
                leadingContent = { RadioButton(selected = selected, onClick = null) },
                trailingContent = { Icon(Icons.Default.Favorite, contentDescription = null) },
                supportingContent = { Text("Additional info") },
                content = { Text("Item ${idx + 1}") },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)


@Composable
fun MultiSelectionSegmentedListItem() {
    val count = 3
    val colors =
        ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    Text("SegmentedListItem multi selection....", style = MaterialTheme.typography.titleMedium)
    Column(verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap)) {
        repeat(count) { index ->
            var checked by rememberSaveable { mutableStateOf(false) }
            SegmentedListItem(
                checked = checked,
                onCheckedChange = { checked = it },
                colors = colors,
                shapes = ListItemDefaults.segmentedShapes(index = index, count = count),
                leadingContent = { Checkbox(checked = checked, onCheckedChange = null) },
                trailingContent = { Icon(Icons.Default.Favorite, contentDescription = null) },
                supportingContent = { Text("Additional info") },
                content = { Text("Item ${index + 1}") },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)

@Composable
fun SegmentedListItemWithExpansion() {
    var expanded by rememberSaveable { mutableStateOf(true) }
    val numChildren = 3
    val itemCount = 1 + if (expanded) numChildren else 0
    val childrenChecked = rememberSaveable { mutableStateListOf(*Array(numChildren) { false }) }

    val colors =
        ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceContainer)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap),
    ) {
        Spacer(Modifier.height(28.dp))
        Text("SegmentedListItem with expansion....", style = MaterialTheme.typography.titleMedium)
        SegmentedListItem(
            onClick = { expanded = !expanded },
            modifier =
                Modifier.semantics { stateDescription = if (expanded) "Expanded" else "Collapsed" },
            colors = colors,
            shapes = ListItemDefaults.segmentedShapes(index = 0, count = itemCount),
            leadingContent = { Icon(Icons.Default.Favorite, contentDescription = null) },
            trailingContent = {
                Icon(
                    if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                )
            },
            content = { Text("Click to expand/collapse") },
        )
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(MaterialTheme.motionScheme.fastSpatialSpec()),
            exit = shrinkVertically(MaterialTheme.motionScheme.fastSpatialSpec()),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap)) {
                repeat(numChildren) { index ->
                    SegmentedListItem(
                        checked = childrenChecked[index],
                        onCheckedChange = { childrenChecked[index] = it },
                        colors = colors,
                        shapes =
                            ListItemDefaults.segmentedShapes(index = index + 1, count = itemCount),
                        leadingContent = {
                            Icon(Icons.Default.Favorite, contentDescription = null)
                        },
                        trailingContent = {
                            Checkbox(checked = childrenChecked[index], onCheckedChange = null)
                        },
                        content = { Text("Child ${index + 1}") },
                    )
                }
            }
        }
    }
}

data class ListItem(
    val headline: String,
    val subline: String? = null,
    val supporting: String? = null,
    val id: String = UUID.randomUUID().toString() // Optional unique ID for keys in LazyColumn
)

val newsItems = listOf(
    ListItem(
        headline = "Jetpack Compose 1.5 Released",
        subline = "Alpha 11 → Stable",
        supporting = "Expressive lists now production-ready with full Material You support"
    ),
    ListItem(
        headline = "Kotlin 2.0 Performance Boost",
        subline = "20% faster compilation",
        supporting = "New inline class optimizations and better coroutines"
    ),
    ListItem(
        headline = "Android 16 Beta Available",
        subline = "Developer preview",
        supporting = "New predictive back gestures and enhanced privacy dashboard"
    )
)

val productItems = listOf(
    ListItem(
        headline = "Pixel 10 Pro",
        subline = "From $999",
        supporting = "Tensor G6 • 120Hz LTPO • 50MP triple camera system"
    ),
    ListItem(
        headline = "Galaxy S26 Ultra",
        subline = "Pre-order now",
        supporting = "Snapdragon 8 Gen 5 • S Pen included • 200MP main sensor"
    ),
    ListItem(
        headline = "iPhone 17 Pro Max",
        subline = "$1,199",
        supporting = "A20 Bionic • 6.9\" ProMotion • Under-display Face ID"
    )
)

val messageItems = listOf(
    ListItem(
        headline = "Sarah Johnson",
        subline = "3m ago",
        supporting = "Great progress on the Compose article! Need the ListItem data class samples?"
    ),
    ListItem(
        headline = "Team Lead",
        subline = "1h ago",
        supporting = "Review the PR for expressive lists integration by EOD please"
    ),
    ListItem(
        headline = "Marketing",
        subline = "2h ago",
        supporting = "Medium article going live tomorrow - add your code samples tonight"
    )
)

val settingsItems = listOf(
    ListItem(
        headline = "Dark Mode",
        subline = "System default",
        supporting = "Follows device appearance settings automatically"
    ),
    ListItem(
        headline = "Notifications",
        subline = "All enabled",
        supporting = "Receive updates for new Compose releases and Kotlin features"
    ),
    ListItem(
        headline = "Performance Monitor",
        subline = "New Relic active",
        supporting = "Real-time app profiling with Firebase integration"
    )
)
