-- This file allow to write SQL commands that will be emitted in test and dev.
INSERT INTO state (id, name) VALUES
    ('AC', 'Acre'),
    ('AL', 'Alagoas'),
    ('AM', 'Amazonas'),
    ('AP', 'Amapá'),
    ('BA', 'Bahia'),
    ('CE', 'Ceará'),
    ('DF', 'Distrito Federal'),
    ('ES', 'Espírito Santo'),
    ('GO', 'Goiás'),
    ('MA', 'Maranhão'),
    ('MG', 'Minas Gerais'),
    ('MS', 'Mato Grosso do Sul'),
    ('MT', 'Mato Grosso'),
    ('PA', 'Pará'),
    ('PB', 'Paraíba'),
    ('PE', 'Pernambuco'),
    ('PI', 'Piauí'),
    ('PR', 'Paraná'),
    ('RJ', 'Rio de Janeiro'),
    ('RN', 'Rio Grande do Norte'),
    ('RO', 'Rondônia'),
    ('RR', 'Roraima'),
    ('RS', 'Rio Grande do Sul'),
    ('SC', 'Santa Catarina'),
    ('SE', 'Sergipe'),
    ('SP', 'São Paulo'),
    ('TO', 'Tocantins');

-------------------------------------------------
INSERT INTO city (id, name, state_id) VALUES
    ('8d5cd4a5-16c5-4d37-bedc-07fb0cce179f', 'São Paulo', 'SP'),
    ('627cbb51-643c-44fc-bf83-6d69ad363e26', 'Itapevi', 'SP'),
    ('7efe0a9b-eb02-4c7b-b487-3468f4de352e', 'Cotia', 'SP'),
    ('85bcb789-ec5a-4cfb-815f-aa24727f6dbe', 'Carapicuípa', 'SP'),
    ('6a36d68e-591f-4d01-b3aa-b10437122dfa', 'Osasco', 'SP');


---------------------------------------
INSERT INTO customer (id, trading_name, company_name, active, cpf_cnpj, photo_url) VALUES
    ('4f653c10-cab3-40d8-8518-c160dbeb74c4', 'AÇAILAND', 'SIMPLES E NATURAL AÇAI LTDA', true, '45181657000130', null),
    ('832344ed-91d9-4962-91fd-a345bcf53bd0', 'SANTO AGOSTINHO', 'Auto Posto Santo Agostinho', true, '21529279000175', null),
    ('4c66c0cc-00e6-4ccd-a2d9-87d5eb6cfd8b', 'SANTA RITA', 'Serviços Automotivos Santa Rita', true, '17268497000126', null),
    ('265edc2e-650d-4fec-97ce-e8a9b253caeb', 'JANDAIA', 'AUTO POSTO JANDAIA', true, '07140748000139', null),
    ('ac379cf6-1d57-4262-8445-822c951936f4', 'MIRIZOLA', 'Comercio Varejista de Combustivel Mirizola Ltda', true, '21227537000169', null),
    ('290295f9-5153-45d1-b050-4221f29baca8', 'BAOBA', 'Auto Posto Baoba', true, '20730162000192', null),
    ('ebf253d7-379b-414d-a185-550f94f14ff0', 'CALABRIA', 'Auto Posto Calabria', true, '11360399000138', null);
---------------------------------

INSERT INTO address (zip_code, neighborhood, street, complement, number, city_id, customer_id) VALUES
    ('03306010', 'Cidade Mãe do Céu', 'Rua Domingos Agostim',null, 91, '8d5cd4a5-16c5-4d37-bedc-07fb0cce179f', '4f653c10-cab3-40d8-8518-c160dbeb74c4'),
    ('06330000', 'Vila da Oportunidade', 'Estrada do Copiúva',null, 814, '8d5cd4a5-16c5-4d37-bedc-07fb0cce179f', '832344ed-91d9-4962-91fd-a345bcf53bd0'),
    ('06660680', 'Jardim Itacolomi', 'Rua Paulo César Duarte',null, 25, '627cbb51-643c-44fc-bf83-6d69ad363e26', '4c66c0cc-00e6-4ccd-a2d9-87d5eb6cfd8b'),
    ('06330010', 'Parque Jandaia', 'Avenida Marginal do Ribeirão',null, 4836, '85bcb789-ec5a-4cfb-815f-aa24727f6dbe', '265edc2e-650d-4fec-97ce-e8a9b253caeb'),
    ('06704505', 'Parque Miguel Mirizola', 'Rua Vicente Strifezzi',null, 10, '7efe0a9b-eb02-4c7b-b487-3468f4de352e', 'ac379cf6-1d57-4262-8445-822c951936f4'),
    ('02675031', 'km 18', 'Avenida dos Autonomistas','- de 4334 ao fim - lado par', 6650, '6a36d68e-591f-4d01-b3aa-b10437122dfa', 'ebf253d7-379b-414d-a185-550f94f14ff0'),
    ('08246000',  'Parada XV de Novembro', 'Estrada Itaquera-Guaianases', null, 400, '6a36d68e-591f-4d01-b3aa-b10437122dfa', 'ebf253d7-379b-414d-a185-550f94f14ff0');

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
	('d80cf336-d28c-4067-a56b-8fe9f7af99b5', 'Projetos', null),
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
    (1, 'Acompanhamento de suporte', 1),
    (2, 'Capacitação', 1),
    (3, 'Desenvolvimento: correçoes', 1),
    (4, 'Dúvidas', 1),
    (5, 'Sistemas bancários e governamentais', 1),
    (6, 'Solicitação de desenvolvimento', 1),
    (7, 'Antivírus', 2),
    (8, 'Cadastros e acessos',2),
    (9, 'Computadores e periféricos',2),
    (10, 'Dispositivos Móveis', 2),
    (11, 'Email', 2),
    (12, 'Impressoras', 2),
    (13, 'Rede e Internet', 2),
    (14, 'Sistema operacional', 2),
    (15, 'Software', 2),
    (16, 'Balança', 3),
    (17, 'Catraca', 3),
    (18, 'Desmontagem de loja', 3),
    (19, 'Manutenção CFTV', 3),
    (20, 'Manutenção rack', 3),
    (21, 'Montagem PDV', 3),
    (22, 'SAT', 3),
    (23, 'Suporte a internet', 3),
    (24, 'Suporte a PDV', 3),
    (25, 'Suporte a servidor', 3),
    (26, 'Totem', 3);

