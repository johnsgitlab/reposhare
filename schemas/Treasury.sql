
CREATE TABLE treasury_tbl
(
  date_tx date NOT NULL,
  one_month    double precision,
  two_months   double precision,
  three_months double precision,
  six_months   double precision,
  one_year    	double precision,
  two_years    double precision,
  three_years  double precision,
  five_years   double precision,
  seven_years  double precision,
  ten_years    double precision,
  twenty_years double precision,
  thirty_years double precision,
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


