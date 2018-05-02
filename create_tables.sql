create table persons (
    id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    gender int not null,
    phone_number varchar(255) not null,
    address varchar(255) not null,

    constraint pk_persons primary key (id)
);
    
create table students (
    id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    person_id int not null,
    old_module int not null default 0,
    new_module int not null default 0,
    total_module int not null default 0,

    constraint pk_students primary key (id),
    constraint fk1_students foreign key (person_id) references persons (id)
);

create table teachers (
    id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    person_id int not null,
    department varchar(255),
    designation varchar(255),
    salary float not null default 0.0,

    constraint pk_teachers primary key (id),
    constraint fk1_teachers foreign key (person_id) references persons (id)
);

