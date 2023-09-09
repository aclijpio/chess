package aclij.pio;

import aclij.pio.waitForAnswer.ListCoordinates;

import java.util.List;

public class CoordinatesWrapper {
    public List<Coordinates> coordinatesList;
    public Coordinates coordinates;

    public CoordinatesWrapper(List<Coordinates> coordinatesList) {
        this.coordinatesList = coordinatesList;
    }

    public CoordinatesWrapper(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public boolean isList(){
        return coordinates == null;
    }
}
