
CREATE TABLE treasury_tbl
(
  date_tx date NOT NULL,
  one_month    double precision DEFAULT '-9999.0'::numeric,
  two_months   double precision DEFAULT '-9999.0'::numeric,
  three_months double precision DEFAULT '-9999.0'::numeric,
  six_months   double precision DEFAULT '-9999.0'::numeric,
  one_year     double precision DEFAULT '-9999.0'::numeric,
  two_years    double precision DEFAULT '-9999.0'::numeric,
  three_years  double precision DEFAULT '-9999.0'::numeric,
  five_years   double precision DEFAULT '-9999.0'::numeric,
  seven_years  double precision DEFAULT '-9999.0'::numeric,
  ten_years    double precision DEFAULT '-9999.0'::numeric,
  twenty_years double precision DEFAULT '-9999.0'::numeric,
  thirty_years double precision DEFAULT '-9999.0'::numeric,
  primary key (date_tx)
)
WITH (
  OIDS=FALSE
);


ALTER TABLE treasury_tbl
  OWNER TO postgres;

CREATE INDEX idx_treasury_date
  ON treasury_tbl
  USING btree
  (date_tx);


