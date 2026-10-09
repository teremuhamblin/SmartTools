### 🛰️ Webhook Discord — SmartTools-DiscordOps

### 🎯 Objectif
Envoyer automatiquement un message dans un canal Discord à chaque événement GitHub critique (push, PR, issue, workflow_run).

### 🔧 Configuration GitHub
`
Settings → Webhooks → Add webhook
`

| Champ | Valeur |
|-------|--------|
| Payload URL | https://smarttools.example.com/webhook/discord |
| Content type | application/json |
| Secret | smarttools-discord-secret |
| Events | push, pullrequest, issues, workflowrun |
| Active | ✔️ |

### 📦 Payload JSON envoyé à ton serveur
```json
{
  "project": "SmartTools",
  "channel": "DiscordOps",
  "event": "{{event}}",
  "branch": "{{ref}}",
  "actor": "{{sender.login}}",
  "details": "{{head_commit.message}}"
}
```

### 🖥️ Code serveur (envoi vers Discord Webhook)
```js
const axios = require("axios");

async function sendDiscord(message) {
  await axios.post("https://discord.com/api/webhooks/ID/TOKEN", {
    content: message
  });
}
```

---

### 📡 Webhook Telegram — SmartTools-TelegramOps

### 🎯 Objectif
Notifier instantanément ton canal Telegram lors d’un événement GitHub (push, PR, workflow_run).

🔧 Configuration GitHub
| Champ | Valeur |
|-------|--------|
| Payload URL | https://smarttools.example.com/webhook/telegram |
| Content type | application/json |
| Secret | smarttools-telegram-secret |
| Events | push, pullrequest, workflowrun |
| Active | ✔️ |

### 📦 Payload JSON
```json
{
  "project": "SmartTools",
  "channel": "TelegramOps",
  "event": "{{event}}",
  "actor": "{{sender.login}}",
  "commit": "{{head_commit.id}}",
  "message": "{{head_commit.message}}"
}
```

### 🖥️ Code serveur (envoi vers Telegram Bot API)
```js
const axios = require("axios");

async function sendTelegram(text) {
  const token = "TELEGRAMBOTTOKEN";
  const chatId = "TELEGRAMCHATID";
  await axios.post(https://api.telegram.org/bot${token}/sendMessage, {
    chat_id: chatId,
    text
  });
}
```

---

### ✉️ Webhook Email — SmartTools-MailOps

### 🎯 Objectif
Envoyer un email automatique lors d’un événement GitHub (PR ouverte, issue créée, workflow terminé).

### 🔧 Configuration GitHub
| Champ | Valeur |
|-------|--------|
| Payload URL | https://smarttools.example.com/webhook/email |
| Content type | application/json |
| Secret | smarttools-email-secret |
| Events | issues, pullrequest, workflowrun |
| Active | ✔️ |

### 📦 Payload JSON
```json
{
  "project": "SmartTools",
  "module": "MailOps",
  "event": "{{event}}",
  "title": "{{issue.title}}",
  "actor": "{{sender.login}}",
  "timestamp": "{{date}}"
}
```

### 🖥️ Code serveur (envoi email via SMTP)
```js
const nodemailer = require("nodemailer");

async function sendEmail(subject, text) {
  const transporter = nodemailer.createTransport({
    host: "smtp.example.com",
    port: 587,
    secure: false,
    auth: {
      user: "smarttools@example.com",
      pass: "PASSWORD"
    }
  });

  await transporter.sendMail({
    from: "SmartTools <smarttools@example.com>",
    to: "admin@example.com",
    subject,
    text
  });
}
```

---

### 🧱 Serveur universel SmartTools (Discord + Telegram + Email)
```js
const express = require("express");
const app = express();
app.use(express.json());

app.post("/webhook/:module", async (req, res) => {
  const module = req.params.module;
  const data = req.body;

  if (module === "discord") {
    await sendDiscord([SmartTools] ${data.event} par ${data.actor});
  }

  if (module === "telegram") {
    await sendTelegram(SmartTools • ${data.event}\n${data.message});
  }

  if (module === "email") {
    await sendEmail(SmartTools – ${data.event}, JSON.stringify(data, null, 2));
  }

  res.status(200).send("OK");
});

app.listen(3000, () => {
  console.log("Serveur SmartTools Webhooks actif sur port 3000");
});
```
