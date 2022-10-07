package de.zorro909.discordnotifier.client;

import de.zorro909.discordnotifier.api.DiscordNotificationDto;
import de.zorro909.discordnotifier.api.SubmitNotificationDto;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.HttpVersion;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.Micronaut;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Client(value = DiscordNotifierClientConfiguration.CLIENT_ID, httpVersion = HttpVersion.HTTP_2_0)
public interface DiscordNotifierClient {

    @SingleResult
    @Post(uri ="/notification", consumes = "application/json", produces = "application/json")
    Mono<DiscordNotificationDto> submitNotification(SubmitNotificationDto notification);

    default Flux<DiscordNotificationDto> submitNotifications(SubmitNotificationDto... notifications) {
        return Flux.fromArray(notifications).flatMap(this::submitNotification);
    }

    @SingleResult
    @Get(uri = "/notification/{id}", produces = "application/json")
    Mono<DiscordNotificationDto> fetchNotification(Long id);

    default Flux<DiscordNotificationDto> fetchNotifications(List<Long> ids) {
        return Flux.fromIterable(ids).flatMap(this::fetchNotification);
    }

}
