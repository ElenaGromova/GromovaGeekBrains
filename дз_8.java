Проанализирован итоговый проект по интернет-магазину на предмет наличия антипаттернов:

1) Magic Number // Магические числа
единственное место, где числовая константа прописана в коде - формирование страницы товаров в части определения их количества на странице
PageDto p = productService.findAllDtos(productFilter.getSpec(), page - 1, 5);

2) Спагетти-код - не обнаружен

3) Lasagna Code // Лазанья-код - не обнаружен, количество слоев в разумном пределе

4) Blind faith // Слепая вера 
- отсутствует проверка на ввод номера телефона при подтверждении заказа
- аналогично отсутствует проверка на ввод номера телефона на странице профиля

5) Cryptic Code // Шифрокод
- обнаружено неократно в методах, например, Product p, OrderItem o и тп

6) Hard Code // Жесткое кодирование
- единственное жестко прописанное - это путь в файле index "const contextPath = 'http://localhost:8189/store';", но мне кажется, что это не хард код

7) Soft Code // Мягкое кодирование - не обнаружен, конфигурирование гибкое и в пределах разумного

8) Lava flow // Поток лавы
- было на протяжении нарастания логики проекта, конкретный пример привести затруднительно, поскольку код переписывался, когда его прежняя версия приводила в тупик

9) Anemic Domain Model // Боязнь размещать логику в объектах предметной области
- три объекта Category, City, Gender вынесены отдельно, но практически не несутс самостоятельной логики - созданы исключительно для получения списка значений из БД
- как исправить, если честно, не знаю - как по мне так удобно и логично , что они не засоряют др классы и выынесены отдельно 

10) God object // Божественный объект - не обнаружен

11) Poltergeist // Полтергейст - не обнаружен 

12) Singletonitis // Сплошное одиночество - не обнаружен

13) Privatization // Приватизация
- было в функционально важных методах, но далее перешли на final, поэтому считаю проблему устраненной

14) Interface Soup // Интерфейсная солянка - не обнаружен

15) Stub // Заглушка - не обнаружен

16) Copy–Paste // Программирование методом копирования–вставки 
- постоянно использовался на протяжении всего проекта (создание сущностей/контроллеров... , методов)

17) Golden hammer // Золотой молоток - тут сложно дать самостоятельную оценку себя, думаю, со стороны виднее 

18) Improbability factor  // Фактор невероятности
- очень много, где в проекте отсутствут проверки на эксепшн

19) Premature optimization // Преждевременная оптимизация - не обнаружен

20) Reinventing the wheel // Изобретение велосипеда - не обнаружен

21) Reinventing the square wheel // Изобретение квадратного колеса - не обнаружен

22) Abstract Inversion // Инверсия абстракции  - не обнаружен

23) Big ball of mud // Большой комок грязи
- тут скорее отмечу, что был грязный код на протяжении работы над проектом: код был плохо структурирован, перенасыщен неиспользуемыми классами/методами и импортами. В итоговой версии устранено

24) Input kludge // Затычка на ввод  - не обнаружен

25) Magic button // Волшебная кнопка - не обнаружен

26) Mutilation // Членовредительство
- весь проект написан с ориентацией только на свои потребности

Решение для всех найденных антипаттернов: анализ и рефакторинг кода