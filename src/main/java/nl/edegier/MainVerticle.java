package nl.edegier;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by Erwin on 21/02/2017.
 */
public class MainVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new MainVerticle());
    }

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route("/*").handler(this::helloWorld);
        server.requestHandler(router::accept).listen(8080);
    }

    private void helloWorld(RoutingContext routingContext) {
        routingContext.response().end("hello world erwin");
    }
}
