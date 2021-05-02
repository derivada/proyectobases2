package vista;

import aplicacion.AnuncioBeneficios;
import aplicacion.Empresa;
import aplicacion.FachadaAplicacion;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.format.DateTimeParseException;

import vista.componentes.DialogoInfo;
import vista.componentes.FuentesGUI;
import vista.componentes.ImagenesGUI;
import vista.componentes.Utils;
import vista.modeloTablas.ModeloTablaBeneficios;

public class VEmpresa extends javax.swing.JFrame {

    private final FachadaAplicacion fa;
    private Empresa e;

    private float precioParticipaciones = -1.0f;
    private int numeroParticipaciones = -1;

    public VEmpresa(Empresa e, FachadaAplicacion fa) {
        this.fa = fa;
        this.e = e;
        this.setTitle("Gestión de empresa - " + e.getIdUsuario());
        this.setIconImage(ImagenesGUI.getImage("database.png", 128));
        initComponents();

        ModeloTablaBeneficios tabla = (ModeloTablaBeneficios) tablaAnuncios.getModel();
        tabla.setFilas(fa.obtenerAnuncios(this.e.getIdUsuario()));
        setValidators();
    }

    private void setValidators() {
        numeroParticipacionesTextBox.setValidator(s -> {
            if (s.isEmpty()) return true;
            try {
                return Integer.parseInt(s) > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        });

        importeTextBox.setValidator(s -> {
            if (s.isEmpty()) return true;
            try {
                return Float.parseFloat(s) > 0.0f;
            } catch (NumberFormatException e) {
                return false;
            }
        });

        // Comprueba que la fecha está en el futuroLen
        FechaTextBox.setValidator(timestamp -> timestamp.after(Timestamp.from(Instant.now())));
        numParticipacionesAnuncioTextBox.setValidator(s -> {
            if (s.isEmpty()) return true;
            try {
                return Integer.parseInt(s) > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        });
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
        tipoLabel = new vista.componentes.Etiqueta();
        usuarioLabel = new vista.componentes.Etiqueta();
        saldoLabel = new vista.componentes.Etiqueta();
        panelCompra = new javax.swing.JTabbedPane();
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
        abrirHistorial = new vista.componentes.Boton();
        idTextBox = new vista.componentes.Etiqueta();
        tipoTextBox = new vista.componentes.Etiqueta();
        saldoTextBox = new vista.componentes.Etiqueta();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bajaBoton.setText("Solicitud de baja");
        bajaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajaBotonActionPerformed(evt);
            }
        });

        tipoLabel.setText("Tipo:");

        usuarioLabel.setText("Usuario:");

        saldoLabel.setText("Saldo:");

        panelCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelCompra.addTab("Compra", compraParticipacionesPanel1);
        panelCompra.addTab("Venta", ventaPanel);

        participacionesLabel.setText("Número de participaciones");

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

        bienvenidoLabel.setText("Bienvenid@, " + e.getIdUsuario());

