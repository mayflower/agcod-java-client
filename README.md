# AGCOD Client for Java (based on AWS SDK 2.0)
[![Maven](https://img.shields.io/maven-central/v/com.github.mayflower/agcod-java-client?label=Maven)](https://search.maven.org/search?q=g:%22com.github.mayflower%22%20AND%20a%3A%22agcod-java-client%22)

The **AGCOD (Amazon Gift Cards On Demand) SDK for Java** is a client based on the official [AWS SDK Java v2][sdk] for the Amazon Incentives API.

## Getting Started

#### Docs

Before you begin, check the general [documenation][agcod-docs] of the Amazon Incentives API.  

#### Credentials for AGCOD ####

You need credentials for the AGCOD API. Amazon provides a [scratchpad][scratchpad] where you can test your credentials beforehand. 

#### Minimum requirements ####

To run the SDK you will need **Java 1.8+**.

## Using the Client

The recommended way to use the AGCOD Client for Java in your project is to consume it from Maven. 

#### Importing the BOM ####

```xml 
<dependency>
    <groupId>com.github.mayflower</groupId>
    <artifactId>agcod-java-client</artifactId>
    <version>1.0</version>
</dependency>
```

#### Initialize the Client
```java
AgcodClient client = AgcodClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("accessKey", "secretKey")))
                .region(Region.US_EAST_1)
                .endpointOverride(AgcodHost.SANDBOX_NORTH_AMERICA.getUri())
                .overrideConfiguration(o -> o
                        .addExecutionInterceptor(new AcceptJsonInterceptor()))
                .build();
```

#### Create Gift Card Request
```java
CreateGiftCardResponse response = client.createGiftCard(CreateGiftCardRequest.builder()
        .creationRequestId("F0000")
        .partnerId("partnerId")
        .value(AgcodValue.builder()
                .amount(10F)
                .currencyCode(CurrencyCode.EUR)
                .build())
        .build());
```

#### Cancel Gift Card Request
```java
CancelGiftCardResponse response = client.cancelGiftCard(CancelGiftCardRequest.builder()
        .creationRequestId("F0000")
        .partnerId("partnerId")
        .build());
```

#### Get Available Funds Request
```java
GetAvailableFundsResponse response = client.getAvailableFunds(GetAvailableFundsRequest.builder()
        .partnerId("partnerId")
        .build());
```

### Catching Errors

The client will throw the following exceptions according to the documentation:

| Amazon Error Code  | Java Exception | Comment | 
| ------------- | ------------- | ------------- |
| F100  | SystemErrorException  | System Errors  |
| F2xx  | PartnerInputErrorException  | Partner Input Errors  |
| F3xx  | PartnerAccessErrorException  | Partner Account/Access/Onboarding Errors  |
| F400  | ResendErrorException  | System Temporarily Unavailable: The library will attempt automatic retries on this error up to 8 times with an exponential backoff strategy  |
| F500  | UnknownErrorException  | Unknown Error  |

## Note
Since Amazon Incentives "does not fall under Amazon Web Services (AWS)" an integration into the official AWS SDK [is not desired][agcod-aws-issue].  

## To Do

- [x] Create Gift Card
- [x] Cancel Gift Card
- [x] Get Available Funds
- [ ] [Parse dates in response as `Date` - not as `String`](https://github.com/mayflower/agcod-java-client/issues/2) 
- [ ] [Support for physical gift cards](https://github.com/mayflower/agcod-java-client/issues/1)


[sdk]: https://github.com/aws/aws-sdk-java-v2
[agcod-docs]: https://s3-us-west-2.amazonaws.com/incentives-api-docs/incentives-api/incentives-api.html
[scratchpad]: https://s3.amazonaws.com/AGCOD/htmlSDKv2/htmlSDKv2_NAEUFE/index.html
[agcod-aws-issue]: https://github.com/aws/aws-sdk-java-v2/issues/1508