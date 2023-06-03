from flask import Flask, render_template, request

app = Flask(__name__)

@app.route('/')
def home():
    return render_template('home.html')

@app.route('/login', methods=['GET', 'POST'])
def login_get():
   username = "Stoffi05"
   password = "test1234"
   if request.method == 'POST':
      usernameInput = request.form['username']
      passwordInput = request.form['password']

      if usernameInput == username and passwordInput == password:
         return render_template("login.html", message="Hello Stoffi05, the login was successful!")
      else:
         return render_template("login.html", message="Wrong Username or Password")
   else:
      return render_template("login.html")
   return render_template('login.html')