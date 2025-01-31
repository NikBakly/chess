Давайте напишем шахматы. Для начала надо спроектировать нашу игру.

Самое главное в шахматах — это фигуры, поэтому для начала нам надо будет сделать абстрактный класс фигуры (ChessPiece), чтобы в будущем легко написать все типы фигур в игре. Но это ещё не всё, нам надо куда-то ставить фигуры, для этого нам нужен класс доски (ChessBoard), который будет отвечать за управление всей игрой. Эти шахматы будут максимально простыми, с консольным простеньким интерфейсом, но больше сейчас нам и не нужно.

Вам предоставлен класс ChessBoard.

В нём есть:

Поле board, которое представляет собой двумерный массив объектов шахматных фигур. Поле A8 имеет значение (0,0). Фигуры белого цвета находятся снизу, то есть на ячейках двумерного массива [0][0-7] и [1][0-7]. Фигуры черного цвета на [6][0-7] и [7][0-7].
Переменная nowPlayer, которая показывает, чей сейчас ход.
Конструктор и метод nowPlayerColor (на самом деле хорошо бы сделать переменную nowPlayer приватной, но для удобства пусть будет, как есть).
Метод moveToPosition, который передвигает фигуры, можете поподробнее посмотреть, что же там происходит.
Метод printBoard, который просто красиво печатает шахматное поле в консоль.
Этот класс понадобится вам позже, но осуществлять проверку написанного вами кода легче через него.

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        
        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
Задание 2.7.1
Для начала напишем абстрактный класс ChessPiece (шахматная фигура), у которой должны быть следующие перемененные:

строковая переменная color — цвет;
логическая переменная check, по умолчанию true, она понадобится нам сильно позже;
конструктор, принимающий в себя строковую переменную color.
И следующие публичные (public) методы:

getColor(), возвращающий строку — должен вернуть цвет фигуры;
абстрактный метод canMoveToPosition(), возвращающий логическое (boolean) значение и принимающий в себя параметры ChessBoard chessBoard, int line, int column, int toLine, int toColumn;
абстрактный метод getSymbol(), возвращающий строку — тип фигуры.
Задание 2.7.2
Теперь напишите класс Horse (конь). Этот класс должен быть наследником класса ChessPiece, который вы сделали в предыдущей задаче.

В классе Horse:

Реализуйте конструктор, который будет принимать лишь цвет фигуры.
Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
Реализуйте метод canMoveToPosition() так, чтобы конь не мог выйти за доску (доска в нашем случае — это двумерный массив размером 8 на 8, напоминаем, что индексы начинаются с 0) и мог ходить только буквой «Г». Также фигура не может сходить в точку, в которой она сейчас находится. Если конь может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше), то функция вернет true, иначе — false.
Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае конь — это  H.
Также вы можете добавить и свои методы для удобства.

Задание 2.7.3
Создайте класс Pawn (пешка), который так же, как и Horse, должен быть наследником класса ChessPiece.

Реализуйте в классе Pawn, всё тоже самое, что и в классе Horse.

В классе Pawn:

Реализуйте конструктор, который будет принимать цвет фигуры.
Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
Реализуйте метод canMoveToPosition() так, чтобы пешка не могла выйти за доску и могла ходить только вперед. Помните, что первый ход пешка может сдвинуться на 2 поля вперед, сделать это можно, например, сравнив координаты. То есть, если пешка белая (color.equals("White")) и находится в line == 1, то она может пойти на 2 поля вперед, иначе — нет, аналогично с черными пешками. Также фигура не может сходить в точку, в которой она сейчас находится. Если пешка может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше), то функция вернет true, иначе — false.
Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае пешка — это P.
Также вы можете добавить и свои методы для удобства.

Задание 2.7.4
Напишите класс Bishop (слон). Этот класс должен быть наследником от класса ChessPiece, который вы сделали в предыдущей задаче.

