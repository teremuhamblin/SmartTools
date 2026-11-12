<div align="center">

  <h1>⚡ SmartTools</h1>
  <p><strong>La boîte à outils Android moderne, légère et conçue sur mesure pour l'écosystème Samsung Galaxy (One UI).</strong></p>

  <p>
    <a href="#-fonctionnalités">Fonctionnalités</a> •
    <a href="#-nouveautés-v110">Nouveautés v1.1.0</a> •
    <a href="#-historique-des-versions">Historique</a> •
    <a href="#-installation">Installation</a> •
    <a href="#-contribuer">Contribuer</a>
  </p>

  <p>
    <img src="https://img.shields.io/badge/Version-1.1.0-blue?style=flat-square" alt="Version 1.1.0">
    <img src="https://img.shields.io/badge/Platform-Android%208.0+-blue?logo=android&logoColor=white&style=flat-square" alt="Platform">
    <img src="https://img.shields.io/badge/Optimized%20for-One%20UI%204%20à%207-blue?logo=samsung&logoColor=white&style=flat-square" alt="Samsung One UI">
    <img src="https://img.shields.io/badge/Target%20SDK-35%20(Android%2015)-blue?style=flat-square" alt="Target SDK 35">
    <img src="https://img.shields.io/badge/License-MIT-purple.svg?style=flat-square" alt="License">
  </p>

</div>

---

## 📖 À propos

**SmartTools** est une application open-source développée en **Kotlin** et **Jetpack Compose**, dédiée aux smartphones, tablettes et modèles pliables Samsung Galaxy. 

Sans publicité, sans tracking et sans processus invasif d'arrière-plan, elle fournit une interface alignée sur les codes esthétiques de **One UI** pour **diagnostiquer**, **optimiser** et **automatiser** le système en toute simplicité.

---

## 🚀 Nouveautés de la v1.1.0

- 📄 **Export de Diagnostic PDF/JSON :** Génération instantanée d'une fiche technique complète (`PdfExporter`) pour la maintenance, la revente ou le SAV.
- ⚡ **Tuile Réglages Rapides (Quick Settings Tile) :** Optimisez la mémoire vive d'un seul geste depuis le volet des notifications One UI (`QuickTileService`).
- 🔋 **Profils d'Automatisation Avancés :** Protection de la batterie par alerte sonore à 85 %, Mode Nuit Éco Intelligent (23h–07h) et Profil Performance Jeu.
- 🌍 **Internationalisation :** Traduction intégrale disponible en Français, Anglais et Espagnol.

---

## ✨ Fonctionnalités principales

### 🔍 1. Diagnostic de précision
* **Télémétrie batterie :** Température en direct avec jauge dynamique (`ThermalGauge`), niveau de charge et surveillance de la surchauffe.
* **Informations système complètes :** Modèle, processeur, version Android, version One UI et état d'intégrité de la garantie **Samsung Knox** (0x0 / 0x1).
* **Mémoire & Stockage :** Métriques précises de la RAM et de l'espace de stockage disponible en temps réel.
* **Rapports imprimables :** Exportation vectorielle locale au format PDF.

### ⚡ 2. Optimisation One UI
* **Nettoyage rapide de la RAM :** Libération immédiate des allocations de mémoire résiduelles.
* **Tuile One-Tap :** Lancez l'optimisation directement depuis la barre d'état One UI sans ouvrir l'application.
* **Gestion du cache applicatif :** Réduction des ralentissements sans dégrader l'expérience utilisateur.

### ⚙️ 3. Automatisation native
* **Protection de la longévité batterie :** Alertes programmables aux seuils recommandés (80% / 85%).
* **Mode Nuit Intelligent :** Allègement des tâches système nocturnes.
* **Profil Jeu :** Priorisation de l'allocation des ressources CPU et mémoire pour l'application au premier plan.

### 🎨 4. Design & Expérience One UI
* **Composants dédiés (`OneUiCard`) :** Coins arrondis à 26 dp, zones d'interaction ergonomiques pour l'usage à une main.
* **Thème Pure AMOLED Black :** Noir absolu `#000000` réduisant la consommation électrique sur dalles Super AMOLED et Dynamic AMOLED 2X.
* **Adaptabilité multiformat :** Mise en page réactive pour smartphones, écrans externes des Galaxy Z Flip, grands écrans des Galaxy Z Fold et tablettes Galaxy Tab.

