--
CREATE OR REPLACE VIEW view_clientes AS (
    SELECT *
      FROM cliente
INNER JOIN pessoa
     USING (id)
);
--

--
CREATE OR REPLACE VIEW view_profissionais AS (
    SELECT *
      FROM profissional
INNER JOIN pessoa
     USING (id)
);
--

--
CREATE OR REPLACE VIEW view_atendentes AS (
    SELECT *
      FROM atendente
INNER JOIN pessoa
     USING (id)
);
--

--
CREATE OR REPLACE VIEW view_agendamentos AS (
    SELECT agendamento.*,
           statusagendamento.nome AS status_agendamento,
           tipodeatendimento.nome AS tipo_de_atendimento,
           tipodeatendimento.ativo AS tipo_de_atendimento_ativo,
           pessoaCliente.nome AS cliente,
           pessoaProfissional.nome AS profissional,
           pessoaAtendente.nome AS atendente
      FROM agendamento
INNER JOIN statusagendamento
        ON statusagendamento.id = agendamento.statusagendamento_id
INNER JOIN tipodeatendimento
        ON tipodeatendimento.id = agendamento.tipodeatendimento_id
INNER JOIN cliente
        ON cliente.id = agendamento.cliente_id
INNER JOIN pessoa AS pessoaCliente
        ON pessoaCliente.id = cliente.id
INNER JOIN profissional
        ON profissional.id = agendamento.profissional_id
INNER JOIN pessoa AS pessoaProfissional
        ON pessoaProfissional.id = profissional.id
 LEFT JOIN atendente
        ON atendente.id = agendamento.atendente_id
 LEFT JOIN pessoa AS pessoaAtendente
        ON pessoaAtendente.id = atendente.id
);
--

--
CREATE OR REPLACE VIEW view_padroesdeatendimento AS (
    SELECT padraodeatendimento.*,
           diadasemana.nome AS diadasemana,
           diadasemana.abreviatura AS diadasemana_abreviatura
      FROM padraodeatendimento
INNER JOIN diadasemana
        ON diadasemana.id = padraodeatendimento.diadasemana_id
);
--