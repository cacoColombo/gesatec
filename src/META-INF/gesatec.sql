BEGIN;

INSERT INTO diadasemana (id, nome, abreviatura) VALUES (1, 'Domingo', 'Dom'), (2, 'Segunda-feira', 'Seg'), (3, 'Terça-feira', 'Ter'), (4, 'Quarta-feira', 'Qua'), (5, 'Quinta-feira', 'Qui'), (6, 'Sexta-feira', 'Sex'), (7, 'Sábado', 'Sáb');
INSERT INTO tipoderepeticao (id, nome) VALUES (1, 'Diária'), (2, 'Semanal'), (3, 'Mensal'), (4, 'Anual');
INSERT INTO statusagenda (id, nome) VALUES (1, 'AGENDADO'), (2, 'CONFIRMADO'), (3, 'EM ANDAMENTO'), (4, 'FINALIZADO');
INSERT INTO campo (id, nome, tipo) VALUES (1, 'Texto simples', 'TextField'), (2, 'Texto longo', 'TextArea');

COMMIT;