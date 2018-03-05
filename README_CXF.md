maven:

ws-build:
${workspace_loc:/fluidity} --> goals: clean install

ws-run:
${workspace_loc:/fluidity} --> goals: org.apache.tomcat.maven:tomcat7-maven-plugin:2.2:run
