mvn clean package
sudo docker build -t atividade/postgres ./postgres
sudo docker run --name db -p 5433:5432 -d atividade/postgres

#sudo docker build -t atividade/app .
#sudo docker run --name app -p 8080:8080 -p 4848:4848 --link db:host-banco atividade/app
