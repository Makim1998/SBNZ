----------------------------------------AUTHORITIES----------------------------------------
insert into authority_table (name) values ('admin');
insert into authority_table (name) values ('client');

----------------------------------------USERS----------------------------------------
insert into user_table (email, password, first_name, last_name)
values ('admin@gmail.com', '$2a$10$VpH.INOqjymPVNZ986HWkuTWPXMjk68d5AD/9Iu5gO846LsNj2g62', 'admin', 'admin');

insert into user_table (email, password, first_name, last_name)
values ('marko@gmail.com', '$2a$10$VpH.INOqjymPVNZ986HWkuTWPXMjk68d5AD/9Iu5gO846LsNj2g62', 'marko', 'markovic');

insert into user_authority (user_id, authority_id) values (1, 1);
insert into user_authority (user_id, authority_id) values (2, 2);

insert into klijent_table (godine, mesecna_zarada, nagradni_poeni, stanje_racun, pridruzen) values (66, 2000, 0, 5000, '2019-10-10');
update user_table set klijent_id = 1 where id = 2;

insert into kredit (mesecna_rata, kamata, datum_rate, datum_ugovora, klijent_id) values (300, 5, 31, '2020-01-01', 1);
insert into zahtevi_table (iznos, period, tip_kredita, tip_garancije, status, klijent_id, kredit_id) values (30000, 60, 1, 1, true, 1, 1);
