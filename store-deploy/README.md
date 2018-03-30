# store-deploy
```
docker-compose up -d
```

## Specify domain
The containers will be deployed always in the same network configuration (`172.11.0.0/16`). Add entry in the file `/etc/hosts` with the domain of the store to: connect the nginx proxy with this domain:
```
# Store configuration
172.11.0.3 local.store.com
```