
CREATE TABLE etf_tbl
(
  symbol character varying(10) NOT NULL,
  name character varying(126),
  asset_class character varying(126),
  fund_type character varying(126),
  fund_sub_type character varying(126),
  factor character varying(126),
  category character varying(126),
  tracks character varying(126),
  date_tx date,
  last_update date,
  family character varying(126),
  exchange character varying(126),

  underlying_index character varying(126),
  shares double precision DEFAULT '-9999.0'::numeric,
  avg_daily_vol double precision DEFAULT '-9999.0'::numeric,
  inception date,
  market_cap double precision DEFAULT '-9999.0'::numeric,
  expense_ratio double precision DEFAULT '-9999.0'::numeric,
  dividend_yield double precision DEFAULT '-9999.0'::numeric,

  morning_rating character varying(126),
  morning_stars integer,
  lipper_total integer,
  lipper_consistent integer,
  lipper_preservation integer,
  lipper_tax integer,
  lipper_expense integer,

  ytd double precision DEFAULT '-9999.0'::numeric,
  one_day double precision DEFAULT '-9999.0'::numeric,
  one_week double precision DEFAULT '-9999.0'::numeric,
  two_weeks double precision DEFAULT '-9999.0'::numeric,
  four_weeks double precision DEFAULT '-9999.0'::numeric,
  three_months double precision DEFAULT '-9999.0'::numeric,
  one_year double precision DEFAULT '-9999.0'::numeric,
  three_years double precision DEFAULT '-9999.0'::numeric,

  basic_materials double precision DEFAULT '0.0'::numeric,
  consumer_cyclical double precision DEFAULT '0.0'::numeric,
  financial_services double precision DEFAULT '0.0'::numeric,
  realestate double precision DEFAULT '0.0'::numeric,
  consumer_defensive double precision DEFAULT '0.0'::numeric,
  healthcare double precision DEFAULT '0.0'::numeric,
  utilities double precision DEFAULT '0.0'::numeric,
  communication_services double precision DEFAULT '0.0'::numeric,
  energy double precision DEFAULT '0.0'::numeric,
  industrials double precision DEFAULT '0.0'::numeric,
  technology double precision DEFAULT '0.0'::numeric,

  alpha double precision DEFAULT '0.0'::numeric,
  beta double precision DEFAULT '0.0'::numeric,
  mean_annual_return double precision DEFAULT '0.0'::numeric,
  r_squared double precision DEFAULT '0.0'::numeric,
  deviation double precision DEFAULT '0.0'::numeric,
  sharpe_ratio double precision DEFAULT '0.0'::numeric,
  treynor_ratio double precision DEFAULT '0.0'::numeric,

  pe double precision DEFAULT '0.0'::numeric,
  pb double precision DEFAULT '0.0'::numeric,
  ps double precision DEFAULT '0.0'::numeric,
  pc double precision DEFAULT '0.0'::numeric,
  earnings_growth double precision DEFAULT '0.0'::numeric,
  median_market_cap double precision DEFAULT '0.0'::numeric,

  bond_maturity double precision DEFAULT '0.0'::numeric,
  bond_duration double precision DEFAULT '0.0'::numeric,
  bond_credit_quality double precision DEFAULT '0.0'::numeric,
  bond_aaa_percent double precision DEFAULT '0.0'::numeric,
  bond_aa_percent double precision DEFAULT '0.0'::numeric,
  bond_a_percent double precision DEFAULT '0.0'::numeric,
  bond_bbb_percent double precision DEFAULT '0.0'::numeric,
  bond_bb_percent double precision DEFAULT '0.0'::numeric,
  bond_b_percent double precision DEFAULT '0.0'::numeric,
  bond_belowb_percent double precision DEFAULT '0.0'::numeric,
  bond_us_percent double precision DEFAULT '0.0'::numeric,
  bond_other_percent double precision DEFAULT '0.0'::numeric,

  bond_position double precision DEFAULT '0.0'::numeric,
  cash_position double precision DEFAULT '0.0'::numeric,
  convertible_position double precision DEFAULT '0.0'::numeric,
  other_position double precision DEFAULT '0.0'::numeric,
  preferred_position double precision DEFAULT '0.0'::numeric,
  stock_position double precision DEFAULT '0.0'::numeric,

  top_holdings text,
  overview text,

  CONSTRAINT etf_pkey PRIMARY KEY (symbol)
)
WITH (
OIDS=FALSE
);

ALTER TABLE etf_tbl
OWNER TO postgres;


CREATE INDEX idx_etf_asset_class
ON etf_tbl
USING btree
(asset_class COLLATE pg_catalog."default");

CREATE INDEX idx_etf_fund_type
ON etf_tbl
USING btree
(fund_type COLLATE pg_catalog."default");

CREATE INDEX idx_etf_fund_sub_type
ON etf_tbl
USING btree
(fund_sub_type COLLATE pg_catalog."default");

CREATE INDEX idx_etf_factor
ON etf_tbl
USING btree
(factor COLLATE pg_catalog."default");

CREATE INDEX idx_etf_category
ON etf_tbl
USING btree
(category COLLATE pg_catalog."default");

CREATE INDEX idx_etf_tracks
ON etf_tbl
USING btree
(tracks COLLATE pg_catalog."default");

CREATE INDEX idx_etf_family
ON etf_tbl
USING btree
(family COLLATE pg_catalog."default");

CREATE INDEX idx_etf_underlying_index
ON etf_tbl
USING btree
(underlying_index COLLATE pg_catalog."default");
