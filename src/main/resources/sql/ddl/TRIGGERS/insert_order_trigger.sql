CREATE OR REPLACE FUNCTION check_order_insert () RETURNS trigger AS $order_insert$
	DECLARE tissue_quantity public.tissues.quantity%TYPE;
    BEGIN
		-- Проверить, что количество больше ноля
        IF NEW.quantity<=0.0 THEN
            RAISE EXCEPTION 'Tissue quantity is 0';
        END IF;

		SELECT INTO tissue_quantity quantity FROM public.tissues WHERE id=NEW.tissue_id;

		-- Проверить что такая ткань есть
		IF tissue_quantity IS NULL THEN
			RAISE EXCEPTION 'Tissue % is not exists', NEW.tissue_name;
		END IF;
		-- Проверить что запрашиваемое количество есть
		IF (tissue_quantity - NEW.quantity)<0.0 THEN
			RAISE EXCEPTION 'Tissue % is not enough', NEW.tissue_name;
		END IF;
        RETURN NEW;
    END;
$order_insert$ LANGUAGE plpgsql;

CREATE TRIGGER check_order_insert BEFORE INSERT ON orders
  FOR EACH ROW EXECUTE PROCEDURE check_order_insert();

