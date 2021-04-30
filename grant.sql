GRANT EXECUTE ON FUNCTION public.crypt(text, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.armor(bytea) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.armor(bytea, text[], text[]) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.dearmor(text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.decrypt(bytea, bytea, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.decrypt_iv(bytea, bytea, bytea, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.digest(bytea, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.digest(text, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.encrypt(bytea, bytea, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.encrypt_iv(bytea, bytea, bytea, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.gen_random_bytes(integer) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.gen_random_uuid() TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.gen_salt(text, integer) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.gen_salt(text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.hmac(text, text, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.hmac(bytea, bytea, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_armor_headers(text, OUT key text, OUT value text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_key_id(bytea) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_pub_decrypt(bytea, bytea, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_pub_decrypt(bytea, bytea) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_pub_decrypt(bytea, bytea, text, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_pub_decrypt_bytea(bytea, bytea, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_pub_decrypt_bytea(bytea, bytea, text, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_pub_decrypt_bytea(bytea, bytea) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_pub_encrypt(text, bytea) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_pub_encrypt(text, bytea, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_pub_encrypt_bytea(bytea, bytea, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_pub_encrypt_bytea(bytea, bytea) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_sym_decrypt(bytea, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_sym_decrypt(bytea, text, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_sym_decrypt_bytea(bytea, text, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_sym_decrypt_bytea(bytea, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_sym_encrypt(text, text, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_sym_encrypt(text, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_sym_encrypt_bytea(bytea, text, text) TO alumnogreibd;

GRANT EXECUTE ON FUNCTION public.pgp_sym_encrypt_bytea(bytea, text) TO alumnogreibd;

GRANT ALL ON TABLE public.anunciobeneficios TO alumnogreibd;

GRANT ALL ON TABLE public.emitirparticipaciones TO alumnogreibd;

GRANT ALL ON TABLE public.empresa TO alumnogreibd;

GRANT ALL ON TABLE public.historial TO alumnogreibd;

GRANT ALL ON TABLE public.inversor TO alumnogreibd;

GRANT ALL ON TABLE public.ofertaventa TO alumnogreibd;

GRANT ALL ON TABLE public.participacionesempresa TO alumnogreibd;

GRANT ALL ON TABLE public.participacionesinversor TO alumnogreibd;

GRANT ALL ON TABLE public.regulador TO alumnogreibd;

GRANT ALL ON TABLE public.usuario TO alumnogreibd;

