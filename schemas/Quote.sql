
CREATE TABLE quote_tbl
(
  symbol  character varying(10) NOT NULL,
  date_tx date NOT NULL,
  open    double precision DEFAULT '-9999.0'::numeric,
  high    double precision DEFAULT '-9999.0'::numeric,
  low     double precision DEFAULT '-9999.0'::numeric,
  close   double precision DEFAULT '-9999.0'::numeric,
  volume  bigint DEFAULT '-9999'::numeric,
  primary key (symbol,date_tx)
)
WITH (
  OIDS=FALSE
);


ALTER TABLE quote_tbl
  OWNER TO postgres;


CREATE INDEX idx_quote_symbol
  ON quote_tbl
  USING btree
  (symbol COLLATE pg_catalog."default");


CREATE INDEX idx_quote_date
  ON quote_tbl
  USING btree
  (date_tx);


