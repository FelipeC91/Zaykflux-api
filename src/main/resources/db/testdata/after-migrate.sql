INSERT INTO estado (uf, nome) VALUES
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


INSERT INTO cidade (id, nome, estado_id) VALUES
    (1, 'São Paulo', 'SP'),
    (2, 'Itapevi', 'SP'),
    (3, 'Cotia', 'SP'),
    (4, 'Carapicuípa', 'SP'),
    (5, 'Osasco', 'SP');


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

--------------------------------------------
INSERT INTO grupo (id, nome) VALUES
	(1, 'Administrador'),
	(2, 'Financeiro'),
	(3, 'Intermediário'),
	(4, 'Técnico');


INSERT INTO mesa_servico (id, nome) VALUES
	(1, 'Projetos'),
	(2, 'Remoto'),
	(3, 'Presencial'),
	(3, 'Financeiro'),
	(3, 'Interno'),
	(4, 'AMPM');
