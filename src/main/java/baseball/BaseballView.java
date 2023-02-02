package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.Scanner;

public class BaseballView {
    public static void output(String str) {
        System.out.println(str);
    }

    public static Scanner input() {
        return new Scanner(Console.readLine());
    }

}
