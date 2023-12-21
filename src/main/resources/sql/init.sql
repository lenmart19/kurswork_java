create table company
(
    login        varchar(45)  not null
        constraint company_pk
            primary key,
    company_name varchar(255) not null,
    password     varchar(255) not null
);

create table sessions
(
    login   varchar(255)                        not null
        constraint sessions_pkey
            primary key
        constraint sessions_company_login_fk
            references company
            on update cascade on delete cascade,
    date    timestamp default CURRENT_TIMESTAMP not null,
    session varchar(255)                        not null
);

create table trip
(
    id_t      serial      not null
        constraint trip_pkey
            primary key,
    trip_name varchar(45) not null,
    time_from timestamp   not null,
    time_to   timestamp   not null,
    company_n varchar(45) not null
        constraint company_fk
            references company
            on update cascade on delete cascade,
    bus       varchar(45) not null,
    pas_count integer default 0
);

create table passenger
(
    id_p           serial      not null
        constraint passenger_pkey
            primary key,
    passenger_name varchar(45) not null,
    email          varchar(45) not null
        constraint passenger_email_key
            unique
);

create table pass_in_trip
(
    id_pit            serial  not null
        constraint pass_in_trip_pkey
            primary key,
    trip_n            integer not null
        constraint trip_fk
            references trip
            on update cascade on delete cascade,
    passenger_n       integer not null
        constraint passenger_fk
            references passenger
            on update cascade on delete cascade,
    registration_time timestamp
);

create function plus_passenger_count() returns trigger
    language plpgsql
as
$$
BEGIN
UPDATE trip
SET pas_count = pas_count + 1
WHERE id_t = NEW.trip_n;
RETURN NEW;
END;
$$;

create trigger pass_in_trip_after_insert
    after insert
    on pass_in_trip
    for each row
    execute procedure plus_passenger_count();

create function minus_passenger_count() returns trigger
    language plpgsql
as
$$
BEGIN
UPDATE trip
SET pas_count = pas_count - 1
WHERE id_t = OLD.trip_n
  AND pas_count > 0;
RETURN OLD;
END;
$$;

create trigger pass_in_trip_after_delete
    after delete
    on pass_in_trip
    for each row
    execute procedure minus_passenger_count();

