
CREATE TABLE fund_tbl
(
  symbol character varying(10) NOT NULL,
  category character varying(126),
  fund_type character varying(126),
  asset_class character varying(126),
  family character varying(126),
  exchange character varying(126),
  status character varying(126),
  front_load double precision,
  back_load double precision,
  expense_ratio double precision,
  minimum double precision,
  alpha double precision,
  beta double precision,
  deviation double precision,
  r_squared double precision,
  morning_stars integer,
  lipper_total integer,
  lipper_consistent integer,
  lipper_preservation integer,
  lipper_tax integer,
  lipper_expense integer,
  ytd double precision,
  four_weeks double precision,
  three_months double precision,
  one_year double precision,
  three_years double precision,
  basic_materials double precision,
  consumer_cyclical double precision,
  financial_services double precision,
  realestate double precision,
  consumer_defensive double precision,
  healthcare double precision,
  utilities double precision,
  communication_services double precision,
  energy double precision,
  industrials double precision,
  technology double precision,
  name character varying(126),
  overview text,
  date_tx date,
  dividend_yield double precision DEFAULT '-9999.0'::numeric,
  one_day double precision DEFAULT ('-9999'::integer)::double precision,
  one_week double precision DEFAULT ('-9999'::integer)::double precision,
  two_weeks double precision DEFAULT ('-9999'::integer)::double precision,
  factor character varying(126),
  fund_sub_type character varying(126),
  morning_rating character varying(126),
  tracks character varying(126),
  last_update date,
  inception date,
  market_cap double precision DEFAULT '-9999'::double precision,
  mean_annual_return double precision DEFAULT '0'::double precision,
  sharpe_ratio double precision DEFAULT '0'::double precision,
  treynor_ratio double precision DEFAULT '0'::double precision,
  ps double precision DEFAULT '0'::double precision,
  pc double precision DEFAULT '-0'::double precision,
  earnings_growth double precision DEFAULT '0'::double precision,
  median_market_cap double precision DEFAULT '0'::double precision,
  bond_maturity double precision DEFAULT '0'::double precision,
  bond_duration double precision DEFAULT '0'::double precision,
  bond_credit_quality double precision DEFAULT '0'::double precision,
  bond_aaa_percent double precision DEFAULT '0'::double precision,
  bond_aa_percent double precision DEFAULT '0'::double precision,
  bond_a_percent double precision DEFAULT '0'::double precision,
  bond_bbb_percent double precision DEFAULT '0'::double precision,
  bond_bb_percent double precision DEFAULT '0'::double precision,
  bond_b_percent double precision DEFAULT '0'::double precision,
  bond_belowb_percent double precision DEFAULT '0'::double precision,
  bond_us_percent double precision DEFAULT '0'::double precision,
  bond_other_percent double precision DEFAULT '0'::double precision,
  bond_position double precision DEFAULT '0'::double precision,
  cash_position double precision DEFAULT '0'::double precision,
  convertible_position double precision DEFAULT '0'::double precision,
  other_position double precision DEFAULT '0'::double precision,
  preferred_position double precision DEFAULT '0'::double precision,
  stock_position double precision DEFAULT '0'::double precision,
  top_holdings text,
  pe double precision DEFAULT '0'::double precision,
  pb double precision DEFAULT '0'::double precision,
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