        abrirHistorial.setText("Historial");
        abrirHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirHistorialActionPerformed(evt);
            }
        });

        idTextBox.setText(e.getIdUsuario());

        tipoTextBox.setText("Empresa");

        saldoTextBox.setText(Utils.displayCurrency(e.getSaldo()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(bajaBoton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tipoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(modificarBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(botonVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                                        .addComponent(abrirHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(bienvenidoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(idTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tipoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(saldoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
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
                                                .addComponent(idTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tipoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tipoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(saldoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36)
                                                .addComponent(bajaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36)
                                                .addComponent(modificarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(botonVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(abrirHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(40, Short.MAX_VALUE))
        );

        Utils.configurarTabbedPane(panelCompra);
        botonVolver1.configurar(fa, this, false);
        bienvenidoLabel.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.CURSIVA, FuentesGUI.Size.GRANDE));
        idTextBox.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA,
                FuentesGUI.Size.GRANDE));
        tipoTextBox.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA,
                FuentesGUI.Size.GRANDE));
        saldoTextBox.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA,
                FuentesGUI.Size.GRANDE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bajaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaBotonActionPerformed
        if (fa.getNumeroParticipaciones(e.getIdUsuario(), "Empresa") == 0) {
            fa.solicitarBaja(e.getIdUsuario());
            fa.muestraExcepcion("La solicitud se ha realizado con éxito", DialogoInfo.NivelDeAdvertencia.INFORMACION);
        } else {
            fa.muestraExcepcion("La solicitud se ha cancelado ya que el usuario tiene participaciones");
        }
    }//GEN-LAST:event_bajaBotonActionPerformed

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

        if (!importeTextBox.validateInput() | !numParticipacionesAnuncioTextBox.validateInput()) {
            fa.muestraExcepcion("No se han introducido correctamente los datos!");
            return;
        }

        float importe = importeTextBox.getText().isEmpty() ? 0.0f : Float.parseFloat(importeTextBox.getText());
        int numero = numParticipacionesAnuncioTextBox.getText().isEmpty() ? 0 : Integer.parseInt(numParticipacionesAnuncioTextBox.getText());


        if (FechaTextBox.getFecha() == null) {
            fa.muestraExcepcion("ERROR: La fecha introcida no está en un formato válido!\n"
                    + "Use el formato YYYY/MM/DD", DialogoInfo.NivelDeAdvertencia.ERROR);
            return;
        }

        if (!FechaTextBox.validateInput()) {
            fa.muestraExcepcion("ERROR: La fecha no está especificada en el futuro",
                    DialogoInfo.NivelDeAdvertencia.ERROR);
            return;
        }

        Timestamp fecha = FechaTextBox.getFecha();

        fa.crearAnuncio(importe, this.e, fecha, numero);
        this.actualizarDatos();
        ModeloTablaBeneficios tabla = (ModeloTablaBeneficios) tablaAnuncios.getModel();
        tabla.setFilas(fa.obtenerAnuncios(this.e.getIdUsuario()));
    }

    public void solicitarBajaAnuncio() {
        ModeloTablaBeneficios tabla = (ModeloTablaBeneficios) tablaAnuncios.getModel();
        int fila = tablaAnuncios.getSelectedRow();
        AnuncioBeneficios aux = tabla.obtenerBeneficios(fila);
        fa.solicitarBajaAnuncio(aux.getEmpresa(), aux.getFechaPago());
    }


    private void abrirHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirHistorialActionPerformed
        new VHistorial(e);
    }//GEN-LAST:event_abrirHistorialActionPerformed

    public void Pagar() {
        Float importe = 0.0f;
        if (!importeTextBox.getText().isEmpty()) {
            try {
                importe = Float.parseFloat(importeTextBox.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                fa.muestraExcepcion("ERROR: El importe no está en un formato decimal válido!\n"
                        + "Use el formato xxxx.yy", DialogoInfo.NivelDeAdvertencia.ERROR);
                return;
            }
            if (importe <= 0.0) {
                fa.muestraExcepcion("ERROR: El importe debe ser positivo!",
                        DialogoInfo.NivelDeAdvertencia.ERROR);
                return;
            }
        }

        int numero = 0;
        if (!numParticipacionesAnuncioTextBox.getText().isEmpty()) {
            try {
                numero = Integer.parseInt(numParticipacionesAnuncioTextBox.getText());
            } catch (DateTimeParseException e) {
                e.printStackTrace();
                fa.muestraExcepcion("ERROR: El numero de participaciones tiene que ser un entero\n"
                        + "Use el formato xxx", DialogoInfo.NivelDeAdvertencia.ERROR);
                return;
            }
            if (numero <= 0) {
                fa.muestraExcepcion("ERROR: El numero de participaciones debe ser positivo",
                        DialogoInfo.NivelDeAdvertencia.ERROR);
                return;
            }
        }

        ModeloTablaBeneficios tabla = (ModeloTablaBeneficios) tablaAnuncios.getModel();
        int fila = tablaAnuncios.getSelectedRow();
        if (fila != -1) {
            AnuncioBeneficios aux = tabla.obtenerBeneficios(fila);
            fa.pagarBeneficios(importe, numero, this.e, aux);
        } else {
            fa.pagarBeneficios(importe, numero, this.e, null);
        }
        actualizarDatos();

        tabla.setFilas(fa.obtenerAnuncios(this.e.getIdUsuario()));


    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.Etiqueta FechaLabel;
    private vista.componentes.EntradaFecha FechaTextBox;
    private vista.componentes.Boton abrirHistorial;
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
    private vista.componentes.Etiqueta idTextBox;
    private vista.componentes.Etiqueta importeLabel;
    private vista.componentes.TextBox importeTextBox;
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
    private vista.componentes.Etiqueta saldoTextBox;
    private vista.componentes.Tabla tablaAnuncios;
    private vista.componentes.Tabs tabs10;
    private vista.componentes.Tabs tabs9;
    private vista.componentes.TextBox textBox1;
    private vista.componentes.Etiqueta tipoLabel;
    private vista.componentes.Etiqueta tipoTextBox;
    private vista.componentes.Etiqueta usuarioLabel;
    private vista.VentaParticipacionesPanel ventaPanel;
    // End of variables declaration//GEN-END:variables

    public void emitirParticipaciones() {

        //en esta funcion, la empresa indica que quiere crear un nuevo lote de participaciones
        //cuando lo hace, tendrán que añadirse las participaciones a su cartera de participaciones
        //esta cartera puede tener participacioens de otras empresas asi que debemos identificar
        //la tabla de esta empresa en su cartera y sumarlas ahi, es decir no creamos un objeto participaciones
        //cada vez que se añaden, si no que se suman a esa tabla que tiene como PK a esta empresa, y como PKp a esta empresa de nuevo

        if (!numeroParticipacionesTextBox.validateInput()) {
            fa.muestraExcepcion("El número de participaciones no es correcto!", DialogoInfo.NivelDeAdvertencia.ADVERTENCIA);
            return;
        }

        int cantidad = Integer.parseInt(numeroParticipacionesTextBox.getText());
        if (cantidad == 0)
            return;

        fa.emitirParticipaciones(e, cantidad); //hay que meter las participaciones con el precio a la tabla de oferta venta para que ya se genere automatico
        this.actualizarDatos();
    }

    public void eliminarParticipaciones() {

        //en este caso, la empresa solicita eliminar ciertas participaciones de su cartera
        //la cuestion seria saber cuales estan vendidas y cuales no, de manera que elimine unicamente aquellas que no estan vendidas, y si no hay suficientes pues elimine las que hay sin vender
        if (!numeroParticipacionesTextBox.validateInput()) {
            fa.muestraExcepcion("El número de participaciones no es correcto!", DialogoInfo.NivelDeAdvertencia.ADVERTENCIA);
            return;
        }
        int cantidad = Integer.parseInt(numeroParticipacionesTextBox.getText());

        if (cantidad > this.numeroParticipaciones) {
            fa.muestraExcepcion("No se poseen tantas participaciones!", DialogoInfo.NivelDeAdvertencia.ADVERTENCIA);
            return;
        }
        if (cantidad == 0)
            return;

        fa.bajaParticipaciones(e, cantidad);


        this.actualizarDatos();
    }

    public void actualizarDatos() {
        e = fa.obtenerDatosEmpresa(e);
        saldoTextBox.setText(Utils.displayCurrency(e.getSaldo()));
        numeroParticipaciones = fa.getParticipacionesEmpresa(e, e);
        disponibles.setText(String.valueOf(numeroParticipaciones));
        ventaPanel.actualizarDatos();
    }
}
