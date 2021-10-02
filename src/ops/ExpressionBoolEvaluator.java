package ops;

import java.util.Stack;

public class ExpressionBoolEvaluator {
    public boolean parseBoolExpr(String expression) {
        boolean not_flag = false;

        int n = expression.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);

            if(not_flag) {
                if ((c != '(') && (c != ')')) {
                    // Pop the not signal
                    stack.pop();
                    stack.push(performNot(c));
                    System.out.println(stack);
                    continue;
                } else {
                    not_flag = false;
                }
            }

            if (c == '!') {
                not_flag = true;
            }

            stack.push(c);

            /*
            if (c == ')') {
                System.out.println("Stack: " + stack);
                System.out.println(stack.get(0));
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
            } */
        }

        if(stack.size() != 1) {
            System.out.println("ToString: " + stack.toString());
            System.out.println(cleanParentheses(stack));
            return false;
        } else {
            char result = stack.pop();
            System.out.println("Result: " + result);
            System.out.println("Final Stack: " + stack);
            System.out.println("Length: " + stack.size());
            return result == 't';
        }
    }

    public Stack<Character> cleanParentheses(Stack<Character> stack) {
        Stack<Character> newStack = new Stack<>();

        boolean par_before = false;
        boolean bit_before = false;
        char bit;

        for(int i = 0; i < stack.size(); i++) {

            char c = stack.get(i);

            if(c == '(') {
                par_before = true;
            } else {
                if(c == 'f' || c == 't') {
                    bit_before = true;
                } else {
                    if(c == ')') {
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
                    // Can be '+', '!', '(' or '*'
                    } else {
                        par_before = false;
                        bit_before = false;
                    }
                }
            }
            System.out.println(c);
            newStack.push(c);
        }
        return newStack;
    }

    public Stack<Character> cleanParentheses2(Stack<Character> stack) {
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
            return cleanParentheses2(newStack);
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
