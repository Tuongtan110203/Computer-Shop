create database seller
use seller

create table Categories
(
ID [int] primary key,
[name] nvarchar(30) ,
describe nvarchar(50),
)

create table products
(
ID varchar(10) primary key,
[name] [nvarchar](max) NULL,
[quantity] [int] NULL,
[price] [money] NULL,
[releaseDate] [date] NULL,
[describe] [nvarchar](max) NULL,
[image] [nvarchar](max) NULL,
[cid] [int] references Categories(ID),
)

cREATE TABLE registration (
    user_id INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(100),
    password NVARCHAR(100),
    lastname NVARCHAR(100),
    isAdmin BIT
);

CREATE TABLE cart (
    cart_id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT,
    product_id VARCHAR(10),
    quantity INT,
    FOREIGN KEY (user_id) REFERENCES registration(user_id),
    FOREIGN KEY (product_id) REFERENCES products(ID)
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT,
	product_id VARCHAR(10),
    order_date DATETime,
	[status] bit,
    FOREIGN KEY (user_id) REFERENCES registration(user_id),
	 FOREIGN KEY (product_id) REFERENCES products(ID)
);

CREATE TABLE orderdetails (
    orderdetail_id INT PRIMARY KEY IDENTITY(1,1),
    order_id INT,
	    product_id VARCHAR(10),
	[name] [nvarchar](max) NULL,
	[price] [money] NULL, 
	[image] [nvarchar](max) NULL,
	[quantity] [int] NULL, 
    total_money MONEY,
    order_date DATETIME,
    fullname nVARCHAR(100),
    address nVARCHAR(255),
	email nvarchar(255),
    phone VARCHAR(20),
	notice nvarchar(500),
	FOREIGN KEY (product_id) REFERENCES products(ID),
	FOREIGN KEY ( order_id) REFERENCES orders(order_id)
);
create table [view](
	viewed int
)
insert into [view] values(0)
insert into Categories values(1,N'Chuột',N'Sử dụng để điều khiển khiến bị laptop PC ')
insert into Categories values(2,N'Tai Nghe',N'Sử dụng để nghe âm thanh mượt mà hơn')
insert into Categories values(3,N'Bàn Phím',N'Sử dụng để dễ dàng đánh máy hơn')

insert into products values(N'C1',N'Mouse Logitect 703 Hero',15,'300000',
'2023-09-01',N'Dễ dàng sử dụng và giá cả phải chăng',N'images/chuot/C703Hero.jpg',1)
insert into products values(N'C2',N'Mouse Logitect G102',30,'400000',
CAST(0x93400B00 AS Date),N'Dễ dàng sử dụng và ngoại hình đẹp',N'images/chuot/CG102.jpg',1)
insert into products values(N'C3',N'Mouse Logitect G103',40,'500000',
'2024-01-01',N'Dễ dàng sử dụng và tốc độ nhanh nhẹn',N'images/chuot/CG103.jpg',1)
insert into products values(N'C4',N'Mouse Logitect G203',20,'700000',
'2023-01-01',N'Dễ dàng sử dụng và tốc độ nhanh nhẹn nhưng giá hơi cao',N'images/chuot/CG203.jpg',1)
insert into products values(N'C5',N'Mouse Logitect G402',60,'200000',
'2022-01-01',N'Dễ dàng sử dụng và tốc độ nhanh nhẹn giá hợp lí với sinh viên',N'images/chuot/CG402.jpg',1)
insert into products values(N'C6',N'Mouse Logitect G502',100,'150000',
'2023-09-09',N'Dễ dàng sử dụng và tốc độ nhanh nhẹn giá hợp lí với sinh viên',N'images/chuot/CG502.jpg',1)
insert into products values(N'C7',N'Mouse Logitect M280',200,'100000',
'2023-09-09',N'Dễ dàng sử dụng và tốc độ chậm nhưng giá hợp lí với sinh viên',N'images/chuot/CM280.jpg',1)
insert into products values(N'C8',N'Mouse Logitect M350',200,'100000',
'2023-10-09',N'Dễ dàng sử dụng và tốc độ chậm nhưng giá hợp lí với sinh viên',N'images/chuot/CM350.jpg',1)
insert into products values(N'C9',N'Mouse Logitect M650',50,'250000',
'2023-12-12',N'Dễ dàng sử dụng và tốc độ vừa phải hợp lí với dân văn phòng',N'images/chuot/CM650.jpg',1)

