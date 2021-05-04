Cambios de los scripts desde la 1º entrega a la entrega final:

    1. Introducción de la encriptación de las contraseñas cifradas con la extensión pgcrypto:
        - En el script de creación se introdujo la instrucción
            Create extension pgcrypto;
        - En el script de inserción se introdujeron todas las contraseñas usando la función crypt:
            crypt('password', gen_salt('bf', 4))

    2. Valores por defecto añadidos: 
        - En "empresa" las columnnas saldo, saldobloqueado y participacionesbloqueadas a 0
        - En "regulador" la columna saldo a 0
        - En "inversor" la columna saldo a 0
        - En "anunciobeneficios" la columna solicitadobaja a false 
        