package vista;

import vista.componentes.FuentesGUI;
import aplicacion.FachadaAplicacion;
import aplicacion.Historial;
import aplicacion.Inversor;
import java.util.List;
import vista.componentes.OtrosComponentes;
import vista.modeloTablas.ModeloTablaCompra;
import vista.modeloTablas.ModeloTablaMovimientos;
import vista.modeloTablas.ModeloTablaVenta;

public class VInversor extends javax.swing.JFrame {

    private final FachadaAplicacion fa;
    private final Inversor i;

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
        tabs1 = new vista.componentes.Tabs();
        precioLabel = new vista.componentes.Etiqueta();
        filtrarLabel = new vista.componentes.Etiqueta();
        compraBoton = new vista.componentes.Boton();
        empresaTextBox = new vista.componentes.TextBox();
        precioTextBox = new vista.componentes.TextBox();
        empresaLabel = new vista.componentes.Etiqueta();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla1 = new vista.componentes.Tabla();
        compraParticipacionesPanel1 = new vista.CompraParticipacionesPanel(i, fa);
        ventaPanel = new vista.VentaParticipacionesPanel(i, fa);
        tabs3 = new vista.componentes.Tabs();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla2 = new vista.componentes.Tabla();
        modificarBoton = new vista.componentes.Boton();
        bienvenidoLabel = new vista.componentes.Etiqueta();
        botonVolver1 = new vista.componentes.BotonVolver();

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

        precioLabel.setText("Precio máximo");

        filtrarLabel.setText("Filtrar");

