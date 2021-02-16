package GameXO;


    import java.util.Scanner;

    public class GameXO {
        final char SIGN_X = 'x';
        final char SIGN_O = 'o';
        final char SIGN_EMPTY = '.';
        char[][] table;
        Scanner scanner;

        public static void main(String[] args) {
            new GameXO().game();
        }

    GameXO() {    // конструктор: инициализация полей
            scanner = new Scanner(System.in);
            table = new char[3][3];
        }

        void game() {
            // инициализация таблицы
                    initTable();
            System.out.println("Игра крестики-нолики");
            System.out.println("Перед вами игровое поле. Х - столбики, У - линии");
            System.out.println("11  21  31");
            System.out.println("21  22  32");
            System.out.println("31  32  33");
            System.out.println("Игрок1 = Х, Игрок2 = У");

            // игровая логика
            while (true) {
                        turnPlayer1();
                        if (checkWin(SIGN_X)) {
                            System.out.println("Игрок один - победитель!");
                            break;
                        }
                        if (isTableFull()) {
                            System.out.println("Игрок один - лузер!");
                            break;
                        }
                        turnPlayer2();
                        printTable();
                        if (checkWin(SIGN_O)) {
                            System.out.println("Игрок два - победитель!");
                            break;
                        }
                        if (isTableFull()) {
                            System.out.println("Игрок два - лузер!");
                            break;
                        }
                    }
                    System.out.println("ИГРА ЗАКОНЧЕНА.");
                    printTable();
            }

        // дополнительные методы
        void initTable() {
            for (int row = 0; row < 3; row++) //выбирает строки
                for (int col = 0; col < 3; col++) //выбирает ячейки
                    table[row][col] = SIGN_EMPTY;
        }
        void printTable() { //отражение положения на игровом поле
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++)
                    System.out.print(table[row][col] + " ");
                System.out.println();
            }
        }
        void turnPlayer1() { //ход игрока1
            int x, y;
            do {
                System.out.println("Игрок1 введите Х и У (1..3):");
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
            } while (!isCellValid(x, y)); //Валидность ячейки определяет метод isCellValid()
            table[y][x] = SIGN_X;
        }
        boolean isCellValid(int x, int y) {
            if (x < 0 || y < 0 || x >= 3|| y >= 3)
                return false;
            return table[y][x] == SIGN_EMPTY;
        }
        void turnPlayer2() { //ход игрока2
            int x, y;
            do {
                System.out.println("Игрок2 введите Х и У (1..3):");
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
            } while (!isCellValid(x, y)); //выбор только свободных ячеек
            table[y][x] = SIGN_O;
        }
        boolean checkWin(char dot) { //Проверка победы
            for (int i = 0; i < 3; i++)
                if ((table[i][0] == dot && table[i][1] == dot &&
                        table[i][2] == dot) ||
                        (table[0][i] == dot && table[1][i] == dot &&
                                table[2][i] == dot))
                    return true;
            if ((table[0][0] == dot && table[1][1] == dot &&
                    table[2][2] == dot) ||
                    (table[2][0] == dot && table[1][1] == dot &&
                            table[0][2] == dot))
                return true;
            return false;
        }
        boolean isTableFull() { //проверка на ничью
            for (int row = 0; row < 3; row++)
                for (int col = 0; col < 3; col++)
                    if (table[row][col] == SIGN_EMPTY)
                        return false;
            return true;
        }

    }

