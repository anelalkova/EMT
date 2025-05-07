-- Табела за земјите
CREATE TABLE country (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           continent VARCHAR(255) NOT NULL
);

-- Табела за корисниците
CREATE TABLE app_user (
                          id BIGSERIAL PRIMARY KEY,
                          username VARCHAR(255) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          role VARCHAR(255) NOT NULL,
                          country_id BIGINT,
                          FOREIGN KEY (country_id) REFERENCES country(id)
);

-- Табела за сместувањата (Accommodation)
CREATE TABLE accommodation (
                                id BIGSERIAL PRIMARY KEY,
                                name VARCHAR(255) NOT NULL,
                                category VARCHAR(255) NOT NULL,
                                host_id BIGINT NOT NULL,
                                num_rooms INT NOT NULL,
                                num_available_rooms INT NOT NULL,
                                FOREIGN KEY (host_id) REFERENCES app_user(id)
);

-- Табела за достапноста (Availability)
CREATE TABLE availability (
                                id BIGSERIAL PRIMARY KEY,
                                reserved_from DATE NOT NULL,
                                reserved_to DATE NOT NULL,
                                price DECIMAL(10, 2) NOT NULL,
                                accommodation_id BIGINT NOT NULL,
                                FOREIGN KEY (accommodation_id) REFERENCES accommodation(id)
);

-- Табела за резервациите (МНM)
CREATE TABLE app_user_reservations (
                                    accommodation_id BIGINT NOT NULL,
                                    user_id BIGINT NOT NULL,
                                    PRIMARY KEY (accommodation_id, user_id),
                                    FOREIGN KEY (accommodation_id) REFERENCES accommodation(id),
                                    FOREIGN KEY (user_id) REFERENCES app_user(id)
);

-- Вметни примерни земји
INSERT INTO country (name, continent) VALUES ('USA', 'North America');
INSERT INTO country (name, continent) VALUES ('Germany', 'Europe');

CREATE UNIQUE INDEX idx_hosts_by_country_country ON hosts_by_country(country);
