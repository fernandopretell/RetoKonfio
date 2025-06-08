# Reto Konfio

This project is an Android application created for the Konfio technical challenge. It displays a list of dogs fetched from a public JSON endpoint and stores them locally for subsequent launches.

## Features

- **Jetpack Compose** UI with a simple list of dogs.
- **Retrofit** for network requests.
- **Room** database used to cache the retrieved dogs.
- **Hilt** dependency injection.

## Building and Running

1. Open the project in Android Studio (Giraffe or later).
2. Ensure an emulator or device running API 24+ is available.
3. Build and run using the `Run` action or via the command line:

```bash
./gradlew installDebug
```

The app fetches the dog list on the first launch and loads it from the local database afterward.

