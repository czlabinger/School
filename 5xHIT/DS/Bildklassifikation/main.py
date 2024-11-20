import tensorflow as tf
import numpy as np
from tensorflow.keras.preprocessing import image
import os
from sklearn.model_selection import train_test_split
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense, Dropout

def load_images_from_folder(folder_path, num_images):
    images_array = []
    image_files = [file for file in os.listdir(folder_path) if os.path.splitext(file)[1].lower() == ".jpg"]
    for index, image_file in enumerate(image_files[:num_images]):
        image_path = os.path.join(folder_path, image_file)
        print(f"Processing image {index + 1}: {image_file}")
        img = image.load_img(image_path, target_size=(45, 45))
        img_array = image.img_to_array(img)
        images_array.append(img_array)
    return np.array(images_array)

# Load data
zero_folder = "./archive/extracted_images/0/"
exclamation_folder = "./archive/extracted_images/!/"

exclamation_images = load_images_from_folder(zero_folder, 30)
open_images = load_images_from_folder(exclamation_folder, 30)

# Create labels
labels_exclamation = np.zeros(30)  # Label 0 for exclamation mark
labels_open = np.ones(30)  # Label 1 for open bracket

X = np.concatenate((exclamation_images, open_images), axis=0)
y = np.concatenate((labels_exclamation, labels_open), axis=0)

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.33, stratify=y)

X_train = X_train / 255.0
X_test = X_test / 255.0


def create_model():
    model = Sequential([
        Conv2D(32, (3, 3), activation='relu', input_shape=(45, 45, 3)),
        MaxPooling2D((2, 2)),
        Conv2D(64, (3, 3), activation='relu'),
        MaxPooling2D((2, 2)),
        Flatten(),
        Dense(128, activation='relu'),
        Dropout(0.5),
        Dense(1, activation='sigmoid')
    ])
    model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])
    return model

# Ansatz 1: Jedes Symbol in 1000 Durchläufen anpassen
def train_each_symbol_separately(model, epochs=1000):
    for label in np.unique(y_train):
        print(f"Training on symbol with label: {label}")
        mask = y_train == label
        model.fit(X_train[mask], y_train[mask], epochs=epochs, verbose=0)

# Ansatz 2: Durchmischen der Symbole
def train_random_symbols(model, epochs=1000):
    model.fit(X_train, y_train, epochs=epochs, verbose=0)

# Create model
model = create_model()

# Ansatz 1: Trainiere jedes Symbol separat
print("Training each symbol separately...")
train_each_symbol_separately(model)

# Evaluate the model
test_loss, test_acc = model.evaluate(X_test, y_test)
print(f"Test accuracy after separate training: {test_acc:.4f}")

# Reset model for the next training approach
model = create_model()

# Ansatz 2: Trainiere zufällige Symbole
print("Training with mixed symbols...")
train_random_symbols(model)

# Evaluate the model
test_loss, test_acc = model.evaluate(X_test, y_test)
print(f"Test accuracy after random training: {test_acc:.4f}")
