import numpy as np
import pandas as pd
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
from sklearn.preprocessing import PolynomialFeatures
from sklearn.metrics import mean_squared_error
from sklearn.metrics import median_absolute_error
from sklearn.linear_model import LogisticRegression
from sklearn.preprocessing import StandardScaler
import math

"""
Aufgabe 1
Teil 1
"""
economics = pd.read_csv('economics.csv')
economics = economics.sort_values(by=['unemploy'])

uempmed = economics["uempmed"].values.reshape(-1, 1)
unemploy = economics["unemploy"].values.reshape(-1, 1)

unemploy_train, unemploy_test, uempmed_train, uempmed_test = train_test_split(unemploy, uempmed, test_size=0.2, random_state=42)

model1 = LinearRegression()
model1.fit(unemploy_train, uempmed_train)

model2 = LinearRegression()
model2.fit(uempmed_train, unemploy_train)

print(f"unemploy/uempmed: {model1.score(unemploy_test, uempmed_test)}")
print(f"uempmed/unemploy: {model2.score(uempmed_test, unemploy_test)}")

"""
Aufgabe 1
Teil 2
"""

########################### unemploy/uempmed ############################
y1_pred = model1.predict(unemploy)

plt.figure(figsize=(8, 6))
plt.scatter(unemploy, uempmed, color='blue', label='Originaldaten')
plt.plot(unemploy, y1_pred, color='red', label='Vorhersagen des Modells')

plt.xlabel('Anzahl der Arbeitslosen (unemploy)')
plt.ylabel('Mittlere Anzahl der Tage in Arbeitslosigkeit (uempmed)')
plt.legend()

plt.show()

########################## uempmed/unemploy ############################
y2_pred = model2.predict(uempmed)

plt.figure(figsize=(8, 6))
plt.scatter(uempmed, unemploy, color='blue', label='Originaldaten')
plt.plot(uempmed, y2_pred, color='red', label='Vorhersagen des Modells')

plt.xlabel('Mittlere Anzahl der Tage in Arbeitslosigkeit (uempmed)')
plt.ylabel('Anzahl der Arbeitslosen (unemploy)')
plt.legend()

plt.show()


"""
Aufgabe 1
Teil 3
"""

uempmed_log = np.log(uempmed)
unemploy_exp = np.exp(unemploy)

######################## Log ##############################

plt.scatter(unemploy, uempmed_log, color='blue', label='Originaldaten')
plt.show()

log = LinearRegression()
log.fit(unemploy, uempmed_log)
log_pred = log.predict(unemploy)
####################### Exp ################################

# FIXME: Overflow
#plt.scatter(uempmed, unemploy_exp, color='blue', label='')


"""
Aufgabe 1
Teil 4
"""

########################### Exp ############################

plt.scatter(unemploy, uempmed, color='blue', label='Originaldaten')
plt.plot(unemploy, np.exp(log_pred), color='red', label='Vorhersage des Modells')

plt.xlabel('Anzah der Arbeitslosen (unemploy)')
plt.ylabel('Vorhergesagte mittlere Anzahl der Tage in Arbeitslosigkeit (uempmed)')
plt.legend()

plt.show()


########################### Log ############################

# TODO: Plot



"""
Aufgabe 1
Teil 5
"""

poly = PolynomialFeatures(degree=10, include_bias=False)
poly_features = poly.fit_transform(unemploy)
poly_reg_model = LinearRegression()

poly_reg_model.fit(poly_features, uempmed)

poly_pred = poly_reg_model.predict(poly_features)

plt.scatter(unemploy, uempmed, color='blue', label='Originaldaten')
plt.plot(unemploy, poly_pred, color='red', label='Vorhersage des Modells')

plt.xlabel('Anzah der Arbeitslosen (unemploy)')
plt.ylabel('Vorhergesagte mittlere Anzahl der Tage in Arbeitslosigkeit (uempmed)')
plt.legend()

plt.show()


"""
Aufgabe 1
Teil 6
"""

# Linear
mse = mean_squared_error(uempmed, y1_pred)
rmse = math.sqrt(mse)
mad = median_absolute_error(uempmed, y1_pred)

print(f'Linear: MSE: {mse}, RMSE: {rmse}, MAD: {mad}')

# Log
mse = mean_squared_error(unemploy, log_pred)
rmse = math.sqrt(mse)
mad = median_absolute_error(unemploy, log_pred)

print(f'Linear: MSE: {mse}, RMSE: {rmse}, MAD: {mad}')

# Poly
mse = mean_squared_error(uempmed, poly_pred)
rmse = math.sqrt(mse)
mad = median_absolute_error(uempmed, poly_pred)

print(f'Linear: MSE: {mse}, RMSE: {rmse}, MAD: {mad}')


"""
Aufgabe 2
Teil 1
"""
train_data = pd.read_csv('Pima.tr.csv')
test_data = pd.read_csv('Pima.te.csv')

X_train = train_data.drop('type', axis=1)
y_train = train_data['type']
X_test = test_data.drop('type', axis=1)
y_test = test_data['type']

scaler = StandardScaler()
X_train_scaled = scaler.fit_transform(X_train)

model = LogisticRegression(max_iter=1000)
model.fit(X_train_scaled, y_train)

logistic_pred = model.predict(X_test)

plt.scatter(X_train_scaled, y_train, color='blue', label='Originaldaten')
plt.plot(X_train_scaled, logistic_pred, color='red', label='Vorhersage des Modells')

plt.show()
