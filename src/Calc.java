import java.util.*;

class Calc {

    public static void main(String[] args) {
        String input = "5 5 5 5 5 17 10 + 3 * 9 /";
        int expected = 7;
        System.out.println(expected + "=" + Calc.compute(input));
    }

    /**
     * Evaluates an expression in coati notation.
     * 
     * @param expression String containing the expression.
     * @return The result of the expression.
     */
    public static int compute(String expression) {
        Stack<Integer> stack = new Stack<>();

        try (Scanner sc = new Scanner(expression)) {
            stack.push(sc.nextInt());
            stack.push(sc.nextInt());

            while (sc.hasNext()) {
                String top = sc.next();
                if (isDigit(top))
                    stack.push(Integer.parseInt(top));
                else {
                    int y = stack.pop();
                    int x = stack.pop();
                    switch (top) {
                        case "+":
                            stack.push(x + y);
                            break;
                        case "-":
                            stack.push(x - y);
                            break;
                        case "*":
                            stack.push(x * y);
                            break;
                        case "/":
                            stack.push(x / y);
                            break;
                        case "^":
                            stack.push((int) Math.pow(x, y));
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        if (stack.size() > 1)
            throw new IllegalArgumentException();
        return stack.pop();
    }

    private static boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}