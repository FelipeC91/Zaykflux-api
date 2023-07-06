INSERT INTO estado (id, nome, uf) VALUES
    (1, 'Acre', 'AC'),
    (2, 'Alagoas', 'AL'),
    (3, 'Amazonas', 'AM'),
    (4, 'Amapá', 'AP'),
    (5, 'Bahia', 'BA'),
    (6, 'Ceará', 'CE'),
    (7, 'Distrito Federal', 'DF'),
    (8, 'Espírito Santo', 'ES'),
    (9, 'Goiás', 'GO'),
    (10, 'Maranhão', 'MA'),
    (11, 'Minas Gerais', 'MG'),
    (12, 'Mato Grosso do Sul', 'MS'),
    (13, 'Mato Grosso', 'MT'),
    (14, 'Pará', 'PA'),
    (15, 'Paraíba', 'PB'),
    (16, 'Pernambuco', 'PE'),
    (17, 'Piauí', 'PI'),
    (18, 'Paraná', 'PR'),
    (19, 'Rio de Janeiro', 'RJ'),
    (20, 'Rio Grande do Norte', 'RN'),
    (21, 'Rondônia', 'RO'),
    (22, 'Roraima', 'RR'),
    (23, 'Rio Grande do Sul', 'RS'),
    (24, 'Santa Catarina', 'SC'),
    (25, 'Sergipe', 'SE'),
    (26, 'São Paulo', 'SP'),
    (27, 'Tocantins', 'TO');


INSERT INTO cidade (id, nome, estado_id) VALUES
    (1, 'São Paulo', 26),
    (2, 'Itapevi', 26),
    (3, 'Cotia', 26),
    (4, 'Carapicuípa', 26),
    (5, 'Osasco', 26);


--
INSERT INTO cliente (id, nome_fantasia, razao_social, ativo, cpf_cnpj, foto_url) VALUES
    (1, 'AÇAILAND', 'SIMPLES E NATURAL AÇAI LTDA', true, '45181657000130', null),
    (2, 'SANTO AGOSTINHO', 'Auto Posto Santo Agostinho', true, '21529279000175', null),
    (3, 'SANTA RITA', 'Serviços Automotivos Santa Rita', true, '17268497000126', null),
    (4, 'JANDAIA', 'AUTO POSTO JANDAIA', true, '07140748000139', null),
    (5, 'MIRIZOLA', 'Comercio Varejista de Combustivel Mirizola Ltda', true, '21227537000169', null),
    (6, 'BAOBA', 'Auto Posto Baoba', true, '20730162000192', null),
    (7, 'CALABRIA', 'Auto Posto Calabria', true, '11360399000138', null);
---------------------------------

INSERT INTO endereco (id, cep, bairro, logradouro, complemento, numero, cidade_id, cliente_id) VALUES
    (1, '03306900', 'Cidade Mãe do Céu', 'Rua Domingos Agostim',null, 91, 1, 1),
    (2, '06330000', 'Vila da Oportunidade', 'Estrada do Copiúva',null, 814, 2, 2),
    (3, '06660680', 'Jardim Itacolomi', 'Rua Paulo César Duarte',null, 25, 2, 3),
    (4, '06330010', 'Parque Jandaia', 'Avenida Marginal do Ribeirão',null, 4836, 4, 4),
    (5, '06704205', 'Parque Miguel Mirizola', 'Rua Vicente Strifezzi',null, 10, 3, 5),
    (6, '06194060', 'km 18', 'Avenida dos Autonomistas','- de 4334 ao fim - lado par', 6650, 5, 6),
    (7, '08246000', 'Parada XV de Novembro', 'Estrada Itaquera-Guaianases', null, 400, 1, 7);


