# 📄 SmartTools
- *Structure globale du projet, optimisée pour la clarté et la sécurité*.

###### Sructure actuelle est déjà très propre et modulaire (*Kotlin + Jetpack Compose + style One UI*). Voici exactement comment faire évoluer cette arborescence pour la v1.2.0, en reliant proprement les 3 nouvelles fonctionnalités à tes fichiers existants (DiagnosticReport.kt, PdfExporter.kt, AutomationScreen.kt, QuickTileService.kt, etc.).

### 1. 📁 Arborescence
- cible **app/ pour la v1.2.0**

Voici les ajouts ciblés (marqués ✨) et les fichiers existants à enrichir (🔄) :
```text
app/
├── build.gradle.kts                      🔄 (+ Glance AppWidget & WorkManager)
└── src/
    ├── main/
    │   ├── AndroidManifest.xml           🔄 (+ Widget Receiver, shortcuts.xml, deep links)
    │   ├── java/com/smarttools/samsung/
    │   │   ├── MainActivity.kt           🔄 (+ route Test Tactile / deep links Routines)
    │   │   ├── data/
    │   │   │   ├── DeviceSpecs.kt        🔄 (+ détection support S-Pen & taux Hz écran)
    │   │   │   └── DiagnosticReport.kt   🔄 (+ résultats pixels/tactile/S-Pen pour le PDF)
    │   │   ├── service/
    │   │   │   └── QuickTileService.kt   🔄 (+ raccourci vers diagnostic / widget sync)
    │   │   ├── widget/                   ✨ NOUVEAU PACKAGE
    │   │   │   ├── SmartToolsWidget.kt
    │   │   │   ├── SmartToolsWidgetReceiver.kt
    │   │   │   └── WidgetUpdateWorker.kt
    │   │   ├── theme/
    │   │   │   ├── Color.kt
    │   │   │   └── Theme.kt
    │   │   ├── ui/
    │   │   │   ├── components/
    │   │   │   │   ├── OneUiCard.kt
    │   │   │   │   ├── ThermalGauge.kt
    │   │   │   │   └── TouchMatrixView.kt ✨ (Canvas Android haute fréquence 120/240Hz)
    │   │   │   └── screens/
    │   │   │       ├── AutomationScreen.kt    🔄 (+ recettes & test Modes et Routines)
    │   │   │       ├── DiagnosticScreen.kt    🔄 (+ carte lancement Test Écran & S-Pen)
    │   │   │       ├── OptimizationScreen.kt
    │   │   │       └── TouchTestScreen.kt     ✨ (Plein écran OLED, Grille, S-Pen)
    │   │   └── utils/
    │   │       ├── BatteryHelper.kt           🔄 (+ snapshot rapide partagé avec le Widget)
    │   │       ├── PdfExporter.kt             🔄 (+ section Test Écran & S-Pen dans le PDF)
    │   │       ├── RoutineNotifier.kt         ✨ (Canaux d'alertes déclencheurs Routines)
    │   │       └── SamsungSystemHelper.kt     🔄 (+ hasSPenSupport(), ThermalStatus API 29+)
    │   └── res/xml/
    │       ├── file_paths.xml
    │       ├── shortcuts.xml                  ✨ (Actions natives Modes & Routines)
    │       └── smart_tools_widget_info.xml    ✨ (Config dimensions & arrondis One UI)
    └── test/java/com/smarttools/samsung/      ✨ NOUVEAU DOSSIER (Tests unitaires)
        └── DiagnosticReportTest.kt
```

Ta structure actuelle est déjà très propre et modulaire (**Kotlin + Jetpack Compose + style One UI**). Voici exactement comment faire évoluer cette arborescence pour la **v1.2.0**, en reliant proprement les 3 nouvelles fonctionnalités à tes fichiers existants (`DiagnosticReport.kt`, `PdfExporter.kt`, `AutomationScreen.kt`, `QuickTileService.kt`, etc.).

