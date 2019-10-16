
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


ALTER TABLE event_tbl
  OWNER TO postgres;


CREATE INDEX idx_event_date
  ON event_tbl
  USING btree
  (date_tx);


