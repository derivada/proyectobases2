package vista;

import aplicacion.Empresa;
import aplicacion.FachadaAplicacion;
import vista.componentes.OtrosComponentes;
import vista.modeloTablas.ModeloTablaBeneficios;
import vista.modeloTablas.ModeloTablaCompra;
import vista.modeloTablas.ModeloTablaMovimientos;
import vista.modeloTablas.ModeloTablaVenta;


public class VEmpresa extends javax.swing.JFrame {
    private final FachadaAplicacion fa;
    private final Empresa e;

    public VEmpresa(Empresa e, FachadaAplicacion fa) {
        this.fa = fa;
        this.e = e;
        initComponents();
        idTextBox.setText(e.getIdUsuario());
        // TODO: Esto debería hacerse con un nuevo acceso, no debería estar
        // guardado permanentemente aquí claveTextBox.setText(e.getClave());
        // También hay un problema importante aquí. El saldo no es parte de la entidad Empresa, deberíamos
        // añadirlo??
        saldoTextBox.setText(String.valueOf(e.getCuenta()));
        tipoTextBox.setText("Empresa");
        this.rellenarHuecos();
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
        claveLabel = new vista.componentes.Etiqueta();
        tipoLabel = new vista.componentes.Etiqueta();
        usuarioLabel = new vista.componentes.Etiqueta();
        saldoTextBox = new vista.componentes.TextBox();
        saldoLabel = new vista.componentes.Etiqueta();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabs11 = new vista.componentes.Tabs();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla1 = new vista.componentes.Tabla();
        tabs9 = new vista.componentes.Tabs();
        importeTextBox = new vista.componentes.TextBox();
        importeLabel = new vista.componentes.Etiqueta();
        beneficiosBoton = new vista.componentes.Boton();
        pagarBoton = new vista.componentes.Boton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla2 = new vista.componentes.Tabla();
        tabs7 = new vista.componentes.Tabs();
        empresaTextBox = new vista.componentes.TextBox();
        precioTextBox = new vista.componentes.TextBox();
        empresaLabel = new vista.componentes.Etiqueta();
        precioLabel = new vista.componentes.Etiqueta();
        filtrarLabel = new vista.componentes.Etiqueta();
        compraBoton = new vista.componentes.Boton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla3 = new vista.componentes.Tabla();
        tabs8 = new vista.componentes.Tabs();
        empresaVentaTextBox = new vista.componentes.TextBox();
        precioVentaTextBox = new vista.componentes.TextBox();
        numeroVentaTextBox = new vista.componentes.TextBox();
        ventaBoton = new vista.componentes.Boton();
        empresaVentaLabel = new vista.componentes.Etiqueta();
        precioVentaLabel = new vista.componentes.Etiqueta();
        numeroVentaLabel = new vista.componentes.Etiqueta();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla4 = new vista.componentes.Tabla();
        tabs10 = new vista.componentes.Tabs();
        participacionesLabel = new vista.componentes.Etiqueta();
        totalTextBox = new vista.componentes.TextBox();
        participacionesTextBox = new vista.componentes.TextBox();
        participacionesBoton = new vista.componentes.Boton();
        totalLabel = new vista.componentes.Etiqueta();
        precioPart = new vista.componentes.TextBox();
        totalLabel1 = new vista.componentes.Etiqueta();
        bajaPArticipaciones = new vista.componentes.Boton();
        claveTextBox = new vista.componentes.PasswordField();
        modificarBoton = new vista.componentes.Boton();

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

        idTextBox.setEditable(false);
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

        tabla1.setModel(new ModeloTablaMovimientos());
        jScrollPane5.setViewportView(tabla1);

        javax.swing.GroupLayout tabs11Layout = new javax.swing.GroupLayout(tabs11);
        tabs11.setLayout(tabs11Layout);
        tabs11Layout.setHorizontalGroup(
            tabs11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs11Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        tabs11Layout.setVerticalGroup(
            tabs11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs11Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lista de movimientos", tabs11);

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

        tabla2.setModel(new ModeloTablaBeneficios());
        jScrollPane4.setViewportView(tabla2);

        javax.swing.GroupLayout tabs9Layout = new javax.swing.GroupLayout(tabs9);
        tabs9.setLayout(tabs9Layout);
        tabs9Layout.setHorizontalGroup(
            tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs9Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pagarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(importeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(tabs9Layout.createSequentialGroup()
                            .addComponent(importeTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(beneficiosBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        filtrarLabel.setText("Filtrar");

        compraBoton.setText("Petición de compra");
        compraBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compraBotonActionPerformed(evt);
            }
        });

        tabla3.setModel(new ModeloTablaCompra());
        tabla3.setToolTipText("");
        jScrollPane3.setViewportView(tabla3);

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
                .addContainerGap(298, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabs7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(tabs7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(compraBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
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
                .addGap(24, 24, 24)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compraBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Compra", tabs7);

        ventaBoton.setText("Crear oferta de venta");

        empresaVentaLabel.setText("Empresa");

        precioVentaLabel.setText("Precio total");

        numeroVentaLabel.setText("Número de participaciones");

        tabla4.setModel(new ModeloTablaVenta());
        jScrollPane1.setViewportView(tabla4);

        javax.swing.GroupLayout tabs8Layout = new javax.swing.GroupLayout(tabs8);
        tabs8.setLayout(tabs8Layout);
        tabs8Layout.setHorizontalGroup(
            tabs8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs8Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(tabs8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(precioVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabs8Layout.createSequentialGroup()
                        .addGroup(tabs8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(empresaVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precioVentaTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(empresaVentaTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(95, 95, 95)
                        .addGroup(tabs8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(numeroVentaTextBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numeroVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ventaBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Venta", tabs8);

        participacionesLabel.setText("Número de participaciones");

        participacionesBoton.setText("Ofertar participaciones");

        totalLabel.setText("Número total de participaciones disponibles");

        totalLabel1.setText("Precio");

        bajaPArticipaciones.setText("Eliminar participaciones");
        bajaPArticipaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajaPArticipacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabs10Layout = new javax.swing.GroupLayout(tabs10);
        tabs10.setLayout(tabs10Layout);
        tabs10Layout.setHorizontalGroup(
            tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabs10Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(participacionesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(participacionesTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bajaPArticipaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioPart, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(participacionesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92))
        );
        tabs10Layout.setVerticalGroup(
            tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs10Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(participacionesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(participacionesTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioPart, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(participacionesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bajaPArticipaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(252, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ofertar participaciones", tabs10);

        modificarBoton.setText("Modificar usuario");
        modificarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desconectarBoton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bajaBoton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tipoTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(saldoTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(tipoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(claveTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modificarBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(3, 3, 3)
                        .addComponent(claveTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(desconectarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bajaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(modificarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saldoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        OtrosComponentes.configurarTabbedPane(jTabbedPane1);

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

    private void modificarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarBotonActionPerformed

    private void bajaPArticipacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaPArticipacionesActionPerformed
        // TODO add your handling code here:
        this.eliminarParticipaciones();
    }//GEN-LAST:event_bajaPArticipacionesActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.Boton bajaBoton;
    private vista.componentes.Boton bajaPArticipaciones;
    private vista.componentes.Boton beneficiosBoton;
    private javax.swing.ButtonGroup buttonGroup1;
    private vista.componentes.Etiqueta claveLabel;
    private vista.componentes.PasswordField claveTextBox;
    private vista.componentes.Boton compraBoton;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private vista.componentes.Boton modificarBoton;
    private vista.componentes.Etiqueta numeroVentaLabel;
    private vista.componentes.TextBox numeroVentaTextBox;
    private vista.componentes.Boton pagarBoton;
    private vista.componentes.Boton participacionesBoton;
    private vista.componentes.Etiqueta participacionesLabel;
    private vista.componentes.TextBox participacionesTextBox;
    private vista.componentes.Etiqueta precioLabel;
    private vista.componentes.TextBox precioPart;
    private vista.componentes.TextBox precioTextBox;
    private vista.componentes.Etiqueta precioVentaLabel;
    private vista.componentes.TextBox precioVentaTextBox;
    private vista.componentes.Etiqueta saldoLabel;
    private vista.componentes.TextBox saldoTextBox;
    private vista.componentes.Tabla tabla1;
    private vista.componentes.Tabla tabla2;
    private vista.componentes.Tabla tabla3;
    private vista.componentes.Tabla tabla4;
    private vista.componentes.Tabs tabs10;
    private vista.componentes.Tabs tabs11;
    private vista.componentes.Tabs tabs7;
    private vista.componentes.Tabs tabs8;
    private vista.componentes.Tabs tabs9;
    private vista.componentes.TextBox textBox1;
    private vista.componentes.Etiqueta tipoLabel;
    private vista.componentes.TextBox tipoTextBox;
    private vista.componentes.Etiqueta totalLabel;
    private vista.componentes.Etiqueta totalLabel1;
    private vista.componentes.TextBox totalTextBox;
    private vista.componentes.Etiqueta usuarioLabel;
    private vista.componentes.Boton ventaBoton;
    // End of variables declaration//GEN-END:variables

    public void emitirParticipaciones(){
        
        //en esta funcion, la empresa indica que quiere crear un nuevo lote de participaciones
        
        //cuando lo hace, tendrán que añadirse las participaciones a su cartera de participaciones
        
        //esta cartera puede tener participacioens de otras empresas asi que debemos identificar
        //la tabla de esta empresa en su cartera y sumarlas ahi, es decir no creamos un objeto participaciones
        //cada vez que se añaden, si no que se suman a esa tabla que tiene como PK a esta empresa, y como PKp a esta empresa de nuevo
        
        String participaciones = participacionesTextBox.getText();
        
        int emision = Integer.parseInt(participaciones);
        
        int precio = Integer.parseInt(precioPart.getText());
        
        fa.emitirParticipaciones(e, emision, precio);
        
        this.rellenarHuecos();
           
    }
    
    public void eliminarParticipaciones(){
        
        //en este caso, la empresa solicita eliminar ciertas participaciones de su cartera
        
        //la cuestion seria saber cuales estan vendidas y cuales no, de manera que elimine unicamente aquellas que no estan vendidas, y si no hay suficientes pues elimine las que hay sin vender
        
        String participaciones = participacionesTextBox.getText();
        
        int bajaP = Integer.parseInt(participaciones);
        
        //aqui iria la consulta, cuando me apetezca la hago
        
        fa.bajaParticipaciones(e, bajaP);
        
        this.rellenarHuecos();
        
    }
    
    
    public void setBotones(){
        
        if(participacionesTextBox.getText().equals("") || participacionesTextBox.getText().equals("Introduzca un número.")){
            participacionesBoton.setEnabled(false);
        }else{
            participacionesBoton.setEnabled(true);
        }
  
    }
    
    public void rellenarHuecos(){
        totalTextBox.setEditable(false);
        idTextBox.setText(e.getIdUsuario());
        saldoTextBox.setText(String.valueOf(e.getCuenta()));
        tipoTextBox.setText("Empresa");
        // TODO: Esto debería hacerse con un nuevo acceso, no debería estar
        // guardado permanentemente aquí claveTextBox.setText(e.getClave());
        // También hay un problema importante aquí. El saldo no es parte de la entidad Empresa, deberíamos
        // añadirlo??
        
        //la funcion getPartPropEmpresa devuelve la cantidad de participaciones que ha emitido la empresa
        int participacionesTot = fa.getPartPropEmpresa(e);
        totalTextBox.setText(String.valueOf(participacionesTot));
        
    }
}
