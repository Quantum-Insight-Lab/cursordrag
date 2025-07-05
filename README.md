# CursorDrag ÆON

Расширение для FlorisBoard с поддержкой жестов курсора.

## Возможности

- **Перетаскивание пробела**: Свайп по пробелу для перемещения курсора
- **Smartbar drag**: Перетаскивание по Smartbar для навигации
- **Backspace жесты**: Длинное перетаскивание для удаления слов
- **Настраиваемая чувствительность**: Точная настройка отзывчивости

## Метрики производительности

- Swipe-Space медленный (30px) → 30 символов (±2)
- Swipe-Space быстрый (600px) → 600 символов без лагов
- Smartbar-drag (100px) → 100 символов
- Покрытие тестами ≥90% логики курсора

## Установка

```bash
git clone https://github.com/Quantum-Insight-Lab/cursordrag.git
cd cursordrag
./gradlew assembleDebug
```

## Разработка

```bash
# Клонирование
git clone https://github.com/Quantum-Insight-Lab/cursordrag.git
cd cursordrag

# Сборка
./gradlew assembleDebug

# Тесты
./gradlew test

# Линтинг
./gradlew lint
```

## Архитектура

- **MVVM + Hilt DI** - современная архитектура
- **StateFlow** - реактивное управление состоянием
- **Compose** - декларативный UI
- **Strategy Pattern** - гибкая обработка жестов

## Лицензия

Apache-2.0 