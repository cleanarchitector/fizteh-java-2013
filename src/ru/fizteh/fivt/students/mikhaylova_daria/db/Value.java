package ru.fizteh.fivt.students.mikhaylova_daria.db;

import ru.fizteh.fivt.storage.structured.*;

import java.util.ArrayList;
import java.util.HashMap;


public class Value implements Storeable {

    private ArrayList<Object> value;
    private Table table;

    Value(Table table) {
        this.table = table;
        value = new ArrayList<>(table.getColumnsCount());
        for (int i = 0; i < table.getColumnsCount(); ++i) {
            value.add(new Object());
        }
    }

    private Class<?> normType(String arg) {
        HashMap<String, Class<?>> types = new HashMap<>();
        types.put("Integer", Integer.class);
        types.put("Long", Long.class);
        types.put("Double", Double.class);
        types.put("Float", Float.class);
        types.put("Boolean", Boolean.class);
        types.put("Byte", Byte.class);
        types.put("byte", Byte.class);
        types.put("String", String.class);
        types.put("int", Integer.class);
        types.put("long", Long.class);
        types.put("double", Double.class);
        types.put("float", Float.class);
        types.put("boolean", Boolean.class);
        return types.get(arg);
    }

    public void setColumnAt(int columnIndex, Object value) throws ColumnFormatException, IndexOutOfBoundsException {
        if (table.getColumnsCount() <= columnIndex) {
            throw new IndexOutOfBoundsException("Wrong index of column " + columnIndex);
        }
        if (!(value == null)) {
            if (normType(value.getClass().getSimpleName()) == null) {
                throw new ColumnFormatException("This type is not supposed: " + value.getClass().getCanonicalName());
            }
            if (!value.getClass().equals(table.getColumnType(columnIndex))) {
                throw new ColumnFormatException("Wrong type of value: " + value.getClass()
                        + " but expected "  + table.getColumnType(columnIndex));
            }
        }
        this.value.add(columnIndex, value);
    }


    public Object getColumnAt(int columnIndex) throws IndexOutOfBoundsException {
        if (table.getColumnsCount() <= columnIndex) {
            throw new IndexOutOfBoundsException("Wrong index of column" + columnIndex);
        }
        return value.get(columnIndex);
    }


    public Integer getIntAt(int columnIndex) throws ColumnFormatException, IndexOutOfBoundsException {
        if (table.getColumnsCount() <= columnIndex) {
            throw new IndexOutOfBoundsException("Wrong index of column" + columnIndex);
        }
        if (!table.getColumnType(columnIndex).equals(Integer.class)) {
            throw new ColumnFormatException("Type of this column is"
                    + table.getColumnType(columnIndex).getCanonicalName());
        }
        Integer integer = null;
        if (value.get(columnIndex) != null) {
            System.out.println(value.get(columnIndex) + "ind" + columnIndex);
            integer = (Integer) value.get(columnIndex);
            System.out.println(integer + " " + value.get(columnIndex) + "\n");
        }
        return integer;
    }


    public Long getLongAt(int columnIndex) throws ColumnFormatException, IndexOutOfBoundsException {
        if (table.getColumnsCount() <= columnIndex) {
            throw new IndexOutOfBoundsException("Wrong index of column " + columnIndex);
        }
        if (!table.getColumnType(columnIndex).equals(Long.class)) {
            throw new ColumnFormatException("Type of this column is "
                    + table.getColumnType(columnIndex).getCanonicalName());
        }
        Long val = null;
        if (value.get(columnIndex) != null) {
            val = (Long) value.get(columnIndex);
        }
        return val;
    }

    public Byte getByteAt(int columnIndex) throws ColumnFormatException, IndexOutOfBoundsException {
        if (table.getColumnsCount() <= columnIndex) {
            throw new IndexOutOfBoundsException("Wrong index of column " + columnIndex);
        }
        if (!table.getColumnType(columnIndex).equals(Byte.class)) {
            throw new ColumnFormatException("Type of this column is "
                    + table.getColumnType(columnIndex).getCanonicalName());
        }
        Byte val = null;
        if (value.get(columnIndex) != null) {
            val = (Byte) value.get(columnIndex);
        }
        return val;
    }

    public Float getFloatAt(int columnIndex) throws ColumnFormatException, IndexOutOfBoundsException {
        if (table.getColumnsCount() <= columnIndex) {
            throw new IndexOutOfBoundsException("Wrong index of column " + columnIndex);
        }
        if (!table.getColumnType(columnIndex).equals(Float.class)) {
            throw new ColumnFormatException("Type of this column is "
                    + table.getColumnType(columnIndex).getCanonicalName());
        }
        Float val = null;
        if (value.get(columnIndex) != null) {
            val = (Float) value.get(columnIndex);
        }
        return val;
    }

    public Double getDoubleAt(int columnIndex) throws ColumnFormatException, IndexOutOfBoundsException {
        if (table.getColumnsCount() <= columnIndex) {
            throw new IndexOutOfBoundsException("Wrong index of column " + columnIndex);
        }
        if (!table.getColumnType(columnIndex).equals(Double.class)) {
            throw new ColumnFormatException("Type of this column is "
                    + table.getColumnType(columnIndex).getCanonicalName());
        }
        Double val = null;
        if (value.get(columnIndex) != null) {
            val = (Double) value.get(columnIndex);
        }
        return val;
    }

    public Boolean getBooleanAt(int columnIndex) throws ColumnFormatException, IndexOutOfBoundsException {
        if (table.getColumnsCount() <= columnIndex) {
            throw new IndexOutOfBoundsException("Wrong index of column " + columnIndex);
        }
        if (!table.getColumnType(columnIndex).equals(Boolean.class)) {
            throw new ColumnFormatException("Type of this column is "
                    + table.getColumnType(columnIndex).getCanonicalName());
        }
        Boolean val = null;
        if (value.get(columnIndex) != null) {
            val = (Boolean) value.get(columnIndex);
        }
        return val;
    }

    public String getStringAt(int columnIndex) throws ColumnFormatException, IndexOutOfBoundsException {
        if (table.getColumnsCount() <= columnIndex) {
            throw new IndexOutOfBoundsException("Wrong index of column " + columnIndex);
        }
        if (!table.getColumnType(columnIndex).equals(String.class)) {
            throw new ColumnFormatException("Type of this column is "
                    + table.getColumnType(columnIndex).getCanonicalName());
        }
        String val = null;
        if (value.get(columnIndex) != null) {
            val = (String) value.get(columnIndex);
        }
        return val;
    }

}
