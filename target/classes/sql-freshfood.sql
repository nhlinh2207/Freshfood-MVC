INSERT INTO freshfood.tbl_category (Id, Description, Name, Status) VALUES (1, 'Cơm bình dân', 'Cơm bình dân', null);
INSERT INTO freshfood.tbl_category (Id, Description, Name, Status) VALUES (2, 'Bánh mì', 'Bánh mì', null);
INSERT INTO freshfood.tbl_category (Id, Description, Name, Status) VALUES (3, 'Đồ uống', 'Đồ uống', null);
INSERT INTO freshfood.tbl_category (Id, Description, Name, Status) VALUES (4, 'Hoa quả tươi', 'Hoa quả tươi', null);
INSERT INTO freshfood.tbl_category (Id, Description, Name, Status) VALUES (5, 'Đồ ăn nhẹ', 'Đồ ăn nhẹ', null);
INSERT INTO freshfood.tbl_category (Id, Description, Name, Status) VALUES (6, 'Sinh tố', 'Sinh tố', null);
INSERT INTO freshfood.tbl_category (Id, Description, Name, Status) VALUES (7, 'Hải sản ', 'Hải sản', null);
INSERT INTO freshfood.tbl_category (Id, Description, Name, Status) VALUES (8, 'Mỳ và phở', 'Mỳ và phở', null);

INSERT INTO freshfood.tbl_country (Id, Name) VALUES (1, 'Việt Nam');
INSERT INTO freshfood.tbl_country (Id, Name) VALUES (2, 'Nhật Bản');
INSERT INTO freshfood.tbl_country (Id, Name) VALUES (3, 'Thái Lan');

INSERT INTO freshfood.tbl_city (Id, Name, CountryId) VALUES (1, 'Hà Nội', 1);
INSERT INTO freshfood.tbl_city (Id, Name, CountryId) VALUES (2, 'Tp Hồ Chí Minh', 1);
INSERT INTO freshfood.tbl_city (Id, Name, CountryId) VALUES (3, 'Đà Nẵng', 1);
INSERT INTO freshfood.tbl_city (Id, Name, CountryId) VALUES (4, 'Tokyo', 2);
INSERT INTO freshfood.tbl_city (Id, Name, CountryId) VALUES (5, 'Hiroshima', 2);
INSERT INTO freshfood.tbl_city (Id, Name, CountryId) VALUES (6, 'Nagasaki', 2);

INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (3, null, '2023-03-25 22:21:44', null, null, 'rau-56x56.jpg', 'rau-335x315.jpg', 'rau-260x200.jpg', null, 'Rau xanh', 15000, 100, null, 5);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (6, null, '2023-03-25 22:27:39', null, null, 'Chuoi-56x56.jpg', 'Chuoi-335x315.jpg', 'Chuoi-260x200.jpg', null, 'Chuối ', 15000, 100, null, 5);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (7, null, '2023-03-25 22:28:16', null, null, 'chanh-56x56.jpg', 'chanh-335x315.jpg', 'chanh-260x200.jpg', null, 'Chanh vàng', 15000, 100, null, 5);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (8, null, '2023-03-25 22:30:28', null, null, 'man-56x56.jpg', 'man-335x315.jpg', 'man-260x200.jpg', null, 'Mận', 15000, 100, null, 5);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (9, null, '2023-03-25 22:31:52', null, null, 'com-ga-cay-56x56.jpg', 'com-ga-cay-335x315.jpg', 'com-ga-cay-260x200.jpg', null, 'Cơm gà sốt cay', 15000, 100, null, 1);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (10, null, '2023-03-25 22:34:52', null, null, 'bun-cha-56x56.jpg', 'bun-cha-335x315.jpg', 'bun-cha-260x200.jpg', null, 'Bún chả', 15000, 100, null, 1);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (11, null, '2023-03-25 22:35:48', null, null, 'com-suon-56x56.jpg', 'com-suon-335x315.jpg', 'com-suon-260x200.jpg', null, 'Cơm sườn', 15000, 100, null, 1);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (12, null, '2023-03-25 22:36:26', null, null, 'com-tam-56x56.jpg', 'com-tam-335x315.jpg', 'com-tam-260x200.jpg', null, 'Cơm tấm', 15000, 100, null, 1);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (13, null, '2023-03-25 22:36:57', null, null, 'com-rang-56x56.jpg', 'com-rang-335x315.jpg', 'com-rang-260x200.jpg', null, 'Cơm rang', 15000, 100, null, 1);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (14, null, '2023-03-25 22:37:37', null, null, 'bun-bo-hue-56x56.jpg', 'bun-bo-hue-335x315.jpg', 'bun-bo-hue-260x200.jpg', null, 'Bún bò Huế', 15000, 100, null, 1);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (15, null, '2023-03-25 22:43:46', null, null, 'banh-mi-trung-335x315.jpg', 'banh-mi-trung-335x315.jpg', 'banh-mi-trung-260x200.jpg', null, 'Bánh mì trứng', 15000, 100, null, 2);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (16, null, '2023-03-25 22:44:48', null, null, 'banh-mi-pate-56x56.jpg', 'banh-mi-pate-335x315.jpg', 'banh-mi-pate-260x200.jpg', null, 'Bánh mì pate', 15000, 100, null, 2);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (17, null, '2023-03-25 22:45:21', null, null, 'pizza-56x56.jpg', 'pizza-335x315.jpg', 'pizza-260x200.jpg', null, 'Pizza', 15000, 100, null, 2);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (18, null, '2023-03-25 22:46:05', null, null, 'donut-56x56.jpg', 'donut-335x315.jpg', 'donut-260x200.jpg', null, 'Donut', 15000, 100, null, 2);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (19, null, '2023-03-25 22:46:42', null, null, 'hotdog-56x56.jpg', 'hotdog-335x315.jpg', 'hotdog-260x200.jpg', null, 'Hotdog', 15000, 100, null, 2);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (20, null, '2023-03-25 22:47:17', null, null, 'hamburger-56x56.jpg', 'hamburger-335x315.jpg', 'hamburger-260x200.jpg', null, 'Hamburger', 15000, 100, null, 2);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (21, null, '2023-03-25 22:47:47', null, null, 'bia-56x56.jpg', 'bia-335x315.jpg', 'bia-260x200.jpg', null, 'Bia 333', 15000, 100, null, 3);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (22, null, '2023-03-25 22:48:17', null, null, 'pepsi-56x56.jpg', 'pepsi-335x315.jpg', 'pepsi-260x200.jpg', null, 'Pepsi', 15000, 100, null, 3);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (23, null, '2023-03-25 22:49:45', null, null, 'coca-56x56.jpg', 'coca-335x315.jpg', 'coca-260x200.jpg', null, 'Cocacola', 15000, 100, null, 3);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (24, null, '2023-03-25 22:51:29', null, null, 'cam-ep-56x56.jpg', 'cam-ep-335x315.jpg', 'cam-ep-260x200.jpg', null, 'Nước cam ép', 15000, 100, null, 3);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (25, null, '2023-03-25 22:52:19', null, null, 'sevenup-56x56.jpg', 'sevenup-335x315.jpg', 'sevenup-260x200.jpg', null, '7Up', 15000, 100, null, 3);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (26, null, '2023-03-25 22:52:51', null, null, 'fanta-56x56.jpg', 'fanta-335x315.jpg', 'fanta-260x200.jpg', null, 'Fanta', 15000, 100, null, 3);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (27, null, '2023-03-25 22:53:45', null, null, 'berry-56x56.jpg', 'berry-335x315.jpg', 'berry-260x200.jpg', null, 'Mâm xôi', 15000, 100, null, 4);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (28, null, '2023-03-25 22:54:10', null, null, 'Taodo-56x56.jpg', 'Taodo-335x315.jpg', 'Taodo-260x200.jpg', null, 'Táo đỏ', 15000, 100, null, 4);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (29, null, '2023-03-25 22:54:48', null, null, 'chanh-56x56.jpg', 'chanh-335x315.jpg', 'chanh-260x200.jpg', null, 'Chanh vàng', 15000, 100, null, 4);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (30, null, '2023-03-25 22:55:25', null, null, 'Bo-56x56.jpg', 'Bo-335x315.jpg', 'Bo-260x200.jpg', null, 'Bơ', 15000, 100, null, 4);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (31, null, '2023-03-25 22:56:18', null, null, 'product-5-56x56.jpg', 'product-5-335x315.jpg', 'Tomato-260x200.jpg', null, 'Cà chua', 15000, 100, null, 4);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (32, null, '2023-03-25 22:59:13', null, null, 'Chuoi-56x56.jpg', 'Dautay-240x240.jpg', 'Dautay-260x200.jpg', null, 'Dâu tây', 15000, 100, null, 4);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (33, null, '2023-03-25 23:00:13', null, null, 'Bơ-56x56.jpg', 'Bơ-335x315.jpg', 'blueberry-260x200.jpg', null, 'Blueberry', 15000, 100, null, 6);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (34, null, '2023-03-25 23:00:47', null, null, 'kiwi-56x56.jpg', 'kiwi-335x315.jpg', 'kiwi-260x200.jpg', null, 'Kiwi xanh', 15000, 100, null, 6);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (35, null, '2023-03-25 23:01:16', null, null, 'Cam-56x56.jpg', 'Cam-335x315.jpg', 'Cam-260x200.jpg', null, 'Cam Mỹ', 15000, 100, null, 6);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (36, null, '2023-03-25 23:01:53', null, null, 'Chuoi-56x56.jpg', 'Chuoi-335x315.jpg', 'Chuoi-260x200.jpg', null, 'Chuối ', 15000, 100, null, 6);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (37, null, '2023-03-25 23:02:24', null, null, 'chanh-56x56.jpg', 'chanh-335x315.jpg', 'chanh-260x200.jpg', null, 'Chanh vàng', 15000, 100, null, 6);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (38, null, '2023-03-25 23:03:00', null, null, 'Taoxanh-335x315.jpg', 'Taoxanh-335x315.jpg', 'Taoxanh-260x200.jpg', null, 'Lê xanh', 15000, 100, null, 6);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (39, null, '2023-03-25 23:05:15', null, null, 'berry-56x56.jpg', 'berry-335x315.jpg', 'berry-260x200.jpg', null, 'Mâm xôi', 15000, 100, null, 7);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (40, null, '2023-03-25 23:08:50', null, null, 'Taoxanh-56x56.jpg', 'Taoxanh-335x315.jpg', 'Taoxanh-260x200.jpg', null, 'Lê xanh', 15000, 100, null, 7);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (41, null, '2023-03-25 23:09:36', null, null, 'rau-56x56.jpg', 'rau-335x315.jpg', 'Nam-260x200.jpg', null, 'Nấm', 15000, 100, null, 7);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (42, null, '2023-03-25 23:10:19', null, null, 'Bo-56x56.jpg', 'Bo-335x315.jpg', 'Bo-260x200.jpg', null, 'Bơ', 15000, 100, null, 7);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (43, null, '2023-03-25 23:11:09', null, null, 'Dao-56x56.jpg', 'Dao-335x315.jpg', 'Dao-260x200.jpg', null, 'Đào', 15000, 100, null, 7);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (44, null, '2023-03-25 23:16:09', null, null, 'Bo-56x56.jpg', 'Bo-335x315.jpg', 'Dualeo-260x200.jpg', null, 'Dưa chuột', 15000, 100, null, 7);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (45, null, '2023-03-25 23:17:21', null, null, 'Bo-56x56.jpg', 'Bo-335x315.jpg', 'Bo-260x200.jpg', null, 'Bơ', 15000, 100, null, 8);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (46, null, '2023-03-25 23:18:16', null, null, 'berry-56x56.jpg', 'berry-335x315.jpg', 'berry-260x200.jpg', null, 'Mâm xôi', 15000, 100, null, 8);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (47, null, '2023-03-25 23:20:24', null, null, 'Taodo-56x56.jpg', 'Taodo-335x315.jpg', 'Taodo-260x200.jpg', null, 'Táo đỏ', 15000, 100, null, 8);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (48, null, '2023-03-25 23:24:01', null, null, 'chanh-56x56.jpg', 'chanh-335x315.jpg', 'chanh-260x200.jpg', null, 'Chanh vàng', 15000, 100, null, 8);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (49, null, '2023-03-25 23:24:30', null, null, 'Bo-56x56.jpg', 'Bo-335x315.jpg', 'Bo-260x200.jpg', null, 'Bơ', 15000, 100, null, 8);
INSERT INTO freshfood.tbl_product (Id, BuyingCount, CreateTime, Description, Discount, ExtraImage1, ExtraImage2, Image, modifiedTme, Name, Price, Quantity, Status, CategoryId) VALUES (50, null, '2023-03-25 23:25:09', null, null, 'Bo-56x56.jpg', 'Bo-335x315.jpg', 'Tomato-260x200.jpg', null, 'Cà chua', 15000, 100, null, 8);

