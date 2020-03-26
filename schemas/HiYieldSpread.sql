
CREATE TABLE hi_yield_spread_tbl
(
  date_tx date NOT NULL,
  spread  double precision DEFAULT '-9999.0'::numeric,
  primary key (date_tx)
)
WITH (
  OIDS=FALSE
);


ALTER TABLE hi_yield_spread_tbl
  OWNER TO postgres;

CREATE INDEX idx_hi_yield_spread_date
  ON hi_yield_spread_tbl
  USING btree
  (date_tx);


