package vista;

import vista.componentes.FuentesGUI;
import aplicacion.FachadaAplicacion;
import aplicacion.Inversor;
import vista.componentes.OtrosComponentes;

public class VInversor extends javax.swing.JFrame {

    private final FachadaAplicacion fa;
    private Inversor i;

    public VInversor(Inversor i, FachadaAplicacion fa) {
        this.i = i;
        this.fa = fa;
        initComponents();
        this.huecos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        saldoTextBox = new vista.componentes.TextBox();
        idTextBox = new vista.componentes.TextBox();
        saldoLabel = new vista.componentes.Etiqueta();
        tipoTextBox = new vista.componentes.TextBox();
        bajaBoton = new vista.componentes.Boton();
        tipoLabel = new vista.componentes.Etiqueta();
        usuarioLabel = new vista.componentes.Etiqueta();
        panelCompra = new javax.swing.JTabbedPane();
        compraParticipacionesPanel1 = new vista.CompraParticipacionesPanel(i, fa);
        ventaPanel = new vista.VentaParticipacionesPanel(i, fa);
        modificarBoton = new vista.componentes.Boton();
        bienvenidoLabel = new vista.componentes.Etiqueta();
        botonVolver1 = new vista.componentes.BotonVolver();
        abrirHistorial = new vista.componentes.Boton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        saldoTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saldoTextBoxActionPerformed(evt);
            }
        });

        idTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTextBoxActionPerformed(evt);
            }
        });

        saldoLabel.setText("Saldo");

        tipoTextBox.setEditable(false);
        tipoTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoTextBoxActionPerformed(evt);
            }
        });

        bajaBoton.setText("Solicitud de baja");
        bajaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajaBotonActionPerformed(evt);
            }
        });

        tipoLabel.setText("Tipo");

        usuarioLabel.setText("Usuario:");

        panelCompra.addTab("Compra", compraParticipacionesPanel1);
        panelCompra.addTab("Venta", ventaPanel);

        modificarBoton.setText("Modificar Usuario");
        modificarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBotonActionPerformed(evt);
            }
        });

        bienvenidoLabel.setText("Bienvenid@, " +i.getIdUsuario());
        bienvenidoLabel.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.GRANDE));

        abrirHistorial.setText("Historial");
        abrirHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirHistorialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bienvenidoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(idTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tipoTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
                            .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(botonVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(173, 173, 173)))
                    .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modificarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bajaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saldoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(abrirHistorial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(panelCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bienvenidoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(idTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tipoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tipoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bajaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(modificarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saldoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(abrirHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(panelCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        OtrosComponentes.configurarTabbedPane(panelCompra);
        botonVolver1.configurar(fa, this, false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saldoTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saldoTextBoxActionPerformed
    }//GEN-LAST:event_saldoTextBoxActionPerformed

    private void idTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTextBoxActionPerformed
    }//GEN-LAST:event_idTextBoxActionPerformed

    private void tipoTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoTextBoxActionPerformed
    }//GEN-LAST:event_tipoTextBoxActionPerformed

    private void bajaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaBotonActionPerformed
        fa.solicitarBaja(i.getIdUsuario());
    }//GEN-LAST:event_bajaBotonActionPerformed


    private void modificarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBotonActionPerformed
        fa.menuModificarInversor(i);
    }//GEN-LAST:event_modificarBotonActionPerformed

    private void abrirHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirHistorialActionPerformed
        // TODO add your handling code here:
        new VHistorial(i);
    }//GEN-LAST:event_abrirHistorialActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.Boton abrirHistorial;
    private vista.componentes.Boton bajaBoton;
    private vista.componentes.Etiqueta bienvenidoLabel;
    private vista.componentes.BotonVolver botonVolver1;
    private vista.CompraParticipacionesPanel compraParticipacionesPanel1;
    private vista.componentes.TextBox idTextBox;
    private vista.componentes.Boton modificarBoton;
    private javax.swing.JTabbedPane panelCompra;
    private vista.componentes.Etiqueta saldoLabel;
    private vista.componentes.TextBox saldoTextBox;
    private vista.componentes.Etiqueta tipoLabel;
    private vista.componentes.TextBox tipoTextBox;
    private vista.componentes.Etiqueta usuarioLabel;
    private vista.VentaParticipacionesPanel ventaPanel;
    // End of variables declaration//GEN-END:variables

    public void huecos() {
        idTextBox.setText(i.getIdUsuario());
        // (Ver VEmpresa) claveTextBox.setText(i.getClave());
        saldoTextBox.setText(String.valueOf(i.getSaldo()));
        tipoTextBox.setText("Inversor");
    }

    public void actualizarCampos() {
        i = fa.obtenerDatosInversor(i);
        idTextBox.setText(i.getIdUsuario());
        saldoTextBox.setText(String.valueOf(i.getSaldo()));
        tipoTextBox.setText("Inversor");
    }
}
