package numbers.service;

import numbers.model.Number;
import numbers.model.Properties;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    private Number number;
    private final Scanner scanner;

    public App() {
        scanner = new Scanner(System.in);
    }

    private void help() {
        System.out.println("Welcome to Amazing Numbers!\n\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                " * the first parameter represents a starting number;\n" +
                " * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }

    private boolean getNumberPropertyStatus(Properties properties, Number number) {
        if (properties == Properties.DUCK) {
            return number.isDuck();
        } else if (properties == Properties.BUZZ) {
            return number.isBuzz();
        } else if (properties == Properties.EVEN) {
            return number.isEven();
        } else if (properties == Properties.ODD) {
            return number.isOdd();
        } else if (properties == Properties.SPY) {
            return number.isSpy();
        } else if (properties == Properties.PALINDROMIC) {
            return number.isPalindromic();
        } else if (properties == Properties.GAPFUL) {
            return number.isGapful();
        } else if (properties == Properties.SQUARE) {
            return number.isSquare();
        } else if (properties == Properties.SUNNY) {
            return number.isSunny();
        } else if (properties == Properties.JUMPING) {
            return number.isJumping();
        } else if (properties == Properties.HAPPY) {
            return number.isHappy();
        } else if (properties == Properties.SAD) {
            return number.isSad();
        }
        return false;
    }

    private List<String> checkIfPropertiesHaveCorrectName(String userInput) {
        return Arrays.stream(userInput.split(" "))
                .skip(2)
                .filter(property -> Properties.getProperties(property) == null)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    private boolean checkIfPropertiesExists(String userInput, String propertyName) {
        Optional<String> first = Arrays.stream(userInput.split(" "))
                .filter(property -> property.equals(propertyName))
                .findFirst();
        return first.isPresent();
    }

    private String checkIfPropertiesAreOpposite(String userInput) {
        return Arrays.stream(userInput.split(" "))
                .skip(2)
                .filter(property -> userInput.contains("-" + property))
                .findFirst()
                .orElse("");
    }

    private String checkValidationProperties(String userInput) {
        if (checkIfPropertiesHaveCorrectName(userInput).size() == 1) {
            return "The property [" + checkIfPropertiesHaveCorrectName(userInput).get(0) + "] is wrong.\nAvailable properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]";
        } else if (checkIfPropertiesHaveCorrectName(userInput).size() >= 2) {
            return "The properties [" + checkIfPropertiesHaveCorrectName(userInput) + "] are wrong.\nAvailable properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]";
        } else if (checkIfPropertiesExists(userInput, "odd") && checkIfPropertiesExists(userInput, "even")) {
            return "The request contains mutually exclusive properties: [ODD, EVE]\nThere are no numbers with these properties.";
        } else if (checkIfPropertiesExists(userInput, "duck") && checkIfPropertiesExists(userInput, "spy")) {
            return "The request contains mutually exclusive properties: [DUCK, SPY]\nThere are no numbers with these properties.";
        } else if (checkIfPropertiesExists(userInput, "sunny") && checkIfPropertiesExists(userInput, "square")) {
            return "The request contains mutually exclusive properties: [SUNNY, SQUARE]\nThere are no numbers with these properties.";
        } else if (checkIfPropertiesExists(userInput, "sad") && checkIfPropertiesExists(userInput, "happy")) {
            return "The request contains mutually exclusive properties: [SAD, HAPPY]\nThere are no numbers with these properties.";
        } else if (checkIfPropertiesExists(userInput, "-odd") && checkIfPropertiesExists(userInput, "-even")) {
            return "The request contains mutually exclusive properties: [-ODD, -EVEN]\nThere are no numbers with these properties.";
        } else if (!checkIfPropertiesAreOpposite(userInput).isEmpty()) {
            return "The request contains mutually exclusive properties: [" + checkIfPropertiesAreOpposite(userInput) + ", " + "-" + checkIfPropertiesAreOpposite(userInput) + "]\nThere are no numbers with these properties.";
        }
        return "";
    }

    private void numberProperties(String userInput) {
        if (userInput.matches("[0-9]{1,}")) {
            number = new Number(Long.parseLong(userInput));
            number.diagnose();
            System.out.println(number);
        } else {
            System.out.println("The first parameter should be a natural number or zero.\n");
        }
    }

    private void numbersWithAllProperties(String userInput) {
        if (userInput.split(" ")[0].matches("[0-9]{1,}") && userInput.split(" ")[1].matches("[0-9]{1,}")) {
            for (long i = 0; i < Long.parseLong(userInput.split(" ")[1]); i++) {
                number = new Number(Long.parseLong(userInput.split(" ")[0]) + i);
                number.diagnose();
                System.out.println(number.toStringList());
            }
        } else if (!userInput.split(" ")[1].matches("[0-9]{1,}")) {
            System.out.println("The second parameter should be a natural number.\n");
        } else {
            System.out.println("The first parameter should be a natural number or zero.\n");
        }
    }

    private void numbersWithSpecificProperties(String userInput) {
        if (!checkValidationProperties(userInput).isEmpty()) {
            System.out.println(checkValidationProperties(userInput));
        } else {
            long initialNumber = Long.parseLong(userInput.split(" ")[0]);
            long limit = Long.parseLong(userInput.split(" ")[1]);
            long counter = 0;
            do {
                number = new Number(initialNumber);
                number.diagnose();
                boolean meetRequirements = true;
                for (int i = 2; i < userInput.split(" ").length; i++) {
                    if (userInput.split(" ")[i].charAt(0) == '-' && getNumberPropertyStatus(Properties.getProperties(userInput.split(" ")[i].substring(1)), number) ||
                            userInput.split(" ")[i].charAt(0) != '-' && !getNumberPropertyStatus(Properties.getProperties(userInput.split(" ")[i]), number)) {
                        meetRequirements = false;
                        break;
                    }
                }
                if (meetRequirements) {
                    counter++;
                    System.out.println(number.toStringList());
                }
                initialNumber++;
            } while (counter != limit);
        }
    }

    private void startNumbersProperties() {
        do {
            System.out.println("Enter a request:");
            String userInput = scanner.nextLine();
            if (userInput.equals("0")) { // end app
                break;
            } else if (Arrays.stream(userInput.split(" ")).toArray().length == 1) { // if user provide 1 parameter
                numberProperties(userInput);
            } else if (Arrays.stream(userInput.split(" ")).toArray().length == 2) { // if user provide 2 parameters
                numbersWithAllProperties(userInput);
            } else {  // if user provide >=3 parameters and check validation
                numbersWithSpecificProperties(userInput);
            }
        } while (true);
        System.out.println("Goodbye!");
    }

    public void start() {
        help();
        startNumbersProperties();
    }
}
