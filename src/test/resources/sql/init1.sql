INSERT INTO dictionaries.street ("city", "street") VALUES
('Волгоград','Мира'),
('Волгоград','Симонова'),
('Волгоград','Краснополянская');

INSERT INTO tables.customer ("id", "name", "email", "customer_type_id", "legal_type_id", "comment") VALUES
('d8ea1825-31b7-4924-9eec-be26e5d76b9d', 'Артём', 'artyomnamira@yandex.ru', '7658f0b5-8cf9-4934-90c4-162117e3d423', 'ad0725ea-afd7-4311-91eb-711d8377ae17', 'Волг просп'),
('6534b7c7-7b97-4248-8a05-7c89d4e1a9d6', 'Анна', 'annasimon@mail.ru', '7658f0b5-8cf9-4934-90c4-162117e3d423', '2dc3e2f8-6d0c-43e7-8ca2-118189986ff5', 'Мама'),
('69b94c75-0fed-4bcd-b2f6-b53cdeca2ae9', 'Сергей', 'seregakras@mail.ru', '7658f0b5-8cf9-4934-90c4-162117e3d423', 'ad0725ea-afd7-4311-91eb-711d8377ae17', 'Витя ходил');

INSERT INTO tables.user ("id", "name", "email", "password") VALUES
('09ce9ab2-b36d-4b5c-9ff1-7d018931e993', 'Александр Масловский', 'maslovskiy@mail.ru', 'vfckjdcrbq'),
('23aa416d-4d25-4c3d-8920-78637279bc35', 'Андрей Корнев', 'kornev@mail.ru', 'rjhytd'),
('7d2241b0-c814-4748-9efa-c7a5824380e4', 'Данил Суханов', 'sukhanov@mail.ru', 'cerfyjd');

INSERT INTO tables.order ("id", "customer_id", "problem_description", "user_id") VALUES
('c48b1b74-0ffd-419e-805e-6ca40aae28a9', 'd8ea1825-31b7-4924-9eec-be26e5d76b9d', 'загружается не с того диска', '7d2241b0-c814-4748-9efa-c7a5824380e4'),
('d388b1a2-0738-45f5-9955-c9016e18ea36', '6534b7c7-7b97-4248-8a05-7c89d4e1a9d6', 'роутер, антивирус', '23aa416d-4d25-4c3d-8920-78637279bc35'),
('202cf655-ba2c-42a4-8655-e7935597367a', '69b94c75-0fed-4bcd-b2f6-b53cdeca2ae9', 'проблема с сетью', '09ce9ab2-b36d-4b5c-9ff1-7d018931e993');

INSERT INTO tables.order_status ("id", "order_id", "status_type_id", "user_id") VALUES
('202cf655-ba2c-42a4-8655-e7935597367a', 'c48b1b74-0ffd-419e-805e-6ca40aae28a9', 'ca8dab3c-8881-4ff3-889b-88b33ae3e0c5', '7d2241b0-c814-4748-9efa-c7a5824380e4'),
('d902492f-091d-4d10-91b4-ada28c716452', 'd388b1a2-0738-45f5-9955-c9016e18ea36', '4c5b5840-f453-47f5-9c78-9fd2b00a4094', '23aa416d-4d25-4c3d-8920-78637279bc35'),
('ccce421f-0b57-487f-bcbe-fedace5dabee', '202cf655-ba2c-42a4-8655-e7935597367a', 'c45cd9e1-2fa8-448f-a6bc-c5be40099f7a', 'c48b1b74-0ffd-419e-805e-6ca40aae28a9');

INSERT INTO tables.user_roles ("user_id", "role") VALUES
('09ce9ab2-b36d-4b5c-9ff1-7d018931e993', 'ADMIN'),
('09ce9ab2-b36d-4b5c-9ff1-7d018931e993', 'USER'),
('23aa416d-4d25-4c3d-8920-78637279bc35', 'USER'),
('7d2241b0-c814-4748-9efa-c7a5824380e4', 'USER');

INSERT INTO tables.address ("id", "customer_id", "city", "street", "building_number", "flat_number", "office", "comment") VALUES
('66426061-2192-4f6b-8436-20a9b3c8cce8', 'd8ea1825-31b7-4924-9eec-be26e5d76b9d', 'Волгоград','Мира', '19', '0', '0', 'офис'),
('c51fca2e-a010-4afb-9a11-4a65c29a7bb5', '6534b7c7-7b97-4248-8a05-7c89d4e1a9d6', 'Волгоград','Симонова', '28', '74', '0', 'квартира'),
('59c55ec6-ec51-4563-abc4-9407b7c1d683', '69b94c75-0fed-4bcd-b2f6-b53cdeca2ae9', 'Волгоград','Краснополянская', '23', '0', '0', 'транспортная контора');

INSERT INTO tables.contact ("id", "customer_id", "number", "contact_type_id") VALUES
('e8ae5e97-e48a-4e48-8e53-c1c9ffa9472a', 'd8ea1825-31b7-4924-9eec-be26e5d76b9d', '89023853450', '2fd86263-36e9-447a-a97e-b57a3223fd95'),
('b016d6aa-669b-4645-ac6f-a2523e348eec', '6534b7c7-7b97-4248-8a05-7c89d4e1a9d6', '89034683835', '8a7fb287-71d1-44ae-8168-2f6321c990ec'),
('734c76d8-6ecf-462f-a94d-c001edda553c', '69b94c75-0fed-4bcd-b2f6-b53cdeca2ae9', '89054828068', '2fd86263-36e9-447a-a97e-b57a3223fd95');

INSERT INTO tables.executed_work ("id", "order_id", "user_id", "name", "cost") VALUES
('fad88ef4-57cc-4c1d-9bf4-0c78cad3b4b8', 'c48b1b74-0ffd-419e-805e-6ca40aae28a9', '7d2241b0-c814-4748-9efa-c7a5824380e4', 'Переключение загрузки в биос', 300.00),
('c02aafa6-5aab-4433-ac8f-8ed8fe7b5b9d', '202cf655-ba2c-42a4-8655-e7935597367a', '09ce9ab2-b36d-4b5c-9ff1-7d018931e993', 'поменял канал', 400.00);
