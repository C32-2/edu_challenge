from yandex_cloud_ml_sdk import YCloudML
from dotenv import load_dotenv
import os
import re

BASE_DIR = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
env_path = os.path.join(BASE_DIR, ".env.ai")

load_dotenv(env_path)

yandexgpt_api_key = os.getenv('YANDEXGPT_API_KEY')
yandexgpt_folder_id = os.getenv('YANDEXGPT_FOLDER_ID')
model_name = os.getenv('MODEL_NAME')
model_version = os.getenv('MODEL_VERSION')
temperature = os.getenv('TEMPERATURE')

class DifficultyLevel:
    NOVICE = 1
    INTERMEDIATE = 2
    ADVANCED = 3
    EXPERT = 4
    SCIENTIST = 5

difficulty_mapping = {
    DifficultyLevel.NOVICE: 'Новичок',
    DifficultyLevel.INTERMEDIATE: 'Среднячок',
    DifficultyLevel.ADVANCED: 'Хорошист',
    DifficultyLevel.EXPERT: 'Эксперт',
    DifficultyLevel.SCIENTIST: 'Учёный'
}

sdk = YCloudML(
    folder_id=yandexgpt_folder_id,
    auth=yandexgpt_api_key
)
model = sdk.models.completions(model_name=model_name, model_version=model_version)
model = model.configure(temperature=float(temperature))


def parse_response(result):
    text = result.text.strip()
    lines = [line.strip().replace('*', '') for line in text.split('\n') if line.strip()]

    if len(lines) < 6:
        return {'error': 'Ответ не соответствует ожидаемому формату. Невозможно разобрать текст.'}

    question = {
        'question': lines[0],
        'answer1': lines[1],
        'answer2': lines[2],
        'answer3': lines[3],
        'answer4': lines[4],
        'correct_answer': 0
    }

    match = re.search(r'Правильный ответ:\s*(\d)', lines[5])
    if match:
        question['correct_answer'] = int(match.group(1))
    else:
        return {'error': 'Не удалось извлечь правильный ответ из текста.'}

    return question


def generate_question(topic, difficulty):
    result = model.run(
        [
            {"role": "system", "text": " "},
            {
                "role": "user",
                "text": f"Придумай вопрос для викторины на тему \"{topic}\" уровня сложности {difficulty_mapping[difficulty]}. "
                        f"Сформулируй вопрос с 4 вариантами ответов, один из которых правильный. "
                        f"Ответы должны быть в формате:\n1) Вариант 1\n2) Вариант 2\n3) Вариант 3\n4) Вариант 4\n"
                        f"После вариантов ответа укажи правильный ответ в формате: 'Правильный ответ: X', "
                        f"где X — номер правильного ответа. Пожалуйста, не добавляй никакого дополнительного текста.",
            },
        ]
    )
    return parse_response(result)


print(generate_question('История права зарубежных стран', 4))
