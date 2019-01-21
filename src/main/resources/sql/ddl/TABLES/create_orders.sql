DROP  TABLE IF EXISTS public.orders  CASCADE;
CREATE TABLE public.orders
(
    id serial NOT NULL,
    first_name text NOT NULL,
    last_name text,
    phone_number bigint NOT NULL,
    tissue_id integer NOT NULL,
    quantity numeric(5) NOT NULL,
    address text,
    status integer NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    sending_date timestamp with time zone,
    receving_date timestamp with time zone,
    comments text,
    PRIMARY KEY (id),
    CONSTRAINT unique_client UNIQUE (phone_number, creation_date)

)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.orders
    OWNER to msmdmyzv;