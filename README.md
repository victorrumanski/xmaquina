# X-Maquina
Sample IoT project using SpringBoot + MQTT + Telegraf + QuestDB + Nginx + Docker-Compose deployment
![mqtt](https://github.com/victorrumanski/xmaquina/assets/50980148/33c79f64-df56-40db-a640-27002186cea8)

![image](https://github.com/victorrumanski/xmaquina/assets/50980148/555c0bc8-fd0d-495d-abb5-d62846a338e9)

## First steps
1 - Create a linux server at Linode

2 - Put your public key here deploy/docker-cloud-init.yaml on the line that says `my_ssh_public_key_here`

3 - on User Data section paste the contents of deploy/docker-cloud-init.yaml, you should get a ubuntu server with docker installed and a non-root user called victor. 

## After linode server is running
1 - login as victor user

2 - git clone this repo .

1- change the passwords in this file /mosquitto/config/passwd

2- run this cmd to encrypt the just changed passwords
docker run -it --rm -v $(pwd)/mosquitto/config:/mosquitto/config eclipse-mosquitto mosquitto_passwd -U /mosquitto/config/passwd

3- change the password for telegraf to login into mosquitto in telegraf.env file

4- change the password for questdb jdbc login in questdb/conf/server.conf

5- change the jdbc params to login into questdb from springboot in xmaquina.env file

to check logs: `docker compose logs xmaquina`

to get a shell inside a container: `docker exec -it xmaquina bash`

now you can access your IP address and check the dashboard screen




