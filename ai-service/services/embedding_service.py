from services.model_loader import get_model

def get_embedding(text):
    model = get_model()
    return model.encode([text])