#!/bin/bash
set -e
for s in auth-service user-service item-service; do
  echo "=== Building $s ==="
  (cd "$s" && mvn clean package)
done
echo "=== WAR files ==="
find . -path "*/target/*.war" -type f -print
