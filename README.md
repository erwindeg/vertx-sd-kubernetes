# Example vertx app with kubernets integration
## Deploy on openshift:
oc new-app codecentric/springboot-maven3-centos~https://github.com/erwindeg/vertx-sd-kubernetes

## add role to view kubernetes api
oc policy add-role-to-group view system:serviceaccounts -n vertx

## Start build
oc start-build vertx-sd-kubernetes -n vertx


