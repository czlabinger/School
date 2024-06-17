# GK862
**Christof Zlabinger**

## MySQL in docker

````bash
docker run --name dzs-mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql
docker exec -it bash
mysql -u root -p root
````
```sql
CREATE DATABASE users;
```

## Create Project
```bash
mvn archetype:generate -DgroupId=at.czlabinger -DartifactId=WAREHOUSE_ORM -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

### Configure spring boot
```
# application.properties

# Name of Project
spring.application.name=WAREHOUSE_ORM 

# SQL dialect and host/database
spring.datasource.url=jdbc:mysql://localhost:3306/warehouse

#Account username and password to use
spring.datasource.username=root
spring.datasource.password=root

# Automatically create tables if not existing
spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=true

# Driver to connect to DB
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# MySQL dialect version to use
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```