INSERT INTO freshfood.tbl_role (id, name) VALUES (1, 'ADMIN');
INSERT INTO freshfood.tbl_role (id, name) VALUES (2, 'USER');
INSERT INTO freshfood.tbl_role (id, name) VALUES (3, 'STAFF');

INSERT INTO freshfood.tbl_user (Id, Age, authprovider, CreateTime, Email, FirstName, FullName, Gender, LastName, MiddleName, Password, PhoneNumber, Status, Type) VALUES (1, 20, null, '2023-03-11 17:27:16', 'linh0001@gmail.com', 'Nguyễn', 'Nguyễn Hoài Linh', 'Nam', 'Linh', 'Hoài', '$2a$10$wBEgkLkGaI8z6hKRjLmKo.oXEk3dyfjMTzQhtO3mUoNyvZgfyvlN.', '0123456789', 'ACTIVE', 'ADMIN');
INSERT INTO freshfood.tbl_user (Id, Age, authprovider, CreateTime, Email, FirstName, FullName, Gender, LastName, MiddleName, Password, PhoneNumber, Status, Type) VALUES (4, 20, null, '2023-04-05 16:36:51', 'nvthai123@gmail.com', 'Nguyễn', 'Nguyễn Văn  Thái', 'Nam', 'Thái', 'Văn ', '$2a$10$vR/pVEFeTlohwFo7P3e.JOagh07JvXj1OZm.LpAyfOjHWzhi0mUX.', '0123456789', 'FREE', 'STAFF');
INSERT INTO freshfood.tbl_user (Id, Age, authprovider, CreateTime, Email, FirstName, FullName, Gender, LastName, MiddleName, Password, PhoneNumber, Status, Type) VALUES (5, 20, null, '2023-04-06 14:53:12', 'account@gmail.com', 'Nguyễn', 'Nguyễn Ngọc Minh', 'Nam', 'Minh', 'Ngọc', '$2a$10$eIveqgGxwQVh8bXRiNbnDOOHHqcoB.yYF1q3FmA76H7KoontMHr6e', '0123456789', 'ACTIVE', null);

