package io.tenera.config;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.handlers.RequestHandler2;
import com.amazonaws.services.sns.model.PublishRequest;

import javax.inject.Singleton;

@Singleton
public class SnsFifoMessageGroupIdHandler extends RequestHandler2 {
    @Override
    public AmazonWebServiceRequest beforeExecution(AmazonWebServiceRequest request) {
        var amazonWebServiceRequest = super.beforeExecution(request);
        if (amazonWebServiceRequest instanceof  PublishRequest){
            if (((PublishRequest) amazonWebServiceRequest).getTopicArn().contains(".fifo")){
                ((PublishRequest) amazonWebServiceRequest).setMessageGroupId(((PublishRequest) amazonWebServiceRequest).getTopicArn());
            }
        }
        return amazonWebServiceRequest;
    }

}