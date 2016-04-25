package io.pst.lambda.insert.into.aws.rds;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import io.pst.lambda.insert.into.aws.rds.model.Message;

import java.util.Date;

/**
 * App to test the Handler.
 *
 */
public class App {

    public static void main( String[] args ) {
        App app = new App();
        app.run();
    }

    public void run() {
        Message message = createMessage();
        Context context = createContext();
        Handler handler = new Handler();
        String result = handler.handleRequest(message, context);
        System.out.println(result);
    }

    private Message createMessage() {
        Message message = new Message();
        message.setAge(30);
        message.setBusinessOwner(true);
        message.setName("MyName");
        message.setLastName("MyLastName");
        message.setScore(0.23f);
        message.setRegistrationDate(new Date());
        return message;
    }

    private Context createContext() {
        Context context = new Context() {
            @Override
            public String getAwsRequestId() {
                return null;
            }

            @Override
            public String getLogGroupName() {
                return null;
            }

            @Override
            public String getLogStreamName() {
                return null;
            }

            @Override
            public String getFunctionName() {
                return null;
            }

            @Override
            public String getFunctionVersion() {
                return null;
            }

            @Override
            public String getInvokedFunctionArn() {
                return null;
            }

            @Override
            public CognitoIdentity getIdentity() {
                return null;
            }

            @Override
            public ClientContext getClientContext() {
                return null;
            }

            @Override
            public int getRemainingTimeInMillis() {
                return 0;
            }

            @Override
            public int getMemoryLimitInMB() {
                return 0;
            }

            @Override
            public LambdaLogger getLogger() {
                return null;
            }
        };
        return context;
    }

}
