from flask import Flask
from flask import render_template

app = Flask(__name__)

@app.route("/")
@app.route('/index')

def home():
    users["Christof"] = True
    users["Flo"] = False
    return render_template('index.html', title='Welcome', users)