/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author 34639
 */
public class VEmpresa extends javax.swing.JFrame {
    aplicacion.FachadaAplicacion fa;
    /**
     * Creates new form Empresa
     * @param fa
     */
    public VEmpresa(aplicacion.FachadaAplicacion fa) {
        initComponents();
        this.fa = fa;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textBox1 = new vista.componentes.TextBox();
        etiqueta1 = new vista.componentes.Etiqueta();
        buttonGroup1 = new javax.swing.ButtonGroup();
        desconectarBoton = new vista.componentes.Boton();
        bajaBoton = new vista.componentes.Boton();
        idTextBox = new vista.componentes.TextBox();
        tipoTextBox = new vista.componentes.TextBox();
        claveTextBox = new vista.componentes.TextBox();
        claveLabel = new vista.componentes.Etiqueta();
        tipoLabel = new vista.componentes.Etiqueta();
        usuarioLabel = new vista.componentes.Etiqueta();
        saldoTextBox = new vista.componentes.TextBox();
        saldoLabel = new vista.componentes.Etiqueta();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabs11 = new vista.componentes.Tabs();
        jScrollPane4 = new javax.swing.JScrollPane();
        movimientosTabla = new javax.swing.JTable();
        tabs9 = new vista.componentes.Tabs();
        jScrollPane3 = new javax.swing.JScrollPane();
        beneficiosTabla = new javax.swing.JTable();
        importeTextBox = new vista.componentes.TextBox();
        importeLabel = new vista.componentes.Etiqueta();
        beneficiosBoton = new vista.componentes.Boton();
        pagarBoton = new vista.componentes.Boton();
        tabs7 = new vista.componentes.Tabs();
        empresaTextBox = new vista.componentes.TextBox();
        precioTextBox = new vista.componentes.TextBox();
        empresaLabel = new vista.componentes.Etiqueta();
        precioLabel = new vista.componentes.Etiqueta();
        jScrollPane1 = new javax.swing.JScrollPane();
        compraTabla = new javax.swing.JTable();
        filtrarLabel = new vista.componentes.Etiqueta();
        compraBoton = new vista.componentes.Boton();
        tabs8 = new vista.componentes.Tabs();
        empresaVentaTextBox = new vista.componentes.TextBox();
        precioVentaTextBox = new vista.componentes.TextBox();
        numeroVentaTextBox = new vista.componentes.TextBox();
        ventaBoton = new vista.componentes.Boton();
        empresaVentaLabel = new vista.componentes.Etiqueta();
        precioVentaLabel = new vista.componentes.Etiqueta();
        numeroVentaLabel = new vista.componentes.Etiqueta();
        jScrollPane2 = new javax.swing.JScrollPane();
        ventaTabla = new javax.swing.JTable();
        tabs10 = new vista.componentes.Tabs();
        participacionesLabel = new vista.componentes.Etiqueta();
        totalTextBox = new vista.componentes.TextBox();
        participacionesTextBox = new vista.componentes.TextBox();
        participacionesBoton = new vista.componentes.Boton();
        totalLabel = new vista.componentes.Etiqueta();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desconectarBoton.setText("Desconectar");
        desconectarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desconectarBotonActionPerformed(evt);
            }
        });

        bajaBoton.setText("Solicitud de baja");
        bajaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajaBotonActionPerformed(evt);
            }
        });

        idTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTextBoxActionPerformed(evt);
            }
        });

        tipoTextBox.setEditable(false);
        tipoTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoTextBoxActionPerformed(evt);
            }
        });

        claveLabel.setText("Contraseña:");

        tipoLabel.setText("Tipo:");

        usuarioLabel.setText("Usuario:");

        saldoTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saldoTextBoxActionPerformed(evt);
            }
        });

        saldoLabel.setText("Saldo");

        movimientosTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(movimientosTabla);

        javax.swing.GroupLayout tabs11Layout = new javax.swing.GroupLayout(tabs11);
        tabs11.setLayout(tabs11Layout);
        tabs11Layout.setHorizontalGroup(
            tabs11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs11Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        tabs11Layout.setVerticalGroup(
            tabs11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs11Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lista de movimientos", tabs11);

        beneficiosTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(beneficiosTabla);

        importeLabel.setText("Importe por participación");

        beneficiosBoton.setText("Anunciar beneficios");
        beneficiosBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beneficiosBotonActionPerformed(evt);
            }
        });

        pagarBoton.setText("Pagar");
        pagarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagarBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabs9Layout = new javax.swing.GroupLayout(tabs9);
        tabs9.setLayout(tabs9Layout);
        tabs9Layout.setHorizontalGroup(
            tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs9Layout.createSequentialGroup()
                .addGroup(tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabs9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pagarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabs9Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabs9Layout.createSequentialGroup()
                                .addComponent(importeTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(beneficiosBoton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(tabs9Layout.createSequentialGroup()
                                .addGroup(tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(importeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        tabs9Layout.setVerticalGroup(
            tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabs9Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(importeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importeTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beneficiosBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(pagarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Anunciar beneficios", tabs9);

        empresaTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empresaTextBoxActionPerformed(evt);
            }
        });

        precioTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioTextBoxActionPerformed(evt);
            }
        });

        empresaLabel.setText("Id de Empresa");

        precioLabel.setText("Precio máximo");

        compraTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(compraTabla);

        filtrarLabel.setText("Filtrar");

        compraBoton.setText("Petición de compra");
        compraBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compraBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabs7Layout = new javax.swing.GroupLayout(tabs7);
        tabs7.setLayout(tabs7Layout);
        tabs7Layout.setHorizontalGroup(
            tabs7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs7Layout.createSequentialGroup()
                .addGroup(tabs7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabs7Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(filtrarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabs7Layout.createSequentialGroup()
                        .addGroup(tabs7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabs7Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(empresaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabs7Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(empresaTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44)
                        .addGroup(tabs7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabs7Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(precioTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(precioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabs7Layout.createSequentialGroup()
                .addGap(0, 80, Short.MAX_VALUE)
                .addGroup(tabs7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compraBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );
        tabs7Layout.setVerticalGroup(
            tabs7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(filtrarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabs7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empresaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabs7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empresaTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(compraBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Compra", tabs7);

        ventaBoton.setText("Crear oferta de venta");

        empresaVentaLabel.setText("Empresa");

        precioVentaLabel.setText("Precio total");

        numeroVentaLabel.setText("Número de participaciones");

        ventaTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(ventaTabla);

        javax.swing.GroupLayout tabs8Layout = new javax.swing.GroupLayout(tabs8);
        tabs8.setLayout(tabs8Layout);
        tabs8Layout.setHorizontalGroup(
            tabs8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs8Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(tabs8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(precioVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabs8Layout.createSequentialGroup()
                        .addGroup(tabs8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(empresaVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precioVentaTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                            .addComponent(empresaVentaTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tabs8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(numeroVentaTextBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(numeroVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ventaBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        tabs8Layout.setVerticalGroup(
            tabs8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs8Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(tabs8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empresaVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabs8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empresaVentaTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroVentaTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(precioVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabs8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ventaBoton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(precioVentaTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Venta", tabs8);

        participacionesLabel.setText("Número de participaciones");

        participacionesBoton.setText("Ofertar participaciones");

        totalLabel.setText("Número total de participaciones");

        javax.swing.GroupLayout tabs10Layout = new javax.swing.GroupLayout(tabs10);
        tabs10.setLayout(tabs10Layout);
        tabs10Layout.setHorizontalGroup(
            tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs10Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(participacionesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(participacionesTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabs10Layout.createSequentialGroup()
                .addContainerGap(395, Short.MAX_VALUE)
                .addComponent(participacionesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );
        tabs10Layout.setVerticalGroup(
            tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs10Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(participacionesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(participacionesTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(participacionesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(325, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ofertar participaciones", tabs10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(claveTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(desconectarBoton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bajaBoton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tipoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(saldoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tipoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(claveLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(idTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(claveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(claveTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(desconectarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bajaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saldoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bajaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bajaBotonActionPerformed

    private void idTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTextBoxActionPerformed

    private void tipoTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoTextBoxActionPerformed

    private void saldoTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saldoTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saldoTextBoxActionPerformed

    private void precioTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precioTextBoxActionPerformed

    private void empresaTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empresaTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empresaTextBoxActionPerformed

    private void compraBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compraBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compraBotonActionPerformed

    private void beneficiosBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beneficiosBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_beneficiosBotonActionPerformed

    private void pagarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagarBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pagarBotonActionPerformed

    private void desconectarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desconectarBotonActionPerformed
        // TODO add your handling code here:
        fa.inicializarGUI();
        this.dispose();
    }//GEN-LAST:event_desconectarBotonActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.Boton bajaBoton;
    private vista.componentes.Boton beneficiosBoton;
    private javax.swing.JTable beneficiosTabla;
    private javax.swing.ButtonGroup buttonGroup1;
    private vista.componentes.Etiqueta claveLabel;
    private vista.componentes.TextBox claveTextBox;
    private vista.componentes.Boton compraBoton;
    private javax.swing.JTable compraTabla;
    private vista.componentes.Boton desconectarBoton;
    private vista.componentes.Etiqueta empresaLabel;
    private vista.componentes.TextBox empresaTextBox;
    private vista.componentes.Etiqueta empresaVentaLabel;
    private vista.componentes.TextBox empresaVentaTextBox;
    private vista.componentes.Etiqueta etiqueta1;
    private vista.componentes.Etiqueta filtrarLabel;
    private vista.componentes.TextBox idTextBox;
    private vista.componentes.Etiqueta importeLabel;
    private vista.componentes.TextBox importeTextBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable movimientosTabla;
    private vista.componentes.Etiqueta numeroVentaLabel;
    private vista.componentes.TextBox numeroVentaTextBox;
    private vista.componentes.Boton pagarBoton;
    private vista.componentes.Boton participacionesBoton;
    private vista.componentes.Etiqueta participacionesLabel;
    private vista.componentes.TextBox participacionesTextBox;
    private vista.componentes.Etiqueta precioLabel;
    private vista.componentes.TextBox precioTextBox;
    private vista.componentes.Etiqueta precioVentaLabel;
    private vista.componentes.TextBox precioVentaTextBox;
    private vista.componentes.Etiqueta saldoLabel;
    private vista.componentes.TextBox saldoTextBox;
    private vista.componentes.Tabs tabs10;
    private vista.componentes.Tabs tabs11;
    private vista.componentes.Tabs tabs7;
    private vista.componentes.Tabs tabs8;
    private vista.componentes.Tabs tabs9;
    private vista.componentes.TextBox textBox1;
    private vista.componentes.Etiqueta tipoLabel;
    private vista.componentes.TextBox tipoTextBox;
    private vista.componentes.Etiqueta totalLabel;
    private vista.componentes.TextBox totalTextBox;
    private vista.componentes.Etiqueta usuarioLabel;
    private vista.componentes.Boton ventaBoton;
    private javax.swing.JTable ventaTabla;
    // End of variables declaration//GEN-END:variables
}
