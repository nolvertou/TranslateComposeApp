Translate Compose App

A Jetpack Compose Android application that allows users to translate text between languages using Google ML Kit. The app supports dynamic language selection, offline model downloads, and persists user preferences with DataStore.

🚀 Features

Text Input & Translation

OutlinedTextField to enter the text to translate.

Shows translation result if the language model is downloaded.

Language Selection

Generic DropdownMenu components for selecting source and target languages.

Language options stored in a variables list.

App language can be changed from within the app instead of relying on system language.

State Management

TranslateViewModel built with Jetpack Compose state handling.

Stores input text and translation results.

Persistence

Selected languages are saved with DataStore Preferences.

ML Kit Integration

Uses Google ML Kit Translation API.

Handles model downloads for offline translation.

🛠️ Tech Stack

Kotlin

Jetpack Compose (Material3 components)

ViewModel

DataStore Preferences

Google ML Kit Translation

📦 Dependencies
// ML Kit Translation
implementation("com.google.mlkit:translate:17.0.2")

// DataStore Preferences
implementation("androidx.datastore:datastore-preferences:1.1.1")

// Jetpack Compose (Material3 example)
implementation("androidx.compose.material3:material3:1.3.0")

📝 Project Setup

Clone the repository:

git clone https://github.com/yourusername/translate-compose-app.git
cd translate-compose-app


Open the project in Android Studio.

Sync Gradle dependencies.

Run the app on an emulator or device.

📌 Commit History Highlights

Add OutlinedTextField for text input & show translations.

Create generic DropdownMenus for source & target languages.

TranslateViewModel to handle translation logic with ML Kit.

DataStore integration to persist selected languages.

App language switching independent of device settings.

Initial project setup with dependency management.