INSERT INTO freshfood.tbl_cart (Id, DeliverTime, OrderTime, PaymentType, ReceiverName, ReceiverPhoneNumber, Status, TotalPrice, UserId, ReceiverEmail, StaffId) VALUES (1, null, '2023-03-25 13:45:29', null, 'Nguyễn Văn A', '0372983207', 'UNSENT', 30000, 1, null, null);
INSERT INTO freshfood.tbl_cart (Id, DeliverTime, OrderTime, PaymentType, ReceiverName, ReceiverPhoneNumber, Status, TotalPrice, UserId, ReceiverEmail, StaffId) VALUES (2, '2023-04-06 10:02:10', '2023-04-05 15:38:14', null, 'Nguyễn Văn A', '0372983207', 'SENT', 30000, 1, null, 4);
INSERT INTO freshfood.tbl_cart (Id, DeliverTime, OrderTime, PaymentType, ReceiverName, ReceiverPhoneNumber, Status, TotalPrice, UserId, ReceiverEmail, StaffId) VALUES (3, '2023-04-06 09:02:47', '2023-03-30 22:35:25', null, 'Nguyễn Văn A', '0372983207', 'SENT', 30000, null, null, 4);
INSERT INTO freshfood.tbl_cart (Id, DeliverTime, OrderTime, PaymentType, ReceiverName, ReceiverPhoneNumber, Status, TotalPrice, UserId, ReceiverEmail, StaffId) VALUES (4, null, '2023-03-30 22:36:17', null, 'Nguyễn Văn A', '0372983207', 'UNSENT', 30000, null, null, 4);
INSERT INTO freshfood.tbl_cart (Id, DeliverTime, OrderTime, PaymentType, ReceiverName, ReceiverPhoneNumber, Status, TotalPrice, UserId, ReceiverEmail, StaffId) VALUES (5, '2023-04-05 23:50:20', '2023-03-30 22:42:11', null, 'Nguyễn Văn A', '0372983207', 'SENT', 30000, null, null, 4);
INSERT INTO freshfood.tbl_cart (Id, DeliverTime, OrderTime, PaymentType, ReceiverName, ReceiverPhoneNumber, Status, TotalPrice, UserId, ReceiverEmail, StaffId) VALUES (8, '2023-04-05 23:39:33', '2023-04-05 16:05:10', null, 'Nguyễn Văn A', '0372983207', 'SENT', 105000, 1, null, 4);
INSERT INTO freshfood.tbl_cart (Id, DeliverTime, OrderTime, PaymentType, ReceiverName, ReceiverPhoneNumber, Status, TotalPrice, UserId, ReceiverEmail, StaffId) VALUES (9, null, null, null, null, null, 'UNSENT', null, 1, null, null);
INSERT INTO freshfood.tbl_cart (Id, DeliverTime, OrderTime, PaymentType, ReceiverName, ReceiverPhoneNumber, Status, TotalPrice, UserId, ReceiverEmail, StaffId) VALUES (10, null, null, null, null, null, 'UNSENT', null, 4, null, null);
INSERT INTO freshfood.tbl_cart (Id, DeliverTime, OrderTime, PaymentType, ReceiverName, ReceiverPhoneNumber, Status, TotalPrice, UserId, ReceiverEmail, StaffId) VALUES (11, null, '2023-04-06 10:39:43', null, 'Nguyễn Văn A', '0372983207', 'UNSENT', 30000, null, null, null);
INSERT INTO freshfood.tbl_cart (Id, DeliverTime, OrderTime, PaymentType, ReceiverName, ReceiverPhoneNumber, Status, TotalPrice, UserId, ReceiverEmail, StaffId) VALUES (12, '2023-04-06 15:00:28', '2023-04-06 14:59:04', null, 'Nguyễn Văn A', '0372983207', 'SENT', 75000, 5, null, 4);
INSERT INTO freshfood.tbl_cart (Id, DeliverTime, OrderTime, PaymentType, ReceiverName, ReceiverPhoneNumber, Status, TotalPrice, UserId, ReceiverEmail, StaffId) VALUES (13, null, null, null, null, null, 'UNSENT', null, 5, null, null);

