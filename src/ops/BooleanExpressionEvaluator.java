package ops;

import datastructures.Cell;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Stack;

public class BooleanExpressionEvaluator {

    public static char parseBoolExpression(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char c: expression.toCharArray()) {
            stack.push(c);
        }
        return parseBoolExpression(stack).get(0);
    }

    public static Stack<Character> parseBoolExpression(Stack<Character> stack) {
        Stack<Character> newStack = new Stack<>();

        boolean par_before = false;
        boolean bit_before = false;
        boolean bit_before2 = false;
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
                        bit_before2 = false;
                    }
                    not_before = false;
                    par_before = true;
                    break;

                case 'f':
                case 't':
                    if(not_before) {
                        newStack.pop();
                        not_before = false;
                        char bit_result = performNot(c);
                        if(bit_before) {
                            // get operation
                            char operation = newStack.pop();
                            // if operation == AND... solve...
                            if(operation == '*') {
                                char prev_bit = newStack.pop();
                                StringBuilder nString = new StringBuilder();
                                nString.append(prev_bit);
                                nString.append(bit_result);
                                bit_result = evaluate(operation, nString.toString());
                                ops_before = false;
                            // else... it is an OR... solve later
                            } else {
                                newStack.push(operation);
                                bit_before2 = true;
                            }
                        } else {
                            bit_before = true;
                        }
                        newStack.push(bit_result);
                        continue;
                    } else {
                        if(ops_before && bit_before) {
                            // get operation
                            char operation = newStack.pop();
                            // if operation == AND... solve...
                            if(operation == '*') {
                                char prev_bit = newStack.pop();
                                StringBuilder nString = new StringBuilder();
                                nString.append(prev_bit);
                                nString.append(c);
                                char bit_result = evaluate(operation, nString.toString());
                                ops_before = false;
                                newStack.push(bit_result);
                                bit_before2 = false;
                                continue;
                                // else... it is an OR... solve later
                            } else {
                                newStack.push(operation);
                                bit_before2 = true;
                            }
                        }
                    }
                    bit_before = true;
                    break;

                case ')':

                    if(par_before) {
                        if(bit_before) {
                            if(bit_before2) {
                                StringBuilder nString = new StringBuilder();
                                // Get bit2
                                nString.append(newStack.pop());
                                // pop the stacked OR operation
                                char operation = newStack.pop();
                                nString.append(newStack.pop());
                                char bit_result = evaluate(operation, nString.toString());
                                newStack.push(bit_result);
                                bit_before = false;
                                bit_before2 = false;
                                ops_before = false;
                                par_before = false;
                            } else {
                                bit = newStack.pop();
                                newStack.pop();
                                newStack.push(bit);
                                par_before = false;
                                bit_before = false;
                                continue;
                            }
                        }
                    }

                case '*':
                case '+':
                    if(ops_before) {
                        if(c == '+') {
                            StringBuilder nString = new StringBuilder();
                            // Get bit2
                            nString.append(newStack.pop());
                            // pop the stacked OR operation
                            char operation = newStack.pop();
                            nString.append(newStack.pop());
                            char bit_result = evaluate(operation, nString.toString());
                            newStack.push(bit_result);
                        } else {
                            par_before = false;
                        }
                        bit_before2 = false;
                    }
                    ops_before = true;
                    break;

                case '!':
                    not_before = true;
                    break;

            }
            newStack.push(c);
        }

        if(bit_before && bit_before2) {
            StringBuilder nString = new StringBuilder();
            // Get bit2
            nString.append(newStack.pop());
            // pop the stacked OR operation
            char operation = newStack.pop();
            nString.append(newStack.pop());
            char bit_result = evaluate(operation, nString.toString());
            newStack.push(bit_result);
            bit_before2 = false;
            ops_before = false;
        }


        if(newStack.size() != 1) {
            return parseBoolExpression(newStack);
        } else {
            return newStack;
        }

    }

    private static char evaluate(char operator, String seq) {
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

    private static char performAnd(String seq) {
        for (char c : seq.toCharArray()) {
            if (c == 'f')
                return 'f';
        }
        return 't';
    }

    private static char performOr(String seq) {
        for (char c : seq.toCharArray()) {
            if (c == 't')
                return 't';
        }
        return 'f';
    }

    private static char performNot(char c) {
        return c == 'f' ? 't' : 'f';
    }

    public static String getTruthTable(Cell cell) {

        StringBuilder result = new StringBuilder();
        String expression = cell.getFunctions().get(0).split("=")[1];

        if (expression.toLowerCase().contains("const")) {
            if (expression.contains("0")) {
                return "0";
            } else {
                return "1";
            }
        } else {
            int size = cell.getInputs().size();

            int rows = (int) Math.pow(2, size);
            ArrayList<Integer> temp = new ArrayList<>();

            for (int k = 0; k < rows; k++) {
                temp.clear();

                for (int j = size - 1; j >= 0; j--) {
                    temp.add((k / (int) Math.pow(2, j)) % 2);
                }
                String newString = expression;
                for (int j = 0; j < size; j++) {
                    newString = newString.replaceAll(cell.getInputs().get(j), String.format("%s", (temp.get(j) != 0 ? "t" : "f")));
                }
                //System.out.println(newString);
                result.append((parseBoolExpression(newString) == 'f' ? '0' : '1'));
                //result = (boolean)engine.eval(function + ";");
                //s = s + (result ? "1" : "0");
            }
            return result.toString();
        }
    }
}
