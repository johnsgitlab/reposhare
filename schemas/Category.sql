
CREATE TABLE category_tbl
(
  category character varying(126) NOT NULL,
  CONSTRAINT category_pkey PRIMARY KEY (category)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE public.category_tbl
  OWNER TO postgres;

INSERT into category_tbl (category) VALUES ('conflict');
INSERT into category_tbl (category) VALUES ('earnings');
INSERT into category_tbl (category) VALUES ('economy');
INSERT into category_tbl (category) VALUES ('fed');
INSERT into category_tbl (category) VALUES ('foreign');
INSERT into category_tbl (category) VALUES ('politics');
INSERT into category_tbl (category) VALUES ('rates');
INSERT into category_tbl (category) VALUES ('trade');
INSERT into category_tbl (category) VALUES ('unknown');
INSERT into category_tbl (category) VALUES ('weather');
