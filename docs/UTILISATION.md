# Guide d’utilisation et de développement

Ce guide présente les parcours décrits pour SmartTools v1.2.1. Certaines informations matérielles et fonctions Samsung dépendent du modèle et de la version Android.

## Prérequis

- Un appareil Samsung Galaxy est recommandé pour les fonctions spécifiques à One UI.
- Android 8.0 (API 26) ou ultérieur.
- Pour compiler : Android Studio et un JDK compatible avec la configuration du projet (Java 17).
- Pour les tests matériels : appareil physique compatible ; un émulateur ne reproduit pas toutes les capacités Samsung ou S-Pen.

## Installer l’application

1. Consultez la page [Releases](https://github.com/teremuhamblin/SmartTools/releases).
2. Téléchargez l’APK s’il est disponible pour la version souhaitée.
3. Vérifiez la version et la provenance de l’APK avant installation.
4. Si Android le demande, autorisez l’installation depuis la source choisie.

> La mise à jour des fichiers source vers v1.2.1 ne signifie pas, à elle seule, qu’un APK v1.2.1 a été compilé ou publié.

## Parcours disponibles

### Diagnostic

Consultez les informations matérielles et système exposées par l’appareil. Les métriques disponibles et leur précision peuvent varier selon le fabricant, le modèle et la version Android.

### Widget d’accueil

Ajoutez le widget SmartTools depuis le sélecteur de widgets de l’écran d’accueil. Les formats proposés et les données affichées dépendent de la configuration de l’appareil et du widget.

### Test de l’écran et du S-Pen

Lancez les mires d’affichage ou le test tactile depuis l’application. Les informations de stylet ne sont disponibles que sur du matériel compatible. Utilisez les mires à un niveau de luminosité raisonnable et quittez le test après inspection.

### Modes et Routines

Les raccourcis de l’application peuvent être utilisés avec les intégrations Samsung disponibles sur l’appareil. La disponibilité dépend de l’installation et de la version de Modes et Routines.

### Rapport PDF et tuile rapide

L’export PDF et la tuile de réglages rapides sont accessibles depuis les parcours correspondants de l’application et Android.

## Compiler et tester

Depuis la racine du dépôt, si le wrapper Gradle y est présent :

```bash
./gradlew test
./gradlew assembleDebug
```

Sous Windows :

```powershell
.\gradlew.bat test
.\gradlew.bat assembleDebug
```

L’APK debug est généralement généré sous `app/build/outputs/apk/debug/`. Cette commande ne produit pas un APK signé pour distribution.

## Dépannage

- **Une donnée est absente :** vérifiez la compatibilité du modèle et de la version Android ; certaines API ne sont pas exposées partout.
- **Le widget n’apparaît pas :** vérifiez que l’application est installée et consultez le sélecteur de widgets de One UI.
- **Le S-Pen n’est pas détecté :** confirmez que l’appareil et le stylet prennent en charge les événements concernés.
- **La compilation échoue :** vérifiez le JDK configuré, la synchronisation Gradle et les journaux de build.
