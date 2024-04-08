package algorithm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 计算器
 *
 * @author ljx
 * @version 1.0.0
 * @create 2024/3/11 15:58
 */
public class ComputedExpression {

	static Stack<String> operation = new Stack<>();
	static Stack<Double> operand = new Stack<>();

	private static String[] operators = {"÷", "×", "+", "-", "(", ")", "%"};

	public static double calculate(List<String> args) {
		for (String arg : args) {
			switch (arg) {
				case "+":
				case "-":
				case "×":
				case "÷":
				case "%":
					if (isCalculate(arg)) {
						while (calculate()) {
						}
					}
					operation.push(arg);
					break;
				case "(":
					operation.push(arg);
					break;
				case ")":
					while (calculate()) {
					}
					break;
				default:
					operand.push(Double.parseDouble(arg));
					break;
			}
		}
		while (calculate()) {
		}
		return operand.pop();
	}


	public static boolean isCalculate(String symbol) {
		return !operation.empty() && priority(symbol, operation.peek());
	}

	public static boolean priority(String a, String b) {
		if (a.equals("+") || a.equals("-")) {
			return true;
		}
		return false;
	}

	public static boolean calculate() {
		String symbol;
		if (operation.empty() || (symbol = operation.pop()).equals("(")) {
			return false;
		}
		double a = operand.pop();
		if ("%".equals(symbol)) {
			operand.push(a / 100);
			return true;
		}
		double b = operand.pop();
		switch (symbol) {
			case "+":
				operand.push(a + b);
				break;
			case "-":
				operand.push(b - a);
				break;
			case "×":
				operand.push(a * b);
				break;
			case "÷":
				operand.push(b / a);
				break;
		}
		return true;
	}

	public static List<String> analyticalExpression(String expression) {
		List<String> args = new ArrayList<>();
		String[] split = expression.split("");
		StringBuilder sb = new StringBuilder();
		for (String c : split) {
			if (Arrays.stream(operators).anyMatch(c::contains)) {
				if (sb.length() > 0) {
					args.add(sb.toString());
					sb = new StringBuilder();
				}
				args.add(c);
				continue;
			}
			sb.append(c);
		}
		if (sb.length() > 0) {
			args.add(sb.toString());
		}
		return args;
	}


	public static void main(String[] args) {
		String expression = "1+1×9";
		;
		double calculate = calculate(analyticalExpression(expression));
		if (calculate % 1 == 0.0) {
			System.out.println((int) calculate);
		} else {
			System.out.println(calculate);
		}
	}
}