INSERT INTO freshfood.tbl_cart_item (Id, ModifiedTime, OrderTime, Quantity, TotalPrice, CartId, ProductId) VALUES (5, null, null, 2, 30000, 3, 22);
INSERT INTO freshfood.tbl_cart_item (Id, ModifiedTime, OrderTime, Quantity, TotalPrice, CartId, ProductId) VALUES (6, null, null, 2, 30000, 4, 22);
INSERT INTO freshfood.tbl_cart_item (Id, ModifiedTime, OrderTime, Quantity, TotalPrice, CartId, ProductId) VALUES (7, null, null, 3, 30000, 5, 10);
INSERT INTO freshfood.tbl_cart_item (Id, ModifiedTime, OrderTime, Quantity, TotalPrice, CartId, ProductId) VALUES (8, null, '2023-04-05 15:37:49', 2, 30000, 2, 12);
INSERT INTO freshfood.tbl_cart_item (Id, ModifiedTime, OrderTime, Quantity, TotalPrice, CartId, ProductId) VALUES (9, null, '2023-04-05 16:04:28', 2, 30000, 8, 17);
INSERT INTO freshfood.tbl_cart_item (Id, ModifiedTime, OrderTime, Quantity, TotalPrice, CartId, ProductId) VALUES (10, null, '2023-04-05 16:04:39', 5, 75000, 8, 18);
INSERT INTO freshfood.tbl_cart_item (Id, ModifiedTime, OrderTime, Quantity, TotalPrice, CartId, ProductId) VALUES (11, null, '2023-04-05 23:03:42', 2, 30000, 9, 10);
INSERT INTO freshfood.tbl_cart_item (Id, ModifiedTime, OrderTime, Quantity, TotalPrice, CartId, ProductId) VALUES (12, null, '2023-04-05 23:03:47', 2, 30000, 9, 11);
INSERT INTO freshfood.tbl_cart_item (Id, ModifiedTime, OrderTime, Quantity, TotalPrice, CartId, ProductId) VALUES (13, null, null, 2, null, 11, 6);
INSERT INTO freshfood.tbl_cart_item (Id, ModifiedTime, OrderTime, Quantity, TotalPrice, CartId, ProductId) VALUES (14, null, '2023-04-06 14:54:32', 5, 75000, 12, 12);

