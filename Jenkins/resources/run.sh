#!/usr/bin/env bash

echo "[INFO] Clean ${JENKINS_HOME}/init.groovy.d"

rm -f "${JENKINS_HOME}"/init.groovy.d/*
RUN_SCRIPT='/usr/local/bin/jenkins.sh'

if [ ! -z ${JENKINS_JAVA_OPTS+x} ]
then
  echo '[INFO] Change JAVA_OPTS to JENKINS_JAVA_OPTS'
  RUN_SCRIPT='/tmp/custom_jenkins.sh'
  touch "${RUN_SCRIPT}"
  sed 's/$JAVA_OPTS/$JENKINS_JAVA_OPTS/g' /usr/local/bin/jenkins.sh > \
      "${RUN_SCRIPT}"
  chmod +x "${RUN_SCRIPT}"
fi

echo '[INFO] Run jenkins.sh'
"${RUN_SCRIPT}"
