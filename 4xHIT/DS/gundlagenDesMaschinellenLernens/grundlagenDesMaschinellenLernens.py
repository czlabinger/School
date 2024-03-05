import numpy as np
import pandas as pd
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
from sklearn.preprocessing import PolynomialFeatures

"""
Aufgabe 1
Teil 1
"""
economics = pd.read_csv('economics.csv')

unempmed = economics["uempmed"].values.reshape(-1, 1)
unemploy = economics["unemploy"].values.reshape(-1, 1)


unempmed_train, unempmed_test, unemploy_train, unemploy_test = train_test_split(unempmed, unemploy, test_size=0.2,
                                                                                random_state=0)

model1: LinearRegression = LinearRegression()
model2: LinearRegression = LinearRegression()

model1.fit(unempmed_train, unemploy_train)
model2.fit(unemploy_train, unempmed_train)

# Gespiegelt da Achsen vertauscht wurden

"""
Teil 2
"""
# region Linear 1

plt.scatter(unempmed_train, unemploy_train, label="Data")
plt.plot(unempmed_train, model1.predict(unempmed_train), color="red", label="Linear Regression")
plt.xlabel("uempmed")
plt.ylabel("unemploy")
plt.show()

# endregion

# region Linear 2

plt.scatter(unemploy_train, unempmed_train, label="Data")
plt.plot(unemploy_train, model2.predict(unemploy_train), color="red", label="Linear Regression")
plt.ylabel("uempmed")
plt.xlabel("unemploy")
plt.show()

# endregion

"""
Teil 3
"""

# region Log

log: LinearRegression = LinearRegression()
unempmed_train_log = np.log(unempmed_train)

log.fit(unempmed_train_log, unemploy_train)

print(log.intercept_, log.coef_, log.score(unempmed_train_log, unemploy_train))

# endregion

# region Exp

exp: LinearRegression = LinearRegression()
unempmed_train_exp = np.exp(unempmed_train)

exp.fit(unemploy_train, unempmed_train_log)

print(exp.intercept_, log.coef_, exp.score(unemploy_train, unempmed_train_log))

# endregion

"""

# region Log

log: LinearRegression = LinearRegression()
unempmed_train_log = np.log(unempmed_train)

log.fit(unempmed_train_log, unemploy_train)

plt.scatter(unempmed_train, unemploy_train, label="Data")
plt.plot(unempmed_train, log.predict(unemploy_train), color="red", label="Linear Regression")
plt.xlabel("uempmed")
plt.ylabel("unemploy")
plt.show()

# endregion

# region Exp

exp: LinearRegression = LinearRegression()
unempmed_train_exp = np.exp(unempmed_train)

exp.fit(y_train, x_train_exp)

plt.scatter(y_train, x_train_exp, label="Data")
plt.plot(exp.predict(x_train_exp), x_train, color="red", label="Linear Regression")
plt.ylabel("uempmed")
plt.xlabel("unemploy")
plt.show()

# endregion
"""

"""
Teil 5
"""

# region pol

poly = PolynomialFeatures(degree=10)
x_poly = poly.fit_transform(unempmed.reshape(-1, 1))

x_train, x_test, y_train, y_test = train_test_split(x_poly, unemploy, test_size=0.2, random_state=0)

poly_reg_model = LinearRegression()
poly_reg_model.fit(x_train, y_train)

plt.scatter(unempmed, unemploy, color='blue')
plt.plot(unempmed, poly_reg_model.predict(x_poly), color='red')
plt.show()

# endregion

"""
Teil 6
"""


