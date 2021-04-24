package vista;

import aplicacion.Empresa;
import aplicacion.FachadaAplicacion;
import aplicacion.Historial;
import aplicacion.Usuario;
import java.sql.Date;
import java.sql.Timestamp;

import java.util.List;
import java.util.stream.Collectors;

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
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ventaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(empresaVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(empresaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48))
        );

        try{
            numeroVenta.setMaximum(fa.getParticipacionesEmpresa(
                u, fa.obtenerDatosEmpresa(new Usuario((String) empresaVenta.getItemAt(0),
                    false, false))));
    }catch(Exception ignored){

    }
    }// </editor-fold>//GEN-END:initComponents

    private void crearOfertaVenta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearOfertaVenta

        // 1. Validar input
        float precioVenta = -1.0f;
        try {
            precioVenta = Float.parseFloat(precioVentaTextBox.getText());
        } catch (Exception e) {
            fa.muestraExcepcion("El precio de venta no es un número bien formado!");
            return;
        }
        if (precioVenta <= 0.0f) {
            fa.muestraExcepcion("El precio de venta debe ser positivo!");
            return;
        }


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
        int participacionesDisponibles = fa.getParticipacionesEmpresa(u, empresa);
        if (participacionesDisponibles < numero) {
            fa.muestraExcepcion("El usuario no posee suficientes participaciones!\n" +
                    "Número actual de participaciones de " + empresa.getIdUsuario() + ": " + participacionesDisponibles);
            return;
        }

        // 2. Crear oferta de venta
        fa.crearOfertaVenta(u, empresa, numero, precioVenta);
        fa.insertarHistorial(new Historial(empresa.getIdUsuario(), empresa.getIdUsuario(), new Timestamp(System.currentTimeMillis()), numero, precioVenta, "Venta"));
        fa.actualizarHistorial(u);
        numeroVenta.setValue(0);
        numeroVenta.setMaximum(fa.getParticipacionesEmpresa(u, fa.obtenerDatosEmpresa(new Usuario((String) empresaVenta.getSelectedItem(), false, false))));
    }//GEN-LAST:event_crearOfertaVenta

    private void empresaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empresaVentaActionPerformed
        // Actualizar slider
        numeroVenta.setValue(0);
        numeroVenta.setMaximum(fa.getParticipacionesEmpresa(u, fa.obtenerDatosEmpresa(new Usuario((String) empresaVenta.getSelectedItem(), false, false))));
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
    private vista.componentes.Boton ventaBoton;
    // End of variables declaration//GEN-END:variables
}
