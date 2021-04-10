package vista;

import aplicacion.Usuario;
import vista.componentes.ColoresGUI;
import vista.componentes.Fuentes;
import vista.componentes.FuentesGUI;
import vista.componentes.ImagenesGUI;

import javax.swing.*;

public class VRegistro extends javax.swing.JFrame {

    /**
     * Creates new form Registro
     */

    aplicacion.FachadaAplicacion fa;

    public VRegistro(aplicacion.FachadaAplicacion fa) {
        this.fa = fa;
        initComponents();
        configureComponentes();
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setIconImage(ImagenesGUI.getImage("database.png", 128));
        this.setTitle("Mercado de Valores - Iniciar sesión");
    }

    private void configureComponentes() {
        // Configura los componentes introducidos
        botonSalir.configurar(fa, this, null, true);
        ingresoLabel.setFont(FuentesGUI.getFuente(
                FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.TITULO));
        registroLabel.setFont(FuentesGUI.getFuente(
                FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.TITULO));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        regTipo = new javax.swing.JComboBox<>();
        botonCrearSolicitud = new vista.componentes.Boton();
        claveIngresoLabel = new vista.componentes.Etiqueta();
        ingresoLabel = new vista.componentes.Etiqueta();
        telefonoRegLabel = new vista.componentes.Etiqueta();
        registroLabel = new vista.componentes.Etiqueta();
        usuarioIngresoLabel = new vista.componentes.Etiqueta();
        tipoRegLabel = new vista.componentes.Etiqueta();
        nombreRegLabel = new vista.componentes.Etiqueta();
        CIFRegLabel = new vista.componentes.Etiqueta();
        idRegLabel = new vista.componentes.Etiqueta();
        claveRegLabel = new vista.componentes.Etiqueta();
        direccionRegLabel = new vista.componentes.Etiqueta();
        ingresoUsuario = new vista.componentes.TextBox();
        tipoReg = new vista.componentes.TextBox();
        nombreReg = new vista.componentes.TextBox();
        botonAceptarIngreso = new vista.componentes.Boton();
        nombreReg1 = new vista.componentes.TextBox();
        nombreReg2 = new vista.componentes.TextBox();
        nombreReg4 = new vista.componentes.TextBox();
        botonSalir = new vista.componentes.BotonVolver();
        ingresoClave = new vista.componentes.PasswordField();
        regClave = new vista.componentes.PasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        regTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Empresa", "Inversor", " "}));
        regTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regTipoActionPerformed(evt);
            }
        });

        botonCrearSolicitud.setText("Crear solicitud de registro");
        botonCrearSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarRegistroActionPerformed(evt);
            }
        });

        claveIngresoLabel.setText("Clave:");

        ingresoLabel.setText("Ingreso");

        telefonoRegLabel.setText("Teléfono:");

        registroLabel.setText("Registro");

        usuarioIngresoLabel.setText("Usuario:");

        tipoRegLabel.setText("Tipo:");

        nombreRegLabel.setText("Nombre:");

        CIFRegLabel.setText("CIF/DNI:");

        idRegLabel.setText("ID de usuario:");

        claveRegLabel.setText("Clave:");

        direccionRegLabel.setText("Dirección:");

        ingresoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresoUsuarioActionPerformed(evt);
            }
        });

        tipoReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoRegActionPerformed(evt);
            }
        });

        nombreReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreRegActionPerformed(evt);
            }
        });

        botonAceptarIngreso.setText("Aceptar");
        botonAceptarIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarIngresocancelarRegistroActionPerformed(evt);
            }
        });

        nombreReg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreReg1ActionPerformed(evt);
            }
        });

        nombreReg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreReg2ActionPerformed(evt);
            }
        });

        nombreReg4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreReg4ActionPerformed(evt);
            }
        });

        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        ingresoClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresoClaveActionPerformed(evt);
            }
        });

        regClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regClaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(248, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(ingresoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(usuarioIngresoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(botonAceptarIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(claveIngresoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(48, 48, 48)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(ingresoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                                                .addComponent(ingresoClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(direccionRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(claveRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(idRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(telefonoRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(CIFRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nombreRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tipoRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(registroLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(botonCrearSolicitud, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                                        .addComponent(nombreReg4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nombreReg1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nombreReg2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nombreReg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tipoReg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(regTipo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 386, Short.MAX_VALUE)
                                        .addComponent(regClave, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
                                .addGap(225, 225, 225))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(registroLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ingresoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(usuarioIngresoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ingresoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tipoRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(regTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(claveIngresoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nombreRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tipoReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ingresoClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(nombreReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CIFRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botonAceptarIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(telefonoRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nombreReg2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(idRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nombreReg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(claveRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(regClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(direccionRegLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nombreReg4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(botonCrearSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(88, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarRegistroActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelarRegistroActionPerformed

    private void regTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regTipoActionPerformed

    private void botoncancelarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton2cancelarRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton2cancelarRegistroActionPerformed

    private void ingresoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingresoUsuarioActionPerformed

    private void tipoRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoRegActionPerformed

    private void nombreRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreRegActionPerformed

    private void botonAceptarIngresocancelarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarIngresocancelarRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAceptarIngresocancelarRegistroActionPerformed

    private void nombreReg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreReg1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreReg1ActionPerformed

    private void nombreReg2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreReg2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreReg2ActionPerformed

    private void nombreReg4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreReg4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreReg4ActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonSalirActionPerformed

    private void ingresoClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresoClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingresoClaveActionPerformed

    private void regClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regClaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.Etiqueta CIFRegLabel;
    private vista.componentes.Boton botonAceptarIngreso;
    private vista.componentes.Boton botonCrearSolicitud;
    private vista.componentes.BotonVolver botonSalir;
    private vista.componentes.Etiqueta claveIngresoLabel;
    private vista.componentes.Etiqueta claveRegLabel;
    private vista.componentes.Etiqueta direccionRegLabel;
    private vista.componentes.Etiqueta idRegLabel;
    private vista.componentes.PasswordField ingresoClave;
    private vista.componentes.Etiqueta ingresoLabel;
    private vista.componentes.TextBox ingresoUsuario;
    private vista.componentes.TextBox nombreReg;
    private vista.componentes.TextBox nombreReg1;
    private vista.componentes.TextBox nombreReg2;
    private vista.componentes.TextBox nombreReg4;
    private vista.componentes.Etiqueta nombreRegLabel;
    private vista.componentes.PasswordField regClave;
    private javax.swing.JComboBox<String> regTipo;
    private vista.componentes.Etiqueta registroLabel;
    private vista.componentes.Etiqueta telefonoRegLabel;
    private vista.componentes.TextBox tipoReg;
    private vista.componentes.Etiqueta tipoRegLabel;
    private vista.componentes.Etiqueta usuarioIngresoLabel;
    // End of variables declaration//GEN-END:variables

    public void validarUsuario() {
        String nombreU, contraseña;

        Usuario user;

        nombreU = ingresoUsuario.getText();
        contraseña = ingresoClave.getText();

        String tipo = fa.validarUsuario(nombreU, contraseña);

        if (tipo == null) {
            System.out.println("Usuario no encontrado");
        } else if (tipo.equals("Inversor")) {
            fa.menuUsuarios(tipo, nombreU, contraseña);
            System.out.println("Usuario encontrado, es inversor");
            this.dispose();
        } else if (tipo.equals("Empresa")) {
            fa.menuEmpresa(tipo, nombreU, contraseña);
            System.out.println("Usuario encontrado, es empresa");
            this.dispose();
        } else if (tipo.equals("Regulador")) {
            fa.menuRegulador(tipo, nombreU, contraseña);
            System.out.println("Usuario encontrado, es regulador");
            this.dispose();
        }
    }
}
