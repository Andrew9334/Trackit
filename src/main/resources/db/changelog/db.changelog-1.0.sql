DROP TABLE IF EXISTS post_movement;
DROP TABLE IF EXISTS post_item;
DROP TABLE IF EXISTS post_office;

-- Миграция для создания таблицы post_office
CREATE TABLE IF NOT EXISTS post_office
(
    postal_code VARCHAR(10) PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    address     VARCHAR(255) NOT NULL
);

-- Миграция для создания таблицы post_item
CREATE TABLE IF NOT EXISTS post_item
(
    id                    BIGSERIAL PRIMARY KEY,
    recipient_name        VARCHAR(255) NOT NULL,
    recipient_address     VARCHAR(255) NOT NULL,
    recipient_postal_code VARCHAR(10)  NOT NULL,
    item_type             VARCHAR(50)  NOT NULL,
    status                VARCHAR(50)  NOT NULL,
    version               BIGINT DEFAULT 0,
    CONSTRAINT fk_post_item_postal_code FOREIGN KEY (recipient_postal_code)
        REFERENCES post_office (postal_code)
        ON DELETE CASCADE
);

-- Миграция для создания таблицы post_movement
CREATE TABLE IF NOT EXISTS post_movement
(
    id                      BIGSERIAL PRIMARY KEY,
    post_item_id            BIGINT      NOT NULL,
    post_office_postal_code VARCHAR(10) NOT NULL,
    movement_type           VARCHAR(50) NOT NULL,
    timestamp               TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    version                 BIGINT               DEFAULT 0,
    CONSTRAINT fk_post_movement_post_item FOREIGN KEY (post_item_id)
        REFERENCES post_item (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_post_movement_post_office FOREIGN KEY (post_office_postal_code)
        REFERENCES post_office (postal_code)
        ON DELETE CASCADE
);