INSERT INTO freshfood.tbl_address (Id, CityId, Commune, CCountryId, CreateDate, District, FullAddress, ModifiedTime, Type, CartId, UserId) VALUES (1, 1, null, 1, '2023-03-11 17:27:16', null, 'Thanh Liệt - Thanh Trì - Hà Nội', null, 'RESIDENT ADDRESS', null, 1);
INSERT INTO freshfood.tbl_address (Id, CityId, Commune, CCountryId, CreateDate, District, FullAddress, ModifiedTime, Type, CartId, UserId) VALUES (2, 1, null, 1, '2023-03-25 13:45:29', null, 'Nhà số 3', null, 'DELIVERY ADDRESS', 1, null);
INSERT INTO freshfood.tbl_address (Id, CityId, Commune, CCountryId, CreateDate, District, FullAddress, ModifiedTime, Type, CartId, UserId) VALUES (3, 1, null, 1, '2023-03-30 22:35:25', null, 'Nhà số 3', null, 'DELIVERY ADDRESS', 3, null);
INSERT INTO freshfood.tbl_address (Id, CityId, Commune, CCountryId, CreateDate, District, FullAddress, ModifiedTime, Type, CartId, UserId) VALUES (4, 1, null, 1, '2023-03-30 22:36:17', null, 'Nhà số 3', null, 'DELIVERY ADDRESS', 4, null);
INSERT INTO freshfood.tbl_address (Id, CityId, Commune, CCountryId, CreateDate, District, FullAddress, ModifiedTime, Type, CartId, UserId) VALUES (5, 1, null, 1, '2023-03-30 22:42:11', null, 'Nhà số 3', null, 'DELIVERY ADDRESS', 5, null);
INSERT INTO freshfood.tbl_address (Id, CityId, Commune, CCountryId, CreateDate, District, FullAddress, ModifiedTime, Type, CartId, UserId) VALUES (8, 1, null, 1, '2023-04-05 15:38:14', null, 'Nhà số 3', null, 'DELIVERY ADDRESS', 2, null);
INSERT INTO freshfood.tbl_address (Id, CityId, Commune, CCountryId, CreateDate, District, FullAddress, ModifiedTime, Type, CartId, UserId) VALUES (9, 1, null, 1, '2023-04-05 16:05:10', null, 'Nhà số 3', null, 'DELIVERY ADDRESS', 8, null);
INSERT INTO freshfood.tbl_address (Id, CityId, Commune, CCountryId, CreateDate, District, FullAddress, ModifiedTime, Type, CartId, UserId) VALUES (10, 1, null, 1, '2023-04-05 16:36:51', null, 'Thanh Liệt - Thanh Trì - Hà Nội', null, 'RESIDENT ADDRESS', null, 4);
INSERT INTO freshfood.tbl_address (Id, CityId, Commune, CCountryId, CreateDate, District, FullAddress, ModifiedTime, Type, CartId, UserId) VALUES (11, 1, null, 1, '2023-04-06 10:39:43', null, 'Nhà số 3', null, 'DELIVERY ADDRESS', 11, null);
INSERT INTO freshfood.tbl_address (Id, CityId, Commune, CCountryId, CreateDate, District, FullAddress, ModifiedTime, Type, CartId, UserId) VALUES (12, 1, null, 1, '2023-04-06 14:53:12', null, 'Thanh Liệt - Thanh Trì - Hà Nội', null, 'RESIDENT ADDRESS', null, 5);
INSERT INTO freshfood.tbl_address (Id, CityId, Commune, CCountryId, CreateDate, District, FullAddress, ModifiedTime, Type, CartId, UserId) VALUES (13, 1, null, 1, '2023-04-06 14:59:04', null, 'Nhà số 3', null, 'DELIVERY ADDRESS', 12, null);