INSERT INTO catalog_item (id, name, catalog_area_id) VALUES
    (1, 'Correção', 1),
    (2, 'Melhorias', 1),
    (3, 'Treinamento',2),
    (4, 'Correção de funcionalidades',3),
    (5, 'Correção em relatórios',3),
    (6, 'Dúvidas utilização',4),
    (7, 'Modificação de processos',4),
    (8, 'Instalação de plugins de bancos',5),
    (9, 'Instalação de programas diversos',5),
    (10, 'Instalação de sistemas da receita federal',5),
    (11, 'Suporte a sistema',5),
    (12, 'Melhoria',6),
    (13, 'Configuração',7),
    (14, 'Nova funcionalidade',6),
    (15, 'Novo relatório',6),
    (16, 'Instalação',7),
    (17, 'Remoção',7),
    (18, 'Alteração de cadastro',8),
    (19, 'Alteração de senha',8),
    (20, 'Cancelamento de login',8),
    (21, 'Desbloqueio de senha',8),
    (22, 'Liberar acesso',8),
    (23, 'Novo login',8),
    (24, 'Configuração de perfil',9),
    (25, 'Instalar/substituir equipamento',9),
    (26, 'Manutenção preventiva',9),
    (27, 'Mudaça de local',9),
    (28, 'Teste de equipamento',9),
    (29, 'Substituição de peças',9),
    (30,'Configuração de email',10),
    (31,'Configuração de rede',10),
    (32,'Reset de fábrica',10),
    (33,'Alteração de senha',11),
    (34,'Auditoria de email',11),
    (35, 'Configurção de assinatura',11),
    (36, 'Configuração d email',11),
    (37, 'Configurar/ajustar ainti-SPAM',11),
    (38, 'Resolução de problemas',11),
    (39, 'Instalção',12),
    (40, 'Manutenção',12),
    (41, 'Re-instalação',12),
    (42, 'Ativar ponto de rede', 13),
    (43, 'Compartilhamento de rede', 13),
    (44, 'Configuração do roteador', 13),
    (45, 'Configuração do Proxy', 13),
    (46, 'Configuração de regras de bloqueio', 13),
    (47, 'Falha de acesso à rede', 13),
    (48, 'Instalação de novo link', 13),
    (49, 'Monitoramento de links', 13),
    (50, 'Relógio de ponto', 13),
    (51, 'Verificação de cabeamento', 13),
    (52, 'Investigar lentidão', 13),
    (53, 'Atualização de drivers',14),
    (54, 'Instalação',14),
    (55, 'Reinstalação com backup',14),
    (56, 'Reinstalaçao sem backup',14),
    (57, 'Reparo de sistema operacional',14),
    (58, 'Atualizaçao',15),
    (59, 'Instalação',15),
    (60, 'Reinstalação',15),
    (61, 'Cabeamento', 16),
    (62, 'Software', 16),
    (63 , 'Substituiçaõ', 16),
    (64 , 'Acompanhameto técnico', 17),
    (65 , 'Comanda', 17),
    (66 , 'Remoção de equipamentos', 18),
    (67 , 'Acesso as câmeras', 19),
    (68 , 'Câmeras', 19),
    (69 , 'Configuração de câmeras', 19),
    (70 , 'DVR', 19),
    (71 , 'Fonte de câmeras', 19),
    (72 , 'HD', 19),
    (73 , 'Switch POE', 19),
    (74 , 'Manutençao de Rack', 20),
    (75 , 'Nobreak', 20),
    (76 , 'Organização do rack', 20),
    (77 , 'Roteador ADM', 20),
    (78 , 'Instalação de computadores e perifericos', 21),
    (79 , 'Ativação', 22),
    (80 , 'Substituição', 22),
    (81 , 'Validação de instalações', 22),
    (82 , 'Verificação de configurações', 22),
    (83 , 'Vinculo', 22),
    (84, 'KM De Vantagens', 23),
    (85 , 'Rede ADM', 23),
    (86 , 'Roteadores', 23),
    (87 , 'Verificação de link', 23),
    (88 , 'Impressora L42(PRO)', 24),
    (89 , 'Nobreak', 24),
    (90 , 'Perifericos', 24),
    (91 , 'Pinpad', 24),
    (92 , 'Rede', 24),
    (93 , 'Sistema Operacional', 24),
    (94 , 'Software', 24),
    (95 , 'Upgrade', 24),
    (96 , 'Autoatendimento', 24),
    (97 , 'Hardware', 24),
    (98 , 'Impressora I9', 24),
    (99, 'Cabo HDMI', 25),
    (100, 'Cabo VGA', 25),
    (101, 'Conversor', 25),
    (102, 'Hardware', 25),
    (103, 'HD Exerno', 25),
    (104, 'Rede', 25),
    (105, 'Sinconização de sistema', 25),
    (106, 'Sistema operacional', 25),
    (107, 'Software', 25),
    (108, 'Upgrade', 25),
    (109, 'Impressora', 26),
    (110, 'Rede', 26);
