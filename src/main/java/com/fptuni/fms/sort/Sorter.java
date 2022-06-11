package com.fptuni.fms.sort;

public class Sorter {
    private String sortField;
    private boolean isAscending; // true: ASC; false: DESC

    public Sorter(String sortField, boolean isAscending){
        this.sortField = sortField;
        this.isAscending = isAscending;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public boolean isAscending() {
        return isAscending;
    }

    public void setAscending(boolean bool) {
        isAscending = bool;
    }
}
