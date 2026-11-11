package com.smarttools.samsung.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BatteryChargingFull
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smarttools.samsung.ui.theme.SmartToolsTheme

/**
 * Carte stylisée selon les lignes directrices de Samsung One UI :
 * - Coins arrondis doux (24.dp par défaut)
 * - En-tête optionnel (Icône + Titre + Sous-titre + Action à droite)
 * - Contenu principal flexible avec espacement interne cohérent
 */
@Composable
fun OneUiCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    subtitle: String? = null,
    icon: ImageVector? = null,
    iconTint: Color = MaterialTheme.colorScheme.primary,
    action: @Composable (() -> Unit)? = null,
    cornerRadius: Dp = 24.dp,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    border: BorderStroke? = null,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius)

    val cardModifier = modifier
        .fillMaxWidth()
        .clip(shape)
        .then(
            if (onClick != null) {
                Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = onClick
                )
            } else {
                Modifier
            }
        )

    Surface(
        modifier = cardModifier,
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        border = border,
        tonalElevation = 2.dp,
        shadowElevation = 0.dp // One UI privilégie les surfaces plates et nettes
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Affichage de l'en-tête uniquement si un titre ou une icône est fourni
            if (title != null || icon != null || action != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f, fill = false),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (icon != null) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = iconTint.copy(alpha = 0.12f),
                                modifier = Modifier.size(36.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = null,
                                        tint = iconTint,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                        }

                        Column {
                            if (title != null) {
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = contentColor
                                )
                            }
                            if (subtitle != null) {
                                Text(
                                    text = subtitle,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    // Action à droite (ex: Switch, flèche, bouton)
                    if (action != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        action()
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
            }

            // Contenu personnalisé de la carte
            content()
        }
    }
}

// ==========================================
// 🔍 Prévisualisation Android Studio
// ==========================================
@Preview(showBackground = true)
@Composable
private fun OneUiCardPreview() {
    SmartToolsTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            OneUiCard(
                title = "Batterie",
                subtitle = "État matériel et cycles",
                icon = Icons.Rounded.BatteryChargingFull,
                action = {
                    Switch(checked = true, onCheckedChange = {})
                }
            ) {
                Text(
                    text = "Niveau actuel : 85% • Température : 31.4°C",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
