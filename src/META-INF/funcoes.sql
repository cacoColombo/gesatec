--
CREATE OR REPLACE FUNCTION removerAcentos(text)
RETURNS text AS  
$BODY$
BEGIN
    RETURN translate($1, 'ãáàâãäéèêëíìïóòôõöúùûüÁÀÂÃÄÉÈÊËÍÌÏÓÒÔÕÖÚÙÛÜçÇñÑ`´''', 'aaaaaaeeeeiiiooooouuuuAAAAAEEEEIIIOOOOOUUUUcCnN   ');
END;
$BODY$
LANGUAGE 'plpgsql' IMMUTABLE;
--

--
CREATE OR REPLACE FUNCTION obterDiaDaSemana(p_diaSemana int)
RETURNS SETOF diaDaSemana AS
$BODY$
BEGIN
    RETURN QUERY (
        SELECT * 
          FROM diaDaSemana
         WHERE id = p_diaSemana
    );
END;
$BODY$
LANGUAGE 'plpgsql' IMMUTABLE;
--

--
CREATE OR REPLACE FUNCTION obterMesPorExtenso(p_mes int)
RETURNS varchar AS
$BODY$
BEGIN
    RETURN CASE p_mes
        WHEN 01 THEN 'Janeiro'
        WHEN 02 THEN 'Fevereiro'
        WHEN 03 THEN 'Março'
        WHEN 04 THEN 'Abril'
        WHEN 05 THEN 'Maio'
        WHEN 06 THEN 'Junho'
        WHEN 07 THEN 'Julho'
        WHEN 08 THEN 'Agosto'
        WHEN 09 THEN 'Setembro'
        WHEN 10 THEN 'Outubro'
        WHEN 11 THEN 'Novembro'
        WHEN 12 THEN 'Dezembro'
    END;
END;
$BODY$
LANGUAGE 'plpgsql' IMMUTABLE;
--

--
CREATE OR REPLACE FUNCTION obterDataPorExtenso(p_data date)
RETURNS varchar AS
$BODY$
BEGIN
    RETURN EXTRACT(day FROM p_data) || ' de ' || obterMesPorExtenso(EXTRACT(month FROM p_data)::int) || ' de ' || EXTRACT(year FROM p_data);
END;
$BODY$
LANGUAGE 'plpgsql' IMMUTABLE;
--

--
CREATE OR REPLACE FUNCTION dataParaDb(p_date varchar)
RETURNS date AS
$BODY$
BEGIN
    RETURN TO_DATE(p_date, 'dd/mm/yyyy');
END;
$BODY$
LANGUAGE 'plpgsql' IMMUTABLE;
--

--
CREATE OR REPLACE FUNCTION dataParaUsuario(p_date date)
RETURNS varchar AS
$BODY$
BEGIN
    IF p_date IS NOT NULL
    THEN
        RETURN TO_CHAR(p_date, 'dd/mm/yyyy');
    ELSE
        RETURN NULL;
    END IF;
END;
$BODY$
LANGUAGE plpgsql IMMUTABLE;
--

--
CREATE OR REPLACE FUNCTION validaCPFouCNPJ(text)
RETURNS BOOLEAN AS
$BODY$
DECLARE
    v_string text := $1;
    v_caldv1 int4;
    v_caldv2 int4;
    v_dv1 int4;
    v_dv2 int4;
    v_array1 text[] ;
    v_array2 text[] ;
    v_tst_string int4;
