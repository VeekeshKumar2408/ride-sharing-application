INSERT INTO app_user (name, email, password) VALUES
('Aarav Sharma', 'aarav.sharma@example.com', 'aarav@123'),
('Vivaan Mehta', 'vivaan.mehta@example.com', 'vivaan@456'),
('Diya Verma', 'diya.verma@example.com', 'diyaPass1'),
('Ananya Iyer', 'ananya.iyer@example.com', 'ananya#2025'),
('Krishna Rao', 'krishna.rao@example.com', 'krish@pass'),
('Ishaan Kapoor', 'ishaan.kapoor@example.com', 'ishaanPW'),
('Sneha Nair', 'sneha.nair@example.com', 'sneha123'),
('Rohan Das', 'rohan.das@example.com', 'rohandas88'),
('Pooja Reddy', 'pooja.reddy@example.com', 'pooja@reddy'),
('Kunal Malhotra', 'kunal.malhotra@example.com', 'kunal2025'),
('Meera Joshi', 'meera.joshi@example.com', 'meeraSecure'),
('Aditya Singh', 'aditya.singh@example.com', 'adi@321'),
('Priya Desai', 'priya.desai@example.com', 'priya456'),
('Arjun Chauhan', 'arjun.chauhan@example.com', 'arjunchau@1'),
('Tanya Bhatt', 'tanya.bhatt@example.com', 'tanya_2025'),
('Rahul Bansal', 'rahul.bansal@example.com', 'rahul@ban$'),
('Kritika Sen', 'kritika.sen@example.com', 'kritika12'),
('Manav Gupta', 'manav.gupta@example.com', 'manavG!23'),
('Neha Pillai', 'neha.pillai@example.com', 'nehaPillai'),
('Siddharth Patil', 'siddharth.patil@example.com', 'siddh@rth');

INSERT INTO user_roles (user_id, roles) VALUES
(1, 'RIDER'),
(2, 'DRIVER'),
(3, 'DRIVER'),
(3, 'RIDER'),
(4, 'RIDER'),
(5, 'DRIVER'),
(6, 'DRIVER'),
(6, 'RIDER'),
(7, 'RIDER'),
(8, 'DRIVER'),
(9, 'DRIVER'),
(9, 'RIDER'),
(10, 'RIDER'),
(11, 'DRIVER'),
(11, 'RIDER'),
(12, 'DRIVER'),
(13, 'RIDER'),
(14, 'DRIVER'),
(14, 'RIDER'),
(15, 'DRIVER'),
(16, 'RIDER'),
(17, 'DRIVER'),
(17, 'RIDER'),
(18, 'DRIVER'),
(19, 'RIDER'),
(20, 'DRIVER'),
(20, 'RIDER');

INSERT INTO rider (user_id, rating) VALUES
(1, 1, 4.9);

INSERT INTO driver (id, user_id, rating, available, vehicle_id, current_location) VALUES
(2, 2, 4.7, true, 101, ST_GeomFromText('POINT(76.8500 30.6942)', 4326)),
(3, 3, 4.5, false, 102, ST_GeomFromText('POINT(76.8512 30.6950)', 4326)),
(5, 5, 4.2, true, 103, ST_GeomFromText('POINT(76.8485 30.6930)', 4326)),
(6, 6, 4.6, true, 104, ST_GeomFromText('POINT(76.8498 30.6965)', 4326)),
(8, 8, 3.9, false, 105, ST_GeomFromText('POINT(76.8523 30.6921)', 4326)),
(9, 9, 4.3, true, 106, ST_GeomFromText('POINT(76.8467 30.6905)', 4326)),
(11, 11, 4.8, true, 107, ST_GeomFromText('POINT(76.8479 30.6973)', 4326)),
(12, 12, 3.8, false, 108, ST_GeomFromText('POINT(76.8531 30.6914)', 4326)),
(14, 14, 4.1, true, 109, ST_GeomFromText('POINT(76.8490 30.6899)', 4326)),
(15, 15, 4.4, true, 110, ST_GeomFromText('POINT(76.8517 30.6885)', 4326)),
(17, 17, 4.0, false, 111, ST_GeomFromText('POINT(76.8481 30.6947)', 4326)),
(18, 18, 4.6, true, 112, ST_GeomFromText('POINT(76.8508 30.6934)', 4326)),
(20, 20, 4.9, true, 113, ST_GeomFromText('POINT(76.8473 30.6959)', 4326));

INSERT INTO wallet (id, user_id, balance) VALUES
(1, 1, 100.0),
(2, 2, 500.0);