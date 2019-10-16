
CREATE TABLE index_tbl
(
symbol character varying(10) NOT NULL,
name character varying(126),
date_tx date,
last_update date,
ytd double precision DEFAULT '-9999.0'::numeric,
one_day double precision DEFAULT '-9999.0'::numeric,
one_week double precision DEFAULT '-9999.0'::numeric,
two_weeks double precision DEFAULT '-9999.0'::numeric,
four_weeks double precision DEFAULT '-9999.0'::numeric,
three_months double precision DEFAULT '-9999.0'::numeric,
one_year double precision DEFAULT '-9999.0'::numeric,
three_years double precision DEFAULT '-9999.0'::numeric,
overview text,
CONSTRAINT index_pkey PRIMARY KEY (symbol)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE index_tbl
  OWNER TO postgres;

  