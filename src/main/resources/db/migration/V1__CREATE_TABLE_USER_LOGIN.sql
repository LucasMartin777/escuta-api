CREATE TABLE USER_LOGIN(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    date_of_birth DATE NOT NULL,
    created_at DATE
    is_active tinyint(1) not null default 1
);
