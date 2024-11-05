package com.ssspamqe.roomrent.mappers;

import com.ssspamqe.roomrent.configuration.mapstruct.MapStructConfig;
import com.ssspamqe.roomrent.controllers.room.request.RoomDTO;
import com.ssspamqe.roomrent.domain.entities.stuff.Room;
import com.ssspamqe.roomrent.domain.entities.users.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MapStructConfig.class)
public abstract class RoomMapper {

    @Autowired
    protected GeometryMapper geometryMapper;

    @Mapping(target = "position", expression = "java(geometryMapper.toPoint(roomDTO.position()))")
    @Mapping(target = "owner", source = "owner")
    public abstract Room toEntity(RoomDTO roomDTO, Seller owner);

    @Mapping(target = "rents", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "announcements", ignore = true)
    @Mapping(target = "position", expression = "java(geometryMapper.toPoint(roomDTO.position()))")
    public abstract void update(@MappingTarget Room room, RoomDTO roomDTO);
}
