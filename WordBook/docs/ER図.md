```mermaid
erDiagram
    USERS {
        INT id PK
        VARCHAR name
        VARCHAR password
        VARCHAR book
    }

    WORDS {
        INT id PK
        VARCHAR term
        VARCHAR formal_term
        VARCHAR category
        VARCHAR meaning
        VARCHAR book FK
    }

    USERS ||--o{ WORDS : owns
```