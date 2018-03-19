package com.wfy.expressiontree;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wfy on 18-3-18, good luck.
 */
public class ExpressionTree {
    private static final Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?)|([+\\-*/()])");
    private static final String operators = "+-*/()";
    private TreeNode root;

    public ExpressionTree(String expression, ExpressionType expressionType) {
        if (expressionType == ExpressionType.NIFIX) {
            constructFromNifix(expression);
        } else if (expressionType == ExpressionType.PREFIX) {
            constructFromPrefix(expression);
        } else if (expressionType == ExpressionType.SUFFIX) {
            constructFromSuffix(expression);
        }
    }

    public static void main(String[] args) throws ScriptException {
        ExpressionTree tree1 = new ExpressionTree("32+85*4+(17*9.11+11)*7", ExpressionType.NIFIX);
        ExpressionTree tree2 = new ExpressionTree(
                "+ + * 7 + 11 * 9.11 17 * 4 85 32",
                ExpressionType.PREFIX
        );
        ExpressionTree tree3 = new ExpressionTree(
                "7 11 9.11 17 * + * 4 85 * + 32 +",
                ExpressionType.SUFFIX
        );
        System.out.println("Input with Nifix:");
        System.out.println("Nifix expression: " + tree1.toString());
        System.out.println("Prefix expression: " + tree1.toPrefixString());
        System.out.println("Suffix expression: " + tree1.toSuffixString());
        System.out.println("Result: " + tree1.getResult());

        System.out.println("\nInput with Prefix:");
        System.out.println("Nifix expression: " + tree2.toString());
        System.out.println("Prefix expression: " + tree2.toPrefixString());
        System.out.println("Suffix expression: " + tree2.toSuffixString());
        System.out.println("Result: " + tree2.getResult());

        System.out.println("\nInput with Suffix:");
        System.out.println("Nifix expression: " + tree3.toString());
        System.out.println("Prefix expression: " + tree3.toPrefixString());
        System.out.println("Suffix expression: " + tree3.toSuffixString());
        System.out.println("Result: " + tree3.getResult());
    }

    private static boolean isPrior(String b, String a) {
        // 判断b（栈内）的优先级是否比a（栈外）高
        // '('在栈外优先级最高,栈内最低,')'在栈外优先级最低
        int valueA = 0;
        int valueB = 0;
        switch (a) {
            case "(":
                valueA = 100;
                break;
            case ")":
                valueA = -100;
                break;
            case "*":
                valueA = 1;
                break;
            case "/":
                valueA = 1;
                break;
            case "+":
                valueA = 0;
                break;
            case "-":
                valueA = 0;
                break;
        }
        switch (b) {
            case "(":
                valueB = -99;
                break;
            case ")":
                valueB = -100;
                break;
            case "*":
                valueB = 1;
                break;
            case "/":
                valueB = 1;
                break;
            case "+":
                valueB = 0;
                break;
            case "-":
                valueB = 0;
                break;
        }
        return valueB > valueA;
    }

    private void constructFromNifix(String expression) {
        Stack operatorStack = new Stack();
        Stack<TreeNode> expressionStack = new Stack<>();
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find() || !operatorStack.isEmpty()) {
            String token;
            try {
                token = matcher.group();
            } catch (Exception e) {
                String operator = (String) operatorStack.pop();
                if (operator.equals("(") || operator.equals(")")) {
                    continue;
                }
                TreeNode node = new TreeNode(operator);
                node.setLChild(expressionStack.pop());
                node.setRChild(expressionStack.pop());
                expressionStack.push(node);
                if (expressionStack.size() == 1) {
                    this.root = expressionStack.pop();
                    return;
                }
                continue;
            }
            if (operators.contains(token)) { // token is an operator
                if (operatorStack.isEmpty() || !isPrior((String) operatorStack.peek(), token)) {
                    operatorStack.push(token);
                } else {
                    if (operatorStack.peek() == "(" && token.equals(")")) { //左右括号匹配
                        operatorStack.pop();
                    } else {
                        TreeNode node = new TreeNode((String) operatorStack.pop());
                        node.setLChild(expressionStack.pop());
                        node.setRChild(expressionStack.pop());
                        expressionStack.push(node);
                        operatorStack.push(token);
                    }
                }
            } else {
                expressionStack.push(new TreeNode(Double.parseDouble(token)));
            }
        }
    }

    private void constructFromPrefix(String expression) {
        Stack<TreeNode> expressionStack = new Stack<>();
        boolean topIsOperator = true;
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            String token = matcher.group();
            if (operators.contains(token)) { // token is an operator
                expressionStack.push(new TreeNode(token));
                topIsOperator = true;
            } else {
                expressionStack.push(new TreeNode(Double.parseDouble(token)));
                if (!topIsOperator) {
                    while (true) {
                        TreeNode expressionNode1 = expressionStack.pop();
                        TreeNode expressionNode2 = expressionStack.pop();
                        TreeNode operatorNode = expressionStack.pop();
                        operatorNode.setLChild(expressionNode1);
                        operatorNode.setRChild(expressionNode2);
                        if (expressionStack.isEmpty()) {
                            this.root = operatorNode;
                            return;
                        }
                        if (expressionStack.isEmpty()
                                || expressionStack.peek().lChild == null
                                && expressionStack.peek().isOperator) {
                            expressionStack.push(operatorNode);
                            break;
                        }
                        expressionStack.push(operatorNode);
                    }
                }
                topIsOperator = false;
            }
        }
    }

    private void constructFromSuffix(String expression) {
        Stack<TreeNode> expressionStack = new Stack<>();
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            String token = matcher.group();
            if (operators.contains(token)) { // token is an operator
                TreeNode operatorNode = new TreeNode(token);
                TreeNode expressionNode1 = expressionStack.pop();
                TreeNode expressionNode2 = expressionStack.pop();
                operatorNode.setLChild(expressionNode1);
                operatorNode.setRChild(expressionNode2);
                expressionStack.push(operatorNode);
            } else {
                expressionStack.push(new TreeNode(Double.parseDouble(token)));
            }
        }
        this.root = expressionStack.pop();
    }

    public String toPrefixString() {
        return this.root.toPrefixString();
    }

    public String toNifixString() {
        return this.root.toNifixString();
    }

    public String toSuffixString() {
        return this.root.toSuffixString();
    }

    @Override
    public String toString() {
        return this.root.toString();
    }

    public double getResult() throws ScriptException {
        return this.root.getResult();
    }

    public enum ExpressionType {
        PREFIX,
        NIFIX,
        SUFFIX
    }

    public static class TreeNode {
        private boolean isOperator;
        private double data;
        private String operator;
        private TreeNode lChild;
        private TreeNode rChild;
        private TreeNode parent;

        TreeNode(double data) {
            this.data = data;
            this.isOperator = false;
        }

        TreeNode(String operator) {
            this.operator = operator;
            this.isOperator = true;
        }

        String toPrefixString() {
            if (this.lChild == null && this.rChild == null) {
                return this.isOperator ? this.operator : String.valueOf(this.data);
            }
            String ret = this.isOperator ? this.operator : String.valueOf(this.data);
            if (this.lChild != null) {
                ret += " " + this.lChild.toPrefixString();
            }
            if (this.rChild != null) {
                ret += " " + this.rChild.toPrefixString();
            }
            return ret;
        }

        String toNifixString() {
            if (this.lChild == null && this.rChild == null) {
                return this.isOperator ? this.operator : String.valueOf(this.data);
            }
            return "(" + this.lChild +
                    (this.isOperator ? this.operator : String.valueOf(this.data))
                    + this.rChild + ")";
        }

        String toSuffixString() {
            if (this.lChild == null && this.rChild == null) {
                return this.isOperator ? this.operator : String.valueOf(this.data);
            }
            String ret = "";
            if (this.lChild != null) {
                ret += this.lChild.toSuffixString() + " ";
            }
            if (this.rChild != null) {
                ret += this.rChild.toSuffixString() + " ";
            }
            ret += this.isOperator ? this.operator : String.valueOf(this.data);
            return ret;
        }

        @Override
        public String toString() {
            return toNifixString();
        }

        double getResult() throws ScriptException {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine se = manager.getEngineByName("js");
            return (Double) se.eval(this.toString());
        }

        void setLChild(TreeNode lChild) {
            this.lChild = lChild;
        }

        void setRChild(TreeNode rChild) {
            this.rChild = rChild;
        }
    }
}
