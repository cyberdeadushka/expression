import java.util.Stack;

/**
 * Expression parsing.
 */

public class evaluation {
    /**
     * Evaluates expression. Divides numbers and operators  into two different stacks,
     * if closing brace is found pops everything until opening brace and evaluates expression.
     * @param expression - given expression.
     * @return result of expression.
     */
    public static double expreval(String expression)
    {
        char[] tokens = expression.toCharArray();
        Stack<Double> values = new Stack<Double>();
        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++)
        {
            if (tokens[i] == ' ')
                continue;

            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer sbuf = new StringBuffer();

                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Double.parseDouble(sbuf.toString()));

                i--;
            }

            else if (tokens[i] == '(')
                ops.push(tokens[i]);

            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                    values.push(arithmetic(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/')
            {
                while (!ops.empty() && precedence(tokens[i], ops.peek()))
                    values.push(arithmetic(ops.pop(), values.pop(), values.pop()));
                ops.push(tokens[i]);
            }
        }

        while (!ops.empty())
            values.push(arithmetic(ops.pop(), values.pop(), values.pop()));

        return values.pop();
    }

    /**
     * Prioritizing operators precedence.
     * @param op1 - first operator.
     * @param op2 - second operator.
     * @return true, if second operator is more or equal to first one.
     */
    public static boolean precedence(char op1, char op2)
    {
        if (op2 == '(' || ((op2 == '(') && (op2 == ')')))
            return false;
        if ((op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-'))
            return false;
        else return true;
    }

    /**
     * Applies operators.
     * @param op - operator.
     * @param a - variable.
     * @param b - variable.
     * @return result of expression evaluating.
     */
    public static double arithmetic(char op, double b, double a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }
}
