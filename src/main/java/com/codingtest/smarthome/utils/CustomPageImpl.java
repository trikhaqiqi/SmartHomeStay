package com.codingtest.smarthome.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CustomPageImpl<T> implements Page {

    private List<T> content;
    private Pageable pageable;
    private long total = 0;

    public CustomPageImpl(List<T> content, Pageable pageable, long total) {
        super();
        this.content = content;
        this.pageable = pageable;
        this.total = total;
    }

    @Override
    public int getTotalPages() {
        return 0;
    }

    @Override
    public long getTotalElements() {
        return this.total;
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getNumberOfElements() {
        return this.content == null ? 0 : this.content.size();
    }

    @Override
    public List<T> getContent() {
        return this.content;
    }

    @Override
    public boolean hasContent() {
        return this.content != null && !this.content.isEmpty();
    }

    @Override
    public Sort getSort() {
        return pageable.getSort();
    }

    @Override
    public boolean isFirst() {
        return false;
    }

    @Override
    public boolean isLast() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Pageable nextPageable() {
        return null;
    }

    @Override
    public Pageable previousPageable() {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Page map(Function function) {
        return null;
    }

    @Override
    public Pageable getPageable() {
        return null;
    }

    @Override
    public Stream stream() {
        return null;
    }

    @Override
    public Streamable flatMap(Function mapper) {
        return null;
    }

    @Override
    public Streamable filter(Predicate predicate) {
        return null;
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }
}
