package vista.modeloTablas;

import aplicacion.Usuario;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaAlta extends AbstractTableModel{

    private java.util.List<Usuario> usuariosAlta;

    public ModeloTablaAlta() {
        this.usuariosAlta = new java.util.ArrayList<Usuario>();
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return usuariosAlta.size();
    }

    @Override
    public String getColumnName(int col) {
        String nombre = "";

        switch (col) {
            case 0:
                nombre = "Id de usuario";
                break;
            case 1:
                nombre = "Nombre";
                break;
            case 2:
                nombre = "DNI/CIF";
                break;


        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col) {
        Class clase = null;

        switch (col) {
            case 0:
            case 2:
            case 1:
                clase = java.lang.String.class;
                break;

        }
        return clase;
    }

    public Object getValueAt(int row, int col) {
        Object resultado = null;

        switch (col) {
            case 0:
                resultado = usuariosAlta.get(row).getIdUsuario();
                break;
            case 1:
                resultado = "Nombre";
                break;
            case 2:
                resultado = "DNI";
                break;

        }
        return resultado;
    }

    public void setFilas(java.util.List<Usuario> usuariosAlta) {
        this.usuariosAlta = usuariosAlta;
        fireTableDataChanged();
    }

    public void nuevoUsuario(Usuario u) {
        this.usuariosAlta.add(u);
        fireTableRowsInserted(this.usuariosAlta.size() - 1, this.usuariosAlta.size() - 1);
    }

    public void borrarUsuario(int indice) {
        this.usuariosAlta.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<Usuario> getFilas() {
        return this.usuariosAlta;
    }

    public Usuario obtenerUsuario(int i) {
        return this.usuariosAlta.get(i);
    }
}

