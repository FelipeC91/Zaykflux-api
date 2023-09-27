-- This file allow to write SQL commands that will be emitted in test and dev.
-- This file allow to write SQL commands that will be emitted in test and dev.
INSERT INTO state (id, name) VALUES
    ('SP', 'São Paulo');

-------------------------------------------------
INSERT INTO city (id, name, state_id) VALUES
    ('8d5cd4a5-16c5-4d37-bedc-07fb0cce179f', 'São Paulo', 'SP'),
    ('85bcb789-ec5a-4cfb-815f-aa24727f6dbe', 'Carapicuípa', 'SP');


---------------------------------------
INSERT INTO customer (id, trading_name, company_name, active, cpf_cnpj, photo_url) VALUES
    ('4f653c10-cab3-40d8-8518-c160dbeb74c4', 'AÇAILAND', 'SIMPLES E NATURAL AÇAI LTDA', true, '11111111000130', null),
    ('265edc2e-650d-4fec-97ce-e8a9b253caeb', 'JANDAIA', 'AUTO POSTO JANDAIA', true, '22222222000139', null);
---------------------------------

INSERT INTO address (zip_code, neighborhood, street, complement, number, city_id, customer_id) VALUES
    ('03306010', 'Cidade Mãe do Céu', 'Rua Domingos Agostim',null, 91, '8d5cd4a5-16c5-4d37-bedc-07fb0cce179f', '4f653c10-cab3-40d8-8518-c160dbeb74c4'),
    ('06330010', 'Parque Jandaia', 'Avenida Marginal do Ribeirão',null, 4836, '85bcb789-ec5a-4cfb-815f-aa24727f6dbe', '265edc2e-650d-4fec-97ce-e8a9b253caeb');
--------------------------------------------
INSERT INTO contact (customer_id, name, email, phone, contact_type) VALUES
    ('4f653c10-cab3-40d8-8518-c160dbeb74c4', 'Teste', 'test@test.com', '11911112222', 'PESSOAL');

--------------------------------------------------------------------------
INSERT INTO group_model (id, name) VALUES
	('1cc8c16f-1194-4aab-9ad1-eb3127528af3', 'Administrador'),
	('7774dddf-f1ce-4747-83c2-40bb262b80ab', 'Financeiro'),
	('dc52b4d3-dc7a-4668-996f-1d8dc0cab715', 'Intermediário'),
	('3d6d7402-ef3f-4d80-98e5-58a5c59b1928', 'Técnico');
--------------------------------------------

INSERT INTO service_desk (id, name, description) VALUES
	('744daba3-a502-4cc0-b323-740c789a0b28', 'Remoto', 'Dedicado ao atendimento e análise preliminar do problema'),
	('3039802e-f12d-4f45-9fc0-a80f7b5aec43', 'Presencial', 'Dedicado a visitas presenciais'),
	('b9458763-649c-41b4-962d-6f991542505a', 'Financeiro', 'Dedicado a finalizaçao e cobrança dos tickets'),
	('27532144-007c-4d92-ae7b-419626ab29c0', 'Interno', 'Dedicado ao tratamento de chamados Internos'),
	('55f55ba5-9c67-48d1-b8e8-dd60705c98dd', 'AMPM', 'Dedicado ao tratamento de tickets AMPM');


INSERT INTO service_catalog (id, name) VALUES
	(1, 'Serviços de suporte de software'),
	(2, 'Serviços de helpdesk'),
	(3, 'Suporte a infraestrutura');


INSERT INTO service_desk_service_catalog (service_desk_id, service_catalog_id) VALUES
    ('744daba3-a502-4cc0-b323-740c789a0b28', 1),
    ('744daba3-a502-4cc0-b323-740c789a0b28', 2),
    ('744daba3-a502-4cc0-b323-740c789a0b28', 3),
    ('3039802e-f12d-4f45-9fc0-a80f7b5aec43', 1),
    ('3039802e-f12d-4f45-9fc0-a80f7b5aec43', 2),
    ('3039802e-f12d-4f45-9fc0-a80f7b5aec43', 3);

