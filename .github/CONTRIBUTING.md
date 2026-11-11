###### **SmartTools v1.0.0** en respectant scrupuleusement ses piliers (absence totale de réseau, sécurité Knox/Shizuku, légèreté et design One UI).

---

### 1. `CONTRIBUTING.md`
# Contribuer à SmartTools ⚡

Merci de vous intéresser au développement de **SmartTools** ! Ce projet est un utilitaire open-source axé sur la confidentialité, la légèreté et la sécurité pour les appareils Samsung.

Toutes les contributions sont les bienvenues : corrections de bugs, nouvelles automatisations, optimisations de code ou améliorations de l'interface.

---

## 🛑 Règles d'or du projet

Pour préserver l'ADN de SmartTools, toute Pull Request devra respecter ces règles sous peine de refus :

1. **Aucun accès Internet :** La permission `android.permission.INTERNET` est formellement interdite dans le projet. Aucune bibliothèque de télémétrie, de crash report externe ou d'analytics ne sera acceptée.
2. **Taille maîtrisée (< 15 Mo) :** Évitez l'ajout de dépendances lourdes. Privilégiez les APIs Android natives et le code compact.
3. **Approche non destructrice :** Le module de débloat ne doit jamais supprimer physiquement de binaires système (uniquement gel/dégel via `pm disable-user` ou Shizuku).
4. **Cohérence One UI :** L'UI doit respecter les codes de design de Samsung (espaces, placement des boutons sur la moitié inférieure de l'écran pour l'accessibilité à une main).

---

## 🛠️ Configuration de l'environnement

1. Installez **Android Studio** (dernière version stable recommandée).
2. Clonez votre fork du dépôt :
   ```bash
   git clone https://github.com/VOTRE_NOM/smarttools.git
   cd smarttools
   ```
3. Ouvrez le projet dans Android Studio et synchronisez Gradle.
4. Lancez le build de vérification :
   ```bash
   ./gradlew assembleDebug
   ```

---

## 🌿 Workflow Git & Branches

1. Créez une branche thématique depuis `main` :
   * Nouvelle fonctionnalité : `feature/nom-de-la-feature`
   * Correction de bug : `fix/description-du-bug`
   * Optimisation : `perf/optimisation-concernee`
2. Adoptez des messages de commit clairs ([Conventional Commits](https://www.conventionalcommits.org/)) :
   * `feat(diagnostic): ajout du test du capteur de proximité`
   * `fix(debloater): protection contre la désactivation de com.android.phone`
   * `docs: mise à jour du guide Shizuku`
3. Poussez votre branche et ouvrez une **Pull Request** détaillée avec des captures d'écran si l'interface graphique a été modifiée.

---

## 🧪 Tests recommandés

Avant de soumettre votre PR :
* Vérifiez que le linting Android passe sans erreur critique :
  ```bash
  ./gradlew lint
  ```
* Testez si possible sur un appareil Samsung physique sous One UI (avec et sans Shizuku)
