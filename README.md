# Imperio Gateway

### Aim

Imperio Gateway is service meant to process and manage incoming conn, WS and messaging sockets into proper service requests and respond with these.

### Functional Testing
Since this is a gateway application, the purpose of it is to process and wait for requests from other services. It is recommended to have them working when testing.

Tests are done via Skaffold.

1.) Run docker, and kubernetes cluster and have it configured in `~/.kube/config`. Rancher desktop is recommended as it runs both. Important note, is to have Traefik disabled here.

2.) Change [application.yml](src/main/resources/application.yml) to match current infrastructure.

3.) Look for istio gateway external ip. Get it by using use kubectl get svc -n istio-system
When IP is obtained, edit `/etc/hosts` and add `<ip> 	x.imperio` where `x` is name of service host, as example`192.168.0.227	gateway.imperio`

3.) Hit `skaffold dev` command and wait for spring app to start in pod

There is <b>no use</b> to run app locally as it is depentand on cloud infrastrucuture. 
### Cloud Deployment
1.) Build docker image

2.) Ensure correct kubeconfig and proper profile is chosen by using `kubectl config get-context` and `kubectl config use-context <name>`

3.) Apply [imperio-linode-services](imperio-linode-services.yaml) file via kubectl `apply -f`
