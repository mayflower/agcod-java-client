
package software.amazon.awssdk.services.agcod;

import org.junit.Before;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.agcod.internal.AcceptJsonInterceptor;
import software.amazon.awssdk.services.agcod.internal.AgcodHost;
import software.amazon.awssdk.services.agcod.model.*;

import java.time.Instant;
import java.util.UUID;

import static org.junit.Assert.*;

public class AgcodClientRealWireTest {
    private static final String accessKey = "accessKey";
    private static final String secretKey = "secretKey";
    private static final String partnerId = "Abc123";
    private static AgcodClient client;

//    @Before
//    public void setup() {
//        initClient();
//    }
//
//    @Test
//    public void successfulCreateGiftCardRequestTest() {
//        CreateGiftCardResponse response = client.createGiftCard(CreateGiftCardRequest.builder()
//                //.creationRequestId("F0000")
//                .creationRequestId(generateCreationRequestId())
//                .partnerId(partnerId)
//                .value(AgcodValue.builder()
//                        .amount(10F)
//                        .currencyCode(CurrencyCode.EUR)
//                        .build())
//                .build());
//
//        assertNotNull(response.gcClaimCode());
//    }
//
//    @Test(expected = ResendErrorException.class)
//    public void resendErrorTest() {
//            CreateGiftCardResponse response = client.createGiftCard(CreateGiftCardRequest.builder()
//                    .creationRequestId("F4000")
//                    .partnerId(partnerId)
//                    .value(AgcodValue.builder()
//                            .amount(10F)
//                            .currencyCode(CurrencyCode.EUR)
//                            .build())
//                    .build());
//    }
//
//    @Test
//    public void cancelGiftCardRequestTest() {
//        CancelGiftCardResponse response = client.cancelGiftCard(CancelGiftCardRequest.builder()
//                .creationRequestId("F0000")
//                .partnerId(partnerId)
//                .build());
//
//        assertEquals(AgcodResponseStatus.SUCCESS, response.status());
//    }
//
//    @Test
//    public void getAvailableFundsRequestTest() {
//        GetAvailableFundsResponse response = client.getAvailableFunds(GetAvailableFundsRequest.builder()
//                .partnerId(partnerId)
//                .build());
//
//        assertEquals(AgcodResponseStatus.SUCCESS, response.status());
//        assertNotNull(response.availableFunds());
//        assertTrue(response.timestamp() instanceof Instant);
//    }
//
//    private void initClient() {
//        client = AgcodClient.builder()
//                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
//                .region(Region.EU_WEST_1)
//                .endpointOverride(AgcodHost.SANDBOX_EU.getUri())
//                .overrideConfiguration(o -> o.addExecutionInterceptor(new AcceptJsonInterceptor()))
//                .build();
//    }
//
//    private String generateCreationRequestId() {
//        return partnerId + UUID.randomUUID()
//                .toString()
//                .replace("-", "");
//    }
}
