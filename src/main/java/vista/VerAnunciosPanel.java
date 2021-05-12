package vista;

import aplicacion.AnuncioBeneficios;
import aplicacion.FachadaAplicacion;
import aplicacion.Usuario;

import java.util.List;
import java.util.stream.Collectors;

import vista.componentes.FuentesGUI;
import vista.componentes.Utils;
import vista.modeloTablas.ModeloTablaBeneficios;

public class VerAnunciosPanel extends javax.swing.JPanel {

    /**
     * Panel donde se consultan las participaciones disponibles en cada empresa
     */
    private Usuario u;
    private FachadaAplicacion fa;
    private String[] empresasDisponibles;

    public VerAnunciosPanel() {
        // No usar, requerido por NetBeans
        empresasDisponibles = new String[0];
        initComponents();
    }

    public VerAnunciosPanel(Usuario usuario, FachadaAplicacion fa) {
        this.u = usuario;
        this.fa = fa;
        List<String> lista = fa.obtenerListaEmpresas().stream().map(e -> e.getIdUsuario()).collect(Collectors.toList());
        lista.removeIf(e -> e.equals(usuario.getIdUsuario()));
        empresasDisponibles = new String[lista.size()];
        empresasDisponibles = lista.toArray(empresasDisponibles);
        
        initComponents();
        actualizarDatos();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new vista.componentes.Etiqueta();
        seleccionEmpresaLabel = new vista.componentes.Etiqueta();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAnuncios = new vista.componentes.Tabla();
        beneficioPartLabel = new vista.componentes.Etiqueta();
        benefPart = new vista.componentes.Etiqueta();
        beneficioMonetarioLabel = new vista.componentes.Etiqueta();
        beneficioMonetario = new vista.componentes.Etiqueta();
        seleccionEmpresa = new vista.componentes.SelecionBox(empresasDisponibles);

        setPreferredSize(new java.awt.Dimension(900, 600));

        titulo.setText("Lista de anuncios");
        titulo.setName("titulo"); // NOI18N

        seleccionEmpresaLabel.setText("Empresa:");
        seleccionEmpresaLabel.setName("seleccionEmpresaLabel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaAnuncios.setModel(new ModeloTablaBeneficios());
        tablaAnuncios.setName("tablaAnuncios"); // NOI18N
        jScrollPane1.setViewportView(tablaAnuncios);

        beneficioPartLabel.setText("Beneficio en participaciones:");
        beneficioPartLabel.setName("beneficioPartLabel"); // NOI18N

        benefPart.setText("0");
        benefPart.setName("benefPart"); // NOI18N

        beneficioMonetarioLabel.setText("Beneficio monetario:");
        beneficioMonetarioLabel.setName("beneficioMonetarioLabel"); // NOI18N

        beneficioMonetario.setText("0");
        beneficioMonetario.setName("beneficioMonetario"); // NOI18N

        seleccionEmpresa.setName("seleccionEmpresa"); // NOI18N
        seleccionEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionEmpresaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(seleccionEmpresaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(beneficioPartLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(benefPart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(beneficioMonetarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(beneficioMonetario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(seleccionEmpresaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seleccionEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(beneficioPartLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(benefPart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(beneficioMonetarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(beneficioMonetario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );

        titulo.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL,
            FuentesGUI.Size.GRANDE));
    benefPart.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA,
        FuentesGUI.Size.GRANDE));
beneficioMonetario.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA,
    FuentesGUI.Size.GRANDE));
    seleccionEmpresa.setSelectedItem(0);
    }// </editor-fold>//GEN-END:initComponents

    private void seleccionEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionEmpresaActionPerformed
        actualizarDatos();
    }//GEN-LAST:event_seleccionEmpresaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.Etiqueta benefPart;
    private vista.componentes.Etiqueta beneficioMonetario;
    private vista.componentes.Etiqueta beneficioMonetarioLabel;
    private vista.componentes.Etiqueta beneficioPartLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private vista.componentes.SelecionBox seleccionEmpresa;
    private vista.componentes.Etiqueta seleccionEmpresaLabel;
    private vista.componentes.Tabla tablaAnuncios;
    private vista.componentes.Etiqueta titulo;
    // End of variables declaration//GEN-END:variables

    public void actualizarDatos() {
        List<AnuncioBeneficios> entradas = fa.obtenerAnuncios(seleccionEmpresa.getSelectedItem().toString());
        this.beneficioMonetario.setText(String.valueOf(entradas.size()));
        int sumaPartBenef = 0;
        float sumaDineroBenef = 0.0f;
        for (AnuncioBeneficios a : entradas) {
            sumaPartBenef += a.getNumeroparticipaciones();
            sumaDineroBenef += a.getImporteparticipacion();
        }

        this.benefPart.setText(String.valueOf(sumaPartBenef));
        this.benefPart.setText(Utils.displayCurrency(sumaDineroBenef));

        ModeloTablaBeneficios m = (ModeloTablaBeneficios) tablaAnuncios.getModel();
        m.setFilas(entradas);
    }
}