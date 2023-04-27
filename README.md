# aluraflix-ms-final


Projeto de cadastro de video com categorias, utilizando microsserviços está utilizando um gateway e o server eureka para o gerenciamento de microsserviço, 
usar porta 8082/videos-ms(nome do micro serviço)/"end point"  para atender as requisições 

para o rabbit funcionar rodar o comando docker :
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management
