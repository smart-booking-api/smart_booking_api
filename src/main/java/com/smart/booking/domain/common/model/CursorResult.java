package com.smart.booking.domain.common.model;

import java.util.List;
import lombok.NonNull;


public record CursorResult<T>(
    @NonNull List<T> content,
    boolean hasNext,
    int totalCount
) {

}
