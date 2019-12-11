package software.amazon.awssdk.services.agcod.internal;

import java.net.URI;

public enum AgcodHost {

    /**
     * North America
     * (US, CA, MX)
     * (use region us-east-1)
     */
    SANDBOX_NORTH_AMERICA("https://agcod-v2-gamma.amazon.com"),

    /**
     * European Union
     * (IT, ES, DE, FR, UK, TR, UAE)
     * (use region eu-west-1)
     */
    SANDBOX_EU("https://agcod-v2-eu-gamma.amazon.com"),

    /**
     * Far East
     * (JP, AU)
     * (use region us-west-2)
     */
    SANDBOX_FAR_EAST("https://agcod-v2-fe-gamma.amazon.com"),

    /**
     * North America
     * (US, CA, MX)
     * (use region us-east-1)
     */
    PROD_NORTH_AMERICA("https://agcod-v2.amazon.com"),

    /**
     * European Union
     * (IT, ES, DE, FR, UK, TR, UAE)
     * (use region eu-west-1)
     */
    PROD_EU("https://agcod-v2-eu.amazon.com"),

    /**
     * Far East
     * (JP, AU)
     * (use region us-west-2)
     */
    PROD_FAR_EAST("https://agcod-v2-fe.amazon.com");

    private String url;

    AgcodHost(final String url) {
        this.url = url;
    }

    public URI getUri() {
        return URI.create(url);
    }
}
