CREATE DATABASE unit_test;

CREATE TABLE category_tbl
(
  category character varying(126) NOT NULL,
  CONSTRAINT category_pkey PRIMARY KEY (category)
)
WITH (
  OIDS=FALSE
);

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
  morning_stars integer DEFAULT '-9999'::numeric,
  lipper_total integer DEFAULT '-9999'::numeric,
  lipper_consistent integer DEFAULT '-9999'::numeric,
  lipper_preservation integer DEFAULT '-9999'::numeric,
  lipper_tax integer DEFAULT '-9999'::numeric,
  lipper_expense integer DEFAULT '-9999'::numeric,

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


CREATE TABLE event_tbl
(
  row_id  		BIGSERIAL,
  date_tx 		date NOT NULL,
  category 		character varying(126),
  sub_category character varying(126),
  effect 		character varying(126),
  description 	text,
  CONSTRAINT event_pkey PRIMARY KEY (row_id),
  CONSTRAINT event_tbl_category_fkey FOREIGN KEY (category)
      REFERENCES public.category_tbl (category) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE INDEX idx_event_date
  ON event_tbl
  USING btree
  (date_tx);
  
  
CREATE TABLE fund_tbl
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

  status character varying(126),
  front_load double precision DEFAULT '-9999.0'::numeric,
  back_load double precision DEFAULT '-9999.0'::numeric,
  minimum double precision DEFAULT '-9999.0'::numeric,
  inception date,

  expense_ratio double precision DEFAULT '-9999.0'::numeric,
  dividend_yield double precision DEFAULT '-9999.0'::numeric,

  morning_rating character varying(126),
  morning_stars integer DEFAULT '-9999'::numeric,
  lipper_total integer DEFAULT '-9999'::numeric,
  lipper_consistent integer DEFAULT '-9999'::numeric,
  lipper_preservation integer DEFAULT '-9999'::numeric,
  lipper_tax integer DEFAULT '-9999'::numeric,
  lipper_expense integer DEFAULT '-9999'::numeric,

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
  market_cap double precision DEFAULT '0.0'::numeric,

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

  CONSTRAINT fund_tbl_pkey PRIMARY KEY (symbol)
)
WITH (
  OIDS=FALSE
);


CREATE INDEX idx_fund_asset_class
  ON public.fund_tbl
  USING btree
  (asset_class COLLATE pg_catalog."default");


CREATE INDEX idx_fund_category
  ON public.fund_tbl
  USING btree
  (category COLLATE pg_catalog."default");


CREATE INDEX idx_fund_factor
  ON public.fund_tbl
  USING btree
  (factor COLLATE pg_catalog."default");


CREATE INDEX idx_fund_family
  ON public.fund_tbl
  USING btree
  (family COLLATE pg_catalog."default");


CREATE INDEX idx_fund_fund_sub_type
  ON public.fund_tbl
  USING btree
  (fund_sub_type COLLATE pg_catalog."default");


CREATE INDEX idx_fund_fund_type
  ON public.fund_tbl
  USING btree
  (fund_type COLLATE pg_catalog."default");


CREATE INDEX idx_fund_lipper_consistent
  ON public.fund_tbl
  USING btree
  (lipper_consistent);


CREATE INDEX idx_fund_lipper_total
  ON public.fund_tbl
  USING btree
  (lipper_total);


CREATE INDEX idx_fund_morning_stars
  ON public.fund_tbl
  USING btree
  (morning_stars);


CREATE TABLE hi_yield_spread_tbl
(
  date_tx date NOT NULL,
  spread  double precision DEFAULT '-9999.0'::numeric,
  primary key (date_tx)
)
WITH (
  OIDS=FALSE
);

CREATE INDEX idx_hi_yield_spread_date
  ON hi_yield_spread_tbl
  USING btree
  (date_tx);


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

CREATE INDEX idx_quote_symbol
  ON quote_tbl
  USING btree
  (symbol COLLATE pg_catalog."default");


CREATE INDEX idx_quote_date
  ON quote_tbl
  USING btree
  (date_tx);


