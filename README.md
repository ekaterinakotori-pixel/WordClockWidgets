# WordClockWidgets
Часы-виджеты с отображением времени словами на русском языке

## Быстрый старт

1. Установите JDK 17
2. Установите Android SDK и Android SDK Command-line Tools
3. Создайте `local.properties`:

```properties
sdk.dir=/usr/lib/android-sdk
```

4. Сборка:

```bash
./gradlew clean assembleDebug --no-daemon
```

APK будет в `app/build/outputs/apk/debug/app-debug.apk`.

## CI (GitHub Actions)

В проекте добавлен workflow: `.github/workflows/android-ci.yml`
- запускается на `push` и `pull_request` в `main`
- собирает `assembleDebug`
- загружает артефакт `app-debug-apk`.

