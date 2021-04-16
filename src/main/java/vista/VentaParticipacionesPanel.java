package vista;

import aplicacion.Empresa;
import aplicacion.FachadaAplicacion;
import aplicacion.Usuario;

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
        lista.remove(u.getIdUsuario());
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
        numeroVentaTextBox = new vista.componentes.TextBox();
        ventaBoton = new vista.componentes.Boton();
        empresaVenta = new vista.componentes.SelecionBox<String>(nombresOtrosUsuarios);

        precioVentaTextBox.setName("precioVentaTextBox"); // NOI18N

        precioVentaLabel.setText("Precio total");
        precioVentaLabel.setName("precioVentaLabel"); // NOI18N

        empresaVentaLabel.setText("Empresa");
        empresaVentaLabel.setName("empresaVentaLabel"); // NOI18N

        numeroVentaLabel.setText("Número de participaciones");
        numeroVentaLabel.setName("numeroVentaLabel"); // NOI18N

        numeroVentaTextBox.setName("numeroVentaTextBox"); // NOI18N

        ventaBoton.setText("Crear oferta de venta");
        ventaBoton.setName("ventaBoton"); // NOI18N
        ventaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearOfertaVenta(evt);
            }
        });

        empresaVenta.setName("empresaVenta"); // NOI18N

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(numeroVentaTextBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(numeroVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ventaBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(numeroVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(numeroVentaTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(precioVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(precioVentaTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(empresaVentaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(empresaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(ventaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(60, 60, 60))
        );
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

        int numero = -1;
        try {
            numero = Integer.parseInt(numeroVentaTextBox.getText());
        } catch (Exception e) {
            fa.muestraExcepcion("El número de participaciones a vender no es un número bien formado!");
            return;
        }
        if (numero <= 0) {
            fa.muestraExcepcion("El número de participaciones debe ser positivo!");
            return;
        }
        int participacionesDisponibles = fa.getParticipacionesEmpresa(u, empresa);
        if (participacionesDisponibles < numero) {
            fa.muestraExcepcion("El usuario no posee suficientes participaciones!\n" +
                    "Número actual de participaciones de " + empresa.getIdUsuario() + ": " + participacionesDisponibles);
            return;
        }

        // 2. Crear oferta de venta
        fa.crearOfertaVenta(u, empresa, numero, precioVenta);
    }//GEN-LAST:event_crearOfertaVenta


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.SelecionBox empresaVenta;
    private vista.componentes.Etiqueta empresaVentaLabel;
    private vista.componentes.Etiqueta numeroVentaLabel;
    private vista.componentes.TextBox numeroVentaTextBox;
    private vista.componentes.Etiqueta precioVentaLabel;
    private vista.componentes.TextBox precioVentaTextBox;
    private vista.componentes.Boton ventaBoton;
    // End of variables declaration//GEN-END:variables
}
