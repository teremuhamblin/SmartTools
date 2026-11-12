###### `CHANGELOG.md` >> markdown 

# SmartTools

Toutes les modifications notables apportées au projet **SmartTools** sont documentées dans ce fichier.

Le format est basé sur [Keep a Changelog](https://keepachangelog.com/fr/1.0.0/) et respecte les conventions [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [1.1.0] - 2025-02-14

### Ajouté
- **Export de diagnostic complet** : Génération de fiches d'état de l'appareil en PDF et JSON via le nouvel utilitaire `PdfExporter.kt` (`DiagnosticScreen`).
- **Moteur d'automatisation avancé** :
  - Déclencheurs basés sur l'état de charge (débranchement automatique simulé, protection à 80%/85%).
  - Profils prédéfinis : *Performance Jeu*, *Nuit Paisible*, *Éco Extrême* (`AutomationScreen`).
- **Tuile Quick Settings One UI** : Ajout du service `QuickTileService.kt` pour lancer une optimisation One-Tap directement depuis le volet des notifications.
- **Support multilingue** : Intégration complète du français, de l'anglais et de l'espagnol dans les ressources `res/values/strings.xml`.

### Modifié
- Alignement visuel sur les guidelines de **One UI 6.1 / 7.0** (courbures de coins `OneUiCard` et animations d'échelle).
- Refactorisation de `SamsungSystemHelper` pour améliorer la compatibilité Knox sur les modèles Galaxy récents.

### Corrigé
- Fuite de mémoire lors de l'observation continue des capteurs thermiques.

---

## [1.0.3] - 2025-01-20

### Ajouté
- **Alertes de seuil de batterie** : Notification programmable lorsque la batterie atteint 80% ou 85% pour préserver sa longévité (`BatteryHelper`).
- Début d'intégration pour les raccourcis système One UI (App Shortcuts).

### Modifié
- Refonte visuelle de `OptimizationScreen` avec indicateur de progression circulaire pour la libération de cache.
- Mise à jour du target SDK vers **Android 15 (API 35)**.

### Corrigé
- Précision accrue du relevé des cycles de charge sur les SoC Exynos et Snapdragon récents.
- Résolution d'un bug de rafraîchissement d'état dans `AutomationScreen` lors du basculement en arrière-plan.

---

## [1.0.2] - 2024-12-28

### Ajouté
- **Indicateur thermique en temps réel** : Ajout du composant `ThermalGauge.kt` dans `DiagnosticScreen`.
- **Statut Knox & Sécurité** : Détection du statut Knox Warranty Void (0x0 / 0x1) intégrée dans `SamsungSystemHelper`.
- **Thème Pure AMOLED Black** : Mode ultra-sombre ajouté dans `Theme.kt` et `Color.kt` pour maximiser l'économie d'énergie sur écrans Super AMOLED / Dynamic AMOLED 2X.

### Modifié
- Allègement de l'empreinte mémoire de `DiagnosticScreen` grâce à la mise en cache de `DeviceSpecs`.

### Corrigé
- Crash au démarrage sur les Galaxy Z Flip lorsque l'écran externe était utilisé.
- Correction d'un calcul erroné de la température de la batterie sur One UI 5.1.

---

## [1.0.1] - 2024-12-05

### Modifié
- Amélioration de `OneUiCard.kt` avec paddings réactifs adaptés aux tablettes (Galaxy Tab) et écrans larges pliables (Galaxy Z Fold).
- Optimisation des appels système dans `BatteryHelper.kt` pour réduire la consommation CPU en tâche de fond.

### Corrigé
- Résolution d'un crash sur Android 8.0/9.0 provoqué par des API de télémétrie batterie réservées à Android 11+.
- Correction du calcul de la mémoire RAM disponible dans `DiagnosticScreen`.
- Ajout de la demande explicite de permission `POST_NOTIFICATIONS` sur Android 13+.

---

## [1.0.0] - 2024-11-15

### Ajouté
- **Version initiale de SmartTools** spécialement taillée pour l'écosystème Samsung Galaxy.
- **DiagnosticScreen** :
  - Métadonnées matérielles complètes via `DeviceSpecs.kt` (processeur, RAM, ROM, santé de batterie).
- **OptimizationScreen** :
  - Nettoyage rapide de la mémoire cache et fermeture des processus d'arrière-plan inactifs.
- **AutomationScreen** :
  - Première mouture de bascules rapides pour les modes de performance système.
- **Design System One UI** :
  - Composant socle `OneUiCard.kt` respectant les marges et la courbure One UI.
  - Thème Compose clair/sombre adaptatif (`Theme.kt`, `Color.kt`).
- **Classes Utilitaires** :
  - `SamsungSystemHelper.kt` : Accès aux propriétés spécifiques Samsung.
  - `BatteryHelper.kt` : Lecture du niveau, de la température et de la tension de charge.

[1.1.0]: https://github.com/teremuhamblin/SmartTools/compare/v1.0.3...v1.1.0
[1.0.3]: https://github.com/teremuhamblin/SmartTools/compare/v1.0.2...v1.0.3
[1.0.2]: https://github.com/teremuhamblin/SmartTools/compare/v1.0.1...v1.0.2
[1.0.1]: https://github.com/teremuhamblin/SmartTools/compare/v1.0.0...v1.0.1
[1.0.0]: https://github.com/teremuhamblin/SmartTools/releases/tag/v1.0.0
