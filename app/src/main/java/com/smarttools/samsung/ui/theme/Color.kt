package com.smarttools.samsung.ui.theme

import androidx.compose.ui.graphics.Color

// ==========================================
// 🔵 Nuances Principales Samsung One UI
// ==========================================
val OneUiBluePrimary = Color(0xFF0072DE)       // Bleu signature Samsung
val OneUiBlueLight   = Color(0xFF4C9EFF)       // Accentuation claire
val OneUiBlueDark    = Color(0xFF0052A3)       // Accentuation sombre
val OneUiBlueContainerLight = Color(0xFFE5F1FC)
val OneUiBlueContainerDark  = Color(0xFF0B2B4C)

// ==========================================
// ☀️ Thème Clair (One UI Light)
// ==========================================
val LightBackground       = Color(0xFFF4F6F9)   // Gris très pâle caractéristique One UI
val LightSurface          = Color(0xFFFFFFFF)   // Blanc pur pour les cartes flottantes
val LightSurfaceVariant   = Color(0xFFEBEFF5)
val LightTextPrimary      = Color(0xFF1C1D1F)   // Presque noir pour le contraste
val LightTextSecondary    = Color(0xFF767B82)   // Gris intermédiaire pour sous-titres
val LightOutline          = Color(0xFFDCE2EC)

// ==========================================
// 🌙 Thème Sombre (One UI Dark / AMOLED)
// ==========================================
val DarkBackground        = Color(0xFF0C0D0E)   // Noir profond optimisé pour écrans AMOLED
val DarkSurface           = Color(0xFF1C1E22)   // Gris sombre pour délimiter les cartes
val DarkSurfaceVariant    = Color(0xFF282B30)
val DarkTextPrimary       = Color(0xFFF3F4F6)
val DarkTextSecondary     = Color(0xFF8E939C)
val DarkOutline           = Color(0xFF2D3139)

// ==========================================
// 🚦 Indicateurs de Diagnostic & Santé
// ==========================================
// Vert (Knox 0x0, Santé batterie excellente)
val StatusSuccess         = Color(0xFF27AE60)
val StatusSuccessContainer= Color(0xFFE8F8F0)

// Orange (Surchauffe légère, Taux rafraîchissement bridé)
val StatusWarning         = Color(0xFFF39C12)
val StatusWarningContainer= Color(0xFFFEF5E7)

// Rouge (Knox altéré 0x1, Batterie critique)
val StatusDanger          = Color(0xFFE74C3C)
val StatusDangerContainer = Color(0xFFFDEDEC)
