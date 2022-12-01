DROP TABLE staff CASCADE;
DROP TABLE students CASCADE;
DROP TABLE courses CASCADE;
DROP TABLE sessions CASCADE;
DROP TABLE enrolments CASCADE;
DROP TABLE sandwiches CASCADE;
DROP TABLE sand_comps CASCADE;
DROP TABLE orders CASCADE;

DROP SEQUENCE staff_stid_seq;
DROP SEQUENCE students_sid_seq;
DROP SEQUENCE courses_cid_seq;
DROP SEQUENCE sessions_seid_seq;
DROP SEQUENCE enrolments_eid_seq;
DROP SEQUENCE sandwiches_said_seq;
DROP SEQUENCE orders_oid_seq;
DROP SEQUENCE sand_comp_scid_seq;


CREATE SEQUENCE staff_stid_seq START WITH 100 increment 1;
CREATE SEQUENCE	students_sid_seq START WITH 200 increment 1;
CREATE SEQUENCE courses_cid_seq START WITH 100 increment 1;
CREATE SEQUENCE sessions_seid_seq START WITH 100 increment 1;
CREATE SEQUENCE enrolments_eid_seq START WITH 100 increment 1;
CREATE SEQUENCE sandwiches_said_seq START WITH 100 increment 1;
CREATE SEQUENCE orders_oid_seq START WITH 100 increment 1;
CREATE SEQUENCE sand_comp_scid_seq START WITH 10 increment 1;

CREATE TABLE staff
	(stid int PRIMARY KEY DEFAULT nextval('staff_stid_seq'),
	stfname char(45) NOT NULL,
	stlname char(45) NOT NULL,
	stemail char (45) NOT NULL,
	stpass char(20) NOT NULL
	);
	
CREATE TABLE students
(sid int PRIMARY KEY DEFAULT nextval('students_sid_seq'),
	sfname char(45) NOT NULL,
	slname char(45) NOT NULL
);

CREATE TABLE courses
( cid int PRIMARY KEY DEFAULT nextval('courses_cid_seq'),
	ctitle char(45) NOT NULL,
	c_stid int,
	cduration int NOT NULL,
	CONSTRAINT fk_course FOREIGN KEY (c_stid) REFERENCES staff
);

CREATE TABLE sessions
	(seid int PRIMARY KEY DEFAULT nextval('sessions_seid_seq'),
	se_cid int NOT NULL,
	sestart date NOT NULL
);

CREATE TABLE enrolments 
( eid int PRIMARY KEY DEFAULT nextval('enrolments_eid_seq'),
e_seid int NOT NULL,
e_sid int NOT NULL,

CONSTRAINT fk_session FOREIGN KEY (e_seid) REFERENCES sessions,
CONSTRAINT fk_students FOREIGN KEY (e_sid) REFERENCES students
);

CREATE TABLE sand_comps
(scid int PRIMARY KEY DEFAULT nextval('sand_comp_scid_seq'),
	scname char(40) NOT NULL,
	scaddress char(100),
	scphone char(10)
);


CREATE TABLE sandwiches
(said int PRIMARY KEY DEFAULT nextval('sandwiches_said_seq'),
sname char(45) NOT NULL,
s_scid int NOT NULL,
scateg char(45) NOT NULL,
sprice NUMERIC(9,2) NOT NULL,
singred char(100),
CONSTRAINT fk_sandcomp FOREIGN KEY (s_scid) REFERENCES sand_comps
);


CREATE TABLE orders 
( oid int PRIMARY KEY DEFAULT nextval('orders_oid_seq'),
	odate date NOT NULL,
	o_seid int NOT NULL,
	osize int NOT NULL,
	o_scid int NOT NULL,
	ototal NUMERIC(9,2) NOT NULL,
	
	CONSTRAINT fk_ordersandcomp FOREIGN KEY (o_scid) REFERENCES sand_comps,
	CONSTRAINT fk_ordersession FOREIGN KEY (o_seid) REFERENCES sessions
);


