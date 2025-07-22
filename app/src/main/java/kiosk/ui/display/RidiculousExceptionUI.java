package kiosk.ui.display;

import java.util.Random;

import kiosk.exception.RidiculousException;
import kiosk.ui.common.Displayable;

public class RidiculousExceptionUI implements Displayable {
    private final Random RNG = new Random();

    @Override
    public void display() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED_BG = "\u001B[41m";
        final String ANSI_WHITE = "\u001B[1;37m";
        final String ANSI_BLINK = "\u001B[5m";
        
        try {
            // 아무 설명 없이 갑자기 시작
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            String message = RidiculousException.generateMessage();
            System.out.print(ANSI_RED_BG + ANSI_WHITE);
            
            String[] lines = message.split("\n");
            
            for (String line : lines) {
                // 아무 이유 없이 가끔 글리치
                if (RNG.nextInt(15) == 0) {
                    System.out.print(ANSI_RESET);
                    Thread.sleep(RNG.nextInt(100));
                    System.out.print(ANSI_RED_BG + ANSI_WHITE);
                }
                
                // 완전 불규칙한 타이핑 속도
                for (char c : line.toCharArray()) {
                    if (c == '█') {
                        char[] glitch = {'█', '▓', '▒', '░', '■', '□', '?', '!', '@', '#'};
                        System.out.print(glitch[RNG.nextInt(glitch.length)]);
                    } else {
                        System.out.print(c);
                    }
                    
                    // 예측 불가능한 지연
                    int delay = RNG.nextInt(20);
                    if (RNG.nextInt(25) == 0) delay = 0;
                    if (RNG.nextInt(20) == 0) delay += 100;
                    if (RNG.nextInt(100) == 0) delay += 300;
                    
                    Thread.sleep(delay);
                    
                    // 아무 이유 없이 가끔 깜빡임
                    if (RNG.nextInt(80) == 0) {
                        System.out.print(ANSI_BLINK);
                        Thread.sleep(30);
                        System.out.print(ANSI_RED_BG + ANSI_WHITE);
                    }
                }
                
                System.out.println();
                
                // 줄 간격도 예측 불가능
                Thread.sleep(RNG.nextInt(80));
                
                // 아무 이유 없이 가끔 긴 정지
                if (RNG.nextInt(30) == 0) {
                    Thread.sleep(200 + RNG.nextInt(200));
                }
            }
            
            System.out.print(ANSI_RESET);
            
            // 그냥 갑자기 끝남 (설명 없음)
            Thread.sleep(1000);
            System.exit(1);
        } catch (InterruptedException e) {
            // 인터럽트도 무시하고 계속 진행
            display();
        }
    }
}
