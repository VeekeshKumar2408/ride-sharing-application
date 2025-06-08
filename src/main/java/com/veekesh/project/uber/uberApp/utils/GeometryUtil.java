package com.veekesh.project.uber.uberApp.utils;

import com.veekesh.project.uber.uberApp.dto.PointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryUtil {

    public static Point createPoint(PointDto pointDto){
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Coordinate coordinate = new Coordinate(pointDto.getCoordinate()[0], pointDto.getCoordinate()[1]);
        return geometryFactory.createPoint(coordinate);
    }
}
