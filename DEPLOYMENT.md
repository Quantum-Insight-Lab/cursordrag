# 🚀 Деплой CursorDrag на GitHub

## Шаги для публикации:

### 1. Создание репозитория на GitHub
```bash
# Перейти на https://github.com/new
# Создать репозиторий: cursordrag
# НЕ инициализировать с README (у нас уже есть)
```

### 2. Подключение к GitHub
```bash
git remote add origin https://github.com/YOUR_USERNAME/cursordrag.git
git push -u origin main
```

### 3. Настройка GitHub Actions
- CI/CD уже настроен в `.github/workflows/ci.yml`
- Автоматическая сборка при push в main
- Тесты и линтинг

### 4. Создание Release
```bash
git tag -a v1.0.0 -m "First release"
git push origin v1.0.0
```

### 5. Настройка GitHub Pages (опционально)
- Settings → Pages
- Source: GitHub Actions
- Документация будет доступна на `https://YOUR_USERNAME.github.io/cursordrag`

## Полезные команды:

```bash
# Проверка статуса
git status

# Добавление изменений
git add .
git commit -m "feat: new feature"

# Пуш в репозиторий
git push origin main

# Создание новой ветки
git checkout -b feature/new-feature
```

## Структура репозитория:
```
cursordrag/
├── app/                    # Android приложение
├── .github/workflows/      # CI/CD
├── README.md              # Документация
├── LICENSE                # Apache-2.0
└── DEPLOYMENT.md          # Эта инструкция
``` 