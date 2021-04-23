package vista;

import aplicacion.FachadaAplicacion;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;

import vista.componentes.ColoresGUI;
import vista.componentes.DialogoInfo;
import vista.componentes.FuentesGUI;

public class VentanaConfirmacion extends javax.swing.JDialog {

    private String mensajePregunta, mensajeCancelacion, mensajeConfirmacion;
    private Connection con;
    private boolean aceptar = false;

    public VentanaConfirmacion() {
        // No usar, requerido por Netbeans
        initComponents();
    }

    public VentanaConfirmacion(JFrame owner, Connection con, String mensajePregunta, String mensajeConfirmacion, String mensajeCancelacion) {
        super(owner, true);
        initComponents();
        this.botonAceptar.setBackground(ColoresGUI.getGUIColorClaro(ColoresGUI.Colores.VERDE));
        this.botonCancelar.setBackground(ColoresGUI.getGUIColorClaro(ColoresGUI.Colores.ROJO));
        this.mensajePregunta = mensajePregunta;
        this.mensajeConfirmacion = mensajeConfirmacion;
        this.mensajeCancelacion = mensajeCancelacion;
        this.mensajeConfirmacionText.setText(mensajePregunta);
        this.con = con;
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        confirmacionLabel = new vista.componentes.Etiqueta();
        botonCancelar = new vista.componentes.Boton();
        botonAceptar = new vista.componentes.Boton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mensajeConfirmacionText = new vista.componentes.AreaTexto();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        confirmacionLabel.setText("Confirmación");

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        mensajeConfirmacionText.setColumns(20);
        mensajeConfirmacionText.setRows(5);
        jScrollPane1.setViewportView(mensajeConfirmacionText);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(confirmacionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                                                .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(confirmacionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(botonAceptar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botonCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40))
        );

        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL,
                FuentesGUI.Size.GRANDE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        this.aceptar = true;
        formWindowClosing(null);
        dispose();
        // Para meter funcionalidad, añadir listeners propios
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        formWindowClosing(null);
        dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            if (aceptar) {
                con.commit();
                FachadaAplicacion.getInstance().muestraExcepcion(mensajeConfirmacion, DialogoInfo.NivelDeAdvertencia.INFORMACION);
            } else {
                con.rollback();
                FachadaAplicacion.getInstance().muestraExcepcion(mensajeCancelacion, DialogoInfo.NivelDeAdvertencia.INFORMACION);
            }
        } catch (SQLException ex) {
            FachadaAplicacion.getInstance().muestraExcepcion("Error al confirmar la transacción!");
            ex.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("No se pudo cambiar el auto-commit a true en VentanaConfirmacion");
            }
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.Boton botonAceptar;
    private vista.componentes.Boton botonCancelar;
    private vista.componentes.Etiqueta confirmacionLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private vista.componentes.AreaTexto mensajeConfirmacionText;
    // End of variables declaration//GEN-END:variables

}
