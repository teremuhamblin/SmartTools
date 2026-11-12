###### ROADMAP.md >> markdown
- — Cycle v1.0.0 → v1.1.0

# SmartTools
Ce document retrace la feuille de route opérationnelle suivie de la **v1.0.0** initiale jusqu'à la livraison finale de la **v1.1.0**.

---

## 🎯 Vision du cycle
Transformer un prototype d'utilitaire Samsung de base (v1.0.0) en une suite d'outils mature, stable, parfaitement intégrée à One UI (tuiles, alertes de charge, Knox, exports PDF) et adaptée à tous les formats Galaxy (mobiles, pliables, tablettes).

---

## 🧭 Vue d'ensemble du Cycle
---

## 📌 Détail des Étapes et Jalons

### Jalon 1 : Version Initiale — `v1.0.0` (Novembre 2024)
- [x] Initialisation du squelette Jetpack Compose avec le style One UI.
- [x] Création des trois écrans clés (`DiagnosticScreen`, `OptimizationScreen`, `AutomationScreen`).
- [x] Implémentation du composant réutilisable `OneUiCard`.
- [x] Extraction des données système de base via `DeviceSpecs`, `BatteryHelper` et `SamsungSystemHelper`.

---

### Jalon 2 : Stabilisation & Rétrocompatibilité — `v1.0.1` (Décembre 2024)
- [x] **Correctifs de compatibilité legacy** :
  - Sécurisation des méthodes de `BatteryHelper` pour Android 8 à 10.
  - Résolution des plantages liés aux permissions Android 13/14 (`POST_NOTIFICATIONS`).
- [x] **Adaptabilité de l'interface** :
  - Prise en charge des écrans larges (tablettes Galaxy Tab et Galaxy Z Fold en mode déplié).
- [x] Correction des calculs de mémoire vive (RAM) et de stockage.

---

### Jalon 3 : Approfondissement Matériel Samsung — `v1.0.2` (Fin Décembre 2024)
- [x] **Sécurité & Knox** :
  - Lecture non-intrusive du flag de garantie Knox dans `SamsungSystemHelper`.
- [x] **Thermique & Affichage** :
  - Création du composant visuel `ThermalGauge.kt` pour surveiller la température CPU/Batterie.
  - Ajout du thème **Pure AMOLED Black** (`Theme.kt`) pour optimiser l'autonomie sur dalles OLED.
- [x] Mise en cache locale des spécifications statiques (`DeviceSpecs`) pour alléger les recompositions Compose.

---

### Jalon 4 : Automatisation & Raccourcis — `v1.0.3` (Janvier 2025)
- [x] **Protection Batterie** :
  - Alerte configurable par notification lors de l'atteinte du seuil de 80% / 85%.
- [x] **Modernisation de l'écosystème** :
  - Migration vers le SDK Android 15 (Target API 35).
  - Optimisation des jauges d'animation dans `OptimizationScreen`.
- [x] Préparation de l'architecture pour le futur `QuickTileService`.

---

### Jalon 5 : Version Majeure Mineure — `v1.1.0` (Février 2025)
- [x] **Rapports & Partage** :
  - Création de `PdfExporter.kt` et `DiagnosticReport.kt` pour exporter les fiches de santé en PDF ou JSON.
- [x] **Intégration One UI Complète** :
  - Implémentation de la tuile rapide Android (`QuickTileService.kt`) pour déclencher l'optimisation en un geste.
  - Alignement des styles visuels sur One UI 6.1 et 7.0.
- [x] **Automatisation complète** :
  - Moteur de règles multi-conditions dans `AutomationScreen.kt` (Batterie, Heure, Mode Éco).
- [x] **Internationalisation** :
  - Traductions complètes Français, Anglais et Espagnol.

---

## 🔮 Perspectives Post-v1.1.0 (Vers la v1.2.0)
- [ ] Widget pour l'écran d'accueil One UI (indicateur direct de RAM et batterie).
- [ ] Support d'intégration avec l'application Bixby Routines.
- [ ] Module de test interactif de l'écran tactile (matrices de pixels et numériseur tactile S-Pen).
