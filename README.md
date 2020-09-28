## Спроектировать и разработать систему регистрации и обработки пользовательских заявок. Пользователь посредством системы может подавать заявки оператору на рассмотрение. Оператор может просматривать пользовательские заявки и принимать или отклонять их. Администратор управляет правами доступа.

Спроектировать и разработать back-приложение.
Спроектировать и разработать Базу данных
 
**Функции приложения**

Создать заявку (Заявка помимо прочих системных полей состоит из статуса и текстового обращения пользователя).
Отправить заявку оператору на рассмотрение.
Просмотреть список отправленных на рассмотрение заявок, отсортированных по дате создания.
Посмотреть заявку.
Принять заявку.
Отклонить заявку.
Просмотреть список пользователей.
Назначить права оператора.
 
**В системе предусмотрены 3 роли:**
*Пользователь.*
*Оператор.*
*Администратор.*
У пользователя системы может быть одновременно несколько ролей, например, «Оператор» и «Администратор».

**У заявки пользователя предусмотрено 4 состояния:**
*черновик*,
*отправлено*,
*принято*,
*отклонено*,
 
**Пользователь может**
*создавать заявки*,
*просматривать созданные им заявки*,
*редактировать созданные им заявки в статусе «черновик»*,
*отправлять заявки на рассмотрение оператору.*

**Пользователь НЕ может:**
*редактировать отправленные на рассмотрение заявки*,
*видеть заявки других пользователей*,
*принимать заявки*,
*отклонять заявки*,
*назначать права*,
*смотреть список пользователей*,
 
**Оператор может:**
*Просматривать отправленные на рассмотрение заявки,*
*Принимать заявки,*
*Отклонять заявки*

**Оператор НЕ может:**
*создавать заявки*,
*просматривать заявки в статусе отличном от «отправлено»*,
*редактировать заявки*,
*назначать права*,
 
**Администратор может:**
*смотреть список пользователей,*
*назначать пользователям права оператора,*

**Администратор НЕ может:**
*создавать заявки,*
*просматривать заявки,*
*редактировать заявки,*
*принимать заявки,*
*отклонять заявки,*
 
**Технические требования к приложению**

Java 1.8
Использовать архитектуру REST.
Использовать Spring Boot.
Использовать Spring Security.
Использовать Hibernate.
Использовать реляционную БД (MS SQL, MS SQL Lite, PostgreSQL, MariaBD).
Создание пользователей и ролей не предусмотрено в этой системе. Подразумевается, что данные об учетных записях пользователей и роли уже есть в БД.
В случае просмотра заявки оператором текст заявки выводить со знаком <-> после каждого символа. Пример: Пользователь отправил на рассмотрение заявку с текстом «Мне нужна помощь», а оператор на экране видит текст в формате «М-н-е- -н-у-ж-н-а- -п-о-м-о-щ-ь».

#### Имя и пароль пользователей в базе
##### User1, user1
##### User2, user2
##### Admin, admin
##### Operator1, operator1
##### Operator2, operator2

###### http://localhost:8080/orders
Список заявок пользоваткля с ролью 'USER'

###### http://localhost:8080/orders/create
Создан заявки пользоватклем с ролью 'USER'

###### http://localhost:8080/orders/100006/update
Редактирование заявки, имеющую id=100006, пользоватклем User1 (можно редактировать заявки, имеющие статус DRAFT(черновик))

###### при нажатии пользоватклем с ролью 'USER' кнопки Sent заявка меняет статус на SENT(отправлено)

###### http://localhost:8080/operator
Список заявок доступных пользователю с ролью OPERATOR (со статусом отличным от DRAFT)

###### http://localhost:8080/operator/change-status/100012
Изменение статуса заявки, имеющую id=100012, пользователем с ролью OPERATOR. Текст заявки имеет вид:
d-r-a-f-t- -o-r-d-e-r- -b-y- -U-s-e-r-1

###### http://localhost:8080/admin
Список пользователей

###### при нажатии кнопки 'Add operator role' соответствующему пользователю добавляется роль OPERATOR.

