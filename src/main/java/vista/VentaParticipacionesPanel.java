package vista;

import aplicacion.Empresa;
import aplicacion.FachadaAplicacion;
import aplicacion.EntradaHistorial;
import aplicacion.Usuario;

import java.sql.Timestamp;

import java.util.List;
import java.util.stream.Collectors;

import vista.componentes.DialogoInfo;
import vista.componentes.FuentesGUI;

public class VentaParticipacionesPanel extends javax.swing.JPanel {

    /**
     * Panel donde se venden participaciones por parte de empresas o inversores
     */
    private Usuario u;
    private FachadaAplicacion fa;
    private String[] nombresOtrosUsuarios;

    public VentaParticipacionesPanel() {
        // No usar!!! Requerido por NetBeans. Ir a customize code y llamar al de abajo
    }

    public VentaParticipacionesPanel(Usuario u, FachadaAplicacion fa) {
        this.u = u;
        this.fa = fa;
        List<String> lista = fa.obtenerListaEmpresas().stream().map(e -> e.getIdUsuario()).collect(Collectors.toList());
        //lista.remove(u.getIdUsuario());
        nombresOtrosUsuarios = new String[lista.size()];
        nombresOtrosUsuarios = lista.toArray(nombresOtrosUsuarios);
        initComponents();
        setValidators();
    }

    private void setValidators() {
        precioVentaTextBox.setValidator(s -> {
            try {
                return Float.parseFloat(s) > 0.0f;
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

        precioVentaTextBox = new vista.componentes.TextBox();
        precioVentaLabel = new vista.componentes.Etiqueta();
        empresaVentaLabel = new vista.componentes.Etiqueta();
        numeroVentaLabel = new vista.componentes.Etiqueta();
        ventaBoton = new vista.componentes.Boton();
        empresaVenta = new vista.componentes.SelecionBox<String>(nombresOtrosUsuarios);
        numeroVenta = new vista.componentes.Deslizador(0, 0);
        numeroLabel = new vista.componentes.Etiqueta();
        titulo = new vista.componentes.Etiqueta();

        precioVentaTextBox.setName("precioVentaTextBox"); // NOI18N

        precioVentaLabel.setText("Precio total");
        precioVentaLabel.setName("precioVentaLabel"); // NOI18N

        empresaVentaLabel.setText("Empresa");
        empresaVentaLabel.setName("empresaVentaLabel"); // NOI18N

        numeroVentaLabel.setText("Número de participaciones");
        numeroVentaLabel.setName("numeroVentaLabel"); // NOI18N

        ventaBoton.setText("Crear oferta de venta");
        ventaBoton.setName("ventaBoton"); // NOI18N
        ventaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearOfertaVenta(evt);
            }
        });

        empresaVenta.setName("empresaVenta"); // NOI18N
        empresaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empresaVentaActionPerformed(evt);
            }
        });

        numeroVenta.setName("numeroVenta"); // NOI18N
        numeroVenta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                numeroVentaStateChanged(evt);
            }
        });

        numeroLabel.setText("0");
        numeroLabel.setName("numeroLabel"); // NOI18N

        titulo.setText("Venta de participaciones");
        titulo.setName("titulo"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(40, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(empresaVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(precioVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(precioVentaTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                        .addComponent(empresaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(numeroVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ventaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(numeroLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(numeroVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(40, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(precioVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(numeroVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(1, 1, 1)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(precioVentaTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(numeroVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numeroLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(ventaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(empresaVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(empresaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(48, 48, 48))
        );

        try {
            numeroVenta.setMaximum(fa.getParticipacionesEmpresa2(
                    u, fa.obtenerDatosEmpresa(new Usuario((String) empresaVenta.getItemAt(0),
                            false, false))));
        } catch (Exception ignored) {

        }
        titulo.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL,
                FuentesGUI.Size.GRANDE));
    }// </editor-fold>//GEN-END:initComponents

    private void crearOfertaVenta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearOfertaVenta

        if(!precioVentaTextBox.validateInput()){
            fa.muestraExcepcion("El precio de venta especificado no es válido",
                    DialogoInfo.NivelDeAdvertencia.ADVERTENCIA);
            return;
        }

        float precioVenta = Float.parseFloat(precioVentaTextBox.getText());

        if (empresaVenta.getSelectedItem() == null) {
            fa.muestraExcepcion("Aún no se ha seleccionado ninguna empresa!");
            return;
        }

        Empresa empresa = fa.obtenerDatosEmpresa(new Usuario((String) empresaVenta.getSelectedItem(), false, false));
        if (empresa == null) {
            fa.muestraExcepcion("La empresa especificada no existe!");
            return;
        }

        int numero = numeroVenta.getValue();
        int participacionesDisponibles = fa.getParticipacionesEmpresa2(u, empresa);
        if (participacionesDisponibles < numero) {
            fa.muestraExcepcion("El usuario no posee suficientes participaciones!\n" +
                    "Número actual de participaciones de " + empresa.getIdUsuario() + ": " + participacionesDisponibles);
            return;
        }

        // 2. Crear oferta de venta
        fa.crearOfertaVenta(u, empresa, numero, precioVenta);
        fa.obtenerHistorial(u);
        numeroVenta.setValue(0);
        numeroVenta.setMaximum(fa.getParticipacionesEmpresa2(u, fa.obtenerDatosEmpresa(new Usuario((String) empresaVenta.getSelectedItem(), false, false))));
    }//GEN-LAST:event_crearOfertaVenta

    private void empresaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empresaVentaActionPerformed
        // Actualizar slider
        numeroVenta.setValue(0);
        numeroVenta.setMaximum(fa.getParticipacionesEmpresa2(u, fa.obtenerDatosEmpresa(new Usuario((String) empresaVenta.getSelectedItem(), false, false))));
    }//GEN-LAST:event_empresaVentaActionPerformed

    private void numeroVentaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_numeroVentaStateChanged
        numeroLabel.setText(Integer.toString(numeroVenta.getValue()));
    }//GEN-LAST:event_numeroVentaStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.SelecionBox empresaVenta;
    private vista.componentes.Etiqueta empresaVentaLabel;
    private vista.componentes.Etiqueta numeroLabel;
    private vista.componentes.Deslizador numeroVenta;
    private vista.componentes.Etiqueta numeroVentaLabel;
    private vista.componentes.Etiqueta precioVentaLabel;
    private vista.componentes.TextBox precioVentaTextBox;
    private vista.componentes.Etiqueta titulo;
    private vista.componentes.Boton ventaBoton;
    // End of variables declaration//GEN-END:variables
}
