
CREATE TABLE quote_tbl
(
  symbol  character varying(10) NOT NULL,
  date_tx date NOT NULL,
  open    double precision,
  high    double precision,
  low     double precision,
  close   double precision,
  volume  bigint,
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


