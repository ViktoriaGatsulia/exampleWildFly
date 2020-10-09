![hibernate](https://www.edureka.co/blog/wp-content/uploads/2019/07/Hibernate-Java-frameworks-Edureka-1.png)
![wildfly](https://design.jboss.org/wildfly/logo/final/wildfly_logomark.svg)
![maven](https://habrastorage.org/webt/5a/f3/22/5af322981410c538019113.png)
# Web-приложение с таблицей, содержащей пользователей

**exampleWildFly** - это лёгкое web-приложение для работы с таблицей студентов, имеющее следующий **функционал**:

**Добавление нового студента**           

  * Ввести данные студента в соответствующие поля и нажать кнопку ````Сохранить студента````              
  * С помощью curl запроса:        
  
````curl -X PUT -H 'Content-Type:application/json' -d '{"dateOfBirth" : "2001-08-14", "email" : "viki@gmail.com", "firstName" : "Viktoria", "lastName" : "Gatsulia"}' 'localhost:8080/brandMaker/app/main/add'````        
        
**Удаление студента**

Перейти на страницу списка студентов группы:                    
  * Нажав на кнопку ````Удалить```` в соответствующей строке                
  * С помощью curl запроса (в поле id указывается идентификатор студента, который можно посмотреть в разделе "Console" в вашем браузере):     
  
````curl -X DELETE 'localhost:8080/brandMaker/app/main/delete_id=4'````               
    

**Страница со списком студентов находится по адресу ````http://localhost:8080/brandMaker/````**
    
## Используемые версии

* Ubuntu 18.04.4 LTS
* openjdk версии "1.8.0_162"
* Среда выполнения OpenJDK (сборка 1.8.0_162-8u162-b12-1-b12)
* 64-разрядная серверная виртуальная машина OpenJDK (сборка 25.162-b12, смешанный режим)
* mySQL 5.7
* Apache Maven 3.5.2
* WildFly 10.1 Final
* curl 7.58.0 

## Процедура сборки и запуска

После установки всех необходимых приложений, необходимо:

### 1. Создать базу данных
1. Зайти в консоль mySQL из под root

    $ sudo mysql -u root
2. Создать пользователя БД

    $ CREATE USER 'user_name'@'localhost' IDENTIFIED BY 'password';
3. Чтобы назначить созданном пользователю неограниченные права доступа к базе данных, выполните следующую команду

    $ GRANT ALL PRIVILEGES ON * . * TO 'user_name'@'localhost';
4. Проверьте результат ещё раз войдя в mysql-консоль

    $ mysql -u user_name -p
5. Создайте новую БД

    $ CREATE DATABASES 'university'
    
**(не следует созадавать новую БД под root, т.к. в последних версиях mySQL для root пользователя используется не пароль а auth_socket)**  

### 2. Сконфигурировать сервер Wildfly

* В папке, куда был скачен Wildfly перейти в каталог ````bin````
* Запустить скрипт ````add-user.sh```` для добавления нового пользоватя - администратора
* Запустить скрипт ````standalone.sh```` в той же папке
* Перейти на страницу администрирования ````127.0.0.1:9990````
    1. Во вкладке ````Deployments```` добавить новую зависимость - драйвер для базы данных mySQL
        1. Скачиваем архив с драйвером, рекомендуется версия mysql-connector-java-5.1.38
        2. Распаковываем
        3. Во вкладке ````Deployments```` добавить jar файл драйвера (обратите внимание на суффикс "-bin", например mysql-connector-java-5.1.38-bin.jar)
    2. Сконфигурировать подключение к БД
        1. Перейти в ````Configuration -> Subsystems -> Datasources -> Non-XA -> Add
        2. Настроить подключение к вашей БД (в поле JNDI Name рекомендуется указать имя "java:/MySqlDS", если указываете другой адрес, то измените соответствующую запись в файле src/main/resources/META-INF/persistence.xml), в качестве JDBC драйвера указываем добавленный драйвер
    3. Перезагружаем сервер

### 3. Сборка проекта

Проект собирается с помощью команды mvn, собранный war-файл будет находиться в папке target

    ````$ mvn clean install````

### 3. Деплой проекта

Можно добавить проект на сервер вручную так же, как и драйвер для mySQL, или с помощью команды:

````$ mvn wildfly:deploy````
    
Перезагрузить проект на сервер:

````$ mvn wildfly:redeploy````

Удалить прект с сервера:

````$ mvn wildfly:undeploy````

Для работы команд, которые указаны выше, нужно в файле ````pom.xml```` отредактировать следующий раздел, указав логин и пароль вашего пользователя

````
<plugin>
<groupId>org.wildfly.plugins</groupId>
<artifactId>wildfly-maven-plugin</artifactId>
<version>${version.wildfly.maven.plugin}</version>
<configuration>
<hostname>localhost</hostname>
<port>9990</port>
<username>login</username>
<password>password</password>
</configuration>
</plugin>
````
    
После деплоя проекта на сервер, доступ к нему будет по адресу

````http://localhost:8080/brandMaker/````
