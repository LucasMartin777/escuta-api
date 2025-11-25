# 🎵📸 Escuta — Backend  
Um aplicativo que combina **Spotify + Instagram** em um único lugar:  
streaming de músicas, criação de playlists, perfis, seguidores e interação social.

Backend desenvolvido em **Java + Spring Boot**, com segurança via **JWT + BCrypt**, documentação pelo **Swagger**, e banco modelado para suportar toda a experiência social/musical.

---

## 🚀 Tecnologias utilizadas

- **Java 21**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **JWT (Auth0)**
- **BCrypt Password Encoder**
- **Swagger / SpringDoc**
- **MySQL**
- **Lombok**
- **Maven**
- **Lovable (Frontend)**

---

## 🛡️ Autenticação & Segurança

O projeto utiliza:

- **BCrypt** para criptografia de senhas  
- **JWT** para autenticação stateless  
- Filtros para autorização  
- Regras de acesso por endpoint

---

## 🧩 Modelagem do Banco de Dados

A aplicação utiliza uma estrutura relacional pensada para unir funcionalidades musicais e sociais.  
Principais entidades:

- **User_Login** → credenciais e informações sensíveis  
- **User_Perfil** → informações públicas do perfil  
- **Music** → músicas enviadas ou vinculadas ao usuário  
- **Album** → agrupamento de músicas  
- **Playlist** → playlists criadas pelo usuário  
- **Playlist_Music** → relacionamento entre playlist e músicas  
- **Follow_Profile** → sistema de seguidores  

A modelagem foi construída para permitir:

- Feed social entre perfis  
- Sistema de seguir outros usuários  
- Upload e associação de músicas  
- Criação de playlists públicas ou privadas  
- Relação entre álbuns e artistas  

---

## 📁 Estrutura de Pacotes

A organização segue boas práticas do Spring Boot:

- **controller** → entrada da API  
- **service** → regras de negócio  
- **repository** → acesso ao banco  
- **dto** → objetos de transferência  
- **mapper** → conversão entre Entity e DTO  
- **security** → autenticação e autorização  
- **config** → configurações gerais  

---

## 📘 Documentação da API

Toda a API está documentada automaticamente pelo **Swagger / SpringDoc**:

http://localhost:8080/swagger-ui/index.html

---

## ▶️ Como rodar o projeto

### 1. Configurar variáveis de ambiente
Defina as credenciais do banco e chaves JWT:

DATASOURCE_URL
DATASOURCE_USERNAME
DATASOURCE_PASSWORD
JWT_SECRET


### 3. Rodar a aplicação

mvn spring-boot:run


### 3. Rodar a aplicação

mvn spring-boot:run


---

## 🌟 Objetivo do Projeto

Construir um backend sólido que permita:

- Postar músicas, criar álbuns e playlists  
- Seguir perfis e interagir socialmente  
- Autenticação segura e escalável  
- Uma API completa para ser consumida pelo frontend (Lovable)  

---

## 📌 Roadmap

- Upload de imagens e áudio  
- Player com streaming real  
- Sistema de comentários  
- Notificações em tempo real  
- Recomendação de músicas  
- Feed social aprimorado  

---

## 📜 Licença

Projeto desenvolvido para fins de estudo, aberto para melhorias.



