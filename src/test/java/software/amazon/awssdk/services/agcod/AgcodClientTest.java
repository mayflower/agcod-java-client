
package software.amazon.awssdk.services.agcod;

import org.junit.Before;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.agcod.internal.AcceptJsonInterceptor;
import software.amazon.awssdk.services.agcod.internal.AgcodHost;

public class AgcodClientTest {
    private static final String _accessKey = "accessKey";
    private static final String _secretKey = "secretKey";
    private static final String _partnerId = "Abc123";
    private static AgcodClient _client;

    @Before
    public void setup() {
        initClient();
    }

//    @Test
//    public void successfulCreateGiftCardRequestTest() {
//        CreateGiftCardResponse response = _client.createGiftCard(CreateGiftCardRequest.builder()
//                .creationRequestId("F0000")
//                .partnerId(_partnerId)
//                .value(AgcodValue.builder()
//                        .amount(0.01F)
//                        .currencyCode(CurrencyCode.EUR)
//                        .build())
//                .build());
//
//        assertNotNull(response.gcClaimCode());
//    }
//
//    @Test(expected = ResendErrorException.class)
//    public void resendErrorTest() {
//            CreateGiftCardResponse response = _client.createGiftCard(CreateGiftCardRequest.builder()
//                    .creationRequestId("F4000")
//                    .partnerId(_partnerId)
//                    .value(AgcodValue.builder()
//                            .amount(10F)
//                            .currencyCode(CurrencyCode.EUR)
//                            .build())
//                    .build());
//    }
//
//    @Test
//    public void cancelGiftCardRequestTest() {
//        CancelGiftCardResponse response = _client.cancelGiftCard(CancelGiftCardRequest.builder()
//                .creationRequestId("F0000")
//                .partnerId(_partnerId)
//                .build());
//
//        assertEquals(response.status(), Status.SUCCESS);
//    }
//
//    @Test
//    public void getAvailableFundsRequestTest() {
//        GetAvailableFundsResponse response = _client.getAvailableFunds(GetAvailableFundsRequest.builder()
//                .partnerId(_partnerId)
//                .build());
//
//        assertEquals(response.status(), Status.SUCCESS);
//        assertNotNull(response.availableFunds());
//        assertTrue(response.timestamp() instanceof Instant);
//    }

    private void initClient() {
        _client = AgcodClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(_accessKey, _secretKey)))
                .region(Region.EU_WEST_1)
                .endpointOverride(AgcodHost.SANDBOX_EU.getUri())
                .overrideConfiguration(o -> o
                        .addExecutionInterceptor(new AcceptJsonInterceptor())
                        .retryPolicy(AgcodRetryPolicy.defaultPolicy()))
                .build();
    }
}