---

## 1. 📁 Arborescence cible `app/` pour la v1.2.0

Voici les **ajouts ciblés** (marqués `✨`) et les **fichiers existants à enrichir** (`🔄`) :

```text
app/
├── build.gradle.kts                      🔄 (+ Glance AppWidget & WorkManager)
└── src/
    ├── main/
    │   ├── AndroidManifest.xml           🔄 (+ Widget Receiver, shortcuts.xml, deep links)
    │   ├── java/com/smarttools/samsung/
    │   │   ├── MainActivity.kt           🔄 (+ route Test Tactile / deep links Routines)
    │   │   ├── data/
    │   │   │   ├── DeviceSpecs.kt        🔄 (+ détection support S-Pen & taux Hz écran)
    │   │   │   └── DiagnosticReport.kt   🔄 (+ résultats pixels/tactile/S-Pen pour le PDF)
    │   │   ├── service/
    │   │   │   └── QuickTileService.kt   🔄 (+ raccourci vers diagnostic / widget sync)
    │   │   ├── widget/                   ✨ NOUVEAU PACKAGE
    │   │   │   ├── SmartToolsWidget.kt
    │   │   │   ├── SmartToolsWidgetReceiver.kt
    │   │   │   └── WidgetUpdateWorker.kt
    │   │   ├── theme/
    │   │   │   ├── Color.kt
    │   │   │   └── Theme.kt
    │   │   ├── ui/
    │   │   │   ├── components/
    │   │   │   │   ├── OneUiCard.kt
    │   │   │   │   ├── ThermalGauge.kt
    │   │   │   │   └── TouchMatrixView.kt ✨ (Canvas Android haute fréquence 120/240Hz)
    │   │   │   └── screens/
    │   │   │       ├── AutomationScreen.kt    🔄 (+ recettes & test Modes et Routines)
    │   │   │       ├── DiagnosticScreen.kt    🔄 (+ carte lancement Test Écran & S-Pen)
    │   │   │       ├── OptimizationScreen.kt
    │   │   │       └── TouchTestScreen.kt     ✨ (Plein écran OLED, Grille, S-Pen)
    │   │   └── utils/
    │   │       ├── BatteryHelper.kt           🔄 (+ snapshot rapide partagé avec le Widget)
    │   │       ├── PdfExporter.kt             🔄 (+ section Test Écran & S-Pen dans le PDF)
    │   │       ├── RoutineNotifier.kt         ✨ (Canaux d'alertes déclencheurs Routines)
    │   │       └── SamsungSystemHelper.kt     🔄 (+ hasSPenSupport(), ThermalStatus API 29+)
    │   └── res/xml/
    │       ├── file_paths.xml
    │       ├── shortcuts.xml                  ✨ (Actions natives Modes & Routines)
    │       └── smart_tools_widget_info.xml    ✨ (Config dimensions & arrondis One UI)
    └── test/java/com/smarttools/samsung/      ✨ NOUVEAU DOSSIER (Tests unitaires)
        └── DiagnosticReportTest.kt
```

---

## 2. 🔗 Comment brancher les 3 nouveautés
- *v1.2.0 sur ton code existant*
#### A. 📊 Widget One UI (*RAM & Batterie*)
* **`BatteryHelper.kt` & `SamsungSystemHelper.kt` (🔄)** : extraire une fonction légère `getQuickSnapshot(context)` qui renvoie uniquement `ramUsedPct`, `ramFreeMb`, `batteryPct`, `batteryTempC`, `isCharging`. Ce snapshot sera partagé entre le **Widget**, **`QuickTileService.kt`** et **`DiagnosticScreen.kt`** (zéro duplication).

* **`widget/SmartToolsWidget.kt` (✨)** : basé sur **Jetpack Glance** (`GlanceAppWidget`), utilisant les couleurs de `theme/Color.kt` (ou Dynamic Color) et des coins arrondis `24dp` façon One UI, avec un bouton *Actualiser* et un clic direct vers `DiagnosticScreen` ou `OptimizationScreen`.

