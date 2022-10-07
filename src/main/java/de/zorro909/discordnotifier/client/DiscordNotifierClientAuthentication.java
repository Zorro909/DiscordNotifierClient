package de.zorro909.discordnotifier.client;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;

@Filter("/discord/notifier/api/**")
public class DiscordNotifierClientAuthentication implements HttpClientFilter {

    private DiscordNotifierClientConfiguration configuration;

    @Inject
    public DiscordNotifierClientAuthentication(DiscordNotifierClientConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        return chain.proceed(request.basicAuth(configuration.getUsername(), configuration.getPassword()));
    }

}
