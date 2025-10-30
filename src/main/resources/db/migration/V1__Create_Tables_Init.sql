CREATE TABLE states
  (
     id   BIGSERIAL NOT NULL PRIMARY KEY,
     name VARCHAR(255) NOT NULL
  );

CREATE TABLE cities
  (
     id       BIGSERIAL NOT NULL PRIMARY KEY,
     name     VARCHAR(255) NOT NULL,
     state_id INT8,
     FOREIGN KEY (state_id) REFERENCES states(id)
  );

CREATE TABLE kitchens
  (
     id   BIGSERIAL NOT NULL PRIMARY KEY,
     name VARCHAR(255) NOT NULL
  );

CREATE TABLE restaurants
  (
     id                   BIGSERIAL NOT NULL PRIMARY KEY,
     name                 VARCHAR(255) NOT NULL,
     address_cep          VARCHAR(255),
     address_complement   VARCHAR(255),
     address_neighborhood VARCHAR(255),
     address_number       VARCHAR(255),
     address_street       VARCHAR(255),
     delivery             NUMERIC(19, 2) NOT NULL DEFAULT(0),
     status               VARCHAR(50),
     address_city_id      INT8,
     kitchen_id           INT8 NOT NULL,
     created_at           TIMESTAMP,
     updated_at           TIMESTAMP,
     FOREIGN KEY (address_city_id) REFERENCES cities(id),
     FOREIGN KEY (kitchen_id) REFERENCES kitchens(id)
  );

CREATE TABLE products
  (
     id            BIGSERIAL NOT NULL PRIMARY KEY,
     name          VARCHAR(255) NOT NULL,
     price         NUMERIC(19, 2) NOT NULL,
     status        BOOLEAN,
     restaurant_id INT8 NOT NULL,
     FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
  );

  CREATE TABLE public.product_image (
  	product_id int8 NULL,
  	"name" varchar NULL,
  	"type" varchar NULL,
  	"size" int8 NULL,
  	CONSTRAINT product_image_pk PRIMARY KEY (product_id),
  	CONSTRAINT product_image_products_fk FOREIGN KEY (product_id) REFERENCES public.products(id) ON DELETE CASCADE
  );

CREATE TABLE payments
  (
     id          BIGSERIAL NOT NULL PRIMARY KEY,
     description VARCHAR(255) NOT NULL
  );

CREATE TABLE restaurant_payment
  (
     restaurant_id INT8 NOT NULL,
     payment_id    INT8 NOT NULL,
     FOREIGN KEY (payment_id) REFERENCES payments(id),
     FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) on delete cascade
  );

  CREATE TABLE users
    (
       id         BIGSERIAL NOT NULL PRIMARY KEY,
       email      VARCHAR(255) NOT NULL,
       name       VARCHAR(255) NOT NULL,
       password   VARCHAR(255) NOT NULL,
       created_at TIMESTAMP,
       updated_at TIMESTAMP
    );


  CREATE TABLE restaurant_user
  (
     restaurant_id INT8 NOT NULL,
     user_id    INT8 NOT NULL,
     FOREIGN KEY (user_id) REFERENCES users(id),
     FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) on delete cascade
  );

CREATE TABLE GROUPS
  (
     id   BIGSERIAL NOT NULL PRIMARY KEY,
     name VARCHAR(255) NOT NULL
  );

CREATE TABLE permissions
  (
     id          BIGSERIAL NOT NULL PRIMARY KEY,
     description VARCHAR(255),
     name        VARCHAR(255) NOT NULL
  );

CREATE TABLE group_permission
  (
     group_id      INT8 NOT NULL,
     permission_id INT8 NOT NULL,
     FOREIGN KEY (group_id) REFERENCES GROUPS(id) on delete cascade,
     FOREIGN KEY (permission_id) REFERENCES permissions(id)
  );

CREATE TABLE user_group
  (
     user_id  INT8 NOT NULL,
     group_id INT8 NOT NULL,
     FOREIGN KEY (group_id) REFERENCES GROUPS(id) on delete cascade,
     FOREIGN KEY (user_id) REFERENCES users(id)
  );

  CREATE TABLE orders (
    id BIGSERIAL not null primary key,
    code VARCHAR(100) NOT NULL UNIQUE,
  	total numeric(19, 2) NOT NULL,
  	sub_total numeric(19, 2) NOT NULL,
  	delivery numeric(19, 2) NOT NULL,
  	status VARCHAR NOT NULL,
  	address_street varchar NOT NULL,
  	address_number varchar NOT NULL,
  	address_complement varchar NULL,
  	address_cep varchar NOT NULL,
  	address_neighborhood varchar NOT NULL,
  	address_city_id int8 not null,
  	created_at timestamp NOT NULL,
  	confirmation_date timestamp NULL,
  	delivery_date timestamp NULL,
  	cancel_date timestamp NULL,
  	restaurant_id int8 NOT NULL,
  	payment_id int8 NOT NULL,
  	user_id int8 NOT null,
  	foreign key (restaurant_id) references restaurants(id),
  	foreign key (payment_id) references payments(id),
  	foreign key (user_id) references users(id)
  );

  CREATE TABLE order_items (
    id BIGSERIAL not null primary key,
  	quantity int8 NOT NULL,
  	total_price numeric(19, 2) NOT NULL,
  	unit_price numeric(19, 2) NOT NULL,
  	product_id int8 NOT NULL,
  	order_id int8 NOT null ,
  	foreign key (product_id) references products(id),
  	foreign key (order_id) references orders(id) on delete cascade
  );
