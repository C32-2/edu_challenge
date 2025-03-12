from flask import Flask, render_template
from flask_sqlalchemy import SQLAlchemy
import datetime

app = Flask(__name__)

if __name__ == '__main__':
    app.run(debug=True)