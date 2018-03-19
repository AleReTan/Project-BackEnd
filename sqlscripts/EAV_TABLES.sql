CREATE SEQUENCE object_type_sequence
START 1
INCREMENT 1;
CREATE SEQUENCE attribute_sequence
START 1
INCREMENT 1;
CREATE SEQUENCE object_sequence
START 1
INCREMENT 1;
CREATE SEQUENCE  session_id_sequence
START 1
INCREMENT 1;


CREATE TABLE object_type
(
  id     BIGINT DEFAULT nextval('object_type_sequence' :: REGCLASS) NOT NULL
    CONSTRAINT object_type_pkey
    PRIMARY KEY,
  name   VARCHAR(150)                                               NOT NULL,
  parent BIGINT
    CONSTRAINT object_type_object_type_id_fk
    REFERENCES object_type
  ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE attribute
(
  id             BIGINT DEFAULT nextval('attribute_sequence' :: REGCLASS) NOT NULL
    CONSTRAINT attribute_pkey
    PRIMARY KEY,
  name           VARCHAR(150)                                             NOT NULL,
  object_type_id BIGINT                                                   NOT NULL
    CONSTRAINT attribute_object_type_id_fk
    REFERENCES object_type
  ON UPDATE CASCADE ON DELETE CASCADE,
  value_type     VARCHAR(150)                                             NOT NULL,
  require        BOOLEAN                                                  NOT NULL
);

CREATE TABLE object
(
  id             BIGINT DEFAULT nextval('object_sequence' :: REGCLASS) NOT NULL
    CONSTRAINT object_pkey
    PRIMARY KEY,
  name           VARCHAR(150)                                          NOT NULL,
  object_type_id BIGINT                                                NOT NULL
    CONSTRAINT object_object_type_id_fk
    REFERENCES object_type
  ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE params
(
  attr_id   BIGINT                                                NOT NULL
    CONSTRAINT params_attribute_id_fk
    REFERENCES attribute
  ON UPDATE CASCADE ON DELETE CASCADE,
  object_id BIGINT                                                NOT NULL
    CONSTRAINT params_object_id_fk
    REFERENCES object
  ON UPDATE CASCADE ON DELETE CASCADE,
  value     VARCHAR(150)                                          NOT NULL
);
CREATE TABLE reference
(
  reference BIGINT NOT NULL
    CONSTRAINT reference_object_ref_id_fk
    REFERENCES object
  ON UPDATE CASCADE ON DELETE CASCADE,
  object_id BIGINT NOT NULL
    CONSTRAINT reference_object_id_fk
    REFERENCES object
  ON UPDATE CASCADE ON DELETE CASCADE,
  attr_id   BIGINT NOT NULL
    CONSTRAINT reference_attribute_id_fk
    REFERENCES attribute
  ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE users
(
  login    VARCHAR(100) NOT NULL
    CONSTRAINT users_pkey
    PRIMARY KEY,
  password VARCHAR(500) NOT NULL,
  role     VARCHAR(50)  NOT NULL
);


CREATE SEQUENCE  session_id_sequence
START 1
INCREMENT 1;

create table users
(
  login varchar(100) not null
    constraint users_pkey
    primary key,
  password varchar(500) not null,
  role varchar(50) not null
);

create table sessions
(
  id bigint default nextval('session_id_sequence'::regclass) not null
    constraint session_id_pkey
    primary key,
  login varchar(100) not null
    constraint login_fk
    references users
  on update cascade on delete cascade,
  timeofbegin timestamp not null,
  timerecentactivity timestamp not null
);
