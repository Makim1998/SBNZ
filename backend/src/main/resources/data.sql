----------------------------------------AUTHORITIES----------------------------------------
insert into authority_table (name) values ('admin');
insert into authority_table (name) values ('client');

----------------------------------------USERS----------------------------------------
insert into user_table (email, password, first_name, last_name)
values ('admin@gmail.com', '$2a$10$VpH.INOqjymPVNZ986HWkuTWPXMjk68d5AD/9Iu5gO846LsNj2g62', 'admin', 'admin');
insert into user_authority (user_id, authority_id) values (1, 1);