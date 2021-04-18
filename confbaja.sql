--Creamos la funcion a la que llama el trigger
--Funcion para ver si es un inversor 
CREATE OR REPLACE FUNCTION ISINVERSOR(in id_u varchar) RETURNS BOOLEAN AS $$
DECLARE
	ids varchar;
	resultado boolean;
BEGIN
	ids = (select id_usuario from inversor where id_usuario = id_u);
	resultado = ids is not null;
	
	return resultado;
END;
$$ LANGUAGE PLPGSQL;

--Funcion para ver si es una empresa 
CREATE OR REPLACE FUNCTION ISEMPRESA(in id_u varchar) RETURNS BOOLEAN AS $$
DECLARE
	ids varchar;
	resultado boolean;
BEGIN
	ids = (select id_usuario from empresa where id_usuario = id_u);
	resultado = ids is not null;
	
	return resultado;
END;
$$ LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION CONFIRMACION_BAJA() RETURNS TRIGGER AS $$
DECLARE
   idu varchar;
   part int = 0;
   dinero double precision = 0.0;
BEGIN
	if(new.solicitadobaja=true) then
		if(isempresa(new.id_usuario)=true) then
			dinero = (select saldo from empresa where id_usuario = new.id_usuario );
			part = (select sum(numparticipaciones) from participacionesempresa where usuario = new.id_usuario);
			if(dinero=0.0 and (part = 0 or part is null)) then
				delete from usuario where id_usuario = new.id_usuario;
				delete from empresa where id_usuario = new.id_usuario;
			elseif(part!=0 and part is not null) then
				rollback;
			end if;
			
		elseif(isinversor(new.id_usuario)=true) then
			dinero = (select saldo from inversor where id_usuario = new.id_usuario );
			part = (select sum(numparticipaciones) from participacionesinversor where usuario = new.id_usuario);
			if(dinero=0.0 and (part = 0 or part is null)) then
				delete from usuario where id_usuario = new.id_usuario;
				delete from inversor where id_usuario = new.id_usuario;
			elseif(part!=0 and part is not null) then
				rollback;
			end if;
		end if;
	end if;

	return new;
END;
$$ LANGUAGE PLPGSQL;

--Creamos el trigger

CREATE TRIGGER confirbaja
    AFTER UPDATE OF solicitadobaja
    ON public.usuario
    FOR EACH ROW
    EXECUTE PROCEDURE public.confirmacion_baja();
	
	