insert into sand_comps values(1, 'Vleugels', 'J. B. Van Monsstraat 127, 3000, Leuven', '0488451720');
insert into sand_comps values(2, E'Pinky\'s', 'Tiensevest 2, 3000, Leuven', '016293303');


insert into sandwiches values(1, 'Americain',1,'vlees',5.00,null);
insert into sandwiches values(2, 'Boulette',1,'vlees',5.00,null);
insert into sandwiches values(3, 'Pastrami',1,'vlees',5.00,null);
insert into sandwiches values(4, 'Hesp + kaas',1,'vlees',5.00,null);
insert into sandwiches values(5, 'Martino',1,'vlees',5.00,null);
insert into sandwiches values(6, 'Vleessalade',1,'vlees',5.00,null);
insert into sandwiches values(7, 'Hesp',1,'vlees',5.00,null);
insert into sandwiches values(8, 'Parmaham',1,'vlees',5.00,null);

insert into sandwiches values(9, 'Hesp',2,'vlees',5.00,null);
insert into sandwiches values(10, 'Rosbief',2,'vlees',5.00,null);
insert into sandwiches values(11, 'Gebraad',2,'vlees',5.00,null);
insert into sandwiches values(12, 'Gerookte Nootham',2,'vlees',5.00,null);
insert into sandwiches values(13, 'Parma ham',2,'vlees',5.00,null);
insert into sandwiches values(14, 'Salami',2,'vlees',5.00,null);
insert into sandwiches values(15, 'Americain Prepare',2,'vlees',5.00,null);
insert into sandwiches values(16, 'Gemend gehakt',2,'vlees',5.00,null);
insert into sandwiches values(17, 'Martino',2,'vlees',5.00,null);
insert into sandwiches values(18, 'Kip curry',2,'vlees',5.00,null);
insert into sandwiches values(19, 'Kip zigeuner',2,'vlees',5.00,null);
insert into sandwiches values(20, 'Kipsla',2,'vlees',5.00,null);
insert into sandwiches values(21, 'Weense sla',2,'vlees',5.00,null);
insert into sandwiches values(22, 'Vleessla',2,'vlees',5.00,null);

insert into sandwiches values(23, 'Kipfilet', 1, 'kip', 5.00, null);
insert into sandwiches values(24, 'Spicy kip curry', 1, 'kip', 5.00, null);
insert into sandwiches values(25, 'Kip Hawaii', 1, 'kip', 5.00, null);

insert into sandwiches values(26, 'Hollandse kaas', 2, 'kaas', 5.00, null);
insert into sandwiches values(27, 'Brie', 2, 'kaas', 5.00, null);
insert into sandwiches values(28, 'Kaassla', 2, 'kaas', 5.00, null);

insert into sandwiches values(29, 'Tonijnsalade', 1, 'vis', 5.00, null);
insert into sandwiches values(30, 'Tonijnsalade pikant', 1, 'vis', 5.00, null);
insert into sandwiches values(31, 'Zalm(gemarineerd) + philadelphia', 1, 'vis', 5.00, null);

insert into sandwiches values(32, 'Tomaat grnaal', 2, 'vis', 5.00, null);
insert into sandwiches values(33, 'Garnaalsla', 2, 'vis', 5.00, null);
insert into sandwiches values(34, 'Tonijnsla', 2, 'vis', 5.00, null);
insert into sandwiches values(35, 'Pikante tonijnsla', 2, 'vis', 5.00, null);
insert into sandwiches values(36, 'Zalmsla', 2, 'vis', 5.00, null);
insert into sandwiches values(37, 'Gerooktezalmsla', 2, 'vis', 5.00, null);
insert into sandwiches values(38, 'Kraabsla', 2, 'vis', 5.00, null);

