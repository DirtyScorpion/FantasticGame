# 🛠 Git: Решение конфликтов и ошибок
*Справочник для совместной работы в Obsidian через Git*

## 🔍 **Основные команды**
```bash
git status          # Показать текущее состояние
git diff           # Показать изменения
git log --oneline  # Показать историю коммитов
```

## 1️⃣ **Обычный конфликт при слиянии**
**Симптомы**:  
`CONFLICT (content): Merge conflict in file.md`  
В файле появились `<<<<<<<`, `=======`, `>>>>>>>`

**Решение**:
   - Откройте файл и вручную:
   - Удалите маркеры конфликта
   - Оставьте нужную версию (или объедините изменения)
## 4️⃣ "Your local changes would be overwritten"
**Симптомы**:  
`error: Your local changes would be overwritten by merge`

**Решение**:
1. Сохраните изменения временно:
```bash
git stash
```
2. Получите изменения:
```bash
git pull
```
3. Верните свои изменения:
```bash
git stash pop
```
(Может вызвать новый конфликт - решайте как в п.1)

## 5️⃣ "Merging is not possible"
**Симптомы**:  
`error: Merging is not possible because you have unmerged files`

**Решение**:
1. Проверьте конфликтные файлы:
```bash
git status
```
2. Исправьте их и добавьте в индекс:
```bash
git add conflicted_file.md
```
3. Завершите слияние:
```bash
git commit
```
## 🆘 **Экстренные случаи**
### Сброс Локальных изменений:
```bash
git fetch origin
git reset --hard origin/main
```
(⚠️ Удаляет все локальные изменения!)

### Просмотр изменений между ветками:
```bash
git diff main..feature-branch
```

---

*Создано для проекта {{Название проекта}}*  
*Последнее обновление: {{Дата}}*