В классе Bishop:

Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
Реализуйте метод canMoveToPosition() так, чтобы слон не мог выйти за доску (доска в нашем случае — это двумерный массив размером 8 на 8, напоминаем, что индексы начинаются с 0) и мог ходить только по диагонали, также фигура не может сходить в точку, в которой она сейчас находится. Если слон может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше), то функция вернет true, иначе — false.
Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае слон —  B.
Также вы можете добавить и свои методы для удобства.

Задание 2.7.5
Аналогично предыдущим фигурам создайте класс Rook (ладья).

В классе Rook:

Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
Реализуйте метод canMoveToPosition() так, чтобы ладья не могла выйти за доску (доска в нашем случае — это двумерный массив размером 8 на 8, напоминаем, что индексы начинаются с 0) и мог ходить только по прямой, также фигура не может сходить в точку, в которой она сейчас находится. Если ладья может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше), то функция вернет true, иначе — false.
Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае ладья — R.
Также вы можете добавить и свои методы для удобства.

Задание 2.7.6
Напишите класс Queen и класс King.

В этих классах:

Реализуйте конструктор, который будет принимать лишь цвет фигуры.
Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
Реализуйте метод canMoveToPosition() так, чтобы фигуры не могли выйти за доску (доска в нашем случае — это двумерный массив размером 8 на 8, напоминаем, что индексы начинаются с 0) и могли ходить так, как ходят в шахматах (Королева ходит и по диагонали и по прямой, Король — в любое поле вокруг себя), также фигура не может сходить в точку, в которой она сейчас находится. Если фигура может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше), то функция вернет true, иначе — false.
Реализуйте метод getSymbol так, чтобы он возвращал строку — символ фигуры, для короля — K, для ферзя — Q.
Отдельно в классе King создайте метод isUnderAttack(ChessBoard board, int line, int column), возвращающий логическое (boolean) значение, который  будет проверять, находится ли поле, на котором стоит король (или куда собирается пойти) под атакой. Если это так, то метод должен вернуть true, иначе — false. Это позволит нам проверять шахи.

Также вы можете добавить и свои методы для удобства.

Задание 2.7.7
Теперь реализуем рокировку.

Рокировка возможна, если ни король, ни ладья ни разу не двигались, и между ними все поля свободны. 

Помните, в самом начале мы создали поле check? Теперь оно поможет нам отследить, двигались ли фигуры или нет.

Теперь модернизируйте метод moveToPosition(класс ChessBoard) так, чтобы после первого хода ладей и королей их переменные check становились false, так мы сможем отслеживать возможность рокировки.

Метод castling0(), должен находится в классе ChessBoard и отвечать за рокировку по 0 столбцу.

В классе ChessBoard создайте метод boolean castling7() (аналогично castling0). Если во время хода белого игрока вызывается метод castling7(), то надо проверить, возможна ли рокировка белого короля с ладьей, стоящей в 7 столбце, и если возможна, то совершить рокировку. Если рокировку пытается совершить игрок, играющий черными фигурами, то наши действия аналогичны.

Подсказка
Задание 2.7.8
Мы почти сделали полноценную игру. У вас уже есть все фигуры, все они правильно ходят, и даже реализована рокировка, но есть одно но. Наши фигуры не умеют атаковать, а главное — проходят друг через друга.

Допишите методы canMoveToPosition (у всех фигур) так, чтобы фигура не могла проходить через другую (для этого мы передаем в этот метод ChessBoard, у которого есть публичная переменная board). Также давайте сделаем так, чтобы фигуры могли есть друг друга. То есть, если мы двигаем белую фигуру на поле, на котором стоит черная, то черная фигура просто съедается (съедание фигуры уже реализовано в классе ChessBoard, вам надо сделать только так, чтобы метод canMoveToPosition() возвращал true при таком раскладе).

А вот вам класс Main для теста вашей программы:

import java.util.Scanner;

public class Main {