* **`widget/WidgetUpdateWorker.kt` (✨)** : `CoroutineWorker` via `WorkManager` (rafraîchissement toutes les 15–30 min sans réveiller le CPU en boucle).
*
  **`res/xml/smart_tools_widget_info.xml` (✨)** : widget redimensionnable (`targetCellWidth="4"`, `targetCellHeight="1"`, `resizeMode="horizontal|vertical"`).

#### B. ⚙️ Intégration Samsung *Modes et Routines* (ex-Bixby Routines)
* **`res/xml/shortcuts.xml` + `AndroidManifest.xml` (✨/🔄)** : déclarer 3 *App Shortcuts* statiques (détectés automatiquement par Samsung *Modes et Routines* dans *« Ouvrir une appli ou effectuer une action »*) :
  1. `smarttools://diagnostic` → Lancer un scan rapide

  2. `smarttools://touch-test` → Ouvrir le test tactile / S-Pen

  3. `smarttools://optimize` → Ouvrir `OptimizationScreen`

* **`utils/RoutineNotifier.kt` (✨)** : canaux de notification dédiés (ex. *« Alerte Température Batterie »*, *« Alerte RAM critique »*) pour permettre à l'utilisateur de créer un déclencheur **`SI` notification SmartTools → `ALORS` action One UI**.

* **`ui/screens/AutomationScreen.kt` (🔄)** : transformer cet écran en hub *Modes et Routines* avec :
  * un bouton pour ouvrir directement l'appli Samsung *Modes et Routines* (`com.samsung.android.app.routines`, avec repli gracieux si appareil non-Samsung),

  * des cartes `OneUiCard` avec des « recettes prêtes à copier » et un bouton *Tester la notification déclencheur*.

#### C. ✍️ Module Test Tactile, Matrice OLED & S-Pen
* **`ui/components/TouchMatrixView.kt` (✨)** : utiliser une `View` Android personnalisée embarquée via `AndroidView` dans Compose pour profiter de :
  * **`event.getHistoricalX() / getHistoricalY()`** (tracé fluide à 120/240 Hz sans saccades),

  * **`MotionEvent.TOOL_TYPE_STYLUS` & `TOOL_TYPE_ERASER`** (rejet natif de la paume),

  * **HUD S-Pen** : `getPressure()`, `AXIS_TILT`, `OnHoverListener` (*Air View* avant contact), `BUTTON_STYLUS_PRIMARY`,
  * **`setSystemGestureExclusionRects()`** sur les bords gauche/droit pour tester 100 % de la dalle sans déclencher le geste *Retour* de One UI !

* **`ui/screens/TouchTestScreen.kt` (✨)** : mode immersif plein écran (`LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES` autour du poinçon caméra) + luminosité temporaire à `1.0f` sur le test de pixels morts/burn-in (Rouge, Vert, Bleu, Blanc, Noir OLED, Gris 50 %).

* **`data/DiagnosticReport.kt` & `utils/PdfExporter.kt` (🔄)** : ajouter les résultats du test écran/S-Pen dans `DiagnosticReport` afin qu'ils apparaissent automatiquement dans le rapport PDF exporté !

---

### 3. 🛡️ Optimisations transversales sur tes fichiers existants

1. **Fiabilité thermique (`ThermalGauge.kt` + `SamsungSystemHelper.kt`) :**
   * Combiner `BatteryManager.EXTRA_TEMPERATURE` avec `PowerManager.getCurrentThermalStatus()` (API 29+) pour afficher un vrai état thermique système (`NONE`, `LIGHT`, `MODERATE`, `SEVERE`) dans `ThermalGauge`.

