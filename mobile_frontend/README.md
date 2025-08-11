# Recipe Explorer

This is a modern Android app written in Kotlin using Jetpack Compose and Room (SQLite) for offline-first recipe management.

## Features

- Browse categories, search/filter, view and edit recipes.
- Favorites and notes on recipes.
- Fully offline: Room manages local database.
- Material 3 Compose UI, clean and fast.

## Building and Running

Make sure your Android Studio supports Jetpack Compose.

To build:

```shell
  ./gradlew build
```

To install:

```shell
  ./gradlew :app:installDebug
```

Then find "Recipe Explorer" on your Android device or emulator.

No environment configuration is needed. All data is stored locally using Room/SQLite.