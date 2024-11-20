import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Temperature Converter!");

        while (true) {
            System.out.print("Enter the temperature value to convert (or type 'exit' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the converter. Goodbye!");
                break;
            }

            // Validate the temperature input
            double temperature;
            try {
                temperature = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid temperature value. Please enter a valid number.");
                continue;
            }

            // Get the source and target temperature scales
            System.out.print("Enter the source scale (Celsius, Fahrenheit, Kelvin): ");
            String sourceScale = scanner.nextLine().trim().toLowerCase();
            System.out.print("Enter the target scale (Celsius, Fahrenheit, Kelvin): ");
            String targetScale = scanner.nextLine().trim().toLowerCase();

            // Convert and display the temperature
            try {
                double convertedTemperature = convertTemperature(temperature, sourceScale, targetScale);
                System.out.printf("%.2f %s is equal to %.2f %s%n", temperature, capitalizeFirstLetter(sourceScale),
                        convertedTemperature, capitalizeFirstLetter(targetScale));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static double convertTemperature(double temperature, String sourceScale, String targetScale) {
        if (sourceScale.equals("celsius")) {
            if (targetScale.equals("fahrenheit")) {
                return (temperature * 9 / 5) + 32;
            } else if (targetScale.equals("kelvin")) {
                return temperature + 273.15;
            }
        } else if (sourceScale.equals("fahrenheit")) {
            if (targetScale.equals("celsius")) {
                return (temperature - 32) * 5 / 9;
            } else if (targetScale.equals("kelvin")) {
                return (temperature - 32) * 5 / 9 + 273.15;
            }
        } else if (sourceScale.equals("kelvin")) {
            if (targetScale.equals("celsius")) {
                return temperature - 273.15;
            } else if (targetScale.equals("fahrenheit")) {
                return (temperature - 273.15) * 9 / 5 + 32;
            }
        }
        throw new IllegalArgumentException("Invalid temperature scale provided. Please use Celsius, Fahrenheit, or Kelvin.");
    }

    private static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}