2. **Sécurité `PdfExporter.kt` & `res/xml/file_paths.xml` :**
   * Vérifier que `file_paths.xml` expose uniquement un sous-dossier dédié (`<cache-path name="pdf_reports" path="reports/"/>`) plutôt que tout le stockage externe, et que le partage utilise `FileProvider` avec `FLAG_GRANT_READ_URI_PERMISSION`.

3. **Architecture d'état (anti-recomposition inutile) :**
   * Si tes écrans `DiagnosticScreen.kt` et `OptimizationScreen.kt` interrogent actuellement `BatteryHelper` / `SamsungSystemHelper` directement dans les `@Composable`, déplacer les appels système dans un `ViewModel` (`StateFlow` + `Dispatchers.IO` / `Default`) évitera tout *frame drop* à 120 Hz sur One UI.

4. **Tests unitaires (`src/test/`) :**
   * Ajouter au minimum un fichier de test pour `DiagnosticReport` et les seuils de `BatteryHelper` / `ThermalGauge` (facile à lancer en CI GitHub Actions avec `./gradlew test`).

---

### 🚀 Commence par quel module ?
Passe au code tout de suite, dis-moi lequel tu veux ou colle-moi `build.gradle.kts` / `DiagnosticReport.kt` / `AndroidManifest.xml`
   - pour que je te génère le code 100 % compatible avec tes versions de SDK et dépendances :
1. **Le Widget One UI** (`SmartToolsWidget.kt` + `WidgetUpdateWorker.kt` + XML)

2. **L'intégration Modes & Routines** (`shortcuts.xml` + `AndroidManifest.xml` + `AutomationScreen.kt`)

3. **Le Test Écran OLED, Tactile & S-Pen** (`TouchMatrixView.kt` + `TouchTestScreen.kt` + MAJ `DiagnosticReport.kt`)


### 📁 Structure du Projet
- 》》 **v1.1.0**
```text
SmartTools/
├── README.md
├── LICENSE
├── VERSION.md
├── CHANGELOG.md
├── ROADMAP.md
├── .gitignore 
├── .github/
│   ├── workflows/
│   ├── ISSUE_TEMPLATE/
├── app/
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   └── java/com/smarttools/samsung/
│   │       ├── MainActivity.kt
│   │       ├── data/
│   │       │   ├── DeviceSpecs.kt
│   │       │   └── DiagnosticReport.kt          [Ajouté en v1.1.0]
│   │       ├── ui/
│   │       │   ├── theme/
│   │       │   │   ├── Color.kt
│   │       │   │   └── Theme.kt
│   │       │   ├── screens/
│   │       │   │   ├── DiagnosticScreen.kt
│   │       │   │   ├── OptimizationScreen.kt
│   │       │   │   └── AutomationScreen.kt
│   │       │   └── components/
│   │       │       ├── OneUiCard.kt
│   │       │       └── ThermalGauge.kt          [Ajouté en v1.0.2]
│   │       ├── service/
│   │       │   └── QuickTileService.kt          [Ajouté en v1.1.0]
│   │       └── utils/
│   │           ├── BatteryHelper.kt
│   │           ├── SamsungSystemHelper.kt
│   │           └── PdfExporter.kt               [Ajouté en v1.1.0]
│   └── build.gradle.kts
```

### 📌 Règles de structure
- [x] Aucun fichier inutile  
- [x] Noms de fichiers standardisés  
- [x] Documentation centralisée  
- [x] Séparation stricte entre docs et scripts  

### 🧱 Standards du projet
- [x] Markdown propre et lisible  
- [x] Style uniforme  
- [x] Sections courtes et efficaces  
- [x] Optimisé pour GitHub  

### 🔒 Sécurité structurelle
- [x] Aucun fichier exécutable dans la racine  
- [x] Aucun fichier sensible non chiffré  
- [x] Vérification obligatoire avant ajout  
- [x] Structure compatible CI/CD sécurisée  

### 🎯 Objectif
Fournir une base solide, propre et extensible pour tous les outils Samsung.

---
