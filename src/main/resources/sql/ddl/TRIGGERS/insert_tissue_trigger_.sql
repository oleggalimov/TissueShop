CREATE OR REPLACE FUNCTION check_tissue_insert () RETURNS trigger AS $check_insert_trigger$
    BEGIN
        -- Проверить, что указано наименование товара
        IF NEW.name IS NULL THEN
            RAISE EXCEPTION 'name  cannot be null';
        END IF;
        --Проверить, что цена неотрицательная
        IF NEW.price<0.0 THEN
            RAISE EXCEPTION '% price cannot be negative', NEW.name;
        END IF;
        --Проверить что количество неотрицательное
        IF NEW.quantity<0.0 THEN
            RAISE EXCEPTION '% quantity cannot be negative', NEW.name;
        END IF;

        RETURN NEW;
    END;
$check_insert_trigger$ LANGUAGE plpgsql;

CREATE TRIGGER check_insert_tr BEFORE INSERT OR UPDATE ON tissues
  FOR EACH ROW EXECUTE PROCEDURE check_tissue_insert();