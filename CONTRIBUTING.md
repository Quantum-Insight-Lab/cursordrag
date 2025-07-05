# 🤝 Руководство для контрибьюторов

## Как внести вклад в CursorDrag

### 1. Форк репозитория
- Нажмите "Fork" на GitHub
- Склонируйте ваш форк: `git clone https://github.com/YOUR_USERNAME/cursordrag.git`
- Или клонируйте основной репозиторий: `git clone https://github.com/Quantum-Insight-Lab/cursordrag.git`

### 2. Создание ветки
```bash
git checkout -b feature/amazing-feature
```

### 3. Разработка
- Следуйте архитектуре MVVM + Hilt
- Используйте StateFlow для реактивности
- Пишите тесты для новой функциональности
- Соблюдайте стиль кода (ktlint)

### 4. Коммиты
```bash
git add .
git commit -m "feat: add amazing feature"
```

### 5. Пуш и Pull Request
```bash
git push origin feature/amazing-feature
# Создайте PR на GitHub
```

## Стандарты кода

### Архитектура
- **MVVM** - ViewModels для бизнес-логики
- **Hilt DI** - dependency injection
- **StateFlow** - реактивное состояние
- **Compose** - современный UI

### Тестирование
- Unit тесты для ViewModels
- Integration тесты для жестов
- Покрытие ≥90%

### Коммиты
- `feat:` - новая функциональность
- `fix:` - исправление багов
- `refactor:` - рефакторинг
- `test:` - тесты
- `docs:` - документация

## Структура проекта
```
app/src/main/java/dev/patrickgold/florisboard/ime/
├── core/           # Бизнес-логика
├── di/            # Dependency injection
├── preferences/   # Настройки
├── metrics/       # Аналитика
├── text/          # Текстовый ввод
└── ui/            # UI компоненты
```

## Запуск проекта
```bash
./gradlew assembleDebug
./gradlew test
./gradlew lint
```

## Вопросы?
- Создайте Issue на GitHub
- Обсудите в Discussions
- Присоединяйтесь к сообществу! 