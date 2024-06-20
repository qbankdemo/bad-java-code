#!/bin/zsh
set -eux

usage() {
  echo "Usage: $0 <rule>"
  echo "Fetches issues for the specified rule from SonarCloud."
  echo "Example:"
  echo "$0 java:S106"
}

# Check if "rule" parameter is provided
if [ -z "$1" ]; then
  echo "Error: No rule parameter provided."
  usage
  exit 1
fi

rule=$1
host=${SONAR_HOST:-https://sonarcloud.io}
curl \
  --header "Authorization: Bearer ${SONAR_TOKEN}" \
  "${host}/api/issues/search?organization=pixee&componentKeys=pixee_bad-java-code&rules=${rule}"
