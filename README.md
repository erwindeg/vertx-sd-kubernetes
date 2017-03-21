# Preparation
You need the Openshift client (OC) for Windows or Mac: v1.5.0  
You need Docker app 1.13.0  
We will be installing Openshift v3.4.1.5  

# start openshift locally with docker
oc cluster up --image=registry.access.redhat.com/openshift3/ose --version=v3.4.1.5 --create-machine

# login
oc login -u system:admin

# create a new project
oc new-project vertx
oc policy add-role-to-user edit system -n vertx

## add role to view kubernetes api
oc policy add-role-to-group view system:serviceaccounts -n vertx

# Example vertx app with kubernetes integration
## Deploy on openshift:

oc new-app codecentric/springboot-maven3-centos~https://github.com/erwindeg/vertx-sd-kubernetes --name="service1"
oc new-app https://github.com/erwindeg/vertx-sd-kubernetes --name="service2" --image-stream=springboot-maven3-centos

oc expose service/service1
oc expose service/service2


## Start build
oc start-build service1 -n vertx
oc start-build service2 -n vertx


# starting and stopping
docker-machine start openshift
docker-machine stop openshift

# remove openshift
docker-machine rm -f openshift




