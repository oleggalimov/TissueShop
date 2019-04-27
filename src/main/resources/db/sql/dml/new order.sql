INSERT INTO public.orders
(
    first_name,
    last_name,
    phone_number,
    tissue_id,
    tissue_name,
    quantity,
    tissue_price,
    address,
    status,
    creation_date,
    sending_date,
    receving_date,
    comments
) VALUES
(
	'first_name',
    'last_name',
    '84955555550',
    '5000',
    'tissue_name',
    '10',
    '10.0',
    'address',
    1,
    CURRENT_TIMESTAMP,
    NULL,
    NULL,
    'comment'

);

SELECT * FROM orders;
