import numpy as np
import pandas as pd
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt

"""
Aufgabe 1
Teil 1
"""
economics = pd.read_csv('economics.csv')

x = economics["uempmed"].values.reshape(-1, 1)
y = economics["unemploy"].values.reshape(-1, 1)

x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=42)

model1: LinearRegression = LinearRegression()
model2: LinearRegression = LinearRegression()

model1.fit(x_train, y_train)
model2.fit(y_train, x_train)

# Gespiegelt da Achsen vertauscht wurden

"""
Teil 2
"""
# region Linear 1

plt.scatter(x_train, y_train, label="Data")
plt.plot(x_train, model1.predict(x_train), color="red", label="Linear Regression")
plt.xlabel("uempmed")
plt.ylabel("unemploy")
plt.show()

# endregion

# region Linear 2

plt.scatter(y_train, x_train, label="Data")
plt.plot(y_train, model2.predict(y_train), color="red", label="Linear Regression")
plt.ylabel("uempmed")
plt.xlabel("unemploy")
plt.show()

# endregion

"""
Teil 3
"""


# region Exp

new_x = np.exp(x)

coef = np.polyfit(new_x.flatten(), y.flatten(), 1)
exp1 = np.poly1d(coef)

plt.plot(np.log(new_x), y, 'o', np.log(new_x), exp1(new_x), '--k')
plt.show()

# endregion

# region Log

log: LinearRegression = LinearRegression()
x_train_log = np.log(x_train)
y_train_log = np.log(y_train)

log.fit(y_train_log, x_train_log)

plt.scatter(y_train, x_train, label="Data")
plt.plot(y_train, np.exp(log.predict(y_train_log)), color="red", label="Linear Regression")
plt.ylabel("uempmed")
plt.xlabel("unemploy")
plt.show()

# endregion

"""
Teil 5
"""

pol_model = np.poly1d(np.polyfit(x.flatten(), y.flatten(), 10))

plt.scatter(x, y, label="original daten")
plt.plot(x, y, 'o', x, pol_model(x), '--k')
plt.show()

"""
Teil 6
"""