insert into sandwiches values(39, 'Kaas', 1, 'veggie', 5.00, null);
insert into sandwiches values(40, 'Tomaat + mozzarella', 1, 'veggie', 5.00, null);
insert into sandwiches values(41, 'Eiersalade', 1, 'veggie', 5.00, null);
insert into sandwiches values(42, 'Brie', 1, 'veggie', 5.00, null);
insert into sandwiches values(43, 'Feta', 1, 'veggie', 5.00, null);
insert into sandwiches values(44, 'Geitenkaas', 1, 'veggie', 5.00, null);

insert into sandwiches values(45, 'Eiersla', 2, 'ei', 5.00, null);

insert into sandwiches values(46, 'Wortelspread + sesam + tuinkers', 1, 'vegan', 5.00, null);
insert into sandwiches values(47, 'Hummus', 1, 'vegan', 5.00, null);
insert into sandwiches values(48, 'Vegan mayo', 1, 'vegan', 5.00, null);
insert into sandwiches values(49, 'Avocadospread', 1, 'vegan', 5.00, null);

insert into sandwiches values(50, 'Smos', 2, 'specials', 5.00, 'Club met kaas en hesp');
insert into sandwiches values(51, 'Pinky', 2, 'specials', 5.00, 'Sla, tomaat, ei, haringfilet, tartaar');
insert into sandwiches values(52, 'Mozzarella', 2, 'specials', 5.00, 'Olijfolie, zout, peper, basilicum, tomaat');
insert into sandwiches values(53, 'Special', 2, 'specials', 5.00, 'Sla, tomaat, ei, kipfilet, saus');
insert into sandwiches values(54, 'Florida', 2, 'specials', 5.00, 'Sla, ananas, kipfilet, cocktailsaus');
insert into sandwiches values(55, 'Primus', 2, 'specials', 5.00, 'Sla, perzik, tonijnsla');
insert into sandwiches values(56, 'Apollo', 2, 'specials', 5.00, 'Rucola, ananas, gebraad, cocktailsaus');
insert into sandwiches values(57, 'Milano', 2, 'specials', 5.00, 'Sla, tomaat, augurk, ei, salami, tartaar');
insert into sandwiches values(58, 'Ardenne', 2, 'specials', 5.00, 'Rucola, tomaat, nootham, cocktailsaus');
insert into sandwiches values(59, 'Gerookte zalm', 2, 'specials', 5.00, 'Sla, ui, gerookte zalm, cocktailsaus');
insert into sandwiches values(60, 'Tweetybroodje', 2, 'specials', 5.00, 'Eiersla, salami');
insert into sandwiches values(61, 'Lentebroodje', 2, 'specials', 5.00, 'Seldersla, geraspte wortels, ananas');
insert into sandwiches values(62, 'Hawaiibroodje', 2, 'specials', 5.00, 'Ananas, hesp, kaas, cocktailsaus');
insert into sandwiches values(63, 'Gezond', 2, 'specials', 5.00, 'Sla, tomaat, seldersla, geraspte wortelen, komkommer, ei, mayonaise');

insert into sandwiches values(64, 'Carolina', 1, 'specials', 5.00, 'Eiersalade, spek, tuinkers');
insert into sandwiches values(65, 'Parmigiano', 1, 'specials', 5.00, 'Americain, zongedroogde tomaat, rucola, pijnbppmpitten');
insert into sandwiches values(66, 'Kip cocktail', 1, 'specials', 5.00, 'Kipfilet, cocktailsaus, perzik, tuinkers');
insert into sandwiches values(67, 'BBQ chicken', 1, 'specials', 5.00, 'Kip, barbecuesaus, spek, tomaat, sla');
insert into sandwiches values(68, 'Flying brie', 1, 'specials', 5.00, 'Brie, honing, noot, rucola');
insert into sandwiches values(69, 'Provence', 1, 'specials', 5.00, 'Feta, zongedroogde tomaat, rucola');

