# SmartTools — Journal des modifications

Toutes les modifications notables apportées à **SmartTools** sont documentées ici. Le format suit [Keep a Changelog](https://keepachangelog.com/fr/1.0.0/) et [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [1.2.1] - 2026-11-26

### Maintenance
- Alignement des métadonnées Android sur `versionName = "1.2.1"` et `versionCode = 111`.
- Harmonisation de la documentation du dépôt pour la version de maintenance v1.2.1.
- Aucune nouvelle fonctionnalité ni correction applicative n’est revendiquée dans cette version.
- Compilation et tests automatisés à exécuter avant la publication d’un APK.

## [1.2.0] - 2026-11-17

### Ajouté
- Widget One UI Jetpack Glance aux formats `2×1` et `4×1`, actualisation manuelle et planification WorkManager.
- Raccourcis et liens profonds pour Samsung Modes et Routines.
- Tests interactifs OLED, tactile multi-touch et S-Pen compatible.
- Intégration de résultats supplémentaires au rapport PDF et à la tuile rapide.

### Sécurité et qualité
- Partage des rapports limité par la configuration FileProvider.
- Notifications et intégrations déclarées pour les versions Android prises en charge.

---

## [1.1.0] - 2025-02-14

### Ajouté
- Export de diagnostic complet via `PdfExporter.kt`.
- Profils d’automatisation et règles liées à la batterie et aux horaires.
- Tuile Quick Settings One UI et support français, anglais et espagnol.

### Modifié
- Alignement visuel sur les guidelines One UI 6.1 / 7.0.
- Refactorisation de `SamsungSystemHelper` pour la compatibilité Knox.

### Corrigé
- Fuite de mémoire lors de l’observation continue des capteurs thermiques.

---

## [1.0.3] - 2025-01-20

### Ajouté
- Alertes de seuil de batterie configurables à 80 % ou 85 %.
- Préparation de raccourcis système One UI.

### Modifié
- Refonte de l’écran d’optimisation et migration vers Android 15 (Target API 35).

### Corrigé
- Amélioration du relevé des cycles de charge et du rafraîchissement d’état dans AutomationScreen.

---

## [1.0.2] - 2024-12-28

### Ajouté
- Indicateur thermique et lecture de l’état Knox.
- Thème Pure AMOLED Black.

### Corrigé
- Crash sur Galaxy Z Flip et calcul de température de batterie sur One UI 5.1.

---

## [1.0.1] - 2024-12-05

### Modifié
- Paddings adaptatifs pour tablettes et appareils pliables.
- Optimisation des appels système dans BatteryHelper.

### Corrigé
- Compatibilité Android 8/9, calcul RAM et permission de notification Android 13+.

---

## [1.0.0] - 2024-11-15

### Ajouté
- Première version de SmartTools pour Samsung Galaxy.
- Écrans Diagnostic, Optimisation et Automatisation, design One UI et utilitaires système.

[1.2.1]: https://github.com/teremuhamblin/SmartTools/compare/v1.2.0...v1.2.1
[1.2.0]: https://github.com/teremuhamblin/SmartTools/compare/v1.1.0...v1.2.0
[1.1.0]: https://github.com/teremuhamblin/SmartTools/compare/v1.0.3...v1.1.0
[1.0.3]: https://github.com/teremuhamblin/SmartTools/compare/v1.0.2...v1.0.3
[1.0.2]: https://github.com/teremuhamblin/SmartTools/compare/v1.0.1...v1.0.2
[1.0.1]: https://github.com/teremuhamblin/SmartTools/compare/v1.0.0...v1.0.1
[1.0.0]: https://github.com/teremuhamblin/SmartTools/releases/tag/v1.0.0
