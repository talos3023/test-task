CREATE TABLE "order_product_amounts"(
    "order_id" BIGINT NOT NULL,
    "product_amounts" INTEGER,
    "product_amounts_key" BIGINT NOT NULL
);
ALTER TABLE "PUBLIC"."order_product_amounts" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_D" PRIMARY KEY("ORDER_ID", "PRODUCT_AMOUNTS_KEY");


CREATE TABLE "orders"(
    "id" bigint NOT NULL PRIMARY KEY,
    "created_at" DATE,
    "status" varchar(255),
    "user_id" BIGINT
);

ALTER TABLE "order_product_amounts" ADD CONSTRAINT "orders_foreign_id" FOREIGN KEY("order_id") REFERENCES "orders"("id") NOCHECK;


insert into orders (id, created_at, status, user_id) values (1, '2022-09-09', 'DELIVERED', 1);
insert into orders (id, created_at, status, user_id) values (2, '2022-08-19', 'PENDING', 8);
insert into orders (id, created_at, status, user_id) values (3, '2022-11-29', 'DELIVERED', 7);
insert into orders (id, created_at, status, user_id) values (4, '2022-12-09', 'DELIVERED', 8);
insert into orders (id, created_at, status, user_id) values (5, '2022-03-08', 'PENDING', 5);
insert into orders (id, created_at, status, user_id) values (6, '2022-05-15', 'CANCELED', 8);
insert into orders (id, created_at, status, user_id) values (7, '2022-11-07', 'CANCELED', 4);
insert into orders (id, created_at, status, user_id) values (8, '2022-12-19', 'PENDING', 9);
insert into orders (id, created_at, status, user_id) values (9, '2022-05-18', 'DELIVERED', 9);
insert into orders (id, created_at, status, user_id) values (10, '2022-11-27', 'CANCELED', 8);



insert into order_product_amounts (id, product_amounts_key, product_amounts) values (1, 9, 50);
insert into order_product_amounts (id, product_amounts_key, product_amounts) values (2, 5, 10);
insert into order_product_amounts (id, product_amounts_key, product_amounts) values (3, 5, 22);
insert into order_product_amounts (id, product_amounts_key, product_amounts) values (4, 1, 16);
insert into order_product_amounts (id, product_amounts_key, product_amounts) values (5, 10, 4);
insert into order_product_amounts (id, product_amounts_key, product_amounts) values (6, 8, 32);
insert into order_product_amounts (id, product_amounts_key, product_amounts) values (7, 10, 7);
insert into order_product_amounts (id, product_amounts_key, product_amounts) values (8, 3, 13);
insert into order_product_amounts (id, product_amounts_key, product_amounts) values (9, 8, 6);
insert into order_product_amounts (id, product_amounts_key, product_amounts) values (10, 10, 50);