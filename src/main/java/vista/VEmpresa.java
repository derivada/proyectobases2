package vista;

import aplicacion.AnuncioBeneficios;
import aplicacion.Empresa;
import aplicacion.FachadaAplicacion;

import java.sql.Date;
import java.time.format.DateTimeParseException;

import aplicacion.EntradaHistorial;

import java.sql.Timestamp;
import java.util.List;
import vista.componentes.ColoresGUI;

import vista.componentes.DialogoInfo;
import vista.componentes.FuentesGUI;
import vista.componentes.OtrosComponentes;
import vista.modeloTablas.ModeloTablaBeneficios;
import vista.modeloTablas.ModeloTablaMovimientos;

public class VEmpresa extends javax.swing.JFrame {

    private final FachadaAplicacion fa;
    private Empresa e;

    private float precioParticipaciones = -1.0f;
    private int numeroParticipaciones = -1;

    public VEmpresa(Empresa e, FachadaAplicacion fa) {
        this.fa = fa;
        this.e = e;
        initComponents();
        idTextBox.setText(e.getIdUsuario());

        saldoTextBox.setText(String.valueOf(e.getSaldo()));
        this.actualizarCampos();
        tipoTextBox.setText("Empresa");

         ModeloTablaBeneficios tabla=(ModeloTablaBeneficios) tablaAnuncios.getModel();
        tabla.setFilas(fa.obtenerAnuncios(this.e.getIdUsuario()));
        
        int a=fa.getParticipacionesEmpresa2(e, e); 
        
        
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
        bajaBoton = new vista.componentes.Boton();
        idTextBox = new vista.componentes.TextBox();
        tipoTextBox = new vista.componentes.TextBox();
        tipoLabel = new vista.componentes.Etiqueta();
        usuarioLabel = new vista.componentes.Etiqueta();
        saldoTextBox = new vista.componentes.TextBox();
        saldoLabel = new vista.componentes.Etiqueta();
        panelCompra = new javax.swing.JTabbedPane();
        tabs11 = new vista.componentes.Tabs();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new vista.componentes.Tabla();
        compraParticipacionesPanel1 = new vista.CompraParticipacionesPanel(e, fa);
        ventaPanel = new vista.VentaParticipacionesPanel(e, fa);
        tabs10 = new vista.componentes.Tabs();
        participacionesLabel = new vista.componentes.Etiqueta();
        numeroParticipacionesTextBox = new vista.componentes.TextBox();
        participacionesBoton = new vista.componentes.Boton();
        disponiblesLabel = new vista.componentes.Etiqueta();
        bajaParticipacionesBoton = new vista.componentes.Boton();
        disponibles = new vista.componentes.Etiqueta();
        tabs9 = new vista.componentes.Tabs();
        importeTextBox = new vista.componentes.TextBox();
        importeLabel = new vista.componentes.Etiqueta();
        beneficiosBoton = new vista.componentes.Boton();
        pagarBoton = new vista.componentes.Boton();
        FechaLabel = new vista.componentes.Etiqueta();
        numParticipacionesLabel = new vista.componentes.Etiqueta();
        numParticipacionesAnuncioTextBox = new vista.componentes.TextBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAnuncios = new vista.componentes.Tabla();
        bajaAnunciosboton = new vista.componentes.Boton();
        FechaTextBox = new vista.componentes.EntradaFecha();
        modificarBoton = new vista.componentes.Boton();
        botonVolver1 = new vista.componentes.BotonVolver();
        bienvenidoLabel = new vista.componentes.Etiqueta();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        tipoLabel.setText("Tipo:");

        usuarioLabel.setText("Usuario:");

        saldoTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saldoTextBoxActionPerformed(evt);
            }
        });

        saldoLabel.setText("Saldo");

        panelCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tabla1.setModel(new ModeloTablaMovimientos()
        );
        jScrollPane1.setViewportView(tabla1);

        javax.swing.GroupLayout tabs11Layout = new javax.swing.GroupLayout(tabs11);
        tabs11.setLayout(tabs11Layout);
        tabs11Layout.setHorizontalGroup(
            tabs11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs11Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabs11Layout.setVerticalGroup(
            tabs11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs11Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        panelCompra.addTab("Lista de movimientos", tabs11);
        panelCompra.addTab("Compra", compraParticipacionesPanel1);
        panelCompra.addTab("Venta", ventaPanel);

        participacionesLabel.setText("Número de participaciones");

        numeroParticipacionesTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroParticipacionesTextBoxKeyTyped(evt);
            }
        });

        participacionesBoton.setText("Ofertar participaciones");
        participacionesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                participacionesBotonActionPerformed(evt);
            }
        });

        disponiblesLabel.setText("Número total de participaciones disponibles");

        bajaParticipacionesBoton.setText("Eliminar Participaciones");
        bajaParticipacionesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajaParticipacionesBotonActionPerformed(evt);
            }
        });

        disponibles.setText("0");
        disponibles.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL,
            FuentesGUI.Size.GRANDE));

    javax.swing.GroupLayout tabs10Layout = new javax.swing.GroupLayout(tabs10);
    tabs10.setLayout(tabs10Layout);
    tabs10Layout.setHorizontalGroup(
        tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabs10Layout.createSequentialGroup()
            .addGap(48, 48, 48)
            .addGroup(tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(participacionesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(disponiblesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(numeroParticipacionesTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(disponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
            .addGroup(tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bajaParticipacionesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(participacionesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(99, 99, 99))
    );
    tabs10Layout.setVerticalGroup(
        tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(tabs10Layout.createSequentialGroup()
            .addGap(50, 50, 50)
            .addGroup(tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabs10Layout.createSequentialGroup()
                    .addComponent(participacionesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(numeroParticipacionesTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(participacionesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(31, 31, 31)
            .addGroup(tabs10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabs10Layout.createSequentialGroup()
                    .addComponent(disponiblesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(disponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(bajaParticipacionesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(414, Short.MAX_VALUE))
    );

    panelCompra.addTab("Ofertar participaciones", tabs10);

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

    FechaLabel.setText("Fecha de pago");

    numParticipacionesLabel.setText("Numero de participaciones");

    tablaAnuncios.setModel(new ModeloTablaBeneficios()

    );
    jScrollPane2.setViewportView(tablaAnuncios);

    bajaAnunciosboton.setText("Solicitar baja de anuncio");
    bajaAnunciosboton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bajaAnunciosbotonActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout tabs9Layout = new javax.swing.GroupLayout(tabs9);
    tabs9.setLayout(tabs9Layout);
    tabs9Layout.setHorizontalGroup(
        tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(tabs9Layout.createSequentialGroup()
            .addGap(47, 47, 47)
            .addGroup(tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabs9Layout.createSequentialGroup()
                    .addComponent(bajaAnunciosboton, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pagarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32))
                .addGroup(tabs9Layout.createSequentialGroup()
                    .addGroup(tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(importeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FechaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(tabs9Layout.createSequentialGroup()
                            .addGroup(tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(FechaTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(importeTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                            .addGap(48, 48, 48)
                            .addGroup(tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(numParticipacionesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(beneficiosBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(numParticipacionesAnuncioTextBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(60, Short.MAX_VALUE))))
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
            .addGroup(tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabs9Layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(numParticipacionesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabs9Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FechaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(numParticipacionesAnuncioTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(tabs9Layout.createSequentialGroup()
                    .addComponent(FechaTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addGap(6, 6, 6)))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
            .addGroup(tabs9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(pagarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bajaAnunciosboton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(26, 26, 26))
    );

    panelCompra.addTab("Anunciar beneficios", tabs9);

    modificarBoton.setText("Modificar usuario");
    modificarBoton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            modificarBotonActionPerformed(evt);
        }
    });

    bienvenidoLabel.setText("Bienvenid@, " +e.getIdUsuario());

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(83, 83, 83)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bajaBoton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tipoTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(tipoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(modificarBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saldoTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(botonVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bienvenidoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
            .addComponent(panelCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(56, 56, 56))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(43, 43, 43)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(panelCompra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(bienvenidoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(idTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(tipoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(tipoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(43, 43, 43)
                    .addComponent(bajaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(modificarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(saldoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(50, 50, 50)
                    .addComponent(botonVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(464, Short.MAX_VALUE))
    );

    OtrosComponentes.configurarTabbedPane(panelCompra);
    botonVolver1.configurar(fa, this, false);
    bienvenidoLabel.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.GRANDE));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bajaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaBotonActionPerformed
        fa.solicitarBaja(e.getIdUsuario());
    }//GEN-LAST:event_bajaBotonActionPerformed

    private void idTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTextBoxActionPerformed
    }//GEN-LAST:event_idTextBoxActionPerformed

    private void tipoTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoTextBoxActionPerformed
    }//GEN-LAST:event_tipoTextBoxActionPerformed

    private void saldoTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saldoTextBoxActionPerformed
    }//GEN-LAST:event_saldoTextBoxActionPerformed

    private void modificarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBotonActionPerformed
        fa.menuModificarEmpresa(e);
    }//GEN-LAST:event_modificarBotonActionPerformed

    private void pagarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagarBotonActionPerformed
        Pagar();
    }//GEN-LAST:event_pagarBotonActionPerformed

    private void beneficiosBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beneficiosBotonActionPerformed
        AnunciarBeneficios();
    }//GEN-LAST:event_beneficiosBotonActionPerformed

    private void bajaParticipacionesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaParticipacionesBotonActionPerformed
        this.eliminarParticipaciones();
    }//GEN-LAST:event_bajaParticipacionesBotonActionPerformed

    private void participacionesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_participacionesBotonActionPerformed
        this.emitirParticipaciones();
    }//GEN-LAST:event_participacionesBotonActionPerformed


    private void bajaAnunciosbotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaAnunciosbotonActionPerformed
        solicitarBajaAnuncio();
    }//GEN-LAST:event_bajaAnunciosbotonActionPerformed


      public void AnunciarBeneficios() {

        float importe = 0.0f;
        if(!importeTextBox.getText().isEmpty()){
            try {
            importe = Float.parseFloat(importeTextBox.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                fa.muestraExcepcion("ERROR: El importe no está en un formato decimal válido!\n" +
                        "Use el formato xxxx.yy", DialogoInfo.NivelDeAdvertencia.ERROR);
                return;
            }
            if (importe <= 0.0) {
                fa.muestraExcepcion("ERROR: El importe debe ser positivo!",
                        DialogoInfo.NivelDeAdvertencia.ERROR);
                return;
            }
        }

        Date fecha = null;
        try {
            fecha = FechaTextBox.getFecha();
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            fa.muestraExcepcion("ERROR: La fecha introcida no está en un formato válido!\n" +
                    "Use el formato YYYY/MM/DD", DialogoInfo.NivelDeAdvertencia.ERROR);
            return;
        }
        if (fecha.before(new Date(System.currentTimeMillis()))) {
            fa.muestraExcepcion("ERROR: La fecha no está especificada en el futuro",
                    DialogoInfo.NivelDeAdvertencia.ERROR);
            return;
        }
        int numero=0;
        if(!numParticipacionesAnuncioTextBox.getText().isEmpty()){
            try {
                 numero= Integer.parseInt(numParticipacionesAnuncioTextBox.getText());
            } catch (DateTimeParseException e) {
                e.printStackTrace();
                fa.muestraExcepcion("ERROR: El numero de participaciones tiene que ser un entero\n" +
                        "Use el formato xxx", DialogoInfo.NivelDeAdvertencia.ERROR);
                return;
            }
            if (numero <= 0) {
                fa.muestraExcepcion("ERROR: El numero de participaciones debe ser positivo",
                        DialogoInfo.NivelDeAdvertencia.ERROR);
                return;
            }
        }


        fa.crearAnuncio(importe, this.e, fecha,numero);
        ModeloTablaBeneficios tabla=(ModeloTablaBeneficios) tablaAnuncios.getModel();
        tabla.setFilas(fa.obtenerAnuncios(this.e.getIdUsuario()));


    }

    public void solicitarBajaAnuncio(){
        ModeloTablaBeneficios tabla=(ModeloTablaBeneficios) tablaAnuncios.getModel();
        int fila=tablaAnuncios.getSelectedRow();
        AnuncioBeneficios aux=tabla.obtenerBeneficios(fila);
        fa.solicitarBajaAnuncio(aux.getEmpresa(), aux.getFechaPago());
    }



    private void numeroParticipacionesTextBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroParticipacionesTextBoxKeyTyped
        try {
            validarInput(true);
        } catch (Exception ignored) {

        }
    }//GEN-LAST:event_numeroParticipacionesTextBoxKeyTyped

    private void validarInput(boolean numero) throws Exception {
        // Valida el precio y número de participaciones a ofertar, tira Exception
        // con el mensaje correcto si alguna de las 2 falla
        StringBuilder errores = new StringBuilder();
        if (numero) {
            int temp = -1;
            try {
                temp = Integer.parseInt(numeroParticipacionesTextBox.getText());
                if (temp > 0.0f) {
                    numeroParticipaciones = temp;
                    numeroParticipacionesTextBox.setBackground(ColoresGUI.blanco);
                } else {
                    numeroParticipacionesTextBox.setBackground(ColoresGUI.getGUIColorExtraClaro(ColoresGUI.Colores.ROJO));
                    numeroParticipaciones = -1;
                    errores.append("El número de participaciones debe ser positivo!\n");
                }
            } catch (NumberFormatException e) {
                numeroParticipacionesTextBox.setBackground(ColoresGUI.getGUIColorExtraClaro(ColoresGUI.Colores.ROJO));
                numeroParticipaciones = -1;
                errores.append("El número de participaciones no es un válido!\n");
            }
        }


        if (errores.length() > 0) {
            throw new Exception(errores.toString());
        }
    }

    public void Pagar(){
        Float importe=0.0f;
        if(!importeTextBox.getText().isEmpty()){
           try {
                importe = Float.parseFloat(importeTextBox.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                fa.muestraExcepcion("ERROR: El importe no está en un formato decimal válido!\n" +
                        "Use el formato xxxx.yy", DialogoInfo.NivelDeAdvertencia.ERROR);
                return;
            }
            if (importe <= 0.0) {
                fa.muestraExcepcion("ERROR: El importe debe ser positivo!",
                        DialogoInfo.NivelDeAdvertencia.ERROR);
                return;
            }
        }

        int numero=0;
        if(!numParticipacionesAnuncioTextBox.getText().isEmpty()){
            try {
                 numero= Integer.parseInt(numParticipacionesAnuncioTextBox.getText());
            } catch (DateTimeParseException e) {
                e.printStackTrace();
                fa.muestraExcepcion("ERROR: El numero de participaciones tiene que ser un entero\n" +
                        "Use el formato xxx", DialogoInfo.NivelDeAdvertencia.ERROR);
                return;
            }
            if (numero <= 0) {
                fa.muestraExcepcion("ERROR: El numero de participaciones debe ser positivo",
                        DialogoInfo.NivelDeAdvertencia.ERROR);
                return;
            }
        }
        ModeloTablaBeneficios tabla=(ModeloTablaBeneficios) tablaAnuncios.getModel();
        int fila=tablaAnuncios.getSelectedRow();
        if(fila!=-1){
           AnuncioBeneficios aux=tabla.obtenerBeneficios(fila);
           fa.pagarBeneficios(importe,numero, this.e,aux);
        }
        else{
           fa.pagarBeneficios(importe,numero, this.e,null);
        }

    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.Etiqueta FechaLabel;
    private vista.componentes.EntradaFecha FechaTextBox;
    private vista.componentes.Boton bajaAnunciosboton;
    private vista.componentes.Boton bajaBoton;
    private vista.componentes.Boton bajaParticipacionesBoton;
    private vista.componentes.Boton beneficiosBoton;
    private vista.componentes.Etiqueta bienvenidoLabel;
    private vista.componentes.BotonVolver botonVolver1;
    private javax.swing.ButtonGroup buttonGroup1;
    private vista.CompraParticipacionesPanel compraParticipacionesPanel1;
    private vista.componentes.Etiqueta disponibles;
    private vista.componentes.Etiqueta disponiblesLabel;
    private vista.componentes.Etiqueta etiqueta1;
    private vista.componentes.TextBox idTextBox;
    private vista.componentes.Etiqueta importeLabel;
    private vista.componentes.TextBox importeTextBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private vista.componentes.Boton modificarBoton;
    private vista.componentes.TextBox numParticipacionesAnuncioTextBox;
    private vista.componentes.Etiqueta numParticipacionesLabel;
    private vista.componentes.TextBox numeroParticipacionesTextBox;
    private vista.componentes.Boton pagarBoton;
    private javax.swing.JTabbedPane panelCompra;
    private vista.componentes.Boton participacionesBoton;
    private vista.componentes.Etiqueta participacionesLabel;
    private vista.componentes.Etiqueta saldoLabel;
    private vista.componentes.TextBox saldoTextBox;
    private vista.componentes.Tabla tabla1;
    private vista.componentes.Tabla tablaAnuncios;
    private vista.componentes.Tabs tabs10;
    private vista.componentes.Tabs tabs11;
    private vista.componentes.Tabs tabs9;
    private vista.componentes.TextBox textBox1;
    private vista.componentes.Etiqueta tipoLabel;
    private vista.componentes.TextBox tipoTextBox;
    private vista.componentes.Etiqueta usuarioLabel;
    private vista.VentaParticipacionesPanel ventaPanel;
    // End of variables declaration//GEN-END:variables

    public void emitirParticipaciones() {

        //en esta funcion, la empresa indica que quiere crear un nuevo lote de participaciones
        //cuando lo hace, tendrán que añadirse las participaciones a su cartera de participaciones
        //esta cartera puede tener participacioens de otras empresas asi que debemos identificar
        //la tabla de esta empresa en su cartera y sumarlas ahi, es decir no creamos un objeto participaciones
        //cada vez que se añaden, si no que se suman a esa tabla que tiene como PK a esta empresa, y como PKp a esta empresa de nuevo
        try {
            validarInput(true);
        } catch (Exception e) {
            fa.muestraExcepcion(e.getMessage(), DialogoInfo.NivelDeAdvertencia.ADVERTENCIA);
            return;
        }
        String participaciones = numeroParticipacionesTextBox.getText();

        int emision = Integer.parseInt(participaciones);

        fa.emitirParticipaciones(e, emision); //hay que meter las participaciones con el precio a la tabla de oferta venta para que ya se genere automatico

        this.actualizarCampos();

    }

    public void eliminarParticipaciones() {

        //en este caso, la empresa solicita eliminar ciertas participaciones de su cartera
        //la cuestion seria saber cuales estan vendidas y cuales no, de manera que elimine unicamente aquellas que no estan vendidas, y si no hay suficientes pues elimine las que hay sin vender
        try {
            validarInput(true); // solo validar el número
        } catch (Exception e) {
            fa.muestraExcepcion(e.getMessage(), DialogoInfo.NivelDeAdvertencia.ADVERTENCIA);
            return;
        }

        int bajaP = Integer.parseInt(numeroParticipacionesTextBox.getText());

        //aqui iria la consulta, cuando me apetezca la hago
        fa.bajaParticipaciones(e, bajaP);
        

        this.actualizarCampos();
    }

    public void actualizarCampos() {
        e = fa.obtenerDatosEmpresa(e);
        idTextBox.setText(e.getIdUsuario());
        saldoTextBox.setText(String.valueOf(e.getSaldo()));
        tipoTextBox.setText("Empresa");
        disponibles.setText(String.valueOf(fa.getPartPropEmpresa(e)));
        this.actualizarHistorial();
    }

    public void actualizarHistorial(){

        ModeloTablaMovimientos m = (ModeloTablaMovimientos) tabla1.getModel();

        List<EntradaHistorial> historial = fa.actualizarHistorial(e);
        m.setFilas(historial);
    }
}