        compraBoton.setText("Petición de compra");
        compraBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compraBotonActionPerformed(evt);
            }
        });

        empresaTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empresaTextBoxActionPerformed(evt);
            }
        });
        empresaTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                empresaTextBoxKeyPressed(evt);
            }
        });

        precioTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioTextBoxActionPerformed(evt);
            }
        });
        precioTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                precioTextBoxKeyPressed(evt);
            }
        });

        empresaLabel.setText("Id de Empresa");

        tabla1.setModel(new ModeloTablaCompra());
        jScrollPane3.setViewportView(tabla1);

        javax.swing.GroupLayout tabs1Layout = new javax.swing.GroupLayout(tabs1);
        tabs1.setLayout(tabs1Layout);
        tabs1Layout.setHorizontalGroup(
            tabs1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs1Layout.createSequentialGroup()
                .addGroup(tabs1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabs1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(filtrarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabs1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(tabs1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(compraBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tabs1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(tabs1Layout.createSequentialGroup()
                                    .addGroup(tabs1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(empresaTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(empresaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(54, 54, 54)
                                    .addGroup(tabs1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(precioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(precioTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        tabs1Layout.setVerticalGroup(
            tabs1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(filtrarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tabs1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(precioLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(empresaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabs1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empresaTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(compraBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        panelCompra.addTab("Compra", tabs1);
        panelCompra.addTab("Compra", compraParticipacionesPanel1);
        panelCompra.addTab("Venta", ventaPanel);

        tabla2.setModel(new ModeloTablaMovimientos()
        );
        tabla2.setToolTipText("");
        jScrollPane4.setViewportView(tabla2);

        javax.swing.GroupLayout tabs3Layout = new javax.swing.GroupLayout(tabs3);
        tabs3.setLayout(tabs3Layout);
        tabs3Layout.setHorizontalGroup(
            tabs3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabs3Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        tabs3Layout.setVerticalGroup(
            tabs3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        panelCompra.addTab("Lista de movimientos", tabs3);

        modificarBoton.setText("Modificar Usuario");
        modificarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBotonActionPerformed(evt);
            }
        });

        bienvenidoLabel.setText("Bienvenid@, " +i.getIdUsuario());
        bienvenidoLabel.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.GRANDE));

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
                            .addGap(160, 160, 160)))
                    .addComponent(saldoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modificarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bajaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
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
                        .addComponent(botonVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        OtrosComponentes.configurarTabbedPane(panelCompra);
        botonVolver1.configurar(fa, this, false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saldoTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saldoTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saldoTextBoxActionPerformed

    private void idTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTextBoxActionPerformed

    private void tipoTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoTextBoxActionPerformed

    private void bajaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaBotonActionPerformed
        fa.solicitarBaja(i.getIdUsuario());
    }//GEN-LAST:event_bajaBotonActionPerformed


    private void compraBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compraBotonActionPerformed
        // TODO add your handling code here:
        this.compraParticipaciones();
    }//GEN-LAST:event_compraBotonActionPerformed

    private void empresaTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empresaTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empresaTextBoxActionPerformed

    private void precioTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precioTextBoxActionPerformed

    private void modificarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBotonActionPerformed
        fa.menuModificarInversor(i);
    }//GEN-LAST:event_modificarBotonActionPerformed

    private void empresaTextBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_empresaTextBoxKeyPressed
        // TODO add your handling code here:
        this.buscarOfertas();
    }//GEN-LAST:event_empresaTextBoxKeyPressed

    private void precioTextBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioTextBoxKeyPressed
        // TODO add your handling code here:
        this.buscarOfertas();
    }//GEN-LAST:event_precioTextBoxKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.Boton bajaBoton;
    private vista.componentes.Etiqueta bienvenidoLabel;
    private vista.componentes.BotonVolver botonVolver1;
    private vista.componentes.Boton compraBoton;
    private vista.CompraParticipacionesPanel compraParticipacionesPanel1;
    private vista.componentes.Etiqueta empresaLabel;
    private vista.componentes.TextBox empresaTextBox;
    private vista.componentes.Etiqueta filtrarLabel;
    private vista.componentes.TextBox idTextBox;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private vista.componentes.Boton modificarBoton;
    private javax.swing.JTabbedPane panelCompra;
    private vista.componentes.Etiqueta precioLabel;
    private vista.componentes.TextBox precioTextBox;
    private vista.componentes.Etiqueta saldoLabel;
    private vista.componentes.TextBox saldoTextBox;
    private vista.componentes.Tabla tabla1;
    private vista.componentes.Tabla tabla2;
    private vista.componentes.Tabs tabs1;
    private vista.componentes.Tabs tabs3;
    private vista.componentes.Etiqueta tipoLabel;
    private vista.componentes.TextBox tipoTextBox;
    private vista.componentes.Etiqueta usuarioLabel;
    private vista.VentaParticipacionesPanel ventaPanel;
    // End of variables declaration//GEN-END:variables

    public void huecos(){
        idTextBox.setText(i.getIdUsuario());
        // (Ver VEmpresa) claveTextBox.setText(i.getClave());
        saldoTextBox.setText(String.valueOf(i.getSaldo()));
        tipoTextBox.setText("Inversor");
        empresaTextBox.setText("");
        precioTextBox.setText("0");
        this.buscarOfertas();
        this.actualizarHistorial();
    }
    
    public void buscarOfertas(){
        if(precioTextBox.getText().equals("")){
            precioTextBox.setText("0");
        }
        ModeloTablaCompra m;

        m=(ModeloTablaCompra) tabla1.getModel();
        m.setFilas(fa.getOfertasVenta(empresaTextBox.getText(), Integer.parseInt(precioTextBox.getText())));
        if (m.getRowCount() > 0) {
            tabla1.setRowSelectionInterval(0, 0);
            compraBoton.setEnabled(true);
        }
        else compraBoton.setEnabled(false);
    }
    
    public void compraParticipaciones(){
        //en esta funcion se efectuará la compra de acciones
        
        //cuando se compran acciones, tenemos que comprobar a que empresa se refiere, por lo queel usuario tendrá que selccionar una oferta de compra para comprobar la empresa
        
        //aunq seleccione una, podrá comprar varias ofertas de compra si lo necesita para completar el número total de participaciones que quiere
        
        //se compraran las máximas posibles sin pasarnos del precio máximo y se ordenan de menor a mayor precio
        
        //en cuannto se hace la seleccion completa de las ofertas, se guarda y se calcula el precio de manera que no se pase con el saldo
        
        //si se tiene suficiente saldo, se efectua la transaccion, eliminando las ofertas de venta de la tabla de ofertas y metiendolas al historial como una sola operacion, ademas, se acutalizan las carteras tanto del comprador como del vendedor
        
    }
    
    public void actualizarHistorial(){
        
        ModeloTablaMovimientos m = (ModeloTablaMovimientos) tabla2.getModel();
        
        List<Historial> historial = fa.actualizarHistorial(i);
        m.setFilas(historial);
    
    }

}
