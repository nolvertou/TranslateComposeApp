# 🌍 Translate Compose App  

A Jetpack Compose Android application that allows users to <b>translate text between languages</b> using <b>Google ML Kit</b>. The app supports dynamic language selection, offline model downloads, and persists user preferences with <b>DataStore</b>.  

---

## 📑 Table of Contents  

<ul>
  <li><a href="#-features">🚀 Features</a></li>
  <li><a href="#-tech-stack">🛠️ Tech Stack</a></li>
  <li><a href="#-dependencies">📦 Dependencies</a></li>
  <li><a href="#-project-setup">📝 Project Setup</a></li>
  <li><a href="#-commit-history-highlights">📌 Commit History Highlights</a></li>
  <li><a href="#-screenshots-optional">📷 Screenshots</a></li>
  <li><a href="#-author">👤 Author</a></li>
</ul>

---

## 🚀 Features  

<ul>
  <li>✏️ <b>Text Input & Translation</b>
    <ul>
      <li>🔤 OutlinedTextField to enter the text to translate</li>
      <li>📖 Shows translation result if the language model is downloaded</li>
    </ul>
  </li>
  <li>🌐 <b>Language Selection</b>
    <ul>
      <li>⬇️ Generic DropdownMenu components for selecting <b>source</b> and <b>target</b> languages</li>
      <li>📋 Language options stored in a variables list</li>
      <li>🔧 App language can be changed from within the app instead of relying on system language</li>
    </ul>
  </li>
  <li>🧠 <b>State Management</b>
    <ul>
      <li>⚡ TranslateViewModel built with Jetpack Compose state handling</li>
      <li>💾 Stores input text and translation results</li>
    </ul>
  </li>
  <li>💽 <b>Persistence</b>
    <ul>
      <li>📌 Selected languages are saved with DataStore Preferences</li>
    </ul>
  </li>
  <li>🤖 <b>ML Kit Integration</b>
    <ul>
      <li>📡 Uses Google ML Kit Translation API</li>
      <li>⬇️ Handles model downloads for offline translation</li>
    </ul>
  </li>
</ul>

---

## 🛠️ Tech Stack  

<ul>
  <li>🟦 <b>Kotlin</b></li>
  <li>🎨 <b>Jetpack Compose</b> (Material3 components)</li>
  <li>🧩 <b>ViewModel</b></li>
  <li>💾 <b>DataStore Preferences</b></li>
  <li>🤖 <b>Google ML Kit Translation</b></li>
</ul>

---

## 📦 Dependencies  

```kotlin
// 🤖 ML Kit Translation
implementation("com.google.mlkit:translate:17.0.2")

// 💾 DataStore Preferences
implementation("androidx.datastore:datastore-preferences:1.1.1")

// 🎨 Jetpack Compose (Material3 example)
implementation("androidx.compose.material3:material3:1.3.0")
