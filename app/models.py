from app import db
from datetime import datetime

from app import db
from datetime import datetime

class Users(db.Model):
    __tablename__ = 'users'

    user_id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(32), unique=True, nullable=False)
    password_hash = db.Column(db.Text, nullable=False)
    created_at = db.Column(db.DateTime, default=datetime.utcnow)

    # Связь с Questions
    questions = db.relationship('Questions', backref='author', lazy=True)

    def __repr__(self):
        return f'<User {self.username}>'

class QuizTopics(db.Model):
    __tablename__ = 'quiz_topics'

    topic_id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(64), unique=True, nullable=False)

    # Связь с Quizzes и Questions
    quizzes = db.relationship('Quizzes', backref='topic', lazy=True)

    def __repr__(self):
        return f'<QuizTopic {self.name}>'

class Questions(db.Model):
    __tablename__ = 'questions'

    question_id = db.Column(db.Integer, primary_key=True)
    topic_id = db.Column(db.Integer, db.ForeignKey('quiz_topics.topic_id'), nullable=True)
    author_id = db.Column(db.Integer, db.ForeignKey('users.user_id'), nullable=True)
    question_text = db.Column(db.Text, nullable=False)
    answer1 = db.Column(db.Text, nullable=False)
    answer2 = db.Column(db.Text, nullable=False)
    answer3 = db.Column(db.Text, nullable=False)
    answer4 = db.Column(db.Text, nullable=False)
    correct_answer = db.Column(db.Integer, nullable=False)
    difficulty = db.Column(db.Integer)
    created_at = db.Column(db.DateTime, default=datetime.utcnow)

    def __repr__(self):
        return f'<Question {self.question_text[:30]}>'

class Quizzes(db.Model):
    __tablename__ = 'quizzes'

    quiz_id = db.Column(db.Integer, primary_key=True)
    topic_id = db.Column(db.Integer, db.ForeignKey('quiz_topics.topic_id'), nullable=False)
    created_at = db.Column(db.DateTime, default=datetime.utcnow)

    # Связь с QuizQuestions
    quiz_questions = db.relationship('QuizQuestions', backref='quiz', lazy=True)

    def __repr__(self):
        return f'<Quiz {self.quiz_id}>'

class QuizQuestions(db.Model):
    __tablename__ = 'quiz_questions'

    quiz_id = db.Column(db.Integer, db.ForeignKey('quizzes.quiz_id'), primary_key=True)
    question_id = db.Column(db.Integer, db.ForeignKey('questions.question_id'), primary_key=True)

    def __repr__(self):
        return f'<QuizQuestion quiz_id={self.quiz_id} question_id={self.question_id}>'