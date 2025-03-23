import os
from dotenv import load_dotenv

basedir = os.path.abspath(os.path.dirname(__file__)) + '\\.env.server'
load_dotenv(basedir)


class Config:
    SQLALCHEMY_DATABASE_URI = (f"postgresql+psycopg2://{os.environ.get('POSTGRES_USERNAME')}:"
                               f"{os.environ.get('POSTGRES_PASSWORD')}@{os.environ.get('HOST')}:"
                               f"{os.environ.get('PORT')}/{os.environ.get('DB_NAME')}")
    SQLALCHEMY_TRACK_MODIFICATIONS = False
