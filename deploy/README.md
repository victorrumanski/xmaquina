# questitto
Mosquitto + Telegraf + QuestDB Easy-to-Deploy Stack for quick IoT Prototype Scenarios using Docker

## Components

### QuestDB
- UI available on port `http://localhost:9000`
- TCP/ UDP port on `tcp://localhost:9009` or `udp://localhost:9009`

### Mosquitto
- Minimal configuration to provide logs on `stdout` for `docker-compose logs`
- available on `tcp://localhost:1883`
- Add / Remove users in `mosquitto/config/passwd` file
- REQUIRED: encrypt the text passwords with this cmd from inside the cloud server
`docker run -it --rm -v $(pwd)/mosquitto/config:/mosquitto/config eclipse-mosquitto mosquitto_passwd -U /mosquitto/config/passwd`

### Telegraf
- based on [TIG Stack example in tiguitto](https://github.com/shantanoo-desai/tiguitto) the configuration remains same
- See / Adapt the `topics` array in the `telegraf.conf` file
- Remove / Adapt the `processors.regex` and `processors.enum` configuration in `telegraf.conf`


## Blog Post

Complete write up [here](https://shantanoo-desai.github.io/posts/technology/questitto_light_stack_for_iot/)




1- change the passwords in this file /mosquitto/config/passwd

2- run this cmd to encrypt the just changed passwords
docker run -it --rm -v $(pwd)/mosquitto/config:/mosquitto/config eclipse-mosquitto mosquitto_passwd -U /mosquitto/config/passwd

3- change the password for telegraf to login into mosquitto in telegraf.env file

4- change the password for questdb jdbc login in questdb/conf/server.conf

5- change the jdbc params to login into questdb from springboot in xmaquina.env file

to check logs: `docker compose logs xmaquina`

to get a shell inside a container: `docker exec -it xmaquina bash`



