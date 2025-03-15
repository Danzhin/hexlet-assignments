-- BEGIN
drop table if exists products;

create table products (
    id      bigint primary key auto_increment,
    title   varchar(255) not null,
    price   int not null
);
-- END
