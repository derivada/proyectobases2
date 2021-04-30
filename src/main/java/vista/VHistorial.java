package vista;

import aplicacion.EntradaHistorial;
import aplicacion.EntradaHistorial.TipoEntradaHistorial;
import aplicacion.FachadaAplicacion;
import aplicacion.Regulador;
import aplicacion.Usuario;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.JTableHeader;
import vista.componentes.FuentesGUI;
import vista.componentes.ImagenesGUI;
import vista.componentes.Utils;
import vista.modeloTablas.ModeloTablaHistorial;

/**
 * Ventana emergente para ver el historial y filtrarlo según diversos parámetros
 * Accesible desde VEmpresa, VInversor y VRegulador, pero con más permisos desde
 * VRegulador
 *
 */
public class VHistorial extends javax.swing.JDialog {

    private final Usuario u;
    private boolean regulador = false;
    private final List<String> usuariosSeleccionables;
    private final List<String> tiposSeleccionables;
    private final FachadaAplicacion fa = FachadaAplicacion.getInstance();

    // Entradas en la tabla
    private List<EntradaHistorial> entradasActuales;

    // Requerido por netbeans, no usar
    public VHistorial() {
        initComponents();
        u = null;
        usuariosSeleccionables = null;
        tiposSeleccionables = null;
    }

    public VHistorial(Usuario u) {
        super();
        this.u = u;
        this.setTitle("Historial de transicciones");
        this.setIconImage(ImagenesGUI.getImage("database.png", 128));
        this.setModal(false);

        if (u instanceof Regulador) {
            this.regulador = true;
        }

        // Lista de usuarios seleccionables (se podrán ver sus transacciones)
        usuariosSeleccionables = new ArrayList<String>();
        if (regulador) {
            usuariosSeleccionables.add("Ver todos");
            usuariosSeleccionables.addAll(fa.obtenerListaUsuarios()
                    .stream().map(us -> us.getIdUsuario()).collect(Collectors.toList()));
        } else {
            usuariosSeleccionables.add(u.getIdUsuario());
        }

        // Lista de tipos de transaccion seleccionables
        tiposSeleccionables = new ArrayList<>();
        tiposSeleccionables.add("Ver todos");
        TipoEntradaHistorial[] tipos = EntradaHistorial.TipoEntradaHistorial.values();
        for (TipoEntradaHistorial tipo : tipos) {
            tiposSeleccionables.add(tipo.toString());
        }

        initComponents();
        actualizarTabla();
        this.deslizadorCantidad.setValue(0);
        this.deslizadorPrecio.setValue(0);

        // Configurar sorts
        tablaOrdenable();

        // Sort default
        ModeloTablaHistorial m = (ModeloTablaHistorial) tablaHistorial.getModel();
        m.ordenarPor(3);

        this.setVisible(true);

    }

