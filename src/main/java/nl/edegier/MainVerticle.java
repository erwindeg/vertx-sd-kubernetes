package nl.edegier;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Erwin on 21/02/2017.
 */
public class MainVerticle extends AbstractVerticle {

    public static void main(String[] args) throws UnknownHostException {
        String dockerIp = InetAddress.getLocalHost().getHostAddress();
        VertxOptions options = new VertxOptions();
        System.out.println("Bind to "+dockerIp);
        options.setClusterHost(dockerIp);
        Vertx.clusteredVertx(options, result -> {
            Vertx vertx = result.result();
            vertx.deployVerticle(new MainVerticle());
        });
    }

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route("/hello").handler(this::helloWorld);


        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        BridgeOptions options = new BridgeOptions();
        options.addInboundPermitted(new PermittedOptions().setMatch(new JsonObject()));
        options.addOutboundPermitted(new PermittedOptions().setMatch(new JsonObject()));
        sockJSHandler.bridge(options);

        router.route("/eventbus/*").handler(sockJSHandler);

        router.route("/*").handler(StaticHandler.create());

        server.requestHandler(router::accept).listen(8080);

        vertx.setPeriodic(1000, t -> {
            try {
                vertx.eventBus().publish("channel1", new JsonObject().put("message","hello from "+InetAddress.getLocalHost().getHostAddress()));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            System.out.println("send");
        });

    }

    private void helloWorld(RoutingContext routingContext) {
        routingContext.response().end("hello world erwin2");
    }
}
