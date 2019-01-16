CREATE TABLE public.tissues
(
    id SERIAL NOT NULL,
    name text NOT NULL,
    price numeric(5) NOT NULL,
    quantity numeric(5) NOT NULL,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.tissues
    OWNER to msmdmyzv;

ALTER TABLE public.tissues
    ADD CONSTRAINT name_price_constr UNIQUE (name, price);