INSERT INTO STAFF VALUES (1, 'Mary', 'Jones', 'maryjones@gmail.com', 'maryjonespass');
INSERT INTO STAFF VALUES (2, 'Jim', 'McFLee', 'jimmcflee@gmail.com', 'jimpass');
INSERT INTO STAFF VALUES (3, 'Helen', 'Sting', 'helensting@gmail.com', 'helenpass');
INSERT INTO STAFF VALUES (4, 'Larry', 'Lane', 'larrylane@gmail.com', 'larrypass');
INSERT INTO STAFF VALUES (5, 'Mike', 'Randall', 'mikerandall@gmail.com', 'mikepass');
INSERT INTO STAFF VALUES (6, 'Steve', 'Sigmundson', 'stevesigmundson@yahoo.com', 'stevepass');
INSERT INTO STAFF VALUES (7, 'Aaron', 'Simmons', 'aaronsimmons@hotmail.com', 'aaronpass');
INSERT INTO STAFF VALUES (8, 'Lance', 'Armstrong', 'lancearmstrong@yahoo.com', 'lancepass');
INSERT INTO STAFF VALUES (9, 'Leroy', 'Jenkins', 'leroyjenkins@talk.com', 'leroypass');
INSERT INTO STAFF VALUES (10, 'Andrea', 'Serotti', 'andreaserotti@yahoo.it', 'andreapass');
INSERT INTO STAFF VALUES (11, 'Indra', 'Evensen', 'indraevensen@hotmail.com', 'indrapass');
INSERT INTO STAFF VALUES (12, 'Mikael', 'Akerfield', 'makerfield@yahoo.com', 'mikaelpass');
INSERT INTO STAFF VALUES (13, 'Frodo', 'Baggins', 'frodobaggins@theshire.mi', 'frodopass');

INSERT INTO STUDENTS VALUES (101, 'Bob', 'Debouwer');
INSERT INTO STUDENTS VALUES (102, 'Floor', 'Jansens');
INSERT INTO STUDENTS VALUES (103, 'Jos', 'Van Den Bosch');
INSERT INTO STUDENTS VALUES (104, 'Wouter', 'Wouters');
INSERT INTO STUDENTS VALUES (105, 'Tim', 'Smets');
INSERT INTO STUDENTS VALUES (106, 'Jan', 'Klaasens');
INSERT INTO STUDENTS VALUES (107, 'Laura', 'Lynn');
INSERT INTO STUDENTS VALUES (108, 'Kobe', 'Jansens');
INSERT INTO STUDENTS VALUES (109, 'Ward', 'Verbiest');
INSERT INTO STUDENTS VALUES (110, 'Laura', 'Nietlynn');
INSERT INTO STUDENTS VALUES (111, 'Erika', 'Drumstick');
INSERT INTO STUDENTS VALUES (112, 'Sven', 'De Ridder');
INSERT INTO STUDENTS VALUES (113, 'Kevin', 'Backermans');
INSERT INTO STUDENTS VALUES (114, 'Bobby', 'Billiebob');
INSERT INTO STUDENTS VALUES (115, 'Samson', 'Engert');
INSERT INTO STUDENTS VALUES (116, 'Peter', 'Leremans');
INSERT INTO STUDENTS VALUES (117, 'Pieter', 'Van de Gaer');
INSERT INTO STUDENTS VALUES (118, 'Eros', 'De Haan');
INSERT INTO STUDENTS VALUES (119, 'Ramses', 'Wouters');
INSERT INTO STUDENTS VALUES (120, 'Gengis', 'Khan');
INSERT INTO STUDENTS VALUES (121, 'Lewis', 'Parker');
INSERT INTO STUDENTS VALUES (122, 'Sylvain', 'Moors');
INSERT INTO STUDENTS VALUES (123, 'Nicolas', 'Dewulf');
INSERT INTO STUDENTS VALUES (124, 'Paul', 'Olivier');
INSERT INTO STUDENTS VALUES (125, 'Doug', 'Nicholls');
INSERT INTO STUDENTS VALUES (126, 'Jan', 'Bune');
INSERT INTO STUDENTS VALUES (127, 'Bert', 'Koops');
INSERT INTO STUDENTS VALUES (128, 'Louis', 'Mak');
INSERT INTO STUDENTS VALUES (129, 'John', 'Doe');
INSERT INTO STUDENTS VALUES (130, 'Raf', 'Schumacher');

