package com.ssspamqe.roomrent.mappers;

import com.ssspamqe.roomrent.controllers.room.request.EarthPosition;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeometryMapper {

    private final GeometryFactory geometryFactory;

    public Point toPoint(EarthPosition position) {
        return geometryFactory.createPoint(new Coordinate(position.longitude(), position.latitude()));
    }

}
