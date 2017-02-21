# Example vertx app with kubernets integration
## Deploy on openshift:
oc new-app codecentric/springboot-maven3-centos~https://github.com/erwindeg/vertx-sd-kubernetes

## Start build
oc start-build vertx-sd-kubernetes -n vertx