----------------------------------------------
INSERT INTO customer_service_desk (customer_id, service_desk_id) VALUES
        ('4f653c10-cab3-40d8-8518-c160dbeb74c4', '744daba3-a502-4cc0-b323-740c789a0b28'),
        ('4f653c10-cab3-40d8-8518-c160dbeb74c4', '3039802e-f12d-4f45-9fc0-a80f7b5aec43'),
        ('4f653c10-cab3-40d8-8518-c160dbeb74c4', 'b9458763-649c-41b4-962d-6f991542505a'),
        ('4f653c10-cab3-40d8-8518-c160dbeb74c4', '27532144-007c-4d92-ae7b-419626ab29c0'),
        ('4f653c10-cab3-40d8-8518-c160dbeb74c4', '55f55ba5-9c67-48d1-b8e8-dd60705c98dd');

--------------------------------------------

INSERT INTO user_model (id, name, email, role_model) VALUES
        ('a257fdad-672f-49bb-b6cd-1effe7b678a4', 'Heitor', 'heitor.barreto@ampm.com.br','TENANT'),
        ('2665c568-abf6-458e-8801-658fa16ae552', 'Felipe', 'felipe.suporte@zayk.com.br','ATTENDANT');

----------------------------------------------

INSERT INTO ticket(id, number, status, title, description, priority, opened_at, updated_at, closed_at, parent_ticket_id, service_desk_id, customer_id, requester_user_model_id) VALUES
        ('5249812b-e8f9-480d-a8db-2ded686dee6b', 1, 'ABERTO', 'LOJA AÇAILAND', E'Prezados, boa tarde! Tudo bem?\nSolicito orçamento para:\n-Visita técnica;\n-Upgrade de Hardware nos PDVs (Equipamentos AMPM);\n-Organização do Rack de TI. Preciso do Orçamento com urgência. Loja Gioconda.', 'ALTA', '2023-07-19T14:16:10.124395900-03:00', null, null, null, '55f55ba5-9c67-48d1-b8e8-dd60705c98dd', '4f653c10-cab3-40d8-8518-c160dbeb74c4', 'a257fdad-672f-49bb-b6cd-1effe7b678a4'),
        ('309c97b5-2fea-4f73-8244-9a7c3de8edff', 2, 'ABERTO', 'Lorem', E'uma câmera da loja  não está funcionando precisamos da manutenção da mesma . ',  'BAIXA','2023-05-19T14:16:10.124395900-03:00', '2023-07-19T14:16:00.124395900-03:00', null, null, '55f55ba5-9c67-48d1-b8e8-dd60705c98dd', '4f653c10-cab3-40d8-8518-c160dbeb74c4', 'a257fdad-672f-49bb-b6cd-1effe7b678a4');

--------------------------------------------------

INSERT INTO appointment(ticket_id, appointment_date, beginning_at, ending_at, description, user_model_id) VALUES
        ('5249812b-e8f9-480d-a8db-2ded686dee6b', '2023-12-19', '2023-12-19 13:30:00 -03:00', '2023-12-19 14:30:00 -03:00', E'Realizamos nova visita à loja para nova avaliação. Quanto as duas câmera que não funcionam:\\n1. Uma delas (9) não foi instalada mas tem seu cabeamento passado até a beer\\ncave. Beer cave esta que, no momento da entrega da loja, não existia.  Ou seja: neste setor será necessária a instalação de nova câmera.\\n2. Nesta (2), localizada na entrada da loja, identificamos em testes que o cabo que a leva até o local está danificado em algum ponto da passagem.\\nSerá necessária nova visita para atuar nesta pendências.', '2665c568-abf6-458e-8801-658fa16ae552');