BEGIN
    v_string := translate(v_string, './-', '');
    IF (char_length(v_string)::int4) = 14 
    THEN
        SELECT INTO v_array1 '{5,4,3,2,9,8,7,6,5,4,3,2}';
        SELECT INTO v_array2 '{6,5,4,3,2,9,8,7,6,5,4,3,2}';

        v_dv1 := (substring(v_string, 13, 1))::int4;
        v_dv2 := (substring(v_string, 14, 1))::int4;

        /* COLETA DIG VER 1 CNPJ */
        v_caldv1 := 0;
        FOR va IN 1..12 
        LOOP
            v_caldv1 := v_caldv1 + ((SELECT substring(v_string, va, 1))::int4 * (v_array1[va]::int4));
        END LOOP;

        v_caldv1 := v_caldv1 % 11;
        IF (v_caldv1 = 0) OR (v_caldv1 = 1) THEN
            v_caldv1 := 0;
        ELSE
            v_caldv1 := 11 - v_caldv1;
        END IF;

        /* COLETA DIG VER 2 CNPJ */
        v_caldv2 := 0;
        FOR va IN 1..13 LOOP
            v_caldv2 := v_caldv2 + ((SELECT substring(v_string || v_caldv1::text, va, 1))::int4 * (v_array2[va]::int4));
        END LOOP;

        v_caldv2 := v_caldv2 % 11;

        IF (v_caldv2 = 0) OR (v_caldv2 = 1) 
        THEN
            v_caldv2 := 0;
        ELSE
            v_caldv2 := 11 - v_caldv2;
        END IF;

        /* TESTA */
        IF (v_caldv1 = v_dv1) AND (v_caldv2 = v_dv2) 
        THEN
            RETURN TRUE;
        ELSE
            RETURN FALSE;
        END IF;

    ELSIF (char_length(v_string)::int4) = 11 
    THEN
        v_dv1 := (substring(v_string, 10, 1))::int4;
        v_dv2 := (substring(v_string, 11, 1))::int4;
        v_string := substring(v_string, 1, 9);

        /* COLETA DIG VER 1 CPF */
        v_caldv1 := 0;
        FOR va IN 1..9 LOOP
            v_caldv1 := v_caldv1 + ((SELECT substring(v_string, va, 1))::int4 * (11 - va));
        END LOOP;

        v_caldv1 := v_caldv1 % 11;
        IF (v_caldv1 = 0) OR (v_caldv1 = 1) 
        THEN
            v_caldv1 := 0;
        ELSE
            v_caldv1 := 11 - v_caldv1;
        END IF;

        /* COLETA DIG VER 2 CPF */
        v_caldv2 := 0;
        FOR va IN 1..10 
        LOOP
            v_caldv2 := v_caldv2 + ((SELECT substring((v_string || v_caldv1::text), va, 1))::int4 * (12 - va));
        END LOOP;

        v_caldv2 := v_caldv2 % 11;
        IF (v_caldv2 = 0) OR (v_caldv2 = 1) 
        THEN
            v_caldv2 := 0;
        ELSE
            v_caldv2 := 11 - v_caldv2;
        END IF;

        /* TESTA */
        IF (v_caldv1 = v_dv1) AND (v_caldv2 = v_dv2) 
        THEN
            RETURN TRUE;
        ELSE
            RETURN FALSE;
        END IF;

    END IF;

    RETURN FALSE;

END;
$BODY$
LANGUAGE 'plpgsql' IMMUTABLE;
--

