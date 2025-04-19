
CREATE TABLE IF NOT EXISTS word (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    term VARCHAR(255),
    formal_term VARCHAR(255),
    category VARCHAR(255),
    meaning TEXT
);
INSERT INTO  word (term, formal_term, category, meaning) VALUES
('AI', 'Artificial Intelligence', 'IT', '人工知能'),
('CEO', 'Chief Executive Officer', 'ビジネス', '最高経営責任者'),
('GDP', 'Gross Domestic Product', '一般', '国内総生産'),
('HTML', 'HyperText Markup Language', 'IT', 'Webページを作るための言語'),
('SWOT', 'SWOT Analysis', 'ビジネス', '強み・弱み・機会・脅威の分析手法'),
('Wi-Fi', 'Wireless Fidelity', '一般', '無線通信技術の一種'),
('URL', 'Uniform Resource Locator', 'IT', 'Webサイトのアドレス'),
('HR', 'Human Resources', 'ビジネス', '人事部門'),
('SNS', 'Social Networking Service', '一般', 'ネット上の交流サービス'),
('TOEIC', 'Test of English for International Communication', '英語', '英語の実用能力評価テスト');