package com.veekesh.project.uber.uberApp.utils;

import com.veekesh.project.uber.uberApp.dto.PointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryUtil {

    public static Point createPoint(PointDto pointDto){
        double x = pointDto.getCoordinates()[0];
        double y = pointDto.getCoordinates()[1];

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Coordinate coordinate = new Coordinate(x,y);
        return geometryFactory.createPoint(coordinate);
    }
}
