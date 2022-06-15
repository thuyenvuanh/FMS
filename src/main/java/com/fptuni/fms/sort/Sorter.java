package com.fptuni.fms.sort;

public class Sorter {
    private String sortField;
    private Boolean isAscending;

    public Sorter() {
    }

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

    public Boolean isAscending() {
        return isAscending;
    }

    public void setAscending(Boolean bool) {
        isAscending = bool;
    }
}
