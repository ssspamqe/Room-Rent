package com.ssspamqe.roomrent.mappers;

import com.ssspamqe.roomrent.configuration.mapstruct.MapStructConfig;
import com.ssspamqe.roomrent.controllers.room.request.RoomDTO;
import com.ssspamqe.roomrent.domain.entities.stuff.Room;
import com.ssspamqe.roomrent.domain.entities.users.Seller;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
@RequiredArgsConstructor
public abstract class RoomMapper {

    private final GeometryMapper geometryMapper;

    @Mapping(target = "position", expression = "java(geometryMapper.toPoint(roomDTO.position()))")
    @Mapping(target = "owner", source = "owner")
    abstract Room toEntity(RoomDTO roomDTO, Seller owner);

    @Mapping(target = "rents", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "announcements", ignore = true)
    @Mapping(target = "position", expression = "java(geometryMapper.toPoint(roomDTO.position()))")
    abstract void update(@MappingTarget Room room, RoomDTO roomDTO);
}
