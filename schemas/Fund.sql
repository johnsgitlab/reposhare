
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
  other_position double precision DEFAULT DEFAULT '0.0'::numeric,
  preferred_position double precision DEFAULT '0.0'::numeric,
  stock_position double precision DEFAULT '0.0'::numeric,

  top_holdings text,
  overview text,

  CONSTRAINT fund_tbl_pkey PRIMARY KEY (symbol)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.fund_tbl
  OWNER TO postgres;


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

