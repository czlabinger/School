package at.czlabinger.expressions;

import java.util.List;

public class Max {

  private List<Expression> expressions;

  public Max(List<Expression> expressions) {
    this.expressions = expressions;
  }

  public double calculate() {
    double result = this.expressions.get(0).calculate();

    for(Expression e : this.expressions) {
      result = Math.max(e.calculate(), result);
    }
    return result;
  }

  public String toExpressionString() {
    StringBuilder sb = new StringBuilder();
    sb.append("max(");

    for(Expression e : this.expressions) {
      sb.append(e.toExpressionString() + ", ");
    }
    sb.delete(sb.length() - 2, sb.length());
    return sb.toString();
  }
}
