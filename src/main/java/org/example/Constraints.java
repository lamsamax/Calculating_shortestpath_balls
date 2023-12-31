package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Constraints {
    private final String startCode;
    private final String endCode;
    private final String constraintType;
    private final Double probability;

    private static final ArrayList<Constraints> allConstraints = new ArrayList<>();

    public Constraints(String startShortcode, String endShortcode, String constraintName, Double probability) {
        this.startCode = startShortcode;
        this.endCode = endShortcode;
        this.constraintType = constraintName;
        this.probability = probability;

        allConstraints.add(this);
    }

    public static ArrayList<Constraints> getAllConstraints() {
        return allConstraints;
    }

    public String getStartCode() {
        return startCode;
    }

    public String getEndCode() {
        return endCode;
    }

    public String getConstraintType() {
        return constraintType;
    }

    public double getProbability() {
        return probability;
    }

    public static void loadConstraintsFromFile(String filePath) {
        File input = new File(filePath);

        try {
            Scanner s = new Scanner(input);
            if (s.hasNextLine()) s.nextLine();

            while (s.hasNextLine()) {
                String currentLine = s.nextLine();
                String[] lineParts = currentLine.split(",");

                String placeA = lineParts[0].trim();
                String placeB = lineParts[1].trim();
                String constraintType = lineParts[2].trim();
                Double probability = Double.parseDouble(lineParts[3].trim());

                Constraints constraint = new Constraints(placeA, placeB, constraintType, probability);
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

