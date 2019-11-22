package software.amazon.awssdk.services.agcod.internal;

import software.amazon.awssdk.annotations.SdkInternalApi;
import software.amazon.awssdk.core.interceptor.Context;
import software.amazon.awssdk.core.interceptor.ExecutionAttributes;
import software.amazon.awssdk.core.interceptor.ExecutionInterceptor;
import software.amazon.awssdk.http.SdkHttpRequest;

@SdkInternalApi
public final class AcceptJsonInterceptor implements ExecutionInterceptor {
    @Override
    public SdkHttpRequest modifyHttpRequest(Context.ModifyHttpRequest context, ExecutionAttributes executionAttributes) {
        return context.httpRequest()
                .toBuilder()
                .putHeader("Accept", "application/json")
                .putHeader("Content-Type", "application/json")
                .build();
    }
}
