/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

/**
 *
 * @author migue
 */
public class Regulador extends Usuario{
    private Float saldo;
    
    public Regulador(String idUsuario,boolean solicitadobaja, boolean autorizado) {
        super(idUsuario,solicitadobaja,autorizado);
        this.saldo=(float)0.0;
    }

    @Override
    public String getIdUsuario() {
        return super.getIdUsuario();
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
}
