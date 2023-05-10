from flask import Flask
from flask import render_template

app = Flask(__name__)


@app.route("/")
@app.route("/index")
def home():
    users=[{
            "name":"Christof", 
            "formel":True
        },
        {
            "name":"Matthias",
            "formel":False
        }]
    return render_template('index.html', title='Willkommen', users=users)