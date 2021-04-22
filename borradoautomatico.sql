-- Borra tuplas automáticamente de las relaciones ofertaventa, participacionesinversor
-- y participacionesempresa cuando el número de participaciones queda a 0

-- Borrar tupla de participacionesinversor cuando el número de participaciones queda a 0
CREATE OR REPLACE FUNCTION borrar_inversor() RETURNS TRIGGER AS $$
DECLARE
us varchar;
emp varchar;
BEGIN
    if(new.numparticipaciones=0) then
        us = new.usuario;
        emp = new.empresa;
        delete from participacionesinversor
        where usuario = us
        and empresa = emp;
    end if;

    return new;
END;
$$ LANGUAGE PLPGSQL;

--Creamos el trigger

CREATE TRIGGER borrarparticipaciones_inversor
    AFTER UPDATE OF numparticipaciones
    ON public.participacionesinversor
    FOR EACH ROW
    EXECUTE PROCEDURE public.borrar_inversor();

	-- Borrar tupla de participacionesempresa cuando el número de participaciones queda a 0
CREATE OR REPLACE FUNCTION borrar_empresa() RETURNS TRIGGER AS $$
DECLARE
us varchar;
emp varchar;
BEGIN
    if(new.numparticipaciones=0) then
        us = new.usuario;
        emp = new.empresa;
        delete from participacionesempresa
        where usuario = us
        and empresa = emp;
    end if;

    return new;
END;
$$ LANGUAGE PLPGSQL;

--Creamos el trigger

CREATE TRIGGER borrarparticipaciones_empresa
    AFTER UPDATE OF numparticipaciones
    ON public.participacionesempresa
    FOR EACH ROW
    EXECUTE PROCEDURE public.borrar_empresa();

-- Borrar tupla de ofertaventa cuando el número de participaciones queda a 0
CREATE OR REPLACE FUNCTION borrar_oferta() RETURNS TRIGGER AS $$
DECLARE
us varchar;
emp varchar;
BEGIN
    if(new.numparticipaciones=0) then
        us = new.usuario;
        emp = new.empresa;
        delete from ofertaventa
        where usuario = us
        and empresa = emp;
    end if;

    return new;
END;
$$ LANGUAGE PLPGSQL;

--Creamos el trigger

CREATE TRIGGER borrarparticipaciones_oferta
    AFTER UPDATE OF numparticipaciones
    ON public.ofertaventa
    FOR EACH ROW
    EXECUTE PROCEDURE public.borrar_oferta();