--
DROP FUNCTION IF EXISTS obterHorariosParaAgendamento(DATE, INT, INT, INT);
CREATE OR REPLACE FUNCTION obterHorariosParaAgendamento(p_data DATE, p_profissional_id INT DEFAULT NULL, p_tipodeatendimento_id INT DEFAULT NULL, p_agendamento_id INT DEFAULT NULL, p_horario TIME DEFAULT NULL)
RETURNS TABLE (
    descricao_horario VARCHAR,
    data DATE,
    horario TIME,
    esta_disponivel BOOLEAN,
    agendamento_id INT,
    statusagendamento VARCHAR,
    tipodeatendimento_id INT,
    tipodeatendimento VARCHAR,
    cliente_id INT,
    cliente VARCHAR,
    profissional_id INT,
    profissional VARCHAR,
    data_formatada VARCHAR,
    horario_formatado VARCHAR,
    statusagendamento_id INT
) AS
$BODY$
BEGIN
    RETURN QUERY (
        SELECT horarios.descricao_horario,
               horarios.horario::DATE AS data,
               horarios.horario::TIME AS horario,
               ((agendamento.id IS NULL AND horarios.horario >= NOW()::TIMESTAMP) OR 
                ((p_agendamento_id IS NOT NULL AND p_agendamento_id <> 0) AND p_agendamento_id = agendamento.id)) AS esta_disponivel,
	       agendamento.id AS agendamento_id,
               (CASE WHEN statusagendamento.nome IS NOT NULL
                     THEN
                          statusagendamento.nome
                     WHEN (NOW()::TIMESTAMP > horarios.horario)
                     THEN
                          'Indisponível'
                     ELSE
                          'Livre'
                END) AS statusagendamento,
	       tipodeatendimento.id AS tipodeatendimento_id,
	       tipodeatendimento.nome AS tipodeatendimento,
	       pessoa.id AS cliente_id,
	       pessoa.nome AS cliente,
	       horarios.profissional_id,
	       horarios.profissional,
               TO_CHAR(horarios.horario::DATE, 'dd/mm/yyyy')::VARCHAR AS data_formatada,
               TO_CHAR(horarios.horario::TIME, 'hh24:mi')::VARCHAR AS horario_formatado,
               statusagendamento.id AS statusagendamento_id
	  FROM (SELECT padraoDeAtendimento.nome AS descricao_horario,
		       pessoa.id AS profissional_id,
		       pessoa.nome AS profissional,
		       generate_series((dataParaUsuario(p_data) || ' ' || padraoDeAtendimento.horarioinicioexpediente::TEXT)::TIMESTAMP, (dataParaUsuario(p_data) || ' ' || padraoDeAtendimento.horariofimexpediente::TEXT)::TIMESTAMP, (padraoDeAtendimento.tempomedioconsulta::TEXT || 'minutes')::INTERVAL) AS horario
		  FROM padraoDeAtendimentoDoProfissional
	    INNER JOIN padraoDeAtendimento
		    ON padraoDeAtendimento.id = padraoDeAtendimentoDoProfissional.padraoDeAtendimento_id
	    INNER JOIN pessoa
		    ON pessoa.id = padraoDeAtendimentoDoProfissional.profissional_id
		 WHERE diadasemana_id = EXTRACT('DOW' FROM p_data)
                   AND (CASE WHEN p_profissional_id IS NOT NULL AND p_profissional_id <> 0
                             THEN
                                  padraoDeAtendimentoDoProfissional.profissional_id = p_profissional_id
                             ELSE
                                  TRUE
                        END)
		   AND (CASE WHEN p_tipodeatendimento_id IS NOT NULL AND p_tipodeatendimento_id <> 0
			     THEN
			          EXISTS (SELECT tipoDeAtendimentoDoProfissional.id
					    FROM tipoDeAtendimentoDoProfissional
					   WHERE tipoDeAtendimentoDoProfissional.profissional_id = pessoa.id
					     AND tipoDeAtendimentoDoProfissional.tipoDeAtendimento_id = p_tipodeatendimento_id)
			     ELSE
			          TRUE
			END)) horarios
     LEFT JOIN agendamento
	    ON agendamento.dataagendada = horarios.horario::DATE
	   AND agendamento.horarioagendado = horarios.horario::TIME
	   AND agendamento.profissional_id = horarios.profissional_id
           AND (SELECT (liberaHorario IS FALSE)
                  FROM statusagendamento
                 WHERE id = agendamento.statusagendamento_id)
     LEFT JOIN statusagendamento
	    ON statusagendamento.id = agendamento.statusagendamento_id
     LEFT JOIN pessoa
	    ON pessoa.id = agendamento.cliente_id
     LEFT JOIN tipodeatendimento
	    ON tipodeatendimento.id = agendamento.tipodeatendimento_id
	 WHERE (CASE WHEN p_tipodeatendimento_id IS NOT NULL AND p_tipodeatendimento_id <> 0
		     THEN
			  (agendamento.id IS NULL OR agendamento.tipodeatendimento_id = p_tipodeatendimento_id)
		     ELSE
		          TRUE
		END)
	   AND (CASE WHEN p_horario IS NOT NULL
		     THEN
			  horarios.horario::TIME = p_horario
		     ELSE
			  TRUE
		END)
      ORDER BY horarios.horario ASC,
	       horarios.profissional
    );
END;
$BODY$
LANGUAGE plpgsql IMMUTABLE;
--

