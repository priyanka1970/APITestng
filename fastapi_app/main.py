# fastapi_app/main.py

from fastapi import FastAPI, HTTPException, Depends
from pydantic import BaseModel
from typing import Dict
from fastapi.security import OAuth2PasswordBearer
import uuid

app = FastAPI()

# In‐memory “databases”
_USERS: Dict[str, str] = {}
_BOOKS: Dict[int, Dict] = {}

oauth2_scheme = OAuth2PasswordBearer(tokenUrl="login")


class SignupRequest(BaseModel):
    email: str
    password: str


class LoginRequest(BaseModel):
    email: str
    password: str


class BookRequest(BaseModel):
    name: str
    author: str
    published_year: int
    book_summary: str


# 1) Health check
@app.get("/health")
def health_check():
    return {"status": "ok"}


# 2) Signup endpoint
@app.post("/signup")
def signup(req: SignupRequest):
    if req.email in _USERS:
        raise HTTPException(status_code=400, detail="User already exists")
    _USERS[req.email] = req.password
    return {"message": "user created"}


# 3) Login endpoint → returns dummy access token
@app.post("/login")
def login(req: LoginRequest):
    stored_pw = _USERS.get(req.email)
    if stored_pw is None or stored_pw != req.password:
        raise HTTPException(status_code=401, detail="Invalid credentials")
    token = str(uuid.uuid4())
    return {"access_token": token, "token_type": "bearer"}


# Dependency to verify token presence (no real validation)
def get_current_token(token: str = Depends(oauth2_scheme)):
    if not token:
        raise HTTPException(status_code=401, detail="Not authenticated")
    return token


# 4) Create a book
@app.post("/books/")
def create_book(book: BookRequest, token: str = Depends(get_current_token)):
    new_id = len(_BOOKS) + 1
    entry = book.dict()
    entry["id"] = new_id
    _BOOKS[new_id] = entry
    return entry


# 5) Retrieve a book by ID
@app.get("/books/{book_id}")
def get_book(book_id: int, token: str = Depends(get_current_token)):
    book = _BOOKS.get(book_id)
    if not book:
        raise HTTPException(status_code=404, detail="Book not found")
    return book


# 6) Update book details
@app.put("/books/{book_id}")
def update_book(book_id: int, book: BookRequest, token: str = Depends(get_current_token)):
    if book_id not in _BOOKS:
        raise HTTPException(status_code=404, detail="Book not found")
    updated = book.dict()
    updated["id"] = book_id
    _BOOKS[book_id] = updated
    return updated


# 7) Delete a book
@app.delete("/books/{book_id}")
def delete_book(book_id: int, token: str = Depends(get_current_token)):
    if book_id not in _BOOKS:
        raise HTTPException(status_code=404, detail="Book not found")
    del _BOOKS[book_id]
    return {"message": "deleted"}
