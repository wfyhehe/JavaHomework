如果希望运行此程序
- 配置mysql-connector的jar包

- 在数据库中建好表
```mysql
create database db;

use db;

create table contacts (
    id int primary key auto_increment, 
    name varchar(255),
    number varchar(20),
    sex varchar(20),
    email varchar(255),
    address varchar(255)
);
```

- 配置好代码中的数据库config