---

## 📜 Historique des versions (v1.0.0 → v1.1.0)

| Version | Date | Résumé des modifications |
| :---: | :---: | :--- |
| **v1.1.0** | Fév 2025 | **Majeure Mineure** : Ajout de l'export de rapport PDF (`PdfExporter`), tuile Quick Settings (`QuickTileService`), moteur de règles d'automatisation complet, support multilingue (FR/EN/ES) et alignement visuel One UI 6.1/7.0. |
| **v1.0.3** | Jan 2025 | **Protection & SDK 35** : Alerte de charge programmable à 80%/85%, indicateurs visuels circulaires dans l'optimisation, mise à jour vers Android 15 (Target SDK 35) et meilleure précision des cycles de batterie. |
| **v1.0.2** | Déc 2024 | **Matériel & AMOLED** : Vérification du bit Knox (0x0 / 0x1), composant de jauge thermique en temps réel (`ThermalGauge`), mode Pure AMOLED Black pour dalles OLED et fix sur Galaxy Z Flip. |
| **v1.0.1** | Déc 2024 | **Stabilisation & Formats** : Rétrocompatibilité Android 8 à 10 fiabilisée, support des permissions Android 13/14 (`POST_NOTIFICATIONS`), fix du calcul RAM et paddings adaptatifs pour Galaxy Fold et Tab. |
| **v1.0.0** | Nov 2024 | **Sortie Initiale** : Socle Jetpack Compose One UI (`OneUiCard`), écrans Diagnostic, Optimisation et Automatisation de base, utilitaires `BatteryHelper` et `SamsungSystemHelper`. |

---

## 📱 Prérequis de compatibilité

* **Appareils cibles :** Smartphones, pliables et tablettes Samsung Galaxy
* **Version minimale d'Android :** Android 8.0 (API 26 - Oreo)
* **Version recommandée :** Android 14 / Android 15 (One UI 4.0 jusqu'à One UI 7.x)

---

## 🚀 Installation

1. Rendez-vous dans la section [**Releases**](../../releases).
2. Téléchargez le dernier fichier **`SmartTools-v1.1.0.apk`**.
3. Ouvrez le fichier téléchargé sur votre Galaxy et autorisez l'installation d'applications de sources inconnues si le système le demande.
4. Lancez **SmartTools** et ajoutez la tuile rapide dans votre volet de raccourcis One UI !

---

## 🛠️ Prise en main rapide

1. **Diagnostic & Export :** Ouvrez l'onglet `Diagnostic` pour surveiller la santé matérielle, la température de la batterie et cliquez sur **Exporter le Diagnostic (PDF)** pour sauvegarder votre fiche d'état.
2. **Nettoyage :** Allez dans l'onglet `Optimisation` ou cliquez sur la **Tuile Quick Settings** depuis votre barre de notification pour libérer instantanément la mémoire RAM.
3. **Automatisation :** Activez la règle **Protection Batterie 85%** dans l'onglet `Automatisation` pour recevoir un signal dès que la charge atteint son seuil de préservation.

---

## 🔒 Confidentialité & Sécurité

* **100 % Hors-ligne :** Aucune télémétrie, aucune connexion distante, aucun serveur tiers.
* **Respect du Knox :** Les fonctionnalités de diagnostic et d'optimisation n'altèrent pas l'état Knox (`0x0`).
* **Open-Source :** Code auditable librement sous licence MIT.

---

## 🤝 Contribuer

Les contributions, signalements de bugs et suggestions sont les bienvenus !

1. Forkez le dépôt.
2. Créez votre branche : `git checkout -b feature/NouvelleFonctionnalite`
3. Commitez vos modifications : `git commit -m 'feat: ajout de la fonctionnalité X'`
4. Poussez sur votre branche : `git push origin feature/NouvelleFonctionnalite`
5. Ouvrez une **Pull Request**.

---

## 📄 Licence

Ce projet est distribué sous licence **MIT**. Consultez le fichier [LICENSE](LICENSE) pour plus d'informations.
