/*
 * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.services.agcod;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;

import software.amazon.awssdk.annotations.SdkProtectedApi;
import software.amazon.awssdk.awscore.retry.AwsRetryPolicy;
import software.amazon.awssdk.core.internal.retry.SdkDefaultRetrySetting;
import software.amazon.awssdk.core.retry.RetryPolicy;
import software.amazon.awssdk.core.retry.backoff.BackoffStrategy;
import software.amazon.awssdk.core.retry.backoff.FullJitterBackoffStrategy;
import software.amazon.awssdk.core.retry.conditions.OrRetryCondition;
import software.amazon.awssdk.core.retry.conditions.RetryCondition;
import software.amazon.awssdk.core.retry.conditions.RetryOnExceptionsCondition;
import software.amazon.awssdk.services.agcod.model.ResendErrorException;

/**
 * Default retry policy for Agcod Client.
 */
@SdkProtectedApi
public final class AgcodRetryPolicy {

    /**
     * Default max retry count for Agcod client
     **/
    private static final int DEFAULT_MAX_ERROR_RETRY = 8;

    /**
     * Default base sleep time for Agcod.
     **/
    private static final Duration DEFAULT_BASE_DELAY = Duration.ofMillis(250);

    /**
     * Default exeptions for retry for Agcod.
     **/
    private static final Set<Class<? extends Exception>> RETRYABLE_EXCEPTIONS = Collections.singleton(ResendErrorException.class);

    /**
     * The default back-off strategy for Agcod client, which increases
     * exponentially up to a max amount of delay. Compared to the SDK default
     * back-off strategy, it applies a smaller scale factor.
     */
    private static final BackoffStrategy DEFAULT_BACKOFF_STRATEGY =
            FullJitterBackoffStrategy.builder()
                    .baseDelay(DEFAULT_BASE_DELAY)
                    .maxBackoffTime(SdkDefaultRetrySetting.MAX_BACKOFF)
                    .build();

    /**
     * Default retry policy for Agcod.
     */

    private static final RetryPolicy DEFAULT =
            AwsRetryPolicy.defaultRetryPolicy().toBuilder()
                    .numRetries(DEFAULT_MAX_ERROR_RETRY)
                    .retryCondition(OrRetryCondition.create(
                            RetryCondition.defaultRetryCondition(),
                            RetryOnExceptionsCondition.create(RETRYABLE_EXCEPTIONS))
                    )
                    .backoffStrategy(DEFAULT_BACKOFF_STRATEGY).build();

    private AgcodRetryPolicy() {

    }

    /**
     * @return Default retry policy used by AgcodClient
     */
    public static RetryPolicy defaultPolicy() {
        return DEFAULT;
    }
}
