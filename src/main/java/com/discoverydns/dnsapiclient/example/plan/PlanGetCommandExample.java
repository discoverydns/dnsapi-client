package com.discoverydns.dnsapiclient.example.plan;

import ch.qos.logback.classic.Level;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.plan.PlanFeature;
import com.discoverydns.dnsapiclient.command.plan.PlanGetCommand;
import com.discoverydns.dnsapiclient.command.plan.PlanGetResponse;
import com.discoverydns.dnsapiclient.command.plan.PlanUnit;
import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;
import com.discoverydns.dnsapiclient.config.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultTransactionLogHandlerConfig;

/**
 * Example of sending a PlanGetCommand and receiving an PlanGetResponse
 * @author Arnaud Dumont
 */
public class PlanGetCommandExample {

    public static void main(String[] args) {
        //Configure logging properties, using Logback
        final ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory
                .getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);

        //Instantiate configuration beans
        final DNSAPIClientConfig config = new ExampleDNSAPIClientConfig();
        final DefaultSSLContextFactoryConfig sslConfig = new ExampleDefaultSSLContextFactoryConfig();
        final DefaultTransactionLogHandlerConfig logConfig = new ExampleDefaultTransactionLogHandlerConfig();

        //Instantiate client instance from DNSAPIClientFactory
        final DNSAPIClientFactory dnsapiClientFactory = new DNSAPIClientFactory();
        DNSAPIClient client = null;
        try {
            client = dnsapiClientFactory.createInstanceFromDefaultProviders(config, sslConfig, logConfig);
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        //Create command
        final PlanGetCommand command =
                new PlanGetCommand.Builder()
                        .withIdOrName("<my-plan-id>")
                        .build();

        //Send command to server and receive response
        try {
            Response<PlanGetResponse> response = client.process(command);

            System.out.println("== Successful response ==");
            System.out.println("== Server transaction id: " + response.getServerTransactionId());
            System.out.println("== Client transaction id: " + response.getClientTransactionId());
            System.out.println("== Processing time: " + response.getTransactionProcessingTime() + "ms");

            //The response object can now be used
            System.out.println("Plan URI: " + response.getResponseObject().getURI());
            System.out.println("Plan UUID: " + response.getResponseObject().getId());
            System.out.println("Plan version: " + response.getResponseObject().getVersion());
            System.out.println("Plan status: " + response.getResponseObject().getStatus());
            System.out.println("Plan name: " + response.getResponseObject().getName());
            System.out.println("Plan base monthly rate: " + response.getResponseObject().getBaseMonthlyRate());
            System.out.println("Plan currency: " + response.getResponseObject().getCurrency());
            System.out.println("Plan excess grace months: " + response.getResponseObject().getExcessGraceMonths());
            System.out.println("Plan excess grace months period: "
                    + response.getResponseObject().getExcessGraceMonthsPeriod());
            System.out.println("Plan trial period: " + response.getResponseObject().getTrialPeriod());
            for (PlanFeature planFeature : response.getResponseObject().getFeatures()) {
                System.out.println("-- Feature: ");
                System.out.println("   Feature type: " + planFeature.getFeatureType());
                System.out.println("   Additional rate: " + planFeature.getAdditionalRate());
            }
            for (PlanUnit planUnit : response.getResponseObject().getUnits()) {
                System.out.println("-- Unit:");
                System.out.println("   Unit type: " + planUnit.getUnitType());
                System.out.println("   Included units: " + planUnit.getIncludedUnits());
                System.out.println("   Excess units batch size: " + planUnit.getExcessUnitsBatchSize());
                System.out.println("   Excess units batch rate: " + planUnit.getExcessUnitsBatchRate());
            }
            System.out.println("Plan create Account UUID: " + response.getResponseObject().getCreateAccountId());
            System.out.println("Plan create Account Identifier: "
                    + response.getResponseObject().getCreateAccountIdentifier());
            System.out.println("Plan create User UUID: " + response.getResponseObject().getCreateUserId());
            System.out.println("Plan create User name: "
                    + response.getResponseObject().getCreateUserName());
            System.out.println("Plan create date: " + response.getResponseObject().getCreateDate());
            System.out.println("Plan last update Account UUID: "
                    + response.getResponseObject().getLastUpdateAccountId());
            System.out.println("Plan last update Account Identifier: "
                    + response.getResponseObject().getLastUpdateAccountIdentifier());
            System.out.println("Plan last update User UUID: " + response.getResponseObject().getLastUpdateUserId());
            System.out.println("Plan last update User name: "
                    + response.getResponseObject().getLastUpdateUserName());
            System.out.println("Plan last update date: " + response.getResponseObject().getLastUpdateDate());
        } catch (final Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
