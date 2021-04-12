package vista.modeloTablas;

import aplicacion.Usuario;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaBaja extends AbstractTableModel{

    private java.util.List<Usuario> usuariosBaja;

    public ModeloTablaBaja() {
        this.usuariosBaja = new java.util.ArrayList<Usuario>();
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return usuariosBaja.size();
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
                resultado = usuariosBaja.get(row).getIdUsuario();
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
        this.usuariosBaja = usuariosAlta;
        fireTableDataChanged();
    }

    public void nuevoUsuario(Usuario u) {
        this.usuariosBaja.add(u);
        fireTableRowsInserted(this.usuariosBaja.size() - 1, this.usuariosBaja.size() - 1);
    }

    public void borrarUsuario(int indice) {
        this.usuariosBaja.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<Usuario> getFilas() {
        return this.usuariosBaja;
    }

    public Usuario obtenerUsuario(int i) {
        return this.usuariosBaja.get(i);
    }

}
