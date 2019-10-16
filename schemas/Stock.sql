
CREATE TABLE PUBLIC.stock_tbl
(
  symbol character varying(10) NOT NULL,
  name character varying(126),
  
  sp_index character varying(32),
  dow_index character varying(32),
  russell_index character varying(32),
    
  sector character varying(126),
  industry character varying(126),
  tracks character varying(126),
  exchange character varying(126),

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
  
  date_tx date,
  ytd double precision DEFAULT '0.0'::numeric,
  one_day double precision DEFAULT '0.0'::numeric,
  one_week double precision DEFAULT '0.0'::numeric,
  two_weeks double precision DEFAULT '0.0'::numeric,
  four_weeks double precision DEFAULT '0.0'::numeric,
  three_months double precision DEFAULT '0.0'::numeric,
  one_year double precision DEFAULT '0.0'::numeric,
  three_years double precision DEFAULT '0.0'::numeric,
  overview TEXT,
 
  CONSTRAINT stock_pkey PRIMARY KEY (symbol)
)
WITH (
OIDS=FALSE
);

ALTER TABLE stock_tbl
OWNER TO postgres;


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

