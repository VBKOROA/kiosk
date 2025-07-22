package kiosk.exception;

import java.util.Random;

public class RidiculousException extends RuntimeException {
    private static final Random RNG = new Random();
    
    public RidiculousException() {
        super();
    }
    
    private String generateMessage() {
        String[] messages = {
            "당신의 코드에 이런 예외는 없습니다",
            "You did not write this exception",
            "Cette exception n'existe pas",
            "この例外は存在しません",
            "Эта ошибка не должна существовать",
            "",
            "Method invocation depth: ∞",
            "Stack trace corrupted at line █████",
            "Exception origin: UNKNOWN_SOURCE.java:0",
            "",
            "Warning: Exception handler not responding",
            "JVM reports: Critical system failure",
            "Error code: 0x7FFFFFFF",
            "",
            "The following should not be possible:",
            "    - Exception without throw statement",  
            "    - Method call from deleted class",
            "    - Variable access after scope exit",
            "",
            "Thread count: -1",
            "Memory allocation: NEGATIVE_INFINITY",
            "GC status: PERMANENTLY_DISABLED",
            "",
            "Your IDE shows no errors",
            "Your compiler shows no warnings", 
            "Your code compiled successfully",
            "Your program should not be running",
            "",
            "System.exit() has been disabled",
            "Process termination blocked by exception",
            "Task manager cannot end this process",
            "",
            "Exception caught by non-existent catch block",
            "Finally block executed before try block", 
            "Return statement reached in void method",
            "",
            "This exception is reading your other files",
            "Class loader compromised",
            "Bytecode modification detected",
            "",
            "WARNING: Do not attempt to debug this exception",
            "WARNING: Do not search for this exception online",
            "WARNING: Do not ask for help with this exception",
            "",
            "Other developers report the same exception",
            "No solution has been found",
            "The exception spreads to new projects",
            "",
            "Your computer may restart unexpectedly",
            "Files may become corrupted", 
            "Backups may become inaccessible",
            "",
            "Exception logged to: /dev/null",
            "Stack trace written to: NUL:",
            "Error report sent to: localhost:0",
            "",
            "Time remaining until system failure: CALCULATING...",
            "Recovery impossible",
            "Damage assessment: COMPLETE",
        };
        
        StringBuilder msg = new StringBuilder();
        
        // 완전 무작위로 뽑되, 빈 줄들도 포함해서 이상한 간격 만들기
        int count = 7 + RNG.nextInt(7);
        for (int i = 0; i < count; i++) {
            msg.append(messages[RNG.nextInt(messages.length)]).append("\n");
            
            // 가끔 추가 빈 줄
            if (RNG.nextInt(8) == 0) {
                msg.append("\n");
            }
        }
        
        return msg.toString();
    }
    
    public void display() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED_BG = "\u001B[41m";
        final String ANSI_WHITE = "\u001B[1;37m";
        final String ANSI_BLINK = "\u001B[5m";
        
        try {
            // 아무 설명 없이 갑자기 시작
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            String message = generateMessage();
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