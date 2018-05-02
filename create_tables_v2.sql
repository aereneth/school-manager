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
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    gender int not null,
    phone_number varchar(255),
    address varchar(255),
    old_module int not null default 0,
    new_module int not null default 0,
    tuition_fee float not null default 0.0,
    amount_paid float not null default 0.0,

    constraint pk_students primary key (id)
);

create table teachers (
    id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    gender int not null,
    phone_number varchar(255) not null,
    address varchar(255) not null,
    department varchar(255),
    designation varchar(255),
    salary float not null default 0.0,

    constraint pk_teachers primary key (id)
);

create table attendances (
    id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    teacher_id int not null,
    hours int not null,
    log_time timestamp not null default current_timestamp,
    
    constraint pk_attendances primary key (id),
    constraint fk1_attendances foreign key (teacher_id) references teachers (id)
);

create table transactions (
    id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    student_id int not null,
    amount float not null,
    record_time timestamp not null default current_timestamp,
    
    constraint pk_transactions primary key (id),
    constraint fk1_transactions foreign key (student_id) references students (id)
);
