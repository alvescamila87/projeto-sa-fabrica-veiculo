# Projeto S.A: Fábrica de Veículos

## Sobre o projeto
Uma aplicação Java/Spring desenvolvida pela startup "Tech Solutions" em parceria com alunos do SENAI para uma fábrica / montadora automotiva, otimizando processos de produção e integrando dados para facilitar a tomada de decisões na era da Indústria 4.0.

## DER - Diagrama de entidade e relacionamento da S.A 
![image](https://github.com/alvescamila87/projeto-sa-fabrica-veiculo/assets/116912821/d8ac0ec4-98af-4382-9d7c-b46a86484fee)

## Test JSON
### Post PECA
```json
{
  "nome": "Motor",
  "descricao": "Motor de combustão interna para veículos",
  "quantidadePecas": 100
}
```
### Post VEICULO
```json
{
  "chassis": "ABC123456789DEF01",
  "modelo": "Sedan",
  "anoFabricacao": 2023,
  "cor": "Prata",
  "listaDePecasUtilizadasNoVeiculo": [
    {
      "idPeca": 1,
      "nome": "Motor",
      "descricao": "Motor de combustão interna para veículos",
      "quantidadePecas": 1
    },
    {
      "idPeca": 2,
      "nome": "Transmissão",
      "descricao": "Caixa de câmbio automática",
      "quantidadePecas": 1
    }
  ]
}
```

### Post ESTOQUE
```json
{
  "quantidadeDisponivel": 50,
  "peca": {
    "idPeca": 1
  }
}
```

## Tecnologias
* Java (Maven)
* Spring (Boot, JPA, Devtools)
* Swagger (OpenAI Doc)
* Banco de dados em memória (H2)
* JavaDoc
* Postman

## Swagger
![image](https://github.com/alvescamila87/projeto-sa-fabrica-veiculo/assets/116912821/b12a3227-ff7c-4308-885f-ee150def9cf1)


## Autor
* Camila Alves
