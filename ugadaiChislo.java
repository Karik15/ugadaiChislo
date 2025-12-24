import java.util.Random;
import java.util.Scanner;

public class ugadaiChislo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int bestScore = Integer.MAX_VALUE; //статистика
        int totalGames = 0;

        System.out.println("--- ИГРА: УГАДАЙ ЧИСЛО ---");
        System.out.println("Компьютер загадал число от 1 до 100.");
        System.out.println("Для просмотра статистики введи 'RESULT'");
        System.out.println("Для выхода введи 'EXIT'");

        boolean playAgain = true;

        while (playAgain) {
            int secretNumber = random.nextInt(100) + 1; //рандом число
            int attempts = 0;

            System.out.println("\nИгра началась!");

            while (true) {
                System.out.print("Введи число (1-100): ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("RESULT")) {  // Обработка команды RESULT
                    showStatistics(totalGames, bestScore);
                    continue;
                }

                if (input.equalsIgnoreCase("EXIT")) { // Обработка команды EXIT
                    System.out.println("Выход из игры..");
                    playAgain = false;
                    break;
                }

                int userGuess;  // Проверка, является ли ввод числом

                try {
                    userGuess = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка! Введи число от 1 до 100.");
                    continue;
                }

                if (userGuess < 1 || userGuess > 100) { // Проверка диапазона
                    System.out.println("Число должно быть в диапазоне от 1 до 100!");
                    continue;
                }

                attempts++;

                if (userGuess == secretNumber) { // Проверка угадал ли пользователь число
                    System.out.println("\n=== ПОБЕДА! ===");
                    System.out.println("Загаданное число: " + secretNumber);
                    System.out.println("Количество попыток: " + attempts);

                    totalGames++; // Обновление статистики

                    if (attempts < bestScore) {
                        bestScore = attempts;
                        System.out.println("НОВЫЙ РЕКОРД! Это твоя лучшая игра!");
                    }

                    showStatistics(totalGames, bestScore);

                    System.out.print("\nХочешь сыграть еще раз? (да/нет): "); // Предложение сыграть еще раз
                    String answer = scanner.nextLine().trim();

                    if (answer.equalsIgnoreCase("да") || answer.equalsIgnoreCase("yes")) {
                        break; // Начинаем новую игру
                    } else {
                        System.out.println("Спасибо за игру!");
                        playAgain = false;
                        break;
                    }
                } else if (userGuess < secretNumber) {
                    System.out.println("Я сам в шоке, но, загаданное число больше, брат");
                } else {
                    System.out.println("Не ожидал от тебя такого. Загаданное число меньше, брат");
                }
            }
        }

        scanner.close();
    }

    // Метод для отображения статистики
    private static void showStatistics(int totalGames, int bestScore) {
        System.out.println("\n=== СТАТИСТИКА ===");

        if (totalGames == 0) {
            System.out.println("Игр сыграно: 0");
            System.out.println("Лучшая игра: -");
        } else {
            System.out.println("Игр сыграно: " + totalGames);
            System.out.println("Лучшая игра (минимальное количество попыток): " +
                    (bestScore == Integer.MAX_VALUE ? "-" : bestScore));
        }
        System.out.println("----------------");
    }
}