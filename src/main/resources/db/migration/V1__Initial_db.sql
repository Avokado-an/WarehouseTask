create table customer
(
    id            bigserial primary key,
    name          varchar(1024),
    x_coordinate  float,
    y_coordinate  float
);

create table warehouse
(
    id           bigserial primary key,
    x_coordinate float,
    y_coordinate float
);

create table customer_order
(
    id            bigserial primary key,
    customer_id   bigserial,
    creation_date timestamp,
    distance      float,
    warehouse_id  bigserial,
    product_id    bigserial
);

create table product
(
    id           bigserial primary key,
    product_name varchar(1024)
);

create table warehouse_product
(
    product_id   bigserial,
    warehouse_id bigserial
)