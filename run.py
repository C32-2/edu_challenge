from app import create_app, db
from app.models import Users

app = create_app()

with app.app_context():
    new_user = Users(username="vasya", password_hash="hashed_password")
    db.session.add(new_user)
    db.session.commit()