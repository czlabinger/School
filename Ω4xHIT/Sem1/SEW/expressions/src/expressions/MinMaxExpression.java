package expressions;

import java.util.List;

public class MinMaxExpression implements Expression {

	char operation;
	private List<Expression> expressions;
	
	public MinMaxExpression(char operation, List<Expression> expressions) {
		this.operation = operation;
		this.expressions = expressions;
	}
	
	@Override
	public String toExpressionString() {
		String out = "";
		
		if(this.operation == 'm') {
			out += "min(";
		} else if(this.operation == 'x') {
			out += "max(";
		}
		
		for(Expression expression : this.expressions) {
			out += expression.toExpressionString() + ", ";
		}
		if(this.expressions.size() > 0) {
			out = out.substring(0, out.length() - 2);
		}
		return out + ")";
	}

	@Override
	public double calculate() {
		if(this.expressions.isEmpty()) {
			throw new IllegalArgumentException("No Expression");
		}
		
		double result = this.expressions.get(0).calculate();
		
		for(Expression expression : this.expressions) {
			double current = expression.calculate();
			
			//min
			if(operation == 'm') {
				result = Math.min(current, result);
			//max
			} else if(operation == 'x') {
				result = Math.max(current, result);
			} else {
				throw new IllegalArgumentException("Invalid Operation!");
			}
		}
		return result;
	}

}
