package aclij.pio;

import aclij.pio.exceptions.CoordinatesFormatException;

import java.util.Objects;

public class Coordinates {
    public final File file;
    public final Integer rank;

    public Coordinates(File file, Integer rank) {
        this.file = file;
        this.rank = rank;
    }


    @Override
    public String toString() {
        return file + String.valueOf(rank);
    }
    public static Coordinates valueOf(String str){
        String [] strings = str.split("");
        return new Coordinates(File.valueOf(strings[0]), Integer.valueOf(strings[1]));
    }
    public static Coordinates valueOf(String file, String rank) throws CoordinatesFormatException {
        try {
            return new Coordinates(File.valueOf(file), Integer.valueOf(rank));
        }catch (Exception e){
            throw new CoordinatesFormatException("Failed to convert string to coordinates");
        }

    }
    public boolean isSquareDark(){
        return (((this.file.ordinal() + 1) + this.rank) % 2) == 0;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return file == that.file && Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }


}
