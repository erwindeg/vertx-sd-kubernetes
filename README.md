# Example vertx app with kubernetes integration
## Deploy on openshift:
oc new-app codecentric/springboot-maven3-centos~https://github.com/erwindeg/vertx-sd-kubernetes --name="service1"
oc new-app codecentric/springboot-maven3-centos~https://github.com/erwindeg/vertx-sd-kubernetes --name="service2"

oc expose service/test1
oc expose service/test2
oc expose service/test3

## add role to view kubernetes api
oc policy add-role-to-group view system:serviceaccounts -n vertx


## Start build
oc start-build service1 -n vertx
oc start-build service2 -n vertx