INSERT INTO catalog_area (id, name, service_catalog_id) VALUES
    (9, 'Computadores e periféricos',2);


INSERT INTO catalog_item (id, name, catalog_area_id) VALUES
    (26, 'Manutenção preventiva',9);

----------------------------------------------
INSERT INTO customer_service_desk (customer_id, service_desk_id) VALUES
        ('4f653c10-cab3-40d8-8518-c160dbeb74c4', '744daba3-a502-4cc0-b323-740c789a0b28'),
        ('4f653c10-cab3-40d8-8518-c160dbeb74c4', '3039802e-f12d-4f45-9fc0-a80f7b5aec43'),
        ('4f653c10-cab3-40d8-8518-c160dbeb74c4', 'b9458763-649c-41b4-962d-6f991542505a'),
        ('4f653c10-cab3-40d8-8518-c160dbeb74c4', '27532144-007c-4d92-ae7b-419626ab29c0'),
        ('4f653c10-cab3-40d8-8518-c160dbeb74c4', '55f55ba5-9c67-48d1-b8e8-dd60705c98dd');

--------------------------------------------

INSERT INTO user_model (id, name, email, password, active, registration_date) VALUES
        ('a257fdad-672f-49bb-b6cd-1effe7b678a4', 'Heitor', 'heitor.barreto@teste2.com.br','123',  true, '2023-07-19T14:16:10.124395900-03:00'),
        ('2665c568-abf6-458e-8801-658fa16ae552', 'Felipe', 'felipe.suporte@teste1.com.br','123',  true, '2023-10-19T14:16:10.124395900-03:00');

----------------------------------------------

 INSERT INTO ticket(id, number, status, title, description, priority, opened_at, updated_at, closed_at, parent_ticket_id, service_desk_id, customer_id, user_model_id) VALUES
        ('5249812b-e8f9-480d-a8db-2ded686dee6b', 1, 'ABERTO', 'LOJA AÇAILAND', E'Prezados, boa tarde! Tudo bem?\nSolicito orçamento para:\n-Visita técnica;\n-Upgrade de Hardware nos PDVs (Equipamentos AMPM);\n-Organização do Rack de TI. Preciso do Orçamento com urgência. Loja Gioconda.', 'ALTA', '2023-07-19T14:16:10.124395900-03:00', null, null, null, '55f55ba5-9c67-48d1-b8e8-dd60705c98dd', '4f653c10-cab3-40d8-8518-c160dbeb74c4', 'a257fdad-672f-49bb-b6cd-1effe7b678a4'),
        ('309c97b5-2fea-4f73-8244-9a7c3de8edff', 2, 'ABERTO', 'POSTO JANDAIA', E'uma câmera da loja  não está funcionando precisamos da manutenção da mesma . ',  'BAIXA','2023-05-19T14:16:10.124395900-03:00', '2023-07-19T14:16:00.124395900-03:00', null, null, '55f55ba5-9c67-48d1-b8e8-dd60705c98dd', '265edc2e-650d-4fec-97ce-e8a9b253caeb', 'a257fdad-672f-49bb-b6cd-1effe7b678a4');
-------------------------------------------------------------

INSERT INTO appointment(ticket_id, appointment_date, beginning_at, ending_at, description, user_model_id) VALUES
        ('a257fdad-672f-49bb-b6cd-1effe7b678a4', '2023-12-19', '2023-12-19 13:30:00 -03:00', '2023-12-19 14:30:00 -03:00', E'Realizamos nova visita à loja para nova avaliação. Quanto as duas câmera que não funcionam:\\n1. Uma delas (9) não foi instalada mas tem seu cabeamento passado até a beer\\ncave. Beer cave esta que, no momento da entrega da loja, não existia.  Ou seja: neste setor será necessária a instalação de nova câmera.\\n2. Nesta (2), localizada na entrada da loja, identificamos em testes que o cabo que a leva até o local está danificado em algum ponto da passagem.\\nSerá necessária nova visita para atuar nesta pendências.', '5249812b-e8f9-480d-a8db-2ded686dee6b');
