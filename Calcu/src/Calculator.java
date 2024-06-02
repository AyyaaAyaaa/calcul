import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator {

    private static final Map<Character, Integer> romanToArabicMap = new HashMap<>();
    private static final Map<Integer, String> arabicToRomanMap = new HashMap<>();

    static {
        romanToArabicMap.put('I', 1);
        romanToArabicMap.put('V', 5);
        romanToArabicMap.put('X', 10);

        arabicToRomanMap.put(1, "I");
        arabicToRomanMap.put(2, "II");
        arabicToRomanMap.put(3, "III");
        arabicToRomanMap.put(4, "IV");
        arabicToRomanMap.put(5, "V");
        arabicToRomanMap.put(6, "VI");
        arabicToRomanMap.put(7, "VII");
        arabicToRomanMap.put(8, "VIII");
        arabicToRomanMap.put(9, "IX");
        arabicToRomanMap.put(10, "X");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение (например, 2 + 3 или IV - II):");
        String input = scanner.nextLine();

        try {
            String[] parts = input.split("\\s+");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Некорректный формат ввода");
            }

            String firstOperandStr = parts[0];
            String operator = parts[1];
            String secondOperandStr = parts[2];

            boolean isFirstOperandArabic = isArabicNumber(firstOperandStr);
            boolean isSecondOperandArabic = isArabicNumber(secondOperandStr);

            if (isFirstOperandArabic && isSecondOperandArabic) {
                int firstOperand = Integer.parseInt(firstOperandStr);
                int secondOperand = Integer.parseInt(secondOperandStr);
                if (!isValidArabicRange(firstOperand) || !isValidArabicRange(secondOperand)) {
                    throw new IllegalArgumentException("Арабские числа должны быть в диапазоне от 1 до 10 включительно");
                }
                int result = calculate(firstOperand, operator, secondOperand);
                System.out.println("Результат: " + result);
            } else if (!isFirstOperandArabic && !isSecondOperandArabic) {
                int firstOperand = romanToArabic(firstOperandStr);
                int secondOperand = romanToArabic(secondOperandStr);
                if (!isValidArabicRange(firstOperand) || !isValidArabicRange(secondOperand)) {
                    throw new IllegalArgumentException("Римские числа должны быть в диапазоне от I до X включительно");
                }
                int result = calculate(firstOperand, operator, secondOperand);
                if (result < 1) {
                    throw new IllegalArgumentException("Результат римского вычисления должен быть положительным");
                }
                System.out.println("Результат: " + arabicToRoman(result));
            } else {
                throw new IllegalArgumentException("Нельзя смешивать арабские и римские цифры");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static boolean isArabicNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidArabicRange(int number) {
        return number >= 1 && number <= 10;
    }

    private static int romanToArabic(String roman) {
        int result = 0;
        int lastValue = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = romanToArabicMap.get(roman.charAt(i));
            if (value < lastValue) {
                result -= value;
            } else {
                result += value;
            }
            lastValue = value;
        }
        return result;
    }

    private static String arabicToRoman(int number) {
        StringBuilder roman = new StringBuilder();
        while (number >= 10) {
            roman.append("X");
            number -= 10;
        }
        if (number > 0) {
            roman.append(arabicToRomanMap.get(number));
        }
        return roman.toString();
    }

    private static int calculate(int a, String operator, int b) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new IllegalArgumentException("Деление на ноль невозможно");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
        }
    }
}