package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        aGame();
        while (setReStart()) {
            aGame();
        }
    }

    public static void aGame() {                       //게임시작문구부터 1판의 종료까지의 과정
        beginningPhrase();
        List<Integer> opponentScore = getRand();
        int[] myGuess = setNum();                      //내가 추측하는 숫자 입력
        boolean check = compareNum(myGuess, opponentScore);  //컴퓨터가 설정한 답과 비교

        while (!check) {
            myGuess = setNum();
            check = compareNum(myGuess, opponentScore);

        }

    }

    public static boolean setReStart() {                  //aGame을 통해 한판을 마친뒤 계속할지 1과 2의 입력을 통해 결정.
        System.out.println("새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        Scanner s = new Scanner(Console.readLine());      //처음에 테스트 pass가 안되었는데 System.in을 Console.readLine으로 바꾸어 해결되었다.
        int num = s.nextInt();
        if (num == 1) return true;
        return false;
    }

    public static boolean compareNum(int[] me, List<Integer> opponent) {          //사용자가 입력한 점수와 컴퓨터의 점수 비교
        int strike = 0;
        int ball = 0;
        int correct = 0;
        for (int i = 0; i < 3; i++) {

            if (opponent.contains(me[i])) {
                correct = 1;
                ball += 1;
            }
            if (me[i] == opponent.get(i) && correct == 1) {
                ball -= 1;
                strike += 1;
            }
        }
        if (ballCount(strike, ball)) return true;
        return false;
    }

    public static boolean ballCount(Integer strike, Integer ball) {      //compareNum함수에서 넘겨받은 데이터를 통해 출력하고
        if (strike == 0 && ball == 0) {                                  //boolean값을 판별함.
            System.out.println("낫싱");
            return false;
        }
        if (strike > 0 && ball > 0) {
            System.out.println(ball + "볼 " + strike + "스트라이크");
            return false;
        }
        if (ball > 0) {
            System.out.println(ball + "볼");
            return false;
        }

        if (strike > 0) {
            System.out.println(strike + "스트라이크");
            if (strike == 3) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                return true;
            }
            return false;
        }
        return false;

    }

    public static void beginningPhrase() {                               //게임시작문구 출력
        System.out.println("숫자 야구 게임을 시작합니다.");
    }

    public static List<Integer> getRand() {                              //README.md 참고, 컴퓨터가 랜덤한 점수를 설정케함.
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }

    public static int[] setNum() {                                        //내가 추측할 점수를 입력한다.
        System.out.print("숫자를 입력해주세요 : ");
        Scanner s = new Scanner(Console.readLine());
        //Integer num = s.nextInt();
        String str = s.next();
        if (!isNum(str)) {
            throwIllegalArgumentException();
        }
        int[] digits = Stream.of(String.valueOf(str).split("")).mapToInt(Integer::parseInt).toArray();

        if (digits.length != 3) {

            throwIllegalArgumentException();
        }
        return digits;
    }
    public static boolean isNum(String str){
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if (ch<'0' || ch>'9') return false;
        }
        return true;
    }

    public static int[] throwIllegalArgumentException() {
        throw new IllegalArgumentException();
    }



}

//    public static boolean iscontainNumber(int ballNumber, List<Integer> data){  스트림에 숫자가 존재하는지 판별하기 위해 짰는데
//        return Arrays.stream(data)                                              다시 보니컴퓨터의 점수가 스트림 형태가 아니라 일단 주석처리
//                .anyMatch(ball -> ball==ballNumber);
//
//    }
