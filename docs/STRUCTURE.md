# Structure du dépôt

Cette arborescence résume les principaux fichiers de SmartTools v1.2.1. Elle sert de repère de navigation ; les noms exacts et le contenu du code source restent la référence.

```text
SmartTools/
├── .github/
│   ├── workflows/
│   ├── ISSUE_TEMPLATE/
│   ├── CODEOFCONDUCT.md
│   ├── CONTRIBUTING.md
│   └── SECURITY.md
├── app/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/com/smarttools/samsung/
│       │   │   ├── MainActivity.kt
│       │   │   ├── data/
│       │   │   ├── service/
│       │   │   ├── widget/
│       │   │   ├── theme/
│       │   │   ├── ui/
│       │   │   │   ├── components/
│       │   │   │   └── screens/
│       │   │   └── utils/
│       │   └── res/
│       │       └── xml/
│       └── test/java/com/smarttools/samsung/
├── docs/
│   ├── ARCHITECTURE.md
│   ├── STRUCTURE.md
│   └── UTILISATION.md
├── CHANGELOG.md
├── LICENSE
├── README.md
├── ROADMAP.md
└── VERSION.md
```

## Où chercher ?

- **Version Android et dépendances :** `app/build.gradle.kts`
- **Permissions et composants Android :** `app/src/main/AndroidManifest.xml`
- **Écrans et composants d’interface :** `app/src/main/java/com/smarttools/samsung/ui/`
- **Widget d’accueil :** `app/src/main/java/com/smarttools/samsung/widget/`
- **Services et intégrations :** `service/`, `utils/` et `app/src/main/res/xml/`
- **Tests unitaires :** `app/src/test/`
- **Guides et historique :** `docs/`, `CHANGELOG.md`, `VERSION.md` et `ROADMAP.md`

Les dossiers peuvent évoluer avec le code. En cas de divergence, l’arborescence réelle du dépôt prévaut sur ce document.
