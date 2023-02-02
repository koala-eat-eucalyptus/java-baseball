package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;
import java.util.stream.Stream;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현


        aGame();
        while (setReStart()) {
            aGame();
        }
    }

    public static void aGame() {//게임시작문구부터 1판의 종료까지의 과정
        beginningPhrase();
        List<Integer> opponentScore = getRandom();
        int[] myGuess = setNumber();                      //내가 추측하는 숫자 입력
        boolean check = compareNum(myGuess, opponentScore);  //컴퓨터가 설정한 답과 비교

        while (!check) {
            myGuess = setNumber();
            check = compareNum(myGuess, opponentScore);

        }

    }

    public static boolean setReStart() {                  //aGame을 통해 한판을 마친뒤 계속할지 1과 2의 입력을 통해 결정.
        BaseballView.output("새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        Scanner s = BaseballView.input();      //처음에 테스트 pass가 안되었는데 System.in을 Console.readLine으로 바꾸어 해결되었다.
        int num = s.nextInt();
        if (num == 1) return true;
        return false;
    }

    public static boolean compareNum(int[] me, List<Integer> opponent) {          //사용자가 입력한 점수와 컴퓨터의 점수 비교

        int ball = countingBall(me, opponent);                   /*이 메서드는 책임이 여러개가 있어서 분리하는건 어떨까 ?
                                                                    볼을 계산하는 책임
                                                                    스트라이크를 계산하는 책임
                                                                    볼과 스트라이크를 계산을 합쳐서 ballCount에 넘겨주는 책임
                                                                    개발은 알고리즘처럼 효율적으로 코드를 작성하는 것보다
                                                                    비효율적이라도 일단 가독성을 더 우선시한다고 생각*/
        int strike = countingStrike(me, opponent);
        ball -= strike;

        return isThreeStrikeZeroBall(strike, ball);
    }

    public static Boolean isThreeStrikeZeroBall(int strike, int ball) {
        return (ballCount(strike, ball));
    }

    public static int countingBall(int[] me, List<Integer> opponent) {
        int ball = 0;
        for (int j : me) {
            if (opponent.contains(j)) ball += 1;

        }
        return ball;
    }

    public static int countingStrike(int[] me, List<Integer> opponent) {
        int strike = 0;
        for (int i = 0; i < THREE; i++) {
            if (me[i] == opponent.get(i)) {
                strike += 1;
            }
        }
        return strike;
    }

    public static final String BALL = "볼 ";
    public static final String STRIKE = "스트라이크";
    public static final String NOTHING = "낫싱";

    private static final Integer THREE = 3;

    public static boolean ballCount(Integer strike, Integer ball) {      //compareNum함수에서 넘겨받은 데이터를 통해 출력하고
        if (strike == 0 && ball == 0) {                                  //boolean값을 판별함.
            BaseballView.output(NOTHING);
            return false;
        }

        if (strike > 0 && ball > 0) {
            BaseballView.output(ball + BALL + strike + STRIKE);
            return false;
        }
        if (ball > 0) {
            BaseballView.output(ball + BALL);
            return false;
        }

        if (strike > 0) {
            BaseballView.output(strike + STRIKE);
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

    public static List<Integer> getRandom() {                              //README.md 참고, 컴퓨터가 랜덤한 점수를 설정케함.
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }

    public static int[] setNumber() {                                        //내가 추측할 점수를 입력한다.
        System.out.print("숫자를 입력해주세요 : ");
        Scanner s = BaseballView.input();
        String str = s.next();
        if (!isNumber(str)) {
            throw new IllegalArgumentException();
        }
        isOverlapping(str);
        return lengthCorrect(str);
    }

    public static int[] lengthCorrect(String str) {
        int[] digits = Stream.of(String.valueOf(str).split("")).mapToInt(Integer::parseInt).toArray();

        if (digits.length != 3) {
            throw new IllegalArgumentException();
        }
        return digits;

    }

    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch < '0' || ch > '9') return false;
        }
        return true;
    }

    public static void isOverlapping(String str){
        Set<Character> set = new HashSet<>();
        for (int i = 0; i<str.length(); i++){
            set.add(str.charAt(i));
        }
        if(str.length()!=set.size()) throw new IllegalArgumentException();
    }
    //여기 부분은 메서드를 만들지 않거나,
    // 커스텀 예외처리에 대해 공부해서 적용하는걸 추천해
}
