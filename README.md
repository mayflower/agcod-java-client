# AGCOD Client for Java (based on AWS SDK 2.0)

The **AGCOD (Amazon Gift Cards On Demand) SDK for Java** is a client based on the official [AWS SDK Java v2][sdk] for the Amazon Incentives API.

## Getting Started

#### Docs

Before you begin, check the general [documenation][agcod-docs] of the Amazon Incentives API.  

#### Credentials for AGCOD ####

You need credentials for the AGCOD API. Amazon provides a [scratchpad][scratchpad] where you can test your credentials beforehand. 

#### Minimum requirements ####

To run the SDK you will need **Java 1.8+**.

## Using the SDK

The recommended way to use the AWS SDK for Java in your project is to consume it from Maven. 

#### Importing the BOM ####

```xml
<dependency>
  <groupId>de.mayflower.agcod</groupId>
  <artifactId>agcod-java-client</artifactId>
</dependency>
```

## Using the Client

#### Initialize the Client
```java
AgcodClient client = AgcodClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("accessKey", "secretKey")))
                .region(Region.EU_WEST_1)
                .endpointOverride(URI.create("https://agcod-v2-eu-gamma.amazon.com"))
                .overrideConfiguration(o -> o
                        .addExecutionInterceptor(new AcceptJsonInterceptor())
                        .retryPolicy(AgcodRetryPolicy.defaultPolicy()))
                .build();
```

#### Create Gift Card Request
```java
CreateGiftCardResponse response = client.createGiftCard(CreateGiftCardRequest.builder()
        .creationRequestId("F0000")
        .partnerId("partnerId")
        .value(AgcodValue.builder()
                .amount(new BigDecimal(10))
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

## Building From Source

Once you check out the code from GitHub, you can build it using Maven.

```sh
mvn clean install

# Skip tests, checkstyles, findbugs, etc for quick build
mvn clean install -P quick

# Build the agcod service module
mvn clean install -pl :agcod -P quick --am
```

## To Do

- [x] Create Gift Card
- [x] Cancel Gift Card
- [ ] Activate Gift Card
- [ ] Deactivate Gift Card
- [ ] Get Gift Card Activity Page
- [ ] Activation Status Check


[sdk]: https://github.com/aws/aws-sdk-java-v2
[agcod-docs]: https://developer.amazon.com/es/apps-and-games/incentives-api
[scratchpad]: https://s3.amazonaws.com/AGCOD/htmlSDKv2/htmlSDKv2_NAEUFE/index.html