<h1>Sistema de Cadastro de Cidadãos</h1>

<h2>Sobre</h2>
<p>Este projeto é uma aplicação Java desenvolvida para auxiliar a Prefeitura Municipal no gerenciamento de cidadãos, seus endereços e os serviços públicos que utilizam. O sistema utiliza Maven, JPA/Hibernate e MySQL.</p>

## Objetivo

- Cadastrar cidadãos com informações pessoais
- Relacionar cidadãos com seus endereços (1:N)
- Relacionar cidadãos com os serviços públicos utilizados (N:N)
- Permitir consultas personalizadas.

## Tecnologias Utilizadas

- Java
- Maven
- Hibernate (JPA)
- SQL (JPQL)
- Bean Validation (javax.validation)

## Funcionalidades Demonstradas

- Criação de cidadãos, endereços e serviço
- Criação de cidadãos com mais de um endereço
- Associação com serviços públicos
- Consultas:
  
    -Buscar por Id;
  
    -Buscar por nome;
  
   -Listar todos endereços;
  
    -Listar serviços ativos;
  
    -Buscar endereços por Id;
  
    -Buscar endereços e serviço por Id.

## Como Executar 

- Clone o projeto:
  
      git clone https://github.com/brunopedron14/projeto-cadastro-cidadaos.git

- Importe na IDE como Maven
- Execute a classe Main

## Principais Funcionalidades

- Inserção de dados - Aqui é onde crio os cidadãos, endereços e serviços.
![image](https://github.com/user-attachments/assets/ae90903a-dbd3-4a7d-a18d-ba33998d190e)

- Após a criação, setamos endereços e serviços para um determinado cidadão, graças aos relacionamentos.
![image](https://github.com/user-attachments/assets/1f164ee3-8617-4c34-bf6e-50ac83a8f3a5)

- Aqui eu crio o EntityManager e os 3 Storage que são criados para se comunicar com o banco de dados.
![image](https://github.com/user-attachments/assets/0d533717-0446-4264-a9d9-535d73a8b2bb)

- Nessa parte é onde acontece a comunicação com o banco de dados, onde posso cadastrar, deletar e atualizar os dados.
- 
![image](https://github.com/user-attachments/assets/5b13113b-ff68-4fb0-9723-6cc6fe3e5e2d)

## Consultas
- Consulta 1 - Busco por Id do cidadão e retorna seu CPF.

![image](https://github.com/user-attachments/assets/d6233d35-7016-4462-b76c-02fab4c65612) ![image](https://github.com/user-attachments/assets/86f44382-40cb-4ba2-bb6a-d04abc80e216)

- Consulta 2 - Faço uma busca pelo nome do cidadão e retorna seu CPF.

![image](https://github.com/user-attachments/assets/97160ff7-00b7-4c60-a91d-365c5002686a) ![image](https://github.com/user-attachments/assets/9d692d2e-fd1e-4504-a2f0-078151a254ea)

- Consulta 3 -  Uma lista de todos os endereços.

![image](https://github.com/user-attachments/assets/dc994e71-df5c-49d8-a79a-3ba24397623a) ![image](https://github.com/user-attachments/assets/22aaefcf-6f84-48b6-8548-2771e369f00e)

- Consulta 4 - Uma lista de todos os serviços ativos.

![image](https://github.com/user-attachments/assets/98111b29-d1a4-49c2-9a4f-0c9affc38cbe) ![image](https://github.com/user-attachments/assets/1496f2b6-6a5c-4376-adc7-420a16ff7441)

- Consulta 5 - Busco pelo Id do cidadão, me retornado seus endereços cadastrados.

![image](https://github.com/user-attachments/assets/af128089-75af-4d5a-83a1-75c515bf90a8) ![image](https://github.com/user-attachments/assets/eca04545-f365-43c1-9fad-b4099d71234a)

- Consulta 6 - Busco pelo Id do cidadão, assim me retornando serviço e endereço vinculado a esse Id.

![image](https://github.com/user-attachments/assets/f39e8943-6621-4d0d-beda-f011307b5597) ![image](https://github.com/user-attachments/assets/ec264f38-0971-4cdf-ba05-5563dea89905)














  
