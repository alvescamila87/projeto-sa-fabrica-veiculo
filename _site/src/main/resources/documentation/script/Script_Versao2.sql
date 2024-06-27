CREATE TABLE Pecas (
    ID_Peca INT AUTO_INCREMENT,
    Nome VARCHAR(255) NOT NULL,
    Descricao TEXT,
    Quantidade INT DEFAULT 1,
    PRIMARY KEY (ID_Peca)
);

CREATE TABLE Producao (
    ID_Producao INT AUTO_INCREMENT,
    DataHora DATETIME NOT NULL,
    ID_Peca INT NOT NULL,
    QuantidadeProduzida INT NOT NULL,
    Estado VARCHAR(100),
    PRIMARY KEY (ID_Producao),
    FOREIGN KEY (ID_Peca) REFERENCES Pecas(ID_Peca)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Estoque (
    ID_Estoque INT AUTO_INCREMENT,
    ID_Peca INT NOT NULL,
    QuantidadeDisponivel INT NOT NULL,
    PRIMARY KEY (ID_Estoque),
    FOREIGN KEY (ID_Peca) REFERENCES Pecas(ID_Peca)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Equipamentos (
    ID_Equipamento INT AUTO_INCREMENT,
    TipoEquipamento VARCHAR(255) NOT NULL,
    Descricao TEXT,
    Estado VARCHAR(100),
    PRIMARY KEY (ID_Equipamento)
);

CREATE TABLE Manutencao (
    ID_Manutencao INT AUTO_INCREMENT,
    ID_Equipamento INT NOT NULL,
    DataHoraInicio DATETIME NOT NULL,
    DataHoraFim DATETIME,
    DescricaoServico TEXT,
    Estado VARCHAR(100),
    PRIMARY KEY (ID_Manutencao),
    FOREIGN KEY (ID_Equipamento) REFERENCES Equipamentos(ID_Equipamento)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Qualidade (
    ID_Inspecao INT AUTO_INCREMENT,
    ID_Producao INT NOT NULL,
    DataHora DATETIME NOT NULL,
    Resultado VARCHAR(100),
    Comentarios TEXT,
    PRIMARY KEY (ID_Inspecao),
    FOREIGN KEY (ID_Producao) REFERENCES Producao(ID_Producao)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Usuarios (
    ID_Usuario INT AUTO_INCREMENT,
    Nome VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Senha VARCHAR(255) NOT NULL,
    Perfil ENUM('administrador', 'operador', 'gestor') NOT NULL,
    PRIMARY KEY (ID_Usuario)
);

CREATE TABLE Veiculos (
    Chassi VARCHAR(17) NOT NULL,
    Modelo VARCHAR(255) NOT NULL,
    Ano INT,
    Cor VARCHAR(100),
    PRIMARY KEY (Chassi)
);

CREATE TABLE Veiculos_Pecas (
    Chassi VARCHAR(17) NOT NULL,
    ID_Peca INT NOT NULL,
    PRIMARY KEY (Chassi, ID_Peca),
    FOREIGN KEY (Chassi) REFERENCES Veiculos(Chassi)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ID_Peca) REFERENCES Pecas(ID_Peca)
        ON DELETE RESTRICT ON UPDATE CASCADE
);