INSERT INTO COURSES VALUES (1, 'Java basics', 2, 5);
INSERT INTO COURSES VALUES (2, 'Python for Friends', 4, 10);
INSERT INTO COURSES VALUES (3, 'FrontEnd Development', 8, 3);
INSERT INTO COURSES VALUES (4, 'Maven Basics', 1, 2);
INSERT INTO COURSES VALUES (5, 'Java Advanced', 2, 6);
INSERT INTO COURSES VALUES (6, 'Angular', 3, 3);
INSERT INTO COURSES VALUES (7, 'Projectmanagement', 8, 2);
INSERT INTO COURSES VALUES (8, 'System analysis', 6, 3);
INSERT INTO COURSES VALUES (9, 'System design', 13, 6);
INSERT INTO COURSES VALUES (10, 'Structured Programming', 3, 3);


INSERT INTO SESSIONS VALUES (1, 2,'2022-10-25');
INSERT INTO SESSIONS VALUES (2, 4, '2022-10-26');
INSERT INTO SESSIONS VALUES (3, 10, '2022-10-27');
INSERT INTO SESSIONS VALUES (4, 7, '2022-10-27');
INSERT INTO SESSIONS VALUES (5, 1, '2022-10-28');
INSERT INTO SESSIONS VALUES (6, 5, '2022-10-29');

INSERT INTO ENROLMENTS VALUES (1,1,2);
INSERT INTO ENROLMENTS VALUES (2,1,3);
INSERT INTO ENROLMENTS VALUES (3,1,4);
INSERT INTO ENROLMENTS VALUES (4,1,5);
INSERT INTO ENROLMENTS VALUES (5,1,6);
INSERT INTO ENROLMENTS VALUES (6,1,7);
INSERT INTO ENROLMENTS VALUES (7,1,8);
INSERT INTO ENROLMENTS VALUES (8,1,9);
INSERT INTO ENROLMENTS VALUES (9,1,10);
INSERT INTO ENROLMENTS VALUES (10,1,11);
INSERT INTO ENROLMENTS VALUES (11,1,12);
INSERT INTO ENROLMENTS VALUES (12,1,13);
INSERT INTO ENROLMENTS VALUES (13,1,1);
INSERT INTO ENROLMENTS VALUES (14,2,2);
INSERT INTO ENROLMENTS VALUES (15,2,13);
INSERT INTO ENROLMENTS VALUES (16,2,4);
INSERT INTO ENROLMENTS VALUES (17,2,5);
INSERT INTO ENROLMENTS VALUES (18,3,6);
INSERT INTO ENROLMENTS VALUES (19,3,2);
INSERT INTO ENROLMENTS VALUES (20,3,8);
INSERT INTO ENROLMENTS VALUES (21,4,9);
INSERT INTO ENROLMENTS VALUES (22,4,10);
INSERT INTO ENROLMENTS VALUES (23,5,5);
INSERT INTO ENROLMENTS VALUES (24,6,6);
INSERT INTO ENROLMENTS VALUES (25,6,13);
INSERT INTO ENROLMENTS VALUES (26,6,3);

INSERT INTO SESSIONS VALUES (7, 4, '2022-11-29' );
INSERT INTO ENROLMENTS VALUES (27,7,3);
INSERT INTO ENROLMENTS VALUES (28,7,2);

INSERT INTO ORDERS VALUES (1, '2022-10-25', 1, 10, 1, 30.00);
