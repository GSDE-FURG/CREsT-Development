package ops;

import java.util.Stack;

public class BooleanEvaluator {
    public boolean parseBoolExpr(String expression) {
        int n = expression.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (c == ')') {
                System.out.println("Stack: " + stack);
                StringBuilder sb = new StringBuilder();
                while(stack.peek() != '(') {
                    char cc = stack.pop();
                    if (cc == ',')
                        continue;
                    sb.append(cc);
                }
                System.out.println(sb);
                stack.pop(); // pop opening parenthesis
                char operator = stack.pop();
                char result = evaluate(operator, sb.toString());
                stack.push(result);
            } else {
                stack.push(c);
            }
        }
        char result = stack.pop();
        return result == 't';
    }

    private char evaluate(char operator, String seq) {
        char result = 'f';
        switch(operator) {
            case '&': {
                result = performAnd(seq);
                break;
            }
            case '|': {
                result = performOr(seq);
                break;
            }
            case '!': {
                result = performNot(seq);
                break;
            }
        }
        return result;
    }

    private char performAnd(String seq) {
        System.out.println("Mamamae: " + seq);
        for (char c : seq.toCharArray()) {
            if (c == 'f')
                return 'f';
        }
        return 't';
    }

    private char performOr(String seq) {
        for (char c : seq.toCharArray()) {
            if (c == 't')
                return 't';
        }
        return 'f';
    }

    private char performNot(String seq) {
        return seq.charAt(0) == 'f' ? 't' : 'f';
    }
}