CREATE TABLE PUBLIC.stock_tbl
(
  symbol character varying(10) NOT NULL,
  name character varying(126),
  
  sp_index character varying(32),
  dow_index character varying(32),
  russell_index character varying(32),
    
  sector character varying(126),
  industry character varying(126),
  exchange character varying(126),

  tracks character varying(126),
  date_tx date,
  last_update date,

  recommendations integer DEFAULT '0'::numeric,
  strong_buy integer DEFAULT '0'::numeric,
  buy integer DEFAULT '0'::numeric,
  hold integer DEFAULT '0'::numeric,
  sell integer DEFAULT '0'::numeric,
  strong_sell integer DEFAULT '0'::numeric,

  market_cap bigint DEFAULT '0'::numeric,
  enterprise_value bigint DEFAULT '0'::numeric,
  operating_cashflow bigint DEFAULT '0'::numeric,
  ebitda bigint DEFAULT '0'::numeric,
  free_cashflow bigint DEFAULT '0'::numeric,
  total_cash bigint DEFAULT '0'::numeric,
  total_debt bigint DEFAULT '0'::numeric,
  total_revenue bigint DEFAULT '0'::numeric,
  avg_daily_vol bigint DEFAULT '0.0'::numeric,
  
  dividend_rate double precision DEFAULT '0.0'::numeric,
  dividend_Yield double precision DEFAULT '0.0'::numeric,
  beta double precision DEFAULT '0.0'::numeric,
  pe double precision DEFAULT '0.0'::numeric,
  pe_forward double precision DEFAULT '0.0'::numeric,
  ps double precision DEFAULT '0.0'::numeric,
  pb double precision DEFAULT '0.0'::numeric,
  trailing_eps double precision DEFAULT '0.0'::numeric,
  peg_ratio double precision DEFAULT '0.0'::numeric,
  enterprise_to_revenue double precision DEFAULT '0.0'::numeric,
  enterprise_to_ebitda double precision DEFAULT '0.0'::numeric,
  ebitda_margins double precision DEFAULT '0.0'::numeric,
  profit_margins double precision DEFAULT '0.0'::numeric,
  gross_margins double precision DEFAULT '0.0'::numeric,
  revenue_growth double precision DEFAULT '0.0'::numeric,
  operating_margins double precision DEFAULT '0.0'::numeric,
  earnings_growth double precision DEFAULT '0.0'::numeric,
  current_ratio double precision DEFAULT '0.0'::numeric,
  return_on_assets double precision DEFAULT '0.0'::numeric,
  debt_to_equity double precision DEFAULT '0.0'::numeric,
  return_on_equity double precision DEFAULT '0.0'::numeric,
  total_cash_per_share double precision DEFAULT '0.0'::numeric,
  
  
  ytd double precision DEFAULT '-9999.0'::numeric,
  one_day double precision DEFAULT '-9999.0'::numeric,
  one_week double precision DEFAULT '-9999.0'::numeric,
  two_weeks double precision DEFAULT '-9999.0'::numeric,
  four_weeks double precision DEFAULT '-9999.0'::numeric,
  three_months double precision DEFAULT '-9999.0'::numeric,
  one_year double precision DEFAULT '-9999.0'::numeric,
  three_years double precision DEFAULT '-9999.0'::numeric,

  overview TEXT,
 
  CONSTRAINT stock_pkey PRIMARY KEY (symbol)
)
WITH (
OIDS=FALSE
);

CREATE INDEX idx_stock_sp
ON stock_tbl
USING btree
(sp_index COLLATE pg_catalog."default");

CREATE INDEX idx_stock_dow
ON stock_tbl
USING btree
(dow_index COLLATE pg_catalog."default");

CREATE INDEX idx_stock_russell
ON stock_tbl
USING btree
(russell_index COLLATE pg_catalog."default");

CREATE INDEX idx_stock_industry
ON stock_tbl
USING btree
(industry COLLATE pg_catalog."default");

CREATE INDEX idx_stock_sector
ON stock_tbl
USING btree
(sector COLLATE pg_catalog."default");

CREATE INDEX idx_stock_tracks
ON stock_tbl
USING btree
(tracks COLLATE pg_catalog."default");


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

CREATE INDEX idx_treasury_date
  ON treasury_tbl
  USING btree
  (date_tx);
  
