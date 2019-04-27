DROP  TABLE IF EXISTS public.order_items  CASCADE;
CREATE TABLE public.order_items
(

    id serial NOT NULL,
    order_id numeric NOT NULL,
    tissueId numeric(20) NOT NULL,
    name text NOT NULL,
    quantity numeric NOT NULL,
    price numeric(5) NOT NULL,
    total_price numeric(20) NOT NULL,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.orders
    OWNER to msmdmyzv;