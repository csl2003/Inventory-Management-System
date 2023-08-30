package swings;

import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {
    private String[] columnNames;
    private Object[][] data;

    public CustomTableModel(String[] columnNames, Object[][] data) {
        this.columnNames = columnNames;
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public void setData(Object[][] newData) {
        this.data = newData;
        fireTableDataChanged();
    }
}
