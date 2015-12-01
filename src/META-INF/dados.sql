BEGIN;

INSERT INTO tipoDeAtendimento (nome) VALUES ('Exame de rotina'), ('Exame admissional'), ('Consulta odontológica'), ('Consulta pediátrica');

--Registro de profissional
INSERT INTO useraccount (login, password, name, active) VALUES ('prof1', MD5('prof1'), 'Profissional de Teste 1', true);
INSERT INTO pessoa (nome, telefonecelular, usuario_id, tipo, sexo) VALUES ('Profissional de Teste 1', '(51) 9243-3598', (SELECT MAX(id) FROM useraccount), 'profissional', 'M');
INSERT INTO profissional (id) VALUES ((SELECT MAX(id) FROM pessoa));
INSERT INTO tipodeatendimentodoprofissional (profissional_id, tipodeatendimento_id) VALUES ((SELECT MAX(id) FROM profissional), (SELECT MAX(id) FROM tipoDeAtendimento));
INSERT INTO padraoDeAtendimento (horarioinicioexpediente, horariofimexpediente, nome, tempomedioconsulta, diadasemana_id) VALUES ('13:30:00', '18:00:00', 'Vespertino de quinta', 30, 4);
INSERT INTO padraoDeAtendimentoDoProfissional(profissional_id, padraodeatendimento_id) VALUES ((SELECT MAX(id) FROM profissional), (SELECT MAX(id) FROM padraoDeAtendimento));




COMMIT;