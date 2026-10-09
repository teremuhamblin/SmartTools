###### PAYLOAD.md >> markdown
### 🛡️ 1. Webhook #1
- SmartTools-Core
   - Objectif : recevoir tous les événements GitHub pour synchroniser ton tableau de bord SmartTools.

### Configuration GitHub
```text
Settings → Webhooks → Add webhook
```

| Champ | Valeur |
|-------|--------|
| Payload URL | https://smarttools.example.com/webhook/core |
| Content type | application/json |
| Secret | smarttools-core-secret |
| Events | Send me everything |
| Active | ✔️ |

### Payload JSON
```json
{
  "project": "SmartTools",
  "module": "Core",
  "event": "{{event}}",
  "repository": "{{repository.name}}",
  "branch": "{{ref}}",
  "sender": "{{sender.login}}",
  "timestamp": "{{date}}"
}
```

>Fichier : WEBHOOK-CORE.md
```md

SmartTools – Webhook Core

URL
https://smarttools.example.com/webhook/core

Secret
smarttools-core-secret

Événements
- push
- pull_request
- issues
- workflow_run
- deployment

Objectif
Synchronisation globale du projet SmartTools.
```

---

### ⚙️ 2. Webhook #2
### SmartTools-Security
Objectif : recevoir uniquement les événements critiques pour la sécurité.

Configuration GitHub
| Champ | Valeur |
|-------|--------|
| Payload URL | https://smarttools.example.com/webhook/security |
| Content type | application/json |
| Secret | smarttools-security-secret |
| Events | issues, pullrequest, securityadvisory |
| Active | ✔️ |

Payload JSON
```json
{
  "project": "SmartTools",
  "module": "Security",
  "alert": "{{action}}",
  "type": "{{event}}",
  "actor": "{{sender.login}}",
  "details": "{{issue.title}}"
}
```

>Fichier : WEBHOOK-SECURITY.md
```md

SmartTools – Webhook Security

URL
https://smarttools.example.com/webhook/security

Secret
smarttools-security-secret

Événements
- issues
- pull_request
- security_advisory

Objectif
Détection et traitement des événements sensibles.
```

---

### 📡 3. Webhook #3
### SmartTools-Deploy
Objectif : déclencher automatiquement un déploiement externe.

Configuration GitHub
| Champ | Valeur |
|-------|--------|
| Payload URL | https://smarttools.example.com/webhook/deploy |
| Content type | application/json |
| Secret | smarttools-deploy-secret |
| Events | workflow_run (completed) |
| Active | ✔️ |

### Payload JSON
```json
{
  "project": "SmartTools",
  "module": "Deploy",
  "status": "{{workflow_run.conclusion}}",
  "workflow": "{{workflow_run.name}}",
  "commit": "{{workflowrun.headsha}}"
}
```

Fichier : WEBHOOK-DEPLOY.md
```md

SmartTools – Webhook Deploy

URL
https://smarttools.example.com/webhook/deploy

Secret
smarttools-deploy-secret

Événements
- workflow_run (completed)

Objectif
Déclencher un déploiement automatique après CI/CD.
```

---

### 🧱 4. Serveur universel pour SmartTools
Fichier : webhook-server.js

```js
const express = require("express");
const app = express();
app.use(express.json());

app.post("/webhook/:module", (req, res) => {
  const module = req.params.module;
  console.log([SmartTools] Webhook reçu (${module}) :, req.body);
  res.status(200).send("OK");
});

app.listen(3000, () => {
  console.log("Serveur SmartTools Webhooks actif sur port 3000");
});
```

---
