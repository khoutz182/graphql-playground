create table User
(
    id         bigint              not null primary key,
    firstName  varchar(255)        not null,
    lastName   varchar(255)        not null,
    username   varchar(255) unique not null,

    -- hashes are fine: https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html
    password   varchar(255)        not null, -- WHAT!? NOT ENCRYPTED!? ;-)
    address_id bigint              not null -- assuming not null
);

create table Address
(
    id       bigint       not null primary key,
    address1 varchar(255) not null,
    address2 varchar(255),
    city     varchar(255) not null,
    state    varchar(100) not null,
    postal   varchar(10)  not null
);

alter table User
    add foreign key (address_id)
    references Address(id);

insert into Address (id, address1, address2, city, state, postal)
values (1, '1060 W Addison St', null, 'Chicago', 'IL', '60613'),
       (2, '1600 Pennsylvania Ave NW', 'Apt 2', 'Washington', 'D.C.', '20500');

insert into User (id, firstName, lastName, username, password, address_id)
values (1, 'Phil', 'Ingwell', 'PhilIngwell', 'Password123', 1),
       (2, 'Anna', 'Conda', 'AnnaConda', 'Password234', 2);

