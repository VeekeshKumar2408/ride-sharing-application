package com.veekesh.project.uber.uberApp.services.impl;

import com.veekesh.project.uber.uberApp.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    private static final String OSRM_BASE_URL = "https://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point src, Point dest) {
        try {
            String uri = src.getX()+ ","+src.getY()+";"+dest.getX()+","+dest.getY();
            OSRMResponseDTO responseDTO = RestClient.builder()
                    .baseUrl(OSRM_BASE_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDTO.class);

            return responseDTO.getRoutes().get(0).getDistance() / 1000.0;
        } catch (Exception e) {
           throw new RuntimeException("Error getting data from OSRM " + e.getMessage());
        }
    }
}

class OSRMResponseDTO {
   private List<OSRMRoute> routes;

    public List<OSRMRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(List<OSRMRoute> routes) {
        this.routes = routes;
    }
}

class OSRMRoute {
    private Double distance;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