insert into products values(N'T1',N'Head Phone Logitech 331',50,'350000',
'2023-08-12',N'Âm thanh êm giá cả phải chăng',N'images/Tainghe/TG331.jpg',2)
insert into products values(N'T2',N'Head Phone Logitech 435',30,'550000',
'2024-01-13',N'Âm thanh cực kì êm và có cả micro',N'images/Tainghe/TG435.jpg',2)
insert into products values(N'T3',N'Head Phone Logitech 435 version 2',50,'750000',
'2024-02-11',N'Âm thanh cực kì êm và có cả micro',N'images/Tainghe/TG435v2.jpg',2)
insert into products values(N'T4',N'Head Phone Logitech 540',20,'250000',
'2023-09-11',N'Âm thanh bình thường và có cả micro phù hợp với dân văn phòng',N'images/Tainghe/TG540.jpg',2)
insert into products values(N'T5',N'Head Phone Logitech 633s',30,'450000',
'2023-06-07',N'Âm thanh êm ngoại hình đẹp',N'images/Tainghe/TG633s.jpg',2)
insert into products values(N'T6',N'Head Phone Logitech 733',35,'400000',
'2023-07-07',N'Âm thanh sống động ngoại hình đẹp dành cho nữ',N'images/Tainghe/TG733.jpg',2)
insert into products values(N'T7',N'Head Phone Logitech 733 version 2',55,'500000',
'2023-09-11',N'Âm thanh sống động ngoại hình đẹp dành cho nữ và có thêm micro',N'images/Tainghe/TG733v2.jpg',2)
insert into products values(N'T8',N'Head Phone Logitech 933',65,'300000',
'2023-11-11',N'Âm thanh sống động ngoại hình đẹp dành cho nữ và có thêm micro',N'images/Tainghe/TG933.jpg',2)

insert into products values(N'B1',N'Key Board AKKO 3080',100,'800000',
'2023-02-11',N'Bàn phím gõ cực êm và nhạy và có các phím tắt dành cho gaming',N'images/Banphim/BAKKO3080.jpg',3)
insert into products values(N'B2',N'Key Board AKKO 3084',50,'900000',
'2023-03-20',N'Bàn phím gõ cực êm và nhạy và có các phím tắt dành cho gaming',N'images/Banphim/BAKKO3084.jpg',3)
insert into products values(N'B3',N'Key Board AKKO 3087',36,'1000000',
'2023-04-22',N'Bàn phím gõ cực êm và nhạy và có các phím tắt dành cho gaming',N'images/Banphim/BAKKO3087.jpg',3)
insert into products values(N'B4',N'Key Board AKKO 3087 version 2',46,'1200000',
'2023-05-25',N'Bàn phím gõ cực êm và nhạy và có các phím tắt dành cho gaming',N'images/Banphim/BAKKO3087v2.jpg',3)
insert into products values(N'B5',N'Key Board AKKO 3098 ',50,'1600000',
'2023-07-07',N'Bàn phím gõ cực êm và nhạy và có các phím tắt dành cho gaming',N'images/Banphim/BAKKO3098.jpg',3)
insert into products values(N'B6',N'Key Board AKKO 3098b ',55,'2000000',
'2023-11-07',N'Bàn phím gõ cực êm và nhạy và có các phím tắt dành cho gaming',N'images/Banphim/BAKKO3098b.jpg',3)
insert into products values(N'B7',N'Key Board AKKO 3098s ',55,'2100000',
'2023-11-30',N'Bàn phím gõ cực êm và nhạy và có các phím tắt dành cho gaming',N'images/Banphim/BAKKO3098s.jpg',3)
insert into products values(N'B8',N'Key Board AKKO 3108 ',35,'2200000',
'2023-12-07',N'Bàn phím gõ cực êm và nhạy và có các phím tắt dành cho gaming',N'images/Banphim/BAKKO3108.jpg',3)
insert into products values(N'B9',N'Key Board AKKO 3180 ',20,'2500000',
'2024-01-07',N'Bàn phím gõ cực êm và nhạy và có các phím tắt dành cho gaming',N'images/Banphim/BAKKO3180.jpg',3)

insert into registration values('tuong','1234','nguyen',1)
insert into registration values('thao','1234','pham',0)
insert into registration values('miu','1234','pham',0)
insert into registration values('xuxu','1234','pham',0)
insert into registration values('tai','1234','pham',0)
insert into registration values('loc','1234','pham',0)
insert into registration values('hieu','1234','pham',0)
insert into registration values('hiep','1234','pham',0)
insert into registration values('nhung','1234','pham',0)
insert into registration values('nau','1234','pham',0)
insert into registration values('phi','1234','pham',0)
insert into registration values('thinh','1234','pham',0)
insert into registration values('quan','1234','pham',0)
insert into registration values('duyen','1234','pham',0)
insert into registration values('kim','1234','pham',0)
insert into registration values('thuy','1234','pham',0)
insert into registration values('tra','1234','pham',0)
insert into registration values('liem','1234','pham',0)
select * from orders
select * from cart
select * from orderdetails
select * from products
select * from [view]
select * from registration