INSERT INTO freshfood.tbl_message (id, CartId, Content, CreateTime, ModifiedTime, Status, Title, UserId) VALUES (1, 8, 'Đơn hàng 8 đã được giao thành công.', '2023-04-05 23:39:33', null, 'UNSEEN', 'Giao hàng thành công', 1);
INSERT INTO freshfood.tbl_message (id, CartId, Content, CreateTime, ModifiedTime, Status, Title, UserId) VALUES (2, 5, 'Đơn hàng 5 đã được giao thành công.', '2023-04-05 23:50:20', null, 'UNSEEN', 'Giao hàng thành công', null);
INSERT INTO freshfood.tbl_message (id, CartId, Content, CreateTime, ModifiedTime, Status, Title, UserId) VALUES (3, 3, 'Đơn hàng 3 đã được giao thành công.', '2023-04-06 09:02:47', null, 'UNSEEN', 'Giao hàng thành công', null);
INSERT INTO freshfood.tbl_message (id, CartId, Content, CreateTime, ModifiedTime, Status, Title, UserId) VALUES (4, 2, 'Đơn hàng 2 đã được giao thành công.', '2023-04-06 10:02:10', null, 'UNSEEN', 'Giao hàng thành công', 1);
INSERT INTO freshfood.tbl_message (id, CartId, Content, CreateTime, ModifiedTime, Status, Title, UserId) VALUES (5, 12, 'Đơn hàng 12 đã được giao thành công.', '2023-04-06 15:00:28', null, 'UNSEEN', 'Giao hàng thành công', 5);

INSERT INTO freshfood.tbl_user_role (UserId, RoleId) VALUES (1, 1);
INSERT INTO freshfood.tbl_user_role (UserId, RoleId) VALUES (4, 3);
INSERT INTO freshfood.tbl_user_role (UserId, RoleId) VALUES (5, 2);
