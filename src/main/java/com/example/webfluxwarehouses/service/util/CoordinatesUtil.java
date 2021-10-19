package com.example.webfluxwarehouses.service.util;

import org.springframework.stereotype.Component;

@Component
public class CoordinatesUtil {
    /**
     * This method is used to calculate distance between 2 coordinates
     *
     * @param startingX starting x coordinate
     * @param startingY starting y coordinate
     * @param endingX   ending x coordinate
     * @param endingY   ending y coordinate
     * @return the calculated distance
     */
    public long calculateDistance(double startingX, double startingY, double endingX, double endingY) {
        return (long) Math.sqrt(
                Math.pow(startingX - endingX, 2) + Math.pow(startingY - endingY, 2)
        );
    }

    /**
     * Method to calculate distance without getting a root of it
     * used instead of usual formula for better performance
     *
     * @param startingX x of the starting coordinate
     * @param startingY y of the starting coordinate
     * @param endingX x of the ending coordinate
     * @param endingY y of the ending coordinate
     * @return squared distance between 2 coordinates
     */
    public double calculateSquaredDistance(double startingX, double startingY, double endingX, double endingY) {
        return Math.pow(startingX - endingX, 2) + Math.pow(startingY - endingY, 2);
    }

    /**
     * Compare distance between specified coordinates and given 2 points
     *
     * @param xCoordinate x to compare to
     * @param yCoordinate y to compare to
     * @param firstComparedX x of the first comparing coordinate
     * @param firstComparedY y of the first comparing coordinate
     * @param secondComparedX x of the second comparing coordinate
     * @param secondComparedY y of the second comparing coordinate
     * @return int value representing which coordinate is closer
     */
    public int compareDistanceToACoordinate(Long xCoordinate, Long yCoordinate, Long firstComparedX, Long firstComparedY,
                                             Long secondComparedX, Long secondComparedY) {
        return (int) (calculateSquaredDistance(xCoordinate, yCoordinate, secondComparedX, secondComparedY) -
                calculateSquaredDistance(xCoordinate, yCoordinate, firstComparedX, firstComparedY));
    }
}
