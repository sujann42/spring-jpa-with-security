-- insert into user (id, name, birthDate) values(101,  "Terry", sysdate());
-- insert into user (id, name, birthDate) values(102,  "Drogba", sysdate());
-- insert into user (id, name, birthDate) values(103, "Lampard", sysdate(), );
-- insert into user (id, name, birthDate) values(104,  "Cech", sysdate());

insert into user  values(10001, sysdate(), 'Terry');
insert into user  values(10002, sysdate(), 'Drogba');
insert into user  values(10003, sysdate(), 'Lampard');
insert into user  values(10004, sysdate(), 'Cech');
insert into post values(11001, 'My first Post', 10001);
insert into post values(11002, 'My second Post', 10001);