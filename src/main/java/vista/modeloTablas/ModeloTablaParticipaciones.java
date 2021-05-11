package vista.modeloTablas;

import aplicacion.EntradaParticipacion;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaParticipaciones extends AbstractTableModel {

    private java.util.List<EntradaParticipacion> entradas;
    // para la ordenacion al clickar
    private boolean sortInversor = false;
    private int ultimaColumna = 0;
    
    public ModeloTablaParticipaciones() {
        this.entradas = new java.util.ArrayList<>();
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return entradas.size();
    }

    @Override
    public String getColumnName(int col) {
        String nombre = "";

        switch (col) {
            case 0:
                nombre = "Poseedor";
                break;
            case 1:
                nombre = "Empresa";
                break;
            case 2:
                nombre = "Cantidad";
                break;

        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col) {
        Class clase = null;
        switch (col) {
            case 0:
            case 1:
                clase = String.class;
                break;
            case 2:
                clase = Integer.class;
                break;
        }
        return clase;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object resultado = null;

        switch (col) {
            case 0:
                resultado = entradas.get(row).getPoseedor();
                break;
            case 1:
                resultado = entradas.get(row).getEmpresa();
                break;
            case 2:
                resultado = entradas.get(row).getNumero();
                break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<EntradaParticipacion> entradasParticipacion) {
        this.entradas = entradasParticipacion;
        fireTableDataChanged();
    }

    public void nuevaOferta(EntradaParticipacion e) {
        this.entradas.add(e);
        fireTableRowsInserted(this.entradas.size() - 1, this.entradas.size() - 1);
    }

    public void borrarOferta(int indice) {
        this.entradas.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<EntradaParticipacion> getFilas() {
        return this.entradas;
    }

    public EntradaParticipacion obtenerOfertas(int i) {
        return this.entradas.get(i);
    }
    
     public void ordenarPor(int col) {
        final boolean sort = ultimaColumna != col ? false : sortInversor;

        switch (col) {
            case 1:
                // ordenar por empresa (orden alfabetico)
                entradas.sort((o1, o2) -> {
                    if (sort) {
                        return o2.getEmpresa().compareTo(o1.getEmpresa());
                    } else {
                        return o1.getEmpresa().compareTo(o2.getEmpresa());
                    }
                });
                break;
            case 2:
                // ordenar por cantidad (orden numérico)
                entradas.sort((o1, o2) -> {
                     if (!sort) {
                        return  Integer.compare(o1.getNumero(), o2.getNumero());
                    } else {
                        return Integer.compare(o2.getNumero(), o1.getNumero());
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
