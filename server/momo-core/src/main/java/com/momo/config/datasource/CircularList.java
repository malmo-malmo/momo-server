package com.momo.config.datasource;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CircularList<T> {

    private final List<T> list;

    private Integer counter = 0;

    public T getOne() {
        if (counter + 1 >= list.size()) {
            counter = -1;
        }
        return list.get(++counter);
    }
}