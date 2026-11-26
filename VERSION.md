# SmartTools — Suivi des versions

Ce document récapitule l’historique, les versions Android/One UI et l’état du cycle de développement de **SmartTools**.

## Version courante

| Version | Version Code | Date | Statut | Android / One UI | Focus |
|:--|:--:|:--|:--|:--|:--|
| **1.2.1** | `111` | 26 novembre 2026 | **Stable · Maintenance** | Android 8.0+ · One UI 4.x–7.x | Métadonnées de version et documentation harmonisées ; fonctionnalités de la v1.2.0 conservées |
| 1.2.0 | `110` | 17 novembre 2026 | Archive | Android 8.0+ · One UI 4.x–7.x | Widget, Modes et Routines, tests écran/S-Pen |
| 1.1.0 | `110` | Février 2025 | Archive | Android 8.0+ · One UI 5.0–7.x | Rapports PDF, automatisations et tuile rapide |
| 1.0.3 | `103` | Janvier 2025 | Archive | Android 8.0+ · One UI 4.0–6.1 | Seuils batterie et préparation tuile rapide |
| 1.0.2 | `102` | Décembre 2024 | Archive | Android 8.0+ · One UI 4.0–6.1 | Knox, thermique et thème AMOLED |
| 1.0.1 | `101` | Décembre 2024 | Archive | Android 8.0+ · One UI 4.0–6.0 | Correctifs de compatibilité et adaptations d’écran |
| 1.0.0 | `100` | Novembre 2024 | Archive | Android 8.0+ · One UI 4.0–6.0 | Version initiale |

> Le versionCode `110` a été historiquement utilisé pour les versions 1.1.0 et 1.2.0 dans la configuration publiée. La v1.2.1 le fait progresser à `111` afin de conserver un code Android supérieur.

## Compatibilité de la v1.2.1

- **Min SDK :** Android 8.0 (API 26)
- **Target SDK :** Android 15 (API 35)
- **One UI visée :** versions 4.x à 7.x
- Les informations matérielles accessibles peuvent varier selon l’appareil et la version Android.

## Cycle de maintenance v1.2.1

- [x] Mettre à jour `versionName` et `versionCode` dans `app/build.gradle.kts`.
- [x] Harmoniser README, changelog et suivi de version.
- [ ] Compiler l’application et exécuter les tests avant publication d’un APK.
- [ ] Publier les artefacts vérifiés dans GitHub Releases.
