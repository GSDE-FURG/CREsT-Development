package ops;

import java.util.Stack;

public class BooleanExpressionEvaluator {

    public Stack<Character> parseBoolExpression(Stack<Character> stack) {
        Stack<Character> newStack = new Stack<>();

        boolean par_before = false;
        boolean bit_before = false;
        boolean ops_before = false;
        boolean not_before = false;

        char bit;

        for(int i = 0; i < stack.size(); i++) {

            char c = stack.get(i);

            switch (c) {
                case '(':
                    if(ops_before) {
                        ops_before = false;
                        bit_before = false;
                    }
                    par_before = true;
                    break;

                case 'f':
                case 't':
                    if(not_before) {
                        newStack.pop();
                        not_before = false;
                        char bit_result = performNot(c);
                        if(bit_before) {
                            char operation = newStack.pop();
                            char prev_bit = newStack.pop();
                            StringBuilder nString = new StringBuilder();
                            nString.append(prev_bit);
                            nString.append(bit_result);
                            bit_result = evaluate(operation, nString.toString());
                            ops_before = false;
                        } else {
                            bit_before = true;
                        }
                        newStack.push(bit_result);
                        continue;
                    } else {
                        if(ops_before) {
                            char operation = newStack.pop();
                            char prev_bit = newStack.pop();
                            StringBuilder nString = new StringBuilder();
                            nString.append(prev_bit);
                            nString.append(c);
                            char bit_result = evaluate(operation, nString.toString());
                            ops_before = false;
                            newStack.push(bit_result);
                            continue;
                        }
                    }
                    bit_before = true;
                    break;

                case ')':
                    if(par_before) {
                        if(bit_before) {
                            bit = newStack.pop();
                            newStack.pop();

                            newStack.push(bit);
                            par_before = false;
                            bit_before = false;
                            continue;
                        }
                    }

                case '*':
                case '+':
                    ops_before = true;
                    break;

                case '!':
                    not_before = true;
                    break;

            }
            newStack.push(c);
        }
        if(newStack.size() != 1) {
            System.out.println(newStack);
            return parseBoolExpression(newStack);
        } else {
            return newStack;
        }

    }

    private char evaluate(char operator, String seq) {
        char result = 'f';
        switch(operator) {
            case '*': {
                result = performAnd(seq);
                break;
            }
            case '+': {
                result = performOr(seq);
                break;
            }
        }
        return result;
    }

    private char performAnd(String seq) {
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

    private char performNot(char c) {
        return c == 'f' ? 't' : 'f';
    }
}
