package com.example.recentreleasesamplse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun BenefitsCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        // 70%‑height navy bar behind card
        Box(
            modifier = Modifier
                .matchParentSize()          // same size as the whole card area
                .drawBehind {
                    val h = size.height * 0.6f   // 70% of total height
                    drawRect(
                        color = Color(0xFF040615),
                        size = Size(size.width, h)
                    )
                }
        )

        // Foreground card with spacing around
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors().copy(containerColor = Color.White)
        ) {
            content()   // title, subtitle, divider, buttons (FR/EN etc.)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BenefitsCardPre() {
    BenefitsCard {
        BenefitsCardContent("Nav Singh", {}) { }
    }
}


@Composable
fun BenefitsCardContent(
    name: String,
    onSubmitClaim: () -> Unit,
    onViewCoverage: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        BenefitsSubtitleWithTooltip()

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 16.dp)
        )

        // Buttons – wrap to 2nd line if FR text is long
        FlowRow( // from androidx.compose.foundation:foundation
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = onSubmitClaim,
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(stringResource(R.string.submit_claim))
            }

            OutlinedButton(
                onClick = onViewCoverage,
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(stringResource(R.string.view_coverage))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BenefitsSubtitleWithTooltip() {
    val tooltipState = rememberTooltipState(isPersistent = true)
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch { tooltipState.show() }
    }


    TooltipBox(
        enableUserInput = false,
        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
            TooltipAnchorPosition.Below
        ),
        tooltip = {
            PlainTooltip(caretShape = TooltipDefaults.caretShape()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()          // ≈ card width
                        .padding(horizontal = 16.dp)          // inner padding
                ) {
                    Text(
                        stringResource(R.string.view_your_benefits_card_tooltip),
                        modifier = Modifier.padding(vertical = 8.dp),   // ⬅ bigger tooltip
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        },
        state = tooltipState,
        // modifier = Modifier.tooltipAnchor()  // important
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Nav Singh", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        stringResource(R.string.view_your_benefits_card),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
    }
}

