package at.czlabinger.expressions;


public class Add implements Expression {
  
  private Expression e1;
  private Expression e2;

  public Add(Expression e1, Expression e2){
    this.e1 = e1;
    this.e2 = e2;
  }

  public double calculate() {
    return e1.calculate() + e2.calculate();
  }

  public String toExpressionString() {
    return "(" + e1.toExpressionString() + " + " + e2.toExpressionString() + ")";
  }
}
