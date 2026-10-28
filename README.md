# Домашнее задание к занятию 11 «Teamcity» -  Старцев Данила Антонович

## Подготовка к выполнению

1. В Yandex Cloud создайте новый инстанс (4CPU4RAM) на основе образа `jetbrains/teamcity-server`.
2. Дождитесь запуска teamcity, выполните первоначальную настройку.
3. Создайте ещё один инстанс (2CPU4RAM) на основе образа `jetbrains/teamcity-agent`. Пропишите к нему переменную окружения `SERVER_URL: "http://<teamcity_url>:8111"`.
4. Авторизуйте агент.
5. Сделайте fork [репозитория](https://github.com/aragastmatb/example-teamcity).
6. Создайте VM (2CPU4RAM) и запустите [playbook](./infrastructure).

## Основная часть

1. Создайте новый проект в teamcity на основе fork.
2. Сделайте autodetect конфигурации.
3. Сохраните необходимые шаги, запустите первую сборку master.
4. Поменяйте условия сборки: если сборка по ветке `master`, то должен происходит `mvn clean deploy`, иначе `mvn clean test`.
5. Для deploy будет необходимо загрузить [settings.xml](./teamcity/settings.xml) в набор конфигураций maven у teamcity, предварительно записав туда креды для подключения к nexus.
6. В pom.xml необходимо поменять ссылки на репозиторий и nexus.
7. Запустите сборку по master, убедитесь, что всё прошло успешно и артефакт появился в nexus.
8. Мигрируйте `build configuration` в репозиторий.
9. Создайте отдельную ветку `feature/add_reply` в репозитории.ls

10. Напишите новый метод для класса Welcomer: метод должен возвращать произвольную реплику, содержащую слово `hunter`.
11. Дополните тест для нового метода на поиск слова `hunter` в новой реплике.
12. Сделайте push всех изменений в новую ветку репозитория.
13. Убедитесь, что сборка самостоятельно запустилась, тесты прошли успешно.
14. Внесите изменения из произвольной ветки `feature/add_reply` в `master` через `Merge`.
15. Убедитесь, что нет собранного артефакта в сборке по ветке `master`.
16. Настройте конфигурацию так, чтобы она собирала `.jar` в артефакты сборки.
17. Проведите повторную сборку мастера, убедитесь, что сбора прошла успешно и артефакты собраны.
18. Проверьте, что конфигурация в репозитории содержит все настройки конфигурации из teamcity.
19. В ответе пришлите ссылку на репозиторий.

---

### Основная часть
1. Создан проект `teamcity_dz` в TeamCity. Настроен VCS Root через SSH-ключ.
2. Настроены шаги сборки Maven с условиями (Parameter-based execution conditions):
   - Ветка `master`: выполняется `mvn clean deploy`.
   - Остальные ветки: выполняется `mvn clean test`.
3. В TeamCity загружен файл `settings.xml` с кредами к Nexus.
4. В файле `pom.xml` настроен `<distributionManagement>` для деплоя в локальный Nexus.
5. **Сборка по ветке `master` (Build #3) прошла успешно.** Артефакт отправлен в Nexus.
6. Включена синхронизация **Versioned Settings (Kotlin DSL)**. Папка `.teamcity` закоммичена в репозиторий.
7. Создана ветка `feature/add_reply`. В класс `Welcomer` добавлен метод `tellHunter()`, возвращающий реплику со словом "hunter". Написан тест `testTellHunter()`.
8. Изменения из `feature/add_reply` успешно слиты в `master` через Pull Request #2.
9. В настройках сборки (Artifact paths) прописан путь `target/*.jar`. Собранные артефакты успешно сохраняются в TeamCity.

### 1. Успешная сборка в TeamCity (Build #3)
![скриншот 1](https://github.com/MindMaze74/teamcity_dz/blob/master/img/4.png)


### 2. Артефакты сборки (.jar) в TeamCity
![скриншот 2](https://github.com/MindMaze74/teamcity_dz/blob/master/img/5.png)


### 3. Артефакт, успешно задеплоенный в Nexus
![скриншот 3](https://github.com/MindMaze74/teamcity_dz/blob/master/img/9.png)


### 4. Слияние ветки feature/add_reply (Pull Request)
![скриншот 4](https://github.com/MindMaze74/teamcity_dz/blob/master/img/10.png)


---
**Домашнее задание выполнено полностью.**
