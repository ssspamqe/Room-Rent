package com.ssspamqe.roomrent.mappers;

import com.ssspamqe.roomrent.configuration.mapstruct.MapStructConfig;
import com.ssspamqe.roomrent.controllers.room.request.RoomDTO;
import com.ssspamqe.roomrent.domain.entities.stuff.Room;
import com.ssspamqe.roomrent.domain.entities.users.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface RoomMapper {

    Room toEntity(RoomDTO roomDTO, Seller owner);

    @Mapping(target = "rents", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "announcements", ignore = true)
    void update(@MappingTarget Room room, RoomDTO roomDTO);

}
