# Politique de sécurité SmartTools

La sécurité des données et la transparence des permissions sont importantes pour SmartTools. Les affirmations de confidentialité doivent être vérifiées à partir du code, du manifeste et des dépendances de la version concernée.

## Versions

La version publiée la plus récente est la référence pour les signalements et les corrections. La disponibilité d’une version corrigée doit être confirmée dans la page [Releases](https://github.com/teremuhamblin/SmartTools/releases).

## Signaler une vulnérabilité

Pour un problème de sécurité :

1. **Ne publiez pas les détails dans une issue publique.**
2. Si le signalement privé est activé pour ce dépôt, utilisez [GitHub Private Vulnerability Reporting](../../security/advisories/new).
3. Sinon, contactez le mainteneur par un canal privé indiqué sur son profil GitHub.
4. Fournissez une description, les étapes de reproduction, la version SmartTools et les versions Android/One UI concernées. Évitez d’inclure des données personnelles ou identifiants réels.

## Périmètre d’examen

Les signalements pertinents peuvent concerner notamment :

- permissions Android et composants exportés ;
- liens profonds et raccourcis ;
- partage de rapports et configuration `FileProvider` ;
- traitement des données locales ;
- dépendances et chaîne de compilation.

N’envoyez pas de rapport PDF réel ou d’informations sensibles lors d’un signalement public. Le délai de réponse et la disponibilité d’un correctif ne sont pas garantis ; le mainteneur peut confirmer la réception et préciser les prochaines étapes.
