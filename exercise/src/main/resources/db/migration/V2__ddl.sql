CREATE TABLE student
(
    id                uuid      NOT NULL,
    email      		  varchar   unique,
    user_name          varchar	unique,
    first_name     	  varchar,
    last_name     	  varchar,
    display_name       varchar,
    created_at         timestamp,
    started_at		  timestamp,
    version           integer,
    PRIMARY KEY (id)
);