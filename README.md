# start openshift locally with docker
oc cluster up --image=registry.access.redhat.com/openshift3/ose --create-machine

# login
oc login -u system:admin

# create a new project
create a new project in the web gui
oc new-project vertx

# run docker images as root
oc adm policy add-cluster-role-to-user cluster-admin developer

docker-machine start openshift
docker-machine stop openshift

# remove openshift
docker-machine rm -f openshift

# Example vertx app with kubernetes integration
## Deploy on openshift:
<<<<<<< HEAD


oc new-app codecentric/springboot-maven3-centos~https://github.com/erwindeg/vertx-sd-kubernetes --name="service1"
oc new-app codecentric/springboot-maven3-centos~https://github.com/erwindeg/vertx-sd-kubernetes --name="service2"

oc expose service/service1
oc expose service/service2
oc expose service/service3
=======
oc new-app codecentric/springboot-maven3-centos~https://github.com/erwindeg/vertx-sd-kubernetes --name="service1"  
oc new-app codecentric/springboot-maven3-centos~https://github.com/erwindeg/vertx-sd-kubernetes --name="service2"  

oc expose service/test1  
oc expose service/test2  
oc expose service/test3  
>>>>>>> 3fb9e9b23f618c3d82992f130699ef0b11615360

## add role to view kubernetes api
oc policy add-role-to-group view system:serviceaccounts -n vertx  


## Start build
oc start-build service1 -n vertx  
oc start-build service2 -n vertx  



