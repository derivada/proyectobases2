package vista.modeloTablas;

import aplicacion.EntradaHistorial;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import vista.componentes.Utils;

public class ModeloTablaHistorial extends AbstractTableModel {

    private java.util.List<EntradaHistorial> historial;

    public ModeloTablaHistorial() {
        this.historial = new java.util.ArrayList<>();
    }

    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        return historial.size();
    }

    // para la ordenacion al clickar
    private boolean sortInversor = false;
    private int ultimaColumna = 0;

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
                clase = java.lang.Integer.class;
                break;
            case 5:
                clase = java.lang.String.class;
                break;
        }
        return clase;
    }

    public Object getValueAt(int row, int col) {
        Object resultado = null;

        switch (col) {
            case 0:
                resultado = historial.get(row).getTipo().toString();
                break;
            case 1:
                resultado = historial.get(row).getComprador();
                break;
            case 2:
                resultado = historial.get(row).getEmpresa();
                break;
            case 3:
                resultado = historial.get(row).getFecha();
                break;
            case 4:
                resultado = historial.get(row).getCantidad();
                break;
            case 5:
                resultado = Utils.displayCurrency(historial.get(row).getPrecio());
                break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<EntradaHistorial> historial) {
        this.historial = historial;
        fireTableDataChanged();
    }

    public void insertarEntrada(EntradaHistorial b) {
        this.historial.add(b);
        fireTableRowsInserted(this.historial.size() - 1, this.historial.size() - 1);
    }

    public void borrarEntrada(int indice) {
        this.historial.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<EntradaHistorial> getFilas() {
        return this.historial;
    }

    public EntradaHistorial obtenerEntrada(int i) {
        return this.historial.get(i);
    }

    public void ordenarPor(int col) {
        final boolean sort = ultimaColumna != col ? false : sortInversor;

        switch (col) {
            case 0:
                // ordenar por tipo (orden alfabetico)
                historial.sort((o1, o2) -> {
                    if (sort) {
                        return o2.getTipo().toString().compareTo(o1.getTipo().toString());
                    } else {
                        return o1.getTipo().toString().compareTo(o2.getTipo().toString());
                    }
                });
                break;
            case 1:
                // ordenar por usuario (orden alfabetico)
                historial.sort((o1, o2) -> {
                    if (sort) {
                        return o2.getComprador().compareTo(o1.getComprador());
                    } else {
                        return o1.getComprador().compareTo(o2.getComprador());
                    }
                });
                break;
            case 2:
                // ordenar por empresa (orden alfabetico)
                historial.sort((o1, o2) -> {
                    if (sort) {
                        return o2.getEmpresa().compareTo(o1.getEmpresa());
                    } else {
                        return o1.getEmpresa().compareTo(o2.getEmpresa());
                    }
                });
                break;
            case 3:
                // ordenar por fecha (orden cronologico)
                historial.sort((o1, o2) -> {
                    if (!sort) {
                        return o2.getFecha().compareTo(o1.getFecha());
                    } else {
                        return o1.getFecha().compareTo(o2.getFecha());
                    }
                });
                break;
            case 4:
                // ordenar por numero (orden numerico)
                historial.sort((o1, o2) -> {
                    if (!sort) {
                        return o2.getCantidad().compareTo(o1.getCantidad());
                    } else {
                        return o1.getCantidad().compareTo(o2.getCantidad());
                    }
                });
                break;
            case 5:
                // ordenar por precio (orden numerico)
                historial.sort((o1, o2) -> {
                    if (!sort) {
                        return o2.getPrecio().compareTo(o1.getPrecio());
                    } else {
                        return o1.getPrecio().compareTo(o2.getPrecio());
                    }
                });
                break;
        }
        fireTableDataChanged();

        // Poner el sort normal
        if (ultimaColumna != col) {
            ultimaColumna = col;
            sortInversor = false;
        } else {
            // Invertir para la sigueinte
            sortInversor = !sortInversor;
        }
    }
}
