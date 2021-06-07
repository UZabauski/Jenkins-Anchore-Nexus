FROM jenkins/jenkins:2.296-jdk11
LABEL maintainer="uzabauski@gmail.com"

# Install docker in docker
ARG HOST_UID=1004
ARG HOST_GID=999

USER root

#RUN mkdir -p /etc/docker
#COPY resources/daemon.json /etc/docker/

RUN apt-get -y update && \
    apt-get -y install apt-transport-https ca-certificates curl gnupg-agent software-properties-common && \
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add - && \
    add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") $(lsb_release -cs) stable" && \
    apt-get update && \
    apt-get -y install docker-ce docker-ce-cli containerd.io

RUN usermod -u $HOST_UID jenkins
RUN groupmod -g $HOST_GID docker
RUN usermod -aG docker jenkins

VOLUME /var/lib/docker

# https://github.com/jenkinsci/docker/blob/master/README.md
ENV TRY_UPGRADE_IF_NO_MARKER=false

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc.yaml

COPY resources/plugins.txt /usr/share/jenkins/ref/plugins.txt

RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

COPY resources/casc.yaml /var/jenkins_home/casc.yaml
#COPY resources/jobdsl/*-configurator.jobdsl /tmp/
#COPY resources/jobdsl/*.groovy /tmp/
#COPY resources/properties/*.properties /tmp/resources/properties/
COPY resources/jenkins-system-groovy-scripts/*.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY resources/run.sh /usr/local/bin/run.sh

#ENTRYPOINT ["/sbin/tini", "--", "/usr/local/bin/run.sh"]
