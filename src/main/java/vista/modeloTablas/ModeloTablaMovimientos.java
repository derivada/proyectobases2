package vista.modeloTablas;

import aplicacion.Historial;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;
//TODO: Cambiar
public class ModeloTablaMovimientos extends AbstractTableModel{

    private java.util.List<Historial> historial;

    public ModeloTablaMovimientos() {
        this.historial = new java.util.ArrayList<Historial>();
    }

    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        return historial.size();
    }

    @Override
    public String getColumnName(int col) {
        String nombre = "";

        switch (col) {
            case 0:
                nombre = "Tipo";
                break;
            case 1:
                nombre = "Usuario";
                break;
            case 2:
                nombre = "Empresa";
                break;
            case 3:
                nombre = "Fecha";
                break;
            case 4:
                nombre = "Numero de participaciones";
                break;
            case 5:
                nombre = "Precio";


        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col) {
        Class clase = null;

        switch (col) {
            case 0:
                clase = java.lang.String.class;
                break;
            case 1:
                clase = java.lang.String.class;
                break;
            case 2:
                clase = java.lang.String.class;
                break;
            case 3:
                clase = Date.class;
                break;
            case 4:
                clase = java.lang.Double.class;
                break;
            case 5:
                clase = java.lang.Double.class;
                break;
        }
        return clase;
    }

    public Object getValueAt(int row, int col) {
        Object resultado = null;

        switch (col) {
            case 0:
                resultado = historial.get(row).getTipo();
                break;
            case 1:
                resultado = historial.get(row).getEmpresa();
                break;
            case 2:
                resultado = historial.get(row).getComprador();
                break;
            case 3:
                resultado = historial.get(row).getFecha();
                break;
            case 4:
                resultado = historial.get(row).getCantidad();
                break;
            case 5:
                resultado = historial.get(row).getPrecio();
                break;

        }
        return resultado;
    }

    public void setFilas(java.util.List<Historial> historial) {
        this.historial = historial;
        fireTableDataChanged();
    }

    public void nuevaHistoria(Historial b) {
        this.historial.add(b);
        fireTableRowsInserted(this.historial.size() - 1, this.historial.size() - 1);
    }

    public void borrarOferta(int indice) {
        this.historial.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<Historial> getFilas() {
        return this.historial;
    }

    public Historial obtenerOfertas(int i) {
        return this.historial.get(i);
    }

}
