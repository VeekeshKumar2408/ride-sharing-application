package com.veekesh.project.uber.uberApp.strategy;

import com.veekesh.project.uber.uberApp.strategy.impl.DriverMatchingHighestRateDriverStrategy;
import com.veekesh.project.uber.uberApp.strategy.impl.DriverMatchingNearestDriverStrategy;
import com.veekesh.project.uber.uberApp.strategy.impl.RideFareSurgePricingFareCalculationStrategy;
import com.veekesh.project.uber.uberApp.strategy.impl.RiderFareDefaultFareCalculationStrategy;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class RideStrategyManager {

    private final DriverMatchingHighestRateDriverStrategy highestRateDriverStrategy;
    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;
    private final RiderFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;

    public RideStrategyManager(DriverMatchingHighestRateDriverStrategy highestRateDriverStrategy, DriverMatchingNearestDriverStrategy nearestDriverStrategy, RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy, RiderFareDefaultFareCalculationStrategy defaultFareCalculationStrategy) {
        this.highestRateDriverStrategy = highestRateDriverStrategy;
        this.nearestDriverStrategy = nearestDriverStrategy;
        this.surgePricingFareCalculationStrategy = surgePricingFareCalculationStrategy;
        this.defaultFareCalculationStrategy = defaultFareCalculationStrategy;
    }


    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if (riderRating >= 4.8){
            return highestRateDriverStrategy;
        }else{
            return nearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if (isSurgeTime) return surgePricingFareCalculationStrategy;
        else return defaultFareCalculationStrategy;
    }



}
