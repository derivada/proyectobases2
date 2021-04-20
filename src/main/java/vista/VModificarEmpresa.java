/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import aplicacion.FachadaAplicacion;
import aplicacion.Empresa;

/**
 *
 * @author Pablo M
 */
public class VModificarEmpresa extends javax.swing.JFrame {

    
    
    private final FachadaAplicacion fa;
    private final Empresa e;
    /**
     * Creates new form VModificarEmpresa
     */
    public VModificarEmpresa(Empresa e, FachadaAplicacion fa) {
        this.fa=fa;
        this.e=e;
        initComponents();
        
        this.actualizarCampos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idTextBox = new vista.componentes.TextBox();
        telefonoTextBox = new vista.componentes.TextBox();
        claveLabel = new vista.componentes.Etiqueta();
        nombreLabel = new vista.componentes.Etiqueta();
        clave = new vista.componentes.PasswordField();
        direccionTextBox = new vista.componentes.TextBox();
        modificarBoton = new vista.componentes.Boton();
        dniLabel = new vista.componentes.Etiqueta();
        direccionLabel = new vista.componentes.Etiqueta();
        idLabel = new vista.componentes.Etiqueta();
        claveConf = new vista.componentes.PasswordField();
        claveConfLabel = new vista.componentes.Etiqueta();
        nombreTextBox = new vista.componentes.TextBox();
        telefonoLabel = new vista.componentes.Etiqueta();
        dniTextBox = new vista.componentes.TextBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        idTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTextBoxActionPerformed(evt);
            }
        });

        telefonoTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoTextBoxActionPerformed(evt);
            }
        });

        claveLabel.setText("Clave:");

        nombreLabel.setText("Nombre Comercial:");

        clave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveActionPerformed(evt);
            }
        });

        direccionTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccionTextBoxActionPerformed(evt);
            }
        });

        modificarBoton.setText("Guardar modificaciones");
        modificarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBotoncancelarRegistroActionPerformed(evt);
            }
        });

        dniLabel.setText("CIF:");

        direccionLabel.setText("Dirección:");

        idLabel.setText("ID de usuario:");

        claveConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveConfActionPerformed(evt);
            }
        });

        claveConfLabel.setText("Confirmación de la clave:");

        nombreTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreTextBoxActionPerformed(evt);
            }
        });

        telefonoLabel.setText("Teléfono:");

        dniTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dniTextBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(modificarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(telefonoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefonoTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nombreTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(direccionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dniLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(claveConfLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(claveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(claveConf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(direccionTextBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dniTextBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clave, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(dniLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dniTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(claveConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveConfLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(telefonoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(direccionTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(direccionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(modificarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTextBoxActionPerformed

    private void telefonoTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoTextBoxActionPerformed

    private void claveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_claveActionPerformed

    private void direccionTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccionTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionTextBoxActionPerformed

    private void modificarBotoncancelarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBotoncancelarRegistroActionPerformed
        this.modificarEmpresa();
    }//GEN-LAST:event_modificarBotoncancelarRegistroActionPerformed

    private void claveConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claveConfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_claveConfActionPerformed

    private void nombreTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreTextBoxActionPerformed

    private void dniTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dniTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dniTextBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.PasswordField clave;
    private vista.componentes.PasswordField claveConf;
    private vista.componentes.Etiqueta claveConfLabel;
    private vista.componentes.Etiqueta claveLabel;
    private vista.componentes.Etiqueta direccionLabel;
    private vista.componentes.TextBox direccionTextBox;
    private vista.componentes.Etiqueta dniLabel;
    private vista.componentes.TextBox dniTextBox;
    private vista.componentes.Etiqueta idLabel;
    private vista.componentes.TextBox idTextBox;
    private vista.componentes.Boton modificarBoton;
    private vista.componentes.Etiqueta nombreLabel;
    private vista.componentes.TextBox nombreTextBox;
    private vista.componentes.Etiqueta telefonoLabel;
    private vista.componentes.TextBox telefonoTextBox;
    // End of variables declaration//GEN-END:variables

    public void modificarEmpresa(){
        Empresa empresa=new Empresa(this.idTextBox.getText(),this.nombreTextBox.getText(),this.dniTextBox.getText(),0.0f,0.0f,this.direccionTextBox.getText(),
        this.telefonoTextBox.getText(),false,false);
        String pass;
        boolean insertado=false;
        
        //comprobamos que los campos que no pueden estar vacios no esten vacios
        if(this.idTextBox.getText().isEmpty() || this.clave.getText().isEmpty() || this.claveConf.getText().isEmpty() || this.nombreTextBox.getText().isEmpty() || this.dniTextBox.getText().isEmpty()){
            fa.muestraExcepcion("Recuerda que los campos de ID, clave, nombre y DNI/CIF no pueden estar vacíos.");//muestro la excepcion y retorno sin hacer nada mas
            return;
        }
        
        if(this.idTextBox.getText().equals(e.getIdUsuario())){//si no cambio el ID, fantastico, no hay que comprobar que este libre ni guardarlo
            if(this.clave.getText().equals(this.claveConf.getText())){
                pass=this.clave.getText();
                insertado=fa.modificarEmpresa(empresa, pass, e.getIdUsuario());
            }
            else{
                fa.muestraExcepcion("¡Las contraseñas no coinciden!");
            }
        }
        else{
            if(fa.comprobarID(this.idTextBox.getText())){
                if(this.clave.getText().equals(this.claveConf.getText())){
                    pass=this.clave.getText();
                    insertado=fa.modificarEmpresa(empresa, pass, e.getIdUsuario());
                }
                else{
                    fa.muestraExcepcion("¡Las contraseñas no coinciden!");
                }
            }
            else{
                fa.muestraExcepcion("ID En Uso");
            }
        }
        
        if(insertado){
            e.setIdUsuario(this.idTextBox.getText());
            e.setNombre(this.nombreTextBox.getText());
            e.setDireccion(this.direccionTextBox.getText());
            e.setTelefono(this.telefonoTextBox.getText());
            e.setCIF(this.dniTextBox.getText());
        }
        else{
            fa.muestraExcepcion("No se pudo modificar la base de datos.");
        }
    }
    
    private void actualizarCampos(){
        this.idTextBox.setText(e.getIdUsuario());
        this.nombreTextBox.setText(e.getNombre());
        this.telefonoTextBox.setText(e.getTelefono());
        this.clave.setText(e.getClave());
        this.claveConf.setText(e.getClave());
        this.dniTextBox.setText(e.getCIF());
        this.direccionTextBox.setText(e.getDireccion());
    }

}
