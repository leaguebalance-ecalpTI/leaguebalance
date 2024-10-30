CREATE TABLE IF NOT EXISTS player
(
    id        BIGSERIAL PRIMARY KEY,
    nome      VARCHAR(255),
    total_score INTEGER
);

CREATE TABLE IF NOT EXISTS team
(
    id        BIGSERIAL PRIMARY KEY,
    total_score INTEGER
);

CREATE TABLE IF NOT EXISTS player_roles
(
    id        BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(255),
    score     INTEGER,
    player_id BIGINT REFERENCES player (id)
);

CREATE TABLE IF NOT EXISTS team_player
(
    team_id   BIGINT REFERENCES team (id),
    player_roles_id BIGINT REFERENCES player_roles (id)
);


CREATE TABLE IF NOT EXISTS match
(
    id                BIGSERIAL PRIMARY KEY,
    team_red_side_id  BIGINT REFERENCES team (id),
    team_blue_side_id BIGINT REFERENCES team (id)
);
