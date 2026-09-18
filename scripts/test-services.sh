#!/bin/bash
set -e
curl -fsS http://localhost:8080/auth-service/auth/health; echo
curl -fsS http://localhost:8081/user-service/users/health; echo
curl -fsS http://localhost:8082/item-service/items/health; echo
