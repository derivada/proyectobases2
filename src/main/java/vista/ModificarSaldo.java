/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import aplicacion.FachadaAplicacion;
import aplicacion.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;

import vista.componentes.DialogoInfo;
import vista.componentes.FuentesGUI;
import vista.componentes.ImagenesGUI;
import vista.componentes.Utils;


public class ModificarSaldo extends javax.swing.JDialog {

    FachadaAplicacion fa;
    private final Map<String, String> mapaSeleccionables;
    private final List<Usuario> usuariosSeleccionables;

    public ModificarSaldo(JFrame parent, FachadaAplicacion fa) {
        super(parent, true);
        this.fa = fa;
        this.setTitle("Pestaña de modificación de saldos");
        this.setIconImage(ImagenesGUI.getImage("database.png", 128));

        usuariosSeleccionables = new ArrayList<>();
        mapaSeleccionables = new HashMap<>();
        for (Usuario u : fa.obtenerListaEmpresas()) {
            mapaSeleccionables.put(u.getIdUsuario(), "Empresa");
        }
        for (Usuario u : fa.obtenerListaInversores()) {
            mapaSeleccionables.put(u.getIdUsuario(), "Inversor");
        }

        usuariosSeleccionables.addAll(fa.obtenerListaEmpresas());
        usuariosSeleccionables.addAll(fa.obtenerListaInversores());

        initComponents();
        setValidators();
        this.setVisible(true);
    }

    private void setValidators() {
        nuevoSaldo.setValidator(s -> {
            if (s.isEmpty()) return true;
            try {
                return Float.parseFloat(s) >= 0.0f;
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        usuarioSelect = new vista.componentes.SelecionBox(usuariosSeleccionables.toArray());
        usarioLabel = new vista.componentes.Etiqueta();
        titulo = new vista.componentes.Etiqueta();
        etiqueta3 = new vista.componentes.Etiqueta();
        etiqueta4 = new vista.componentes.Etiqueta();
        etiqueta5 = new vista.componentes.Etiqueta();
        saldoActual = new vista.componentes.Etiqueta();
        nuevoSaldo = new vista.componentes.TextBox();
        botonModificar = new vista.componentes.Boton();
        dolar = new vista.componentes.Etiqueta();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        usuarioSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioSelectActionPerformed(evt);
            }
        });

        usarioLabel.setText("Usuario:");

        titulo.setText("Modificar saldo");

        etiqueta3.setText("Nuevo saldo: ");

        etiqueta5.setText("Saldo actual: ");

        saldoActual.setText("-----,--- $");

        nuevoSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoSaldoActionPerformed(evt);
            }
        });

        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        dolar.setText("$");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(403, 403, 403)
                        .addComponent(etiqueta4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(36, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(etiqueta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usuarioSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(nuevoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(etiqueta5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(saldoActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(60, 60, 60))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(usuarioSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saldoActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiqueta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nuevoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(etiqueta4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        titulo.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA,
            FuentesGUI.Size.GRANDE));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usuarioSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioSelectActionPerformed
        Usuario u = (Usuario) usuarioSelect.getSelectedItem();
        String tipo = mapaSeleccionables.get(u.getIdUsuario());

        saldoActual.setText(Utils.displayCurrency(fa.obtenerSaldo(u, tipo)));
    }//GEN-LAST:event_usuarioSelectActionPerformed

    private void nuevoSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevoSaldoActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        if (!nuevoSaldo.validateInput()) {
            fa.muestraExcepcion("El nuevo saldo introducido no es válido!", DialogoInfo.NivelDeAdvertencia.ADVERTENCIA);
            return;
        }
        float nuevoSaldo = this.nuevoSaldo.getText().isEmpty() ? 0 : Float.parseFloat(this.nuevoSaldo.getText());

        Usuario u = (Usuario) usuarioSelect.getSelectedItem();
        String tipo = mapaSeleccionables.get(u.getIdUsuario());

        System.out.println(this.nuevoSaldo.getText());
        fa.modificarSaldo(u.getIdUsuario(), nuevoSaldo, tipo);
        saldoActual.setText(Utils.displayCurrency(nuevoSaldo));
        this.nuevoSaldo.setText("");
    }//GEN-LAST:event_botonModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.Boton botonModificar;
    private vista.componentes.Etiqueta dolar;
    private vista.componentes.Etiqueta etiqueta3;
    private vista.componentes.Etiqueta etiqueta4;
    private vista.componentes.Etiqueta etiqueta5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private vista.componentes.TextBox nuevoSaldo;
    private vista.componentes.Etiqueta saldoActual;
    private vista.componentes.Etiqueta titulo;
    private vista.componentes.Etiqueta usarioLabel;
    private vista.componentes.SelecionBox usuarioSelect;
    // End of variables declaration//GEN-END:variables
}
