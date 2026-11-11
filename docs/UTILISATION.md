##### UTILISATION.md >> markdown
# 📄 SmartTools 
### 📘 Guide d'Utilisation et d'Exécution

Ce document détaille la mise en place, la compilation, l'utilisation pratique et l'extension du code source de la suite **SmartTools** pour smartphones Samsung Galaxy.

---

## 📑 Sommaire
1. [Prérequis & Environnement](#1-prérequis--environnement)
2. [Compilation & Premier Déploiement](#2-compilation--premier-déploiement)
3. [Guide des Fonctionnalités & Écrans](#3-guide-des-fonctionnalités--écrans)
4. [Exemples de Code & Extension du Projet](#4-exemples-de-code--extension-du-projet)
5. [Configuration Avancée (Shizuku & Débloat)](#5-configuration-avancée-shizuku--débloat)
6. [Résolution des Problèmes Fréquents (FAQ)](#6-résolution-des-problèmes-fréquents-faq)

---

## 1. Prérequis & Environnement

### Côté Machine de Développement
* **IDE :** Android Studio (version Iguana, Jellyfish, Koala ou supérieure).
* **JDK :** Java Development Kit **17** (obligatoire pour la cible Gradle configurée).
* **Android SDK :** SDK Platform 35 installé via le SDK Manager.

### Côté Smartphone de Test
* **Marque recommandée :** Smartphone ou tablette Samsung (One UI 2.0 à One UI 7.0 / Android 10 à Android 15).
* **Options de développement activées :**
  1. Rendez-vous dans *Paramètres* > *À propos du téléphone* > *Informations sur le logiciel*.
  2. Tapotez 7 fois sur **Numéro de version** jusqu'à l'activation du mode développeur.
  3. Dans *Paramètres* > *Options de développement*, activez le **Débogage USB**.

> ⚠️ **Note sur les émulateurs :**  
> Les émulateurs AVD standards fonctionnent pour tester l'interface graphique (Compose), mais les fonctions spécifiques matérielles (`ro.boot.warranty_bit` pour Knox, température batterie précise, taux de rafraîchissement adaptatif) ne renverront des valeurs fiables que sur un terminal physique Samsung Galaxy.

---

## 2. Compilation & Premier Déploiement

### Option A : Via Android Studio
1. Ouvrez le dossier racine du projet dans Android Studio.
2. Laissez Gradle synchroniser les dépendances (`Sync Project with Gradle Files`).
3. Connectez votre terminal Samsung par câble USB ou Wi-Fi ADB.
4. Sélectionnez l'appareil dans la liste déroulante en haut.
5. Cliquez sur le bouton vert **Run 'app'** (`Shift + F10`).

### Option B : En Ligne de Commande (Terminal)

* **Générer l'APK de débogage (Debug) :**
  ```bash
  # Sur Linux / macOS
  ./gradlew assembleDebug

  # Sur Windows (PowerShell / CMD)
  .\gradlew.bat assembleDebug
Le fichier APK généré se trouvera dans :
app/build/outputs/apk/debug/app-debug.apk
Installer directement sur l'appareil connecté :
Bash
./gradlew installDebug
3. Guide des Fonctionnalités & Écrans
L'application repose sur une barre de navigation inférieure à 3 onglets principaux :
🔍 Onglet 1 : Diagnostic
Cet écran permet de dresser un bilan de santé matériel immédiat :
Identité Matérielle : Modèle commercial, nom de code de la carte mère et processeur SoC.
Audit Knox :
0x0 : Le smartphone est certifié d'origine. Samsung Pay, Dossier Sécurisé et Samsung Pass sont fonctionnels.
0x1 : Le chargeur d'amorçage (bootloader) a été déverrouillé ou l'appareil a été rooté.
Télémétrie Batterie : Niveau précis au pourcent près, état de charge en direct, température (°C) et qualification de la santé chimique.
⚡ Onglet 2 : Optimiser
Nettoyage RAM / Cache : En appuyant sur « Optimiser maintenant », l'application déclenche une purge des résidus temporaires en mémoire vive.
Débloater : Prépare le smartphone à isoler les applications préinstallées énergivores ou rarement utilisées (services Bixby superflus, traceurs publicitaires Samsung).
⚙️ Onglet 3 : Routines
Alerte Protection Batterie (85%) : Évite de maintenir la batterie à 100% en continu pour réduire la dégradation chimique de l'accumulateur Li-ion.
Passage automatique à 60 Hz : Réduit la fréquence de l'écran AMOLED dès que l'autonomie passe sous un seuil critique afin d'économiser jusqu'à 20 % d'énergie supplémentaire.
4. Exemples de Code & Extension du Projet
Tous les composants créés sont modulaires et réutilisables.
Exemple 1 : Exploiter les métadonnées de DeviceSpecsProvider
Pour afficher une nouvelle information (ex: résolution d'écran ou RAM disponible) dans un Composable :
Kotlin
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.smarttools.samsung.data.DeviceSpecsProvider

@Composable
fun RamSummaryBadge() {
    val context = LocalContext.current
    // Récupération en mémoire mise en cache avec remember
    val specs = remember { DeviceSpecsProvider.getDeviceSpecs(context) }

    Text(
        text = "RAM disponible : %.2f Go / %.2f Go".format(
            specs.memory.availableRamGb,
            specs.memory.totalRamGb
        )
    )
}
Exemple 2 : Créer une carte interactive avec OneUiCard
Le composant OneUiCard s'adapte automatiquement au thème clair/sombre et offre une signature visuelle One UI :
Kotlin
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.smarttools.samsung.ui.components.OneUiCard

@Composable
fun CustomSecurityCard() {
    var isAuditActive by remember { mutableStateOf(true) }

    OneUiCard(
        title = "Protection Knox Active",
        subtitle = "Surveillance du noyau en temps réel",
        icon = Icons.Rounded.Security,
        action = {
            Switch(
                checked = isAuditActive,
                onCheckedChange = { isAuditActive = it }
            )
        },
        onClick = {
            // Action au clic sur la carte entière
        }
    ) {
        Text("Tous les conteneurs sécurisés répondent normalement.")
    }
}
5. Configuration Avancée (Shizuku & Débloat)
Pour effectuer des opérations système comme la désactivation d'applications préinstallées (débloat) sans rooter l'appareil, SmartTools s'associe à l'API Shizuku.
Étapes d'activation de Shizuku :
Téléchargez l'application officielle Shizuku depuis Google Play ou GitHub.
Activez le Débogage sans fil dans les Options de développement de votre Samsung Galaxy.
Associez Shizuku via le code de jumelage à 6 chiffres.
Lancez le service Shizuku.
Lors d'une commande d'optimisation avancée dans SmartTools, acceptez la boîte de dialogue d'autorisation Shizuku.
Commandes ADB Manuelles Équivalentes :
Si vous préférez exécuter les commandes depuis votre PC via câble USB :
Bash
# Exemple : Désactiver le service vocal Bixby sans supprimer les fichiers système
adb shell pm disable-user --user 0 com.samsung.android.bixby.agent

# Pour restaurer l'application à tout moment :
adb shell pm enable com.samsung.android.bixby.agent
6. Résolution des Problèmes Fréquents (FAQ)
Q1 : L'état Knox indique « Inconnu » ou « N/A » ?
Cause : L'application s'exécute sur un émulateur standard Google AVD ou un smartphone d'une autre marque (Xiaomi, Pixel, etc.).
Solution : Testez l'APK sur un véritable terminal Samsung. La propriété ro.boot.warranty_bit n'existe que dans les firmwares One UI officiels.
Q2 : Erreur de compilation Incompatible JVM target ?
Cause : Android Studio utilise une version de JDK différente de Java 17.
Solution : Rendez-vous dans :
File > Settings (ou Preferences sur Mac) > Build, Execution, Deployment > Build Tools > Gradle
Et vérifiez que Gradle JDK est bien configuré sur JDK 17.
Q3 : Pourquoi l'application fait moins de 15 Mo ?
Explication : Le fichier build.gradle.kts active isMinifyEnabled = true et isShrinkResources = true en mode Release. Cela supprime automatiquement les composants graphiques inutilisés et obfusque le code via ProGuard / R8
