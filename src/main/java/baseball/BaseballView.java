package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.Scanner;

public class BaseballView {
    public static Boolean setRestart(){               ////aGame을 통해 한판을 마친뒤 계속할지 1과 2의 입력을 통해 결정.
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

    public static final String BALL = "볼 ";
    public static final String STRIKE = "스트라이크";
    public static final String NOTHING = "낫싱";

    private static final Integer THREE = 3;

    public static boolean ballCount(Integer strike, Integer ball) {      //compareNum함수에서 넘겨받은 데이터를 통해 출력하고
        if (strike == 0 && ball == 0) {                                  //boolean값을 판별함.
            System.out.println(NOTHING);
            return false;
        }

        if (strike > 0 && ball > 0) {
            System.out.println(ball + BALL + strike + STRIKE);
            return false;
        }
        if (ball > 0) {
            System.out.println(ball + BALL);
            return false;
        }

        if (strike > 0) {
            System.out.println(strike + STRIKE);
            return isThreeStrikeGameFinish(strike);
        }
        return false;

    }

    public static Boolean isThreeStrikeGameFinish(int strike) {
        if (strike == THREE) {

            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            return true;
        }
        return false;

    }

    public static void beginningPhrase() {                               //게임시작문구 출력
        System.out.println("숫자 야구 게임을 시작합니다.");
    }


}
