package com.fptuni.fms.paging;

import com.fptuni.fms.sort.Sorter;

public interface Pageable {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    Sorter getSorter();
}