   public static ChessBoard buildBoard() {
       ChessBoard board = new ChessBoard("White");

       board.board[0][0] = new Rook("White");
       board.board[0][1] = new Horse("White");
       board.board[0][2] = new Bishop("White");
       board.board[0][3] = new Queen("White");
       board.board[0][4] = new King("White");
       board.board[0][5] = new Bishop("White");
       board.board[0][6] = new Horse("White");
       board.board[0][7] = new Rook("White");
       board.board[1][0] = new Pawn("White");
       board.board[1][1] = new Pawn("White");
       board.board[1][2] = new Pawn("White");
       board.board[1][3] = new Pawn("White");
       board.board[1][4] = new Pawn("White");
       board.board[1][5] = new Pawn("White");
       board.board[1][6] = new Pawn("White");
       board.board[1][7] = new Pawn("White");

       board.board[7][0] = new Rook("Black");
       board.board[7][1] = new Horse("Black");
       board.board[7][2] = new Bishop("Black");
       board.board[7][3] = new Queen("Black");
       board.board[7][4] = new King("Black");
       board.board[7][5] = new Bishop("Black");
       board.board[7][6] = new Horse("Black");
       board.board[7][7] = new Rook("Black");
       board.board[6][0] = new Pawn("Black");
       board.board[6][1] = new Pawn("Black");
       board.board[6][2] = new Pawn("Black");
       board.board[6][3] = new Pawn("Black");
       board.board[6][4] = new Pawn("Black");
       board.board[6][5] = new Pawn("Black");
       board.board[6][6] = new Pawn("Black");
       board.board[6][7] = new Pawn("Black");
       return board;
   }

   public static void main(String[] args) {

       ChessBoard board = buildBoard();
       Scanner scanner = new Scanner(System.in);
       System.out.println("""
               Чтобы проверить игру надо вводить команды:
               'exit' - для выхода
               'replay' - для перезапуска игры
               'castling0' или 'castling7' - для рокировки по соответствующей линии
               'move 1 1 2 3' - для передвижения фигуры с позиции 1 1 на 2 3(поле это двумерный массив от 0 до 7)
               Проверьте могут ли фигуры ходить друг сквозь друга, корректно ли съедают друг друга, можно ли поставить шах и сделать рокировку?""");
       System.out.println();
       board.printBoard();
       while (true) {
           String s = scanner.nextLine();
           if (s.equals("exit")) break;
           else if (s.equals("replay")) {
               System.out.println("Заново");
               board = buildBoard();
               board.printBoard();
           } else {
               if (s.equals("castling0")) {
                   if (board.castling0()) {
                       System.out.println("Рокировка удалась");
                       board.printBoard();
                   } else {
                       System.out.println("Рокировка не удалась");
                   }
               } else if (s.equals("castling7")) {
                   if (board.castling7()) {
                       System.out.println("Рокировка удалась");
                       board.printBoard();
                   } else {
                       System.out.println("Рокировка не удалась");
                   }
               } else if (s.contains("move")) {
                   String[] a = s.split(" ");
                   try {
                       int line = Integer.parseInt(a[1]);
                       int column = Integer.parseInt(a[2]);
                       int toLine = Integer.parseInt(a[3]);
                       int toColumn = Integer.parseInt(a[4]);
                       if (board.moveToPosition(line, column, toLine, toColumn)) {
                           System.out.println("Успешно передвинулись");
                           board.printBoard();
                       } else System.out.println("Передвижение не удалось");
                   } catch (Exception e) {
                       System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
                   }

               }
           }
       }
   }
}
В итоге мы имеем почти что идеальные шахматы, в которых не реализовано лишь проведение пешки в ферзи, если хотите, то можете реализовать это сами. Также консольный интерфейс нашей игры выглядит не очень привлекательно, но написание чего-то сложнее потребует гораздо больше времени. Думаю, у нас получились очень неплохие шахматы.
