import numpy as np
from factor_analyzer import FactorAnalyzer
import pandas as pd
import seaborn as sns
from sklearn.preprocessing import StandardScaler
from sklearn.decomposition import PCA
import matplotlib.pyplot as plt
import matplotlib as ml
ml.use('Qt5Agg')

data = pd.read_csv("places_tf.csv")

X = data.values

#### Aufgabe 1.1

scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

pca = PCA()
X_pca = pca.fit_transform(X_scaled)

plt.scatter(X_pca[:,0],X_pca[:,1],alpha=0.5)
# add label and title
plt.show()

#### Aufgabe 1.2

corr_matrix = data.corr()

plt.figure(figsize=(10, 8))
sns.heatmap(corr_matrix, annot=True, cmap='coolwarm', fmt=".2f")
plt.title('Correlation Matrix')
plt.show()

#### Aufgabe 1.3

explained_variance_ratio = pca.explained_variance_ratio_

plt.figure(figsize=(10,6))
plt.plot(np.cumsum(explained_variance_ratio),marker='o',linestyle='-')
plt.xlabel('Anzahl der Hauptkomponenten')
plt.ylabel('Kumulative erklaerte Varianz')
plt.title('Screeplot')
plt.grid(True)
plt.show()

#### Aufgabe 1.4

explained_variance = pca.explained_variance_

sorted_indices = np.argsort(explained_variance)[::-1]

num_components_to_keep = 3

selected_components = sorted_indices[:num_components_to_keep]

print("Hauptkomponenten:")
for component_index in selected_components:
        print("Hauptkomponente {}: Eigenwert = {:.4f}".format(component_index + 1,explained_variance[component_index]))

X_selected_components = pca.transform(X_scaled)[:,selected_components]
print(X_selected_components)

#### Aufgabe 1.5

# Biplot erstellen

def biplot(score, coeff, labels=None):
    plt.figure(figsize=(10, 8))
    for i in range(coeff.shape[0]):
        plt.arrow(0, 0, coeff[i,0], coeff[i,1], color='r', alpha=0.5)
        if labels is None:
            plt.text(coeff[i,0]*1.15, coeff[i,1]*1.15, "Var"+str(i+1), color='g', ha='center', va='center')
        else:
            plt.text(coeff[i,0]*1.15, coeff[i,1]*1.15, labels[i], color='g', ha='center', va='center')
    plt.xlabel("PC1")
    plt.ylabel("PC2")
    plt.grid()
# Labels f端r die Variablen
feature_labels = data.columns

# Biplot f端r Hauptkomponenten 1 & 2
biplot(X_pca[:, :2], np.transpose(pca.components_[:2, :]), labels=feature_labels)
plt.title('Biplot PC1 vs PC2')
plt.show()

# Biplot f端r Hauptkomponenten 2 & 3
biplot(X_pca[:, 1:3], np.transpose(pca.components_[1:3, :]), labels=data.columns)
plt.title('Biplot PC2 vs PC3')
plt.show()

# Biplot f端r Hauptkomponenten 3 & 1
biplot(X_pca[:, [2, 0]], np.transpose(pca.components_[[2, 0], :]), labels=data.columns)
plt.title('Biplot PC3 vs PC1')
plt.show()

# Aufgabe 2.1
fa = FactorAnalyzer(rotation=None)
fa.fit(data)

loadings = fa.loadings_

print("Factor Loadings:")
print(pd.DataFrame(loadings, index=data.columns))

# Factor 0 mit housing, health, arts
# Factor 1 mit climate
# Factor 2 mit health am meisten aber sonst nicht wirklich stark


# Aufgabe 2.2

filtered_loadings = loadings.copy()  # Kopieren der Factor Loadings, um die Originaldaten nicht zu verändern
filtered_loadings[abs(filtered_loadings) < 0.3] = 0  # Setzen aller Werte unter dem Schwellenwert auf 0

# Darstellung der gefilterten Factor Loadings
print("Factor Loadings (Thresholded at 0.3):")
print(pd.DataFrame(filtered_loadings, index=data.columns))

# Aufgabe 2.3

# Faktor 0: Lebensqualitaet
# Faktor 1: Umwelt
# Faktor 2: Population

# Aufgabe 2.4

# Extract factor loadings for the first two factors
factor_loadings_2d = loadings[:, :2]

# Plotting the biplot
plt.figure(figsize=(10, 8))
plt.scatter(factor_loadings_2d[:, 0], factor_loadings_2d[:, 1], color='b')  # Plotting variables

# Annotating variable names
for i, (x, y) in enumerate(zip(factor_loadings_2d[:, 0], factor_loadings_2d[:, 1])):
    plt.text(x, y, data.columns[i], fontsize='12', ha='right')

# Adding arrows representing variable loadings
for i in range(len(data.columns)):
    plt.arrow(0, 0, factor_loadings_2d[i, 0], factor_loadings_2d[i, 1], color='r', alpha=0.5, width=0.01)

plt.xlabel('Factor 0')
plt.ylabel('Factor 1')
plt.grid(True)
plt.title('Biplot of Factor Loadings for the First Two Factors')
plt.axhline(0, color='gray', linewidth=0.5)
plt.axvline(0, color='gray', linewidth=0.5)
plt.show()

# Aufgabe 2.5

# Es kann sich auf die grundlegenden Faktoren konzentriert werden und so nicht viele Variabeln beobachtet werden.
