package com.ssspamqe.roomrent;

import org.springframework.boot.SpringApplication;

public class TestRoomRentApplication {

    public static void main(String[] args) {
        SpringApplication.from(RoomRentApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
