package expressions;

public class OperationExpression implements Expression {
	private char operator;
	private Expression left;
	private Expression right;
	
	public OperationExpression(char operator, Expression left, Expression right) {
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toExpressionString() {
		return "(" + this.left.toExpressionString() + " " + this.operator + " " + this.right.toExpressionString() + ")";
	}

	@Override
	public double calculate() {
		switch(operator) {
		case '+':
			return this.left.calculate() + this.right.calculate();
		case '-':
			return this.left.calculate() - this.right.calculate();
		case '*':
			return this.left.calculate() * this.right.calculate();
		case '/':
			if (this.right.calculate() > 0) {
				return this.left.calculate() / this.right.calculate();
			}
			throw new IllegalArgumentException("Right side of division evaluates to 0!");
		default:
			throw new IllegalArgumentException("Not a valid Operator!");
		}
	}

}