    private void tablaOrdenable() {
        JTableHeader header = tablaHistorial.getTableHeader();
        header.addMouseListener(new java.awt.event.MouseListener() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordenarTablaPorColumna(evt);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void ordenarTablaPorColumna(MouseEvent evt) {
        Point point = evt.getPoint();
        int col = tablaHistorial.columnAtPoint(point);
        ModeloTablaHistorial m = (ModeloTablaHistorial) tablaHistorial.getModel();
        m.ordenarPor(col);
    }

    private void actualizarTabla() {
        // Actualiza la lista del historial
        String tipoActual = (String) elegirTipo.getSelectedItem();
        Usuario usuarioActual = new Usuario((String) elegirUsuario.getSelectedItem(), false, true);
        final int precioMaxActual = deslizadorPrecio.getValue();
        final int cantidadMaxActual = deslizadorCantidad.getValue();

        if (regulador) {
            if (((String) elegirUsuario.getSelectedItem()).equals("Ver todos")) {
                entradasActuales = fa.obtenerHistorial();
            } else {
                entradasActuales = fa.obtenerHistorial(usuarioActual);
            }
        } else {
            entradasActuales = fa.obtenerHistorial(u);
        }

        // Filtrar
        // 1. Por tipo
        if (tipoActual != null && (!tipoActual.equals("Ver todos"))) {
            entradasActuales.removeIf(e -> e.getTipo().toString() != tipoActual);
        }

        // Recalcular máximos de los sliders (ANTES DEL FILTRO POR ELLOS)
        // 2. Por precio máximo
        this.deslizadorPrecio.setMaximum(getPrecioMaxActual());
        entradasActuales.removeIf(e -> e.getPrecio() < precioMaxActual);

        this.deslizadorCantidad.setMaximum(getCantidadMaxActual());
        // 3. Por cantidad máxima
        entradasActuales.removeIf(e -> e.getCantidad() < cantidadMaxActual);

        ModeloTablaHistorial m = (ModeloTablaHistorial) tablaHistorial.getModel();
        m.setFilas(entradasActuales);

    }

    private int getCantidadMaxActual() {
        int cantidadMax = 0;
        for (EntradaHistorial e : entradasActuales) {
            if (e.getCantidad() > cantidadMax) {
                cantidadMax = e.getCantidad();
            }
        }
        return cantidadMax;
    }

    private int getPrecioMaxActual() {
        int precioMax = 0;
        for (EntradaHistorial e : entradasActuales) {
            if (Math.ceil(e.getPrecio()) > precioMax) {
                precioMax = (int) Math.ceil(e.getPrecio());
            }
        }
        return precioMax;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        tablaHistorial = new vista.componentes.Tabla();
        titulo = new vista.componentes.Etiqueta();
        usuarioLabel = new vista.componentes.Etiqueta();
        elegirUsuario = new vista.componentes.SelecionBox(usuariosSeleccionables.toArray());
        etiqueta1 = new vista.componentes.Etiqueta();
        elegirTipo = new vista.componentes.SelecionBox(tiposSeleccionables.toArray());
        botonVolver = new vista.componentes.Boton();
        labelPrecio = new vista.componentes.Etiqueta();
        deslizadorPrecio = new vista.componentes.Deslizador();
        deslizadorCantidad = new vista.componentes.Deslizador();
        cantidadMaximaLabel = new vista.componentes.Etiqueta();
        precioLabel = new vista.componentes.Etiqueta();
        cantidadLabel = new vista.componentes.Etiqueta();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaHistorial.setModel(new vista.modeloTablas.ModeloTablaHistorial()
        );
        tablaHistorial.setToolTipText("");
        jScrollPane4.setViewportView(tablaHistorial);

        titulo.setText("Historial de transacciones");

        usuarioLabel.setText("Usuario:");

        elegirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elegirUsuarioActionPerformed(evt);
            }
        });

        etiqueta1.setText("Tipo de transacción:");

        elegirTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elegirTipoActionPerformed(evt);
            }
        });

        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        labelPrecio.setText("Precio máximo");

        deslizadorPrecio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                deslizadorPrecioStateChanged(evt);
            }
        });

        deslizadorCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                deslizadorCantidadStateChanged(evt);
            }
        });

        cantidadMaximaLabel.setText("Cantidad máxima");

        precioLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        precioLabel.setText("0");

        cantidadLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cantidadLabel.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(etiqueta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(elegirUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(elegirTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deslizadorPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                            .addComponent(cantidadMaximaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deslizadorCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precioLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cantidadLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elegirUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(etiqueta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elegirTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(labelPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deslizadorPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(precioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(cantidadMaximaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deslizadorCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );

        titulo.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA, FuentesGUI.Size.ENORME));
        if(!regulador)
        elegirUsuario.setEnabled(false);
        else
        elegirUsuario.setEnabled(true);

        elegirUsuario.setSelectedIndex(0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void elegirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elegirUsuarioActionPerformed
        actualizarTabla();
    }//GEN-LAST:event_elegirUsuarioActionPerformed

    private void elegirTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elegirTipoActionPerformed
        actualizarTabla();
    }//GEN-LAST:event_elegirTipoActionPerformed

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonVolverActionPerformed

    private void deslizadorPrecioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_deslizadorPrecioStateChanged
        actualizarTabla();
        this.precioLabel.setText(Utils.displayCurrency(deslizadorPrecio.getValue()));
    }//GEN-LAST:event_deslizadorPrecioStateChanged

    private void deslizadorCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_deslizadorCantidadStateChanged
        actualizarTabla();
        this.cantidadLabel.setText(Integer.toString(deslizadorCantidad.getValue()));
    }//GEN-LAST:event_deslizadorCantidadStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.componentes.Boton botonVolver;
    private vista.componentes.Etiqueta cantidadLabel;
    private vista.componentes.Etiqueta cantidadMaximaLabel;
    private vista.componentes.Deslizador deslizadorCantidad;
    private vista.componentes.Deslizador deslizadorPrecio;
    private vista.componentes.SelecionBox elegirTipo;
    private vista.componentes.SelecionBox elegirUsuario;
    private vista.componentes.Etiqueta etiqueta1;
    private javax.swing.JScrollPane jScrollPane4;
    private vista.componentes.Etiqueta labelPrecio;
    private vista.componentes.Etiqueta precioLabel;
    private vista.componentes.Tabla tablaHistorial;
    private vista.componentes.Etiqueta titulo;
    private vista.componentes.Etiqueta usuarioLabel;
    // End of variables declaration//GEN-END:variables
}
