package io.tenera;

import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.internal.config.HostRegexToRegionMappingJsonHelper;
import com.amazonaws.internal.config.HttpClientConfigJsonHelper;
import com.amazonaws.internal.config.InternalConfigJsonHelper;
import com.amazonaws.internal.config.JsonIndex;
import com.amazonaws.internal.config.SignerConfigJsonHelper;
import com.amazonaws.partitions.model.CredentialScope;
import com.amazonaws.partitions.model.Endpoint;
import com.amazonaws.services.sqs.QueueUrlHandler;
import com.amazonaws.services.sqs.internal.SQSRequestHandler;
import io.micronaut.core.annotation.TypeHint;
import io.micronaut.runtime.Micronaut;

import static io.micronaut.core.annotation.TypeHint.AccessType.ALL_DECLARED_CONSTRUCTORS;

@TypeHint(value = {
        InternalConfigJsonHelper.class,
        SignerConfigJsonHelper.class,
        HttpClientConfigJsonHelper.class,
        HostRegexToRegionMappingJsonHelper.class,
        JsonIndex.class,
        AWS4Signer.class,
        QueueUrlHandler.class,
        SQSRequestHandler.class,
        com.amazonaws.partitions.model.Partitions.class,
        com.amazonaws.partitions.model.Partition.class,
        Endpoint.class,
        com.amazonaws.partitions.model.Region.class,
        com.amazonaws.partitions.model.Service.class,
        CredentialScope.class,
        com.amazonaws.services.sqs.MessageMD5ChecksumHandler.class,
        io.tenera.service.FileCreatedEvent.class
}, accessType = {TypeHint.AccessType.ALL_PUBLIC, ALL_DECLARED_CONSTRUCTORS})
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
