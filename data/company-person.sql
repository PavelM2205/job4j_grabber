CREATE TABLE company(
	id integer NOT NULL,
	name character varying,
	CONSTRAINT company_pkey PRIMARY KEY(id)
);

CREATE TABLE person(
	id integer NOT NULL,
	name character varying,
	company_id integer REFERENCES company(id),
	CONSTRAINT person_pkey PRIMARY KEY(id)
);

insert into company 
values(1, 'RZD'), (2, 'Rosneft'), (3, 'Pole-Gold'), (4, 'KFC'), (5, 'SGK');
insert into person
values(1, 'Ivan', 1), (2, 'Petr', 2), (3, 'Igor', 3), (4, 'Maxim', 4), (5, 'Pavel', 5);

insert into person
values(6, 'Ibragim', 1), (7, 'Artem', 1), (8, 'Alexey', 3), (9, 'Mihail', 3), (10, 'Vadim', 2);

select p.name, c.name 
from person as p
join company as c
on p.company_id = c.id
where company_id != 5;

select c.name, count(p.name) from person as p
join company as c
on p.company_id = c.id
group by c.name
having count(p.name) = (select max(cp) from (
select count (p.name) as cp from person as p
join company as c
on p.company_id = c.id
group by c.name
) as cn);
