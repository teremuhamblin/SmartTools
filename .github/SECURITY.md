# Politique de Sécurité — SmartTools

La sécurité et la protection de l'intégrité de votre smartphone sont au cœur de la conception de **SmartTools**. Étant donné que cette application interagit avec des paramètres système sensibles (via l'API Shizuku / ADB), nous appliquons une politique de tolérance zéro envers les vulnérabilités logicielles.

---

## 🛡️ Versions supportées

Seule la dernière version publiée reçoit activement des correctifs de sécurité.

| Version | Prise en charge |
| :--- | :--- |
| **v1.0.x** |  **Oui** |
| < v1.0.0 |  Non |

---

## 🔒 Périmètre de Sécurité & Garanties

SmartTools garantit par conception :
* **Zéro Réseau :** L'absence totale de permissions réseau (`INTERNET`) dans le manifest garantit qu'aucune donnée de diagnostic, cycle batterie ou identifiant matériel ne peut quitter votre appareil.
* **Non-Destruction :** Le débloater ne supprime pas de fichiers système en écriture (`/system` reste en lecture seule). Les actions passent par le gestionnaire de packages utilisateur (`pm disable-user`), permettant une réactivation immédiate.
* **Isolation Shizuku :** Les commandes envoyées au daemon Shizuku sont sanitisées en amont pour prévenir toute injection de commande shell non désirée.

---

## 🚨 Signaler une Vulnérabilité

Si vous découvrez une faille de sécurité (par exemple un risque de brique logicielle sur One UI, une vulnérabilité d'escalade de privilèges ou un bypass d'isolation) :

1. **Ne créez PAS d'issue GitHub publique.**
2. Utilisez la fonction [**GitHub Private Vulnerability Reporting**](../../security/advisories/new) intégrée à ce dépôt.
3. À défaut, contactez directement le mainteneur via son adresse e-mail de sécurité présente sur son profil GitHub en précisant :
   * La description précise de la vulnérabilité.
   * Les étapes pour reproduire le problème.
   * Le modèle d'appareil Samsung et la version de One UI testée.

---

## ⏱️ Délai de réponse & Divulgation responsable

* **Accusé de réception :** sous 48 heures.
* **Analyse & Correctif :** un correctif prioritaire sera produit et testé avant toute publication.
* **Divulgation publique :** une note de sécurité détaillée (*Security Advisory*) sera publiée sur GitHub en même temps que la mise à jour corrective (ex: v1.0.1) afin de protéger les utilisateurs déjà en possession de l'APK.
