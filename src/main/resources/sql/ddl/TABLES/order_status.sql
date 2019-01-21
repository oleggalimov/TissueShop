DROP TABLE IF EXISTS public.order_status;

CREATE TABLE public.order_status
(
    id serial NOT NULL,
    title text NOT NULL UNIQUE,
    PRIMARY KEY (id)
    CONSTRAINT unique_title UNIQUE (title)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.order_status
    OWNER to msmdmyzv;

INSERT INTO public.order_status
    (
        title
    )
VALUES
    ('НОВЫЙ'),
    ('ПОДТВЕРЖДЕН'),
    ('ПОДГОТОВКА К ОТПРАВКЕ'),
    ('ОТПРАВЛЕН'),
    ('ПОЛУЧЕН'),
    ('НЕ ПОДТВЕРЖДЕН');