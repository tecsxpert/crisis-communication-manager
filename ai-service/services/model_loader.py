from sentence_transformers import SentenceTransformer

model = None

def load_model():
    global model
    if model is None:
        print("Loading sentence-transformer model...")
        model = SentenceTransformer('all-MiniLM-L6-v2')
        print("Model loaded successfully!")

def get_model():
    return model