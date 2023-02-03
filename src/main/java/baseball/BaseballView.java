package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.Scanner;

public class BaseballView {
    public static Boolean setRestart(){
        System.out.println("새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        Scanner s = new Scanner(Console.readLine());
        int num = s.nextInt();
        if (num==1) return true;
        return false;
    }

    public static String setNumber()
    {
        System.out.print("숫자를 입력해주세요 : ");

        Scanner s = new Scanner(Console.readLine());
        String str = s.next();
        return str;
    }

}
