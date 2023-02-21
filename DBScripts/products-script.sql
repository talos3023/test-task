create database products;
go
CREATE TABLE category(
    "id" bigint NOT NULL PRIMARY KEY,
    "name" varchar(255)
);

CREATE MEMORY TABLE "category_products"(
    "category_id" bigint NOT NULL,
    "product_id" bigint NOT NULL
);
ALTER TABLE "category_products" ADD CONSTRAINT "CONSTRAINT_D" PRIMARY KEY("CATEGORY_ID", "PRODUCT_ID");

CREATE TABLE "product"(
    "id" bigint NOT NULL PRIMARY KEY,
    "name" varchar(255),
    "price" float(53) NOT NULL,
    "quantity" integer NOT NULL
);

ALTER TABLE "category_products" ADD CONSTRAINT "category_foreign_key" FOREIGN KEY("category_id") REFERENCES "PUBLIC"."CATEGORY"("id") NOCHECK;
ALTER TABLE "category_products" ADD CONSTRAINT "product_foreign_key" FOREIGN KEY("product_id") REFERENCES "PUBLIC"."PRODUCT"("id") NOCHECK;

insert into category (id, name) values (1, 'Ornamental Railings');
insert into category (id, name) values (2, 'Epoxy Flooring');
insert into category (id, name) values (3, 'Plumbing & Medical Gas');
insert into category (id, name) values (4, 'RF Shielding');
insert into category (id, name) values (5, 'Drywall & Acoustical (MOB)');
insert into category (id, name) values (6, 'Elevator');
insert into category (id, name) values (7, 'Temp Fencing, Decorative Fencing and Gates');
insert into category (id, name) values (8, 'Fire Sprinkler System');
insert into category (id, name) values (9, 'Curb & Gutter');
insert into category (id, name) values (10, 'Sitework & Site Utilities');


insert into product (id, name, quantity, price) values (1, 'Beans - Navy, Dry', 87, 71.9);
insert into product (id, name, quantity, price) values (2, 'The Pop Shoppe Pinapple', 89, 90.6);
insert into product (id, name, quantity, price) values (3, 'Wine - Cousino Macul Antiguas', 64, 3.6);
insert into product (id, name, quantity, price) values (4, 'Sansho Powder', 22, 79.1);
insert into product (id, name, quantity, price) values (5, 'Wine - Lamancha Do Crianza', 13, 29.6);
insert into product (id, name, quantity, price) values (6, 'Juice - Orangina', 76, 55.4);
insert into product (id, name, quantity, price) values (7, 'Wakami Seaweed', 78, 1.2);
insert into product (id, name, quantity, price) values (8, 'Wine - Prosecco Valdobiaddene', 66, 93.6);
insert into product (id, name, quantity, price) values (9, 'Shrimp - 16/20, Peeled Deviened', 93, 80.6);
insert into product (id, name, quantity, price) values (10, 'Soup - Campbells, Butternut', 54, 90.5);

insert into category_products (category_id, product_id) values (5, 10);
insert into category_products (category_id, product_id) values (9, 8);
insert into category_products (category_id, product_id) values (9, 9);
insert into category_products (category_id, product_id) values (1, 5);
insert into category_products (category_id, product_id) values (1, 9);
insert into category_products (category_id, product_id) values (3, 4);
insert into category_products (category_id, product_id) values (10, 3);
insert into category_products (category_id, product_id) values (5, 4);
insert into category_products (category_id, product_id) values (3, 10);
insert into category_products (category_id, product_id) values (9, 9);