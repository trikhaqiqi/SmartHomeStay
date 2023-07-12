package com.codingtest.smarthome.dto.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Accessors(chain = true)
public class DatatableResponse<T> {
    private long total = 0;
    private Collection<T> data = Collections.emptyList();
}
