package com.discoverydns.dnsapiclient.command.message;

import com.discoverydns.dnsapiclient.internal.views.MessageGetView;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the
 * {@link com.discoverydns.dnsapiclient.DNSAPIClient} from the DNSAPI server
 * subsequently to the sending of a {@link MessageAcknowledgeCommand},
 * describing the result of the Message acknowledgment.
 * 
 * A Message is a notification from the DNSAPI server, for the targeted sponsor
 * Account's Users, offering information about changes performed by the server
 * on a related object in the system.
 * 
 * @author Arnaud Dumont
 */
@JsonRootName("MessageAcknowledgeResponse")
public class MessageAcknowledgeResponse extends MessageGetResponse {

	public MessageAcknowledgeResponse(final MessageGetView messageGetView) {
		super(messageGetView);
	}

}
