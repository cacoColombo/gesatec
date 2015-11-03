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
CREATE OR REPLACE FUNCTION obterHorariosDoProfissionalParaAgendamento(p_profissional_id INT, p_data DATE)
RETURNS TABLE (
    descricao_horario VARCHAR,
    data DATE,
    horario TIME,
    esta_agendado BOOLEAN
) AS
$BODY$
BEGIN
    RETURN QUERY (
        SELECT horarios.descricao_horario,
               horarios.horario::DATE AS data,
               horarios.horario::TIME AS horario,
               (agendamento.id IS NOT NULL) AS esta_agendado
	  FROM (SELECT padraoDeAtendimento.nome AS descricao_horario,
		       generate_series((dataParaUsuario(p_data) || ' ' || padraoDeAtendimento.horarioinicioexpediente::TEXT)::TIMESTAMP, (dataParaUsuario(p_data) || ' ' || padraoDeAtendimento.horariofimexpediente::TEXT)::TIMESTAMP, (padraoDeAtendimento.tempomedioconsulta::TEXT || 'minutes')::INTERVAL) AS horario
		  FROM padraoDeAtendimentoDoProfissional
	    INNER JOIN padraoDeAtendimento
		    ON padraoDeAtendimento.id = padraoDeAtendimentoDoProfissional.padraoDeAtendimento_id
		 WHERE padraoDeAtendimentoDoProfissional.profissional_id = p_profissional_id
		   AND diadasemana_id = EXTRACT('DOW' FROM p_data)) horarios
     LEFT JOIN agendamento
	    ON agendamento.dataagendada = horarios.horario::DATE
	   AND agendamento.horarioagendado = horarios.horario::TIME
	   AND agendamento.profissional_id = p_profissional_id
      ORDER BY horarios.horario ASC
    );
END;
$BODY$
LANGUAGE plpgsql IMMUTABLE;
--

