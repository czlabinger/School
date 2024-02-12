import java.util.ArrayList;

import expressions.Expression;
import expressions.MinMaxExpression;
import expressions.NumberExpression;
import expressions.OperationExpression;

public class Main {

	public static void main(String[] args) {
	
		NumberExpression NE1 = new NumberExpression(1);
		NumberExpression NE2 = new NumberExpression(2);
		OperationExpression OP1 = new OperationExpression('/', NE1, NE2);
		OperationExpression OP2 = new OperationExpression('+', NE2, OP1);
		
		ArrayList<Expression> expressions = new ArrayList<Expression>();
		expressions.add(OP1);
		expressions.add(OP2);
		
		MinMaxExpression MinMax1 = new MinMaxExpression('m', expressions);
		System.out.println(MinMax1.toExpressionString());
		
	}
}
