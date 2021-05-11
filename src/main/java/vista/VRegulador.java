package vista;

import aplicacion.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import vista.componentes.FuentesGUI;
import vista.componentes.ImagenesGUI;
import vista.componentes.Utils;
import vista.modeloTablas.ModeloTablaAlta;
import vista.modeloTablas.ModeloTablaBaja;
import vista.modeloTablas.ModeloTablaBeneficios;
import vista.modeloTablas.ModeloTablaTransacciones;

public class VRegulador extends javax.swing.JFrame {

    private final FachadaAplicacion fa;
    private final Regulador r;

    public VRegulador(Regulador r, aplicacion.FachadaAplicacion fa) {
        this.r = r;
        this.fa = fa;
        this.setTitle("Panel del regulador");
        this.setIconImage(ImagenesGUI.getImage("database.png", 128));
        initComponents();

        //Modelo tabla Alta
        ModeloTablaAlta mTAlta = new ModeloTablaAlta();
        tabla1.setModel(mTAlta);
        mTAlta.setFilas(fa.obtenerUsuariosPorAutorizacion());
        
        //Modelo Tabla Baja
        ModeloTablaBaja mTBaja = new ModeloTablaBaja();
        tabla2.setModel(mTBaja);
        mTBaja.setFilas(fa.obtenerUsuariosBaja());
        tabla2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                try {
                    ModeloTablaBaja mtp = (ModeloTablaBaja) tabla2.getModel();
                    if(tabla2.getSelectedRow() == -1){
                        bajaBoton.setEnabled(false);
                    } else {
                        Usuario u = mtp.obtenerUsuario(tabla2.getSelectedRow());

                        int numeroParticipaciones = 0;

                        if (u instanceof Inversor) {
                            numeroParticipaciones = fa.getNumeroParticipaciones(u.getIdUsuario(), "Inversor");
                        } else {
                            numeroParticipaciones = fa.getNumeroParticipaciones(u.getIdUsuario(), "Empresa");
                        }

                        if (numeroParticipaciones != 0||fa.getNumOfertaVenta(u.getIdUsuario())!=0) {
                            bajaBoton.setEnabled(false);
                        } else if (numeroParticipaciones == 0&&fa.getNumOfertaVenta(u.getIdUsuario())==0) {
                            bajaBoton.setEnabled(true);
                        }
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    bajaBoton.setEnabled(false);                    
                }

            }
        });
        
        ModeloTablaBeneficios tablaAnuncios = (ModeloTablaBeneficios) anunciosTabla.getModel();
        tablaAnuncios.setFilas(fa.obtenerAnunciosRegulador());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabs5 = new vista.componentes.Tabs();
        altaBoton = new vista.componentes.Boton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla1 = new vista.componentes.Tabla();
        tabs6 = new vista.componentes.Tabs();
        bajaBoton = new vista.componentes.Boton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla2 = new vista.componentes.Tabla();
        bajaAnuncioTabs = new vista.componentes.Tabs();
        jScrollPane3 = new javax.swing.JScrollPane();
        anunciosTabla = new vista.componentes.Tabla();
        bajaAnunciosBoton = new vista.componentes.Boton();
        saldoLabel = new vista.componentes.Etiqueta();
        tipoLabel = new vista.componentes.Etiqueta();
        usuarioLabel = new vista.componentes.Etiqueta();
        botonVolver1 = new vista.componentes.BotonVolver();
        bienvenidoLabel = new vista.componentes.Etiqueta();
        botonModificarComision = new vista.componentes.Boton();
        abrirHistorial = new vista.componentes.Boton();
        saldoTextBox = new vista.componentes.Etiqueta();
        tipoTextBox = new vista.componentes.Etiqueta();
        idTextBox = new vista.componentes.Etiqueta();
        boton1 = new vista.componentes.Boton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        altaBoton.setText("Autorizar");
        altaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altaBotonActionPerformed(evt);
            }
        });

        tabla1.setModel(new ModeloTablaAlta());
        jScrollPane4.setViewportView(tabla1);

        javax.swing.GroupLayout tabs5Layout = new javax.swing.GroupLayout(tabs5);
        tabs5.setLayout(tabs5Layout);
        tabs5Layout.setHorizontalGroup(
            tabs5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabs5Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(tabs5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(altaBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        tabs5Layout.setVerticalGroup(
            tabs5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(altaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Solicitudes de alta", tabs5);

        bajaBoton.setText("Autorizar");
        bajaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajaBotonActionPerformed(evt);
            }
        });

        tabla2.setModel(new ModeloTablaBaja());
        jScrollPane1.setViewportView(tabla2);

        javax.swing.GroupLayout tabs6Layout = new javax.swing.GroupLayout(tabs6);
        tabs6.setLayout(tabs6Layout);
        tabs6Layout.setHorizontalGroup(
            tabs6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(tabs6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bajaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        tabs6Layout.setVerticalGroup(
            tabs6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabs6Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bajaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Solicitudes de baja", tabs6);

        anunciosTabla.setModel(new ModeloTablaBeneficios());
        jScrollPane3.setViewportView(anunciosTabla);

        bajaAnunciosBoton.setText("Dar de baja anuncio");
        bajaAnunciosBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajaAnunciosBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bajaAnuncioTabsLayout = new javax.swing.GroupLayout(bajaAnuncioTabs);
        bajaAnuncioTabs.setLayout(bajaAnuncioTabsLayout);
        bajaAnuncioTabsLayout.setHorizontalGroup(
            bajaAnuncioTabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bajaAnuncioTabsLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(bajaAnuncioTabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bajaAnunciosBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        bajaAnuncioTabsLayout.setVerticalGroup(
            bajaAnuncioTabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bajaAnuncioTabsLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(bajaAnunciosBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        jTabbedPane1.addTab("Solicitud baja anuncio", bajaAnuncioTabs);

        saldoLabel.setText("Saldo");

        tipoLabel.setText("Tipo:");

        usuarioLabel.setText("Usuario:");

        bienvenidoLabel.setText("Bienvenid@, " +r.getIdUsuario());
        bienvenidoLabel.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.GRANDE));

        botonModificarComision.setText("Modificar comisión");
        botonModificarComision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarComisionActionPerformed(evt);
            }
        });

        abrirHistorial.setText("Historial");
        abrirHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirHistorialActionPerformed(evt);
            }
        });

        saldoTextBox.setText(Utils.displayCurrency(r.getSaldo()));

        tipoTextBox.setText("Regulador");

        idTextBox.setText(r.getIdUsuario());

        boton1.setText("Modificar saldos");
        boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tipoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(botonVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                            .addComponent(abrirHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(bienvenidoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonModificarComision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saldoTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tipoTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(boton1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bienvenidoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(idTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tipoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tipoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saldoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(botonModificarComision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(boton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(abrirHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        Utils.configurarTabbedPane(jTabbedPane1);
        botonVolver1.configurar(fa, this, false);
        saldoTextBox.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL,
            FuentesGUI.Size.GRANDE));
    tipoTextBox.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL,
        FuentesGUI.Size.GRANDE));
idTextBox.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL,
    FuentesGUI.Size.GRANDE));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void altaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaBotonActionPerformed
        ModeloTablaAlta modelo = (ModeloTablaAlta) tabla1.getModel();
        if(tabla1.getSelectedRow() == -1){
            FachadaGUI.muestraExcepcion("No hay elementos seleccionados");
            return;
        }
        Usuario u = modelo.obtenerUsuario(tabla1.getSelectedRow());  
        
        if (u instanceof Inversor) {
            Inversor us = (Inversor) u;
            us.setAutorizado(true);
        } else {
            Empresa us = (Empresa) u;
            us.setAutorizado(true);
        }

        fa.autorizarUsuarios(u.getIdUsuario());
        

        modelo.setFilas(fa.obtenerUsuariosPorAutorizacion());
    }//GEN-LAST:event_altaBotonActionPerformed

    private void bajaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaBotonActionPerformed
        ModeloTablaBaja modelo = (ModeloTablaBaja) tabla2.getModel();
        if(tabla2.getSelectedRow() == -1){
            FachadaGUI.muestraExcepcion("No hay elementos seleccionados");
            return;
        }
        Usuario u = modelo.obtenerUsuario(tabla2.getSelectedRow());
        float saldo = 0.0f;
        
        if (u instanceof Inversor) {
            Inversor us = (Inversor) u;
            us.setAutorizado(true);
            saldo = us.getSaldo();
        } else {
            Empresa us = (Empresa) u;
            us.setAutorizado(true);
            saldo = us.getSaldo();
        }

        fa.bajaUsuario(u, saldo);

        modelo.setFilas(fa.obtenerUsuariosBaja());
    }//GEN-LAST:event_bajaBotonActionPerformed

    private void bajaAnunciosBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaAnunciosBotonActionPerformed
        darBajaAnuncio();
    }//GEN-LAST:event_bajaAnunciosBotonActionPerformed

    private void botonModificarComisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarComisionActionPerformed
        new AjustarComision(this, r);
    }//GEN-LAST:event_botonModificarComisionActionPerformed

    private void abrirHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirHistorialActionPerformed
        new VHistorial(r);
    }//GEN-LAST:event_abrirHistorialActionPerformed

    private void boton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton1ActionPerformed
        new ModificarSaldo(this, fa);
    }//GEN-LAST:event_boton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.Boton abrirHistorial;
    private vista.componentes.Boton altaBoton;
    private vista.componentes.Tabla anunciosTabla;
    private vista.componentes.Tabs bajaAnuncioTabs;
    private vista.componentes.Boton bajaAnunciosBoton;
    private vista.componentes.Boton bajaBoton;
    private vista.componentes.Etiqueta bienvenidoLabel;
    private vista.componentes.Boton boton1;
    private vista.componentes.Boton botonModificarComision;
    private vista.componentes.BotonVolver botonVolver1;
    private vista.componentes.Etiqueta idTextBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private vista.componentes.Etiqueta saldoLabel;
    private vista.componentes.Etiqueta saldoTextBox;
    private vista.componentes.Tabla tabla1;
    private vista.componentes.Tabla tabla2;
    private vista.componentes.Tabs tabs5;
    private vista.componentes.Tabs tabs6;
    private vista.componentes.Etiqueta tipoLabel;
    private vista.componentes.Etiqueta tipoTextBox;
    private vista.componentes.Etiqueta usuarioLabel;
    // End of variables declaration//GEN-END:variables

    public void darBajaAnuncio() {
        ModeloTablaBeneficios tabla = (ModeloTablaBeneficios) anunciosTabla.getModel();
        int fila = anunciosTabla.getSelectedRow();
        if(fila == -1){
            FachadaGUI.muestraExcepcion("Ninguna fila seleccionada");
            return;
        }
        AnuncioBeneficios aux = tabla.obtenerBeneficios(fila);
        fa.bajaAnuncio(aux.getEmpresa(), aux.getFechaPago(), aux.getImporteparticipacion(),aux.getNumeroparticipaciones());
        //Se actualiza la tabla 
        tabla.setFilas(fa.obtenerAnunciosRegulador());
        if (tabla.getRowCount() > 0) {
            anunciosTabla.setRowSelectionInterval(0, 0);
        }
    }
}
