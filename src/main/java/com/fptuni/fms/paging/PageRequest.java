package com.fptuni.fms.paging;

import com.fptuni.fms.sort.Sorter;

public class PageRequest implements Pageable {
    private final Integer page;
    private final Integer maxItemInPage;
    private final Sorter sorter;

    public PageRequest() {
        page = 1;
        maxItemInPage = 0;
        sorter = null;
    }

    public PageRequest(Integer page, Integer maxPageItem, Sorter sorter) {
        this.page = page;
        this.maxItemInPage = maxPageItem;
        this.sorter = sorter;
    }

    @Override
    public Integer getPage() {
        return this.page;
    }

    @Override
    public Integer getOffset() {
        if (this.page != null && this.maxItemInPage != null) {
            return (this.page - 1) * this.maxItemInPage;
        }
        return null;
    }

    @Override
    public Integer getLimit() {
        return this.maxItemInPage;
    }

    @Override
    public Sorter getSorter() {
        if (this.sorter != null) {
            return this.sorter;
        }
        return null;
    }
}
