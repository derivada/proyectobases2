package vista.modeloTablas;

import aplicacion.OfertaVenta;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;

public class ModeloTablaCompra extends AbstractTableModel {


    private java.util.List<OfertaVenta> ofertaVentas;

    public ModeloTablaCompra() {
        this.ofertaVentas = new java.util.ArrayList<OfertaVenta>();
    }

    public int getColumnCount() {
        return 5;
    }

    public int getRowCount() {
        return ofertaVentas.size();
    }

    @Override
    public String getColumnName(int col) {
        String nombre = "";

        switch (col) {
            case 0:
                nombre = "Usuario";
                break;
            case 1:
                nombre = "Empresa";
                break;
            case 2:
                nombre = "Fecha";
                break;
            case 3:
                nombre = "Número";
                break;
            case 4:
                nombre = "Precio";


        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col) {
        Class clase = null;

        switch (col) {
            case 0:
            case 1:
                clase = java.lang.String.class;
                break;
            case 2:
                clase = Date.class;
                break;
            case 3:
                clase = java.lang.Integer.class;
                break;
            case 4:
                clase = java.lang.Double.class;
                break;
        }
        return clase;
    }

    public Object getValueAt(int row, int col) {
        Object resultado = null;

        switch (col) {
            case 0:
                resultado = ofertaVentas.get(row).getEmpresa();
                break;
            case 1:
                resultado = ofertaVentas.get(row).getUsuario();
                break;
            case 2:
                resultado = ofertaVentas.get(row).getFecha();
                break;
            case 3:
                resultado = ofertaVentas.get(row).getNumParticipaciones();
                break;
            case 4:
                resultado = ofertaVentas.get(row).getPrecio();
                break;

        }
        return resultado;
    }

    public void setFilas(java.util.List<OfertaVenta> ofertaVentas) {
        this.ofertaVentas = ofertaVentas;
        fireTableDataChanged();
    }

    public void nuevaOferta(OfertaVenta b) {
        this.ofertaVentas.add(b);
        fireTableRowsInserted(this.ofertaVentas.size() - 1, this.ofertaVentas.size() - 1);
    }

    public void borrarOferta(int indice) {
        this.ofertaVentas.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<OfertaVenta> getFilas() {
        return this.ofertaVentas;
    }

    public OfertaVenta obtenerOfertas(int i) {
        return this.ofertaVentas.get(i);
    }


}
