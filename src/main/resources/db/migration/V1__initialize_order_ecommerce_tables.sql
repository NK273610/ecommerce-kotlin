create table if not exists ecommerce_payment
(
    payment_id          uuid     primary key,
    amount              double precision not null,
    confirmation_number varchar(60)      not null,
    created_at          timestamp        not null default current_timestamp,
    payment_mode        varchar(60)      not null,
    payment_status      varchar(60)      not null
    );

create table if not exists ecommerce_product
(
    product_id      uuid     primary key,
    created_at  timestamp     not null default current_timestamp,
    description varchar(60)     not null,
    price       double precision not null,
    sku         varchar(60)     not null,
    title       varchar(60)     not null
    );

create table if not exists ecommerce_address
(
    address_id     uuid primary key,
    address1       varchar(60) not null,
    address2       varchar(60) not null,
    city           varchar(60) not null,
    created_at     timestamp   not null default current_timestamp,
    email          varchar(60) not null,
    phone          varchar(60) not null,
    state          varchar(60) not null,
    zip            varchar(60) not null
    );

create table if not exists ecommerce_order
(
    order_id            uuid PRIMARY KEY,
    created_at          timestamp not null default current_timestamp,
    customer_id         varchar(60) not null,
    order_status        varchar(60) not null,
    shipping_charges    double precision,
    shipping_mode       varchar(60) not null,
    sub_total           double precision,
    tax                 double precision,
    title               varchar(60) not null,
    total_amt           double precision,
    payment_id          uuid,
    billing_address_id  uuid,
    shipping_address_id uuid,
    FOREIGN KEY (payment_id) REFERENCES ecommerce_payment (payment_id),
    FOREIGN KEY (billing_address_id) REFERENCES ecommerce_address (address_id),
    FOREIGN KEY (shipping_address_id) REFERENCES ecommerce_address (address_id)
    );

create table if not exists ecommerce_order_item (
    order_id       uuid,
    product_id      uuid,
    quantity       varchar(60) not null,

    PRIMARY KEY (order_id,product_id),
    FOREIGN KEY (order_id) REFERENCES ecommerce_order (order_id),
    FOREIGN KEY (product_id) REFERENCES ecommerce_product (product_id)
);