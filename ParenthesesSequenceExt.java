package seminar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import seminar1.collections.*;

/**
 * 1. пустая строка — правильная скобочная последовательность;
 * 2. правильная скобочная последовательность,
 *      взятая в скобки одного типа — правильная скобочная последовательность;
 * 3. правильная скобочная последовательность,
 *      к которой приписана слева или справа правильная скобочная последовательность
 *      — тоже правильная скобочная последовательность.
 */
public class ParenthesesSequenceExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN     = '(';
    private static final char RIGHT_PAREN    = ')';
    private static final char LEFT_BRACE     = '{';
    private static final char RIGHT_BRACE    = '}';
    private static final char LEFT_BRACKET   = '[';
    private static final char RIGHT_BRACKET  = ']';

    // sequence = "()()" | "(({}[]))[[[" | "{}" | ...
    private static boolean isBalanced(String sequence) {
        IStack<Integer> kek = new ArrayStack<>();

        for (int i = 0; i < sequence.length(); i++) {
            switch (sequence.charAt(i)) {
                case LEFT_BRACE:
                case LEFT_BRACKET:
                case LEFT_PAREN:
                    kek.push((int)sequence.charAt(i));
                    continue;
                case RIGHT_BRACE://лучше if юзать
                 if(!kek.isEmpty() && kek.pop()==(int)LEFT_BRACE)  continue;
                    else return false;
                case RIGHT_PAREN:
                    if(!kek.isEmpty() && kek.pop()==(int)LEFT_PAREN)  continue;
                    else return false;

                case RIGHT_BRACKET:
                    if(!kek.isEmpty() && kek.pop()==(int)LEFT_BRACKET)  continue;
                    else return false;





            }
        }
        if(kek.isEmpty())
        return  true;
        else return false;
    }
    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(isBalanced(sequence) ? "YES" : "NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
