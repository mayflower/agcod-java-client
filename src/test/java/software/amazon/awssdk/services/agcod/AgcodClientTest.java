package software.amazon.awssdk.services.agcod;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.agcod.internal.AcceptJsonInterceptor;
import software.amazon.awssdk.services.agcod.model.*;

import java.net.URI;

public class AgcodClientTest {
    @Rule
    public WireMockRule mockServer = new WireMockRule(0);

    private static final String partnerId = "Abc12";
    private static final String creationRequestId = "Abc121234567890";
    private AgcodClient agcodClient;

    @Before
    public void setup() {
        agcodClient = AgcodClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("accessKey", "secretKey")))
                .overrideConfiguration(o -> o.addExecutionInterceptor(new AcceptJsonInterceptor()))
                .region(Region.EU_WEST_1)
                .endpointOverride(URI.create("http://localhost:" + mockServer.port()))
                .build();
    }


    @Test
    public void createGiftCard_shouldIncludeJsonHeader() {
        stubForCreateSuccess(getSuccessfulBody());

        CreateGiftCardResponse response = agcodClient.createGiftCard(CreateGiftCardRequest.builder()
                .creationRequestId(creationRequestId)
                .partnerId(partnerId)
                .value(AgcodValue.builder()
                        .amount(10F)
                        .currencyCode(CurrencyCode.EUR)
                        .build())
                .build());

        assertRequestHeader();
    }

    @Test
    public void createGiftCard_success() {
        stubForCreateSuccess(getSuccessfulBody());

        CreateGiftCardResponse response = agcodClient.createGiftCard(CreateGiftCardRequest.builder()
                .creationRequestId(creationRequestId)
                .partnerId(partnerId)
                .value(AgcodValue.builder()
                        .amount(10F)
                        .currencyCode(CurrencyCode.EUR)
                        .build())
                .build());

        assertSucessCreateResponse(response);
    }

    @Test(expected = ResendErrorException.class)
    public void createGiftCard_resendError() {
        stubForResendError();

        CreateGiftCardResponse response = agcodClient.createGiftCard(CreateGiftCardRequest.builder()
                .creationRequestId(creationRequestId)
                .partnerId(partnerId)
                .value(AgcodValue.builder()
                        .amount(10F)
                        .currencyCode(CurrencyCode.EUR)
                        .build())
                .build());

        assertSucessCreateResponse(response);
    }

    @Test
    public void createGiftCard_resendErrorThenSuccess() {
        stubForResendError();
        stubForCreateSuccess(getSuccessfulBody());

        CreateGiftCardResponse response = agcodClient.createGiftCard(CreateGiftCardRequest.builder()
                .creationRequestId(creationRequestId)
                .partnerId(partnerId)
                .value(AgcodValue.builder()
                        .amount(10F)
                        .currencyCode(CurrencyCode.EUR)
                        .build())
                .build());

        assertSucessCreateResponse(response);
    }

    private void assertRequestHeader() {
        verify(postRequestedFor(urlEqualTo("/CreateGiftCard"))
                .withHeader("Accept", containing("application/json"))
                .withHeader("Content-Type", containing("application/json")));
    }

    private void assertSucessCreateResponse(CreateGiftCardResponse response) {
        assertEquals(AgcodResponseStatus.SUCCESS, response.status());
        assertNotNull(response.creationRequestId());
        assertNotNull(response.gcClaimCode());
        assertNotNull(response.gcExpirationDate());
        assertNotNull(response.gcId());
    }

    private String getSuccessfulBody() {
        return "{\"cardInfo\":{\"cardNumber\":null,\"cardStatus\":\"Fulfilled\",\"expirationDate\":null,\"value\":{\"amount\":20.0,\"currencyCode\":\"EUR\"}}," +
                "\"creationRequestId\":\"Abc121234567890\"," +
                "\"gcClaimCode\":\"ABCD-F3PGP2-WXYZ\"," +
                "\"gcExpirationDate\":\"Wed Dec 19 22:59:59 UTC 2029\"," +
                "\"gcId\":\"ABCQ7GLE1UKXYZ\"," +
                "\"status\":\"SUCCESS\"}";
    }

    private void stubForCreateSuccess(String body) {
        stubFor(any(urlEqualTo("/CreateGiftCard"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("Accept", "application/json")
                        .withBody(body)));
    }

    private void stubForResendError() {
        WireMock.reset();

        stubFor(post(urlEqualTo("/CreateGiftCard"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("x-amzn-ErrorType", "F400")
                        .withBody("\"errorCode\":\"F400\"")));
    }
}
