import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение в формате 'число оператор число' (например, 2 + 3 или IV - II):");
        String input = scanner.nextLine();

        try {
            String[] parts = input.split("\\s+");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Некорректный формат ввода");
            }

            String firstOperandStr = parts[0];
            String operator = parts[1];
            String secondOperandStr = parts[2];

            // Проверяем, являются ли оба операнда арабскими числами
            boolean isArabic = isArabicNumber(firstOperandStr) && isArabicNumber(secondOperandStr);

            int result;
            if (isArabic) {
                int firstOperand = Integer.parseInt(firstOperandStr);
                int secondOperand = Integer.parseInt(secondOperandStr);

                // Выполняем арифметическую операцию
                result = calculate(firstOperand, operator, secondOperand);
            } else {
                throw new IllegalArgumentException("Римские числа не поддерживаются в данной версии");
            }

            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // Метод для проверки, является ли строка арабским числом
    private static boolean isArabicNumber(String str) {
        try {
            int num = Integer.parseInt(str);
            return num >= 1 && num <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Метод для выполнения арифметической операции
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
