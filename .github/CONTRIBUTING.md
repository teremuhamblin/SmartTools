# Contribuer à SmartTools

Merci de contribuer à SmartTools. Les corrections, améliorations de documentation, tests et propositions de fonctionnalités sont les bienvenus.

## Avant de commencer

- Pour un bug, ouvrez une issue en décrivant le modèle, la version Android/One UI et les étapes pour reproduire le problème.
- Pour une évolution importante, proposez d’abord l’idée dans une issue afin d’en discuter.
- Ne publiez pas d’informations personnelles, journaux sensibles ou données d’identification d’appareil.

## Développement

1. Forkez le dépôt et créez une branche dédiée, par exemple `fix/widget-refresh` ou `docs/installation`.
2. Gardez les changements ciblés et expliquez les choix importants.
3. Ajoutez ou mettez à jour les tests lorsque le changement s’y prête.
4. Lancez les vérifications disponibles :

   ```bash
   ./gradlew test
   ./gradlew assembleDebug
   ```

5. Ouvrez une Pull Request décrivant le problème, la solution et les vérifications effectuées.

## Principes de contribution

- Demandez uniquement les permissions nécessaires et expliquez leur usage.
- Évitez les dépendances sans nécessité claire.
- N’affirmez pas une compatibilité ou une garantie de confidentialité sans vérification dans le manifeste et le code.
- Pour toute évolution du partage de fichiers, vérifiez `FileProvider` et `res/xml/file_paths.xml`.
- Pour les changements d’interface, fournissez si possible une capture d’écran et indiquez l’appareil testé.
- Ne commitez ni clés de signature, ni fichiers locaux, ni données personnelles.

## Messages de commit

Des messages concis et descriptifs sont recommandés, par exemple :

- `fix(widget): corrige le rafraîchissement`
- `docs: clarifie les prérequis Android`
- `test(report): ajoute un cas de validation`

Les Pull Requests sont examinées avant intégration ; l’ouverture d’une PR ne garantit pas son acceptation.
