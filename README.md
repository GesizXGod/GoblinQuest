# GoblinQuest

Консольное RPG-приложение для трекинга привычек. Выполнение привычки = победа над гоблином, дающая опыт персонажу.

## Технологии
- Java (OOP: наследование, интерфейсы, инкапсуляция)
- Collections (ArrayList, HashMap)
- Exception handling

## Структура
- `Player` — персонаж с опытом и здоровьем
- `Habit` (+ `EasyHabit`, `HardHabit`) — привычки-гоблины
- `Game` — управляет списком привычек и персонажем
- `Rewardable` — интерфейс для сущностей, дающих награду

## Как запустить
Открыть в IntelliJ IDEA, запустить `Main.java`
Data is store in SQLite(goblinquest.db).
