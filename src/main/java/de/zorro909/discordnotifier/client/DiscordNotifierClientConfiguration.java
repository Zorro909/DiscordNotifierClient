package de.zorro909.discordnotifier.client;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.client.DefaultHttpClientConfiguration;
import io.micronaut.http.client.HttpClientConfiguration;
import io.micronaut.runtime.ApplicationConfiguration;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named(DiscordNotifierClientConfiguration.CLIENT_ID)
@ConfigurationProperties(DiscordNotifierClientConfiguration.PREFIX)
@Requires(property = DiscordNotifierClientConfiguration.PREFIX)
public class DiscordNotifierClientConfiguration extends HttpClientConfiguration {

    public static final String PREFIX = "discord.notifier.client";
    public static final String CLIENT_ID = "discord-notifier";

    private ConnectionPoolConfiguration connectionPoolConfiguration;

    private String username;
    private String password;

    public DiscordNotifierClientConfiguration() {
        this.connectionPoolConfiguration = new ConnectionPoolConfiguration();
    }

    @Inject
    public DiscordNotifierClientConfiguration(ConnectionPoolConfiguration connectionPoolConfiguration, ApplicationConfiguration applicationConfiguration) {
        super(applicationConfiguration);
        this.connectionPoolConfiguration = connectionPoolConfiguration;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public ConnectionPoolConfiguration getConnectionPoolConfiguration() {
        return connectionPoolConfiguration;
    }
}
