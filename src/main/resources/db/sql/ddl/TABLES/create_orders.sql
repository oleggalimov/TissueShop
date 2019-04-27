DROP  TABLE IF EXISTS public.orders  CASCADE;
CREATE TABLE public.orders
(
    id serial NOT NULL,
    first_name text NOT NULL,
    last_name text,
    phone_number bigint NOT NULL,
    address text,
    status integer NOT NULL,
    creation_date timestamp NOT NULL,
    sending_date timestamp,
    receving_date timestamp,
    comments text,
    PRIMARY KEY (id),
    CONSTRAINT unique_client UNIQUE (phone_number, creation_date)

)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.orders
    OWNER to msmdmyzv;