###### ARCHITECTURE.md >> markdown
```text
Application Android native** (telle que décrite dans le README avec One UI, Shizuku, APK, diagnostic Knox, etc.).
Section **« Architecture » réajustée, technique et professionnelle**.
```

---

## 🏗️ Architecture du projet

SmartTools repose sur une architecture **modulaire, réactive et découplée** (Clean Architecture), pensée pour garantir l'absence de télémétrie, une empreinte mémoire minimale (< 15 Mo) et une sécurité stricte des opérations système.

```text
smarttools/
├── app/                        # Point d'entrée de l'application (DI, App lifecycle)
├── core/
│   ├── designsystem/           # Composants visuels One UI / Material You
│   ├── common/                 # Utilitaires génériques, dispatchers, extensions
│   └── platform/               # Ponts système Android & abstractions de permissions
├── features/
│   ├── diagnostic/             # Cycles batterie, capteurs, écran AMOLED, Knox
│   ├── debloater/              # Gel/dégel de packages One UI, listes sécurisées
│   └── routines/               # Automatisation légère et alertes de charge
└── system-bridge/              # Moteur d'exécution privilégié
    ├── api/                    # Contrats d'exécution abstraits
    ├── shizuku/                # Implémentation du protocole Shizuku (sans root)
    └── standard/               # Fallback sur les APIs publiques Android
```

---

### 🧩 Découpage des couches

1. **Couche Présentation (UI)**  
   * Conçue selon les directives graphiques **Samsung One UI**.
   * Réactivité fluide sans surcharge de bibliothèques tierces superflues.

2. **Couche Métier (Domain / Features)**  
   * Chaque module (`diagnostic`, `debloater`, `routines`) est hermétique et indépendant.
   * Aucune fonctionnalité métier ne dépend directement du mode d'élévation de privilèges (Shizuku ou Standard).

3. **Pont Système (System Bridge)**  
   * Fournit une interface unifiée pour interagir avec le système Samsung :
     * **Mode Standard :** Diagnostics passifs (lectures de `/sys/class/power_supply/`, APIs Android).
     * **Mode Shizuku / ADB :** Gestion des packages (`pm disable-user`, `cmd package`) pour le débloater sans root.

---

### 🔐 Sécurité & Conception Zero-Trust

* **Permissions réseau inexistantes :** `android.permission.INTERNET` est totalement exclu du `AndroidManifest.xml`. Aucune fuite de données n'est techniquement possible.
* **Principe du moindre privilège :** L'application ne sollicite Shizuku que lors d'actions explicites de l'utilisateur (gel d'une app système, changement de fréquence écran).
* **Réversibilité totale :** Le débloater n'efface aucun binaire de `/system` ; il utilise les commandes de désactivation utilisateur réversibles en 1 clic.
* **Exécution transparente :** Les commandes envoyées au système sont visibles et auditables par l'utilisateur avant confirmation.
```

### 💡 Pourquoi ce réajustement valorise votre projet :
* **Cohérence avec le reste du README :** On comprend immédiatement comment l'APK fonctionne concrètement avec One UI et Shizuku.
* **Rassurant pour les contributeurs :** L'arborescence par modules (`features/*`) facilite l'ajout de nouveaux outils sans casser le reste.
* **Gage de sécurité pour les utilisateurs :** La suppression de la permission `INTERNET` au niveau architectural prouve mathématiquement l'engagement *"100% hors-ligne"* mentionné dans la section Confidentialité.
