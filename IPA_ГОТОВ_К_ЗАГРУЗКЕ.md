# ✅ iOS IPA готов к загрузке

## Загрузка файла

1. Перейдите в GitHub Actions: https://github.com/ideoma-last/WordClockWidgets/actions/runs/23986815869
2. Прокрутите вниз до раздела **"Artifacts"**
3. Скачайте **"WordClockWidgets-IPA"** (ZIP архив, ~45KB)

## Содержимое архива

```
WordClockWidgets-IPA.zip
└── WordClockWidgets.ipa      (готовый iOS app, 185KB)
└── README_INSTALL.md         (инструкции по установке)
```

## ⚠️ ВАЖНО: Требуется Apple Developer Account

**IPA файл НЕ подписан разработческим сертификатом!** Для установки на реальный iPhone вам потребуется:

1. **Членство в Apple Developer Program** ($99/год)
2. **Разработческий сертификат** от Apple
3. **Provisioning profile** для вашего устройства

Без этого приложение можно запустить только в iOS Simulator, но не на реальном устройстве.

## Установка на iPhone XS (с Developer Account)

### Через iMazing (Windows)

1. **Загрузить iMazing**: https://imazing.com/
2. **Установить** на ваш Windows PC
3. **Подключить iPhone** через USB (доверить компьютеру)
4. **В iMazing**:
   - Нажать **"Install"** или перетащить **WordClockWidgets.ipa**
   - Выбрать загруженный IPA файл
   - **iMazing покажет ошибку подписи** - вам нужно переподписать IPA вашим сертификатом

### Через Xcode (Mac)

1. **Загрузить Xcode** из Mac App Store
2. **Подключить iPhone** через USB
3. **Открыть Xcode**
4. Меню: **Window > Devices and Simulators**
5. **Выбрать ваш iPhone**
6. **Перетащить IPA** файл на устройство
7. **Xcode покажет ошибку подписи** - добавьте ваш Apple ID и создайте provisioning profile

## Как получить Developer Certificate

1. **Присоединиться** к [Apple Developer Program](https://developer.apple.com/programs/) ($99/год)
2. **Создать** разработческий сертификат в Xcode или онлайн
3. **Добавить** ваш iPhone в устройства разработчика
4. **Создать** provisioning profile
5. **Переподписать** IPA вашим сертификатом

## Альтернатива: Тестирование в Simulator

Если у вас нет developer account, вы можете протестировать логику приложения в iOS Simulator:

1. **Установить Xcode** на Mac
2. **Открыть** этот проект в Xcode
3. **Выбрать** iPhone simulator
4. **Запустить** приложение

## Системные требования

- **iPhone**: XS, XS Max, XR и новее ✓
- **iOS**: 14.0+ ✓
- **Apple Developer Program**: Обязательно для установки на устройство
- **Разработческий сертификат**: Требуется
- **Provisioning profile**: Требуется

## Что я сделал

✅ Создал минимальный валидный Xcode project.pbxproj
✅ Зафиксировал конфликт параметров сборки
✅ Исправил Info.plist с плейсхолдерами
✅ Скомпилировал приложение на GitHub Actions (macOS)
✅ Упаковал в IPA файл
✅ Добавил подробную документацию о code signing

## Размер приложения

- **IPA файл**: 45 KB (сжатый)
- **Бинарный файл**: 181 KB (несжатый)
- **Общий размер**: 185 KB

Размер небольшой, потому что это простое приложение без больших ресурсов.

Файл полностью готов к загрузке, но для установки на реальное устройство требуется Apple Developer Program!
