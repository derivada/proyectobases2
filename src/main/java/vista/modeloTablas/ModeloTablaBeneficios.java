package vista.modeloTablas;

import aplicacion.AnuncioBeneficios;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;

public class ModeloTablaBeneficios extends AbstractTableModel{

    private java.util.List<AnuncioBeneficios> beneficios;

    public ModeloTablaBeneficios() {
        this.beneficios = new java.util.ArrayList<AnuncioBeneficios>();
    }

    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return beneficios.size();
    }

    @Override
    public String getColumnName(int col) {
        String nombre = "";

        switch (col) {
            case 0:
                nombre = "Empresa";
                break;
            case 1:
                nombre = "Fecha pago";
                break;
            case 2:
                nombre = "Fecha anuncio";
                break;
            case 3:
                nombre = "Importe por participacion";
                break;


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

            case 2:
                clase = Date.class;
                break;

            case 3:
                clase = java.lang.Double.class;
                break;
        }
        return clase;
    }

    public Object getValueAt(int row, int col) {
        Object resultado = null;

        switch (col) {
            case 0:
                resultado = beneficios.get(row).getEmpresa();
                break;
            case 1:
                resultado = beneficios.get(row).getFechaPago();
                break;
            case 2:
                resultado = beneficios.get(row).getFechaAnuncio();
                break;
            case 3:
                resultado = beneficios.get(row).getImporteparticipacion();

                break;

        }
        return resultado;
    }

    public void setFilas(java.util.List<AnuncioBeneficios> beneficios) {
        this.beneficios = beneficios;
        fireTableDataChanged();
    }

    public void nuevoAnuncio(AnuncioBeneficios b) {
        this.beneficios.add(b);
        fireTableRowsInserted(this.beneficios.size() - 1, this.beneficios.size() - 1);
    }

    public void borrarAnuncio(int indice) {
        this.beneficios.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<AnuncioBeneficios> getFilas() {
        return this.beneficios;
    }

    public AnuncioBeneficios obtenerBeneficios(int i) {
        return this.beneficios.get(i);
    }

}