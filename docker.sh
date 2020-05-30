docker stop $(docker ps -a -q)
docker kill $(docker ps -q)
docker rmi $(docker images -q)
docker-compose up  --build  --force-recreate
