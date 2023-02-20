package com.bntech.imperio.gateway.handler;


import com.bntech.imperio.gateway.exception.NotFoundException;
import com.bntech.imperio.gateway.exception.ThrowableTransformatorImpl;
import com.bntech.imperio.gateway.object.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * todo: This should is in common. Need to build it somehow into container.
 */
@Slf4j
@Component
public class ErrorHandler {

    private static final String NOT_FOUND = "not found";
    private static final String ERROR_RAISED = "error raised";

    public Mono<ServerResponse> notFound(final ServerRequest request) {
        return Mono.just(new NotFoundException(NOT_FOUND)).transform(this::getResponse);
    }

    <T extends Throwable> Mono<ServerResponse> getResponse(final Mono<T> monoError) {
        return monoError.transform(ThrowableTransformatorImpl::toMono)
                .flatMap(translation -> ServerResponse
                        .status(translation.getHttpStatus())
                        .body(Mono.just(new ErrorResponse(translation.getMessage())), ErrorResponse.class));
    }

    Mono<ServerResponse> throwableError(final Throwable error) {
        log.error(ERROR_RAISED, error);
        return Mono.just(error).transform(this::getResponse);
    }
}
