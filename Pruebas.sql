CREATE OR REPLACE FUNCTION anunciar() RETURNS TRIGGER AS $$
DECLARE
num int;
imp float; 
emp varchar;
BEGIN
    if(new.fechapago) then
        emp = new.empresa;
        num = new.numeroparticipaciones;
        imp = new.importeparticipacion;
        update empresa set participacionesbloqueadas=participacionesbloqueadas+num,saldo=saldo-imp,saldobloqueado=saldobloqueado+imp where id_usuario = emp;
        update participacionesempresa set numparticipaciones=numparticipaciones-num where usuario = emp and empresa = emp;
        
    end if;

    return new;
END;
$$ LANGUAGE PLPGSQL;

--Creamos el trigger

CREATE TRIGGER anunciarbeneficios
    AFTER insert ON public.anunciobeneficios
    FOR EACH ROW
    EXECUTE PROCEDURE public.anunciar();

    drop trigger anunciarbeneficios on anunciobeneficios cascade
    drop function anunciar

    select 

    update participacionesempresa set numparticipaciones=numparticipaciones-2 where empresa ='Lenovo'

    update empresa set saldobloqueado=0,participacionesbloqueadas=0 where id_usuario = 'Lenovo' and saldobloqueado = null 
                and participacionesbloqueadas = null
    

    select * from anunciobeneficios

    select * from empresa 

    select * from participacionesempresa