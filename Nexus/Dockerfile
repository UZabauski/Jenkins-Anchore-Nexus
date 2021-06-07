FROM sonatype/nexus3:3.30.1
LABEL maintainer="uzabauski@gmail.com"

COPY configs/nexus.properties nexus-data/etc/nexus.properties
COPY scripts/*  scripts/


#CMD [ "/bin/sh","scripts/provision.sh" ]