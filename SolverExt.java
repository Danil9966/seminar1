package seminar1;

import seminar1.collections.ArrayStack;
import seminar1.collections.IStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) )
 * ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 *
 * 1 + ( 2 + 3 ) * 4 * 5 = 101
 * 1 + 5 * 4 * 5 = 101
 * 1 + 5 * 20 = 101
 * 1 + 100 = 101
 * 20 / 4 = 5
 * (101 - 1) / 5 = 20
 *
 * Считаем, что операции деления на ноль отсутствуют
 */
public class SolverExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN   = '(';
    private static final char RIGHT_PAREN  = ')';
    private static final char PLUS         = '+';
    private static final char MINUS        = '-';
    private static final char TIMES        = '*';
    private static final char DIVISION     = '/';
    private static double eval(char operation,ArrayStack<Double> oper){
        switch(operation){
            case MINUS:
                return (-oper.pop()+oper.pop());
            case PLUS :
                return (oper.pop()+oper.pop());
            case DIVISION:
                double tmp=oper.pop();
                return (oper.pop()/tmp);
            case TIMES:
                return oper.pop()*oper.pop();
            default:return 0;
        }
    }
    private static double evaluate(String[] values) {
        ArrayStack<Integer> operators=new ArrayStack<>();
        ArrayStack<Double> operands=new ArrayStack<>();
        Scanner str=new Scanner(values[0]);
        double res;
        char tmp=0;
        while(str.hasNext()){
            if(str.hasNextInt()){
                operands.push(str.nextDouble());
                continue;
            }
            if(str.hasNext()){
                 tmp=str.next().charAt(0);
            }
            switch (tmp) {
                case LEFT_PAREN:
                    operators.push((int) LEFT_PAREN);



                    break;
                case RIGHT_PAREN:
                   while(operators.show()!=LEFT_PAREN){
                       operands.push(eval((char)(int)operators.show(),operands));
                    operators.pop();}
                    operators.pop();
                    break;
                case PLUS:
                    if(operators.size()!=0&&(operators.show()==TIMES||operators.show()==DIVISION))
                    {
                        operands.push(eval((char)(int)operators.show(),operands));
                        operators.pop();}
                    operators.push((int)PLUS);
                    break;
                case MINUS:
                    if(operators.size()!=0&&(operators.show()==TIMES||operators.show()==DIVISION))
                    {
                        operands.push(eval((char)(int)operators.show(),operands));
                        operators.pop();}
                    operators.push((int)MINUS);
                    break;
                case TIMES:
                    if(operators.size()!=0&&(operators.show()==TIMES||operators.show()==DIVISION))
                    {
                        operands.push(eval((char)(int)operators.show(),operands));
                        operators.pop();}
                    operators.push((int)TIMES);
                    break;
                case DIVISION:
                    if(operators.size()!=0&&(operators.show()==TIMES||operators.show()==DIVISION))
                    {
                    operands.push(eval((char)(int)operators.show(),operands));
                    operators.pop();}
                    operators.push((int)DIVISION);
                    break;

            }



        }
        while(operands.size()!=1)
            operands.push(eval((char)(int)operators.pop(),operands));

        return operands.pop();
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(evaluate(sequence.split("\n")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
