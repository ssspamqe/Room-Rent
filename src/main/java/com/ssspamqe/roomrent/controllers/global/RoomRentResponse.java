package com.ssspamqe.roomrent.controllers.global;

import java.util.Arrays;
import java.util.List;

public record RoomRentResponse<T>(
        T body,
        List<Exception> errors
) {
    public static <R> RoomRentResponse<R> of(R body) {
        return new RoomRentResponse<>(body, List.of());
    }

    public static RoomRentResponse<Void> error(Exception... exceptions) {
        return new RoomRentResponse<>(null, Arrays.stream(exceptions).toList());
    }
}
