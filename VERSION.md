###### VERSION.md >> markdown

# SmartTools
- — Suivi des Versions

Ce document récapitule l'historique des versions, les correspondances de versions Android/One UI et l'état actuel du cycle de développement de **SmartTools**.

---

## Synthèse du cycle
- 》》 **v1.0.0 → v1.1.0**

| Version | Version Code | Date | Statut | Cible One UI | Focus principal |
| :--- | :---: | :--- | :--- | :--- | :--- |
| **`1.1.0`** | `110` | Février 2025 | **Actuelle** | One UI 5.0 à 7.x | Export diagnostics, profils d'automatisation complets, Tuile One UI |
| **`1.0.3`** | `103` | Janvier 2025 | Archive | One UI 4.0 à 6.1 | Alertes seuil batterie, pré-intégration Tuile Quick Settings |
| **`1.0.2`** | `102` | Décembre 2024| Archive | One UI 4.0 à 6.1 | Détection Knox, suivi thermique en direct, mode AMOLED Black |
| **`1.0.1`** | `101` | Décembre 2024| Archive | One UI 4.0 à 6.0 | Correctifs de rétrocompatibilité, layout adaptatif Fold/Tablette |
| **`1.0.0`** | `100` | Novembre 2024| Archive | One UI 4.0 à 6.0 | Sortie initiale (Diagnostic, Optimisation, Automation, OneUiCard) |

---

## Compatibilité Matérielle & Logicielle (v1.1.0)

- **Min SDK** : Android 8.0 (API 26 - Oreo)
- **Target SDK** : Android 15 (API 35)
- **Surcouche recommandée** : Samsung One UI 4.1 jusqu'à One UI 7.x
- **Appareils testés** :
  - Séries Galaxy S (S21, S22, S23, S24)
  - Séries Galaxy Z (Fold 4/5/6, Flip 4/5/6)
  - Séries Galaxy A (A34, A54, A55)
  - Séries Galaxy Tab (Tab S8, Tab S9)

---

## Arborescence du Projet à la v1.1.0

```text
SmartTools/
├── README.md
├── LICENSE
├── VERSION.md
├── CHANGELOG.md
├── ROADMAP.md
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
