package com.discoverydns.dnsapiclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.commandinterceptors.StopwatchCommandInterceptor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class DefaultTransactionLogHandler implements TransactionLogHandler {

	private static Logger transactionLog = LoggerFactory
			.getLogger("com.discoverydns.dnsapiclient.transactionlog");
	private static Logger log = LoggerFactory
			.getLogger(DefaultTransactionLogHandler.class);

	private final ObjectWriter writer;

	public DefaultTransactionLogHandler(final DefaultTransactionLogHandlerConfig logConfig,
			final ObjectMapper mapper) {
		final LoggerContext loggerContext = (LoggerContext) LoggerFactory
				.getILoggerFactory();
		final PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
		patternLayoutEncoder
				.setPattern("%date{\"yyyy-MM-dd HH:mm:ss.SSS z\",UTC} %msg%n");
		patternLayoutEncoder.setContext(loggerContext);
		patternLayoutEncoder.start();

		final RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();
		fileAppender.setContext(loggerContext);
		fileAppender.setAppend(true);
		fileAppender.setEncoder(patternLayoutEncoder);
		fileAppender.setPrudent(false);
		fileAppender.setFile(logConfig.getTransactionLogFile());
		final TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new TimeBasedRollingPolicy<>();
		rollingPolicy.setFileNamePattern(logConfig
				.getTransactionLogFileRotationPattern());
		rollingPolicy.setMaxHistory(logConfig.getMaxTransactionLogFileVersions());
		rollingPolicy.setCleanHistoryOnStart(false);
		rollingPolicy.setContext(loggerContext);
		rollingPolicy.setParent(fileAppender);
		rollingPolicy.start();
		fileAppender.setRollingPolicy(rollingPolicy);
		fileAppender.setTriggeringPolicy(rollingPolicy);
		fileAppender.start();

		final ch.qos.logback.classic.Logger logBackLogger = (ch.qos.logback.classic.Logger) transactionLog;
		logBackLogger.detachAndStopAllAppenders();
		logBackLogger.addAppender(fileAppender);
		logBackLogger.setLevel(Level.INFO);
		logBackLogger.setAdditive(false);

		this.writer = mapper.writer().without(
				SerializationFeature.INDENT_OUTPUT);
	}

	@Override
	public void logTransaction(final Object command, final Object response,
			final CommandMetaData commandMetaData) {
		final String commandString = getObjectString(command);
		log.trace("Command string is: {}", commandString);
		final String responseString = getObjectString(response);
		log.trace("Response string is: {}", responseString);
		performLog(commandString, responseString, commandMetaData);

	}

	@Override
	public void logFailedTransaction(final Object command,
			final Throwable exception, final CommandMetaData commandMetaData) {
		final String commandString = getObjectString(command);
		log.trace("Command string is: {}", commandString);
		final String responseString = messageToJson(exception.getMessage());
		log.trace("Response string is: {}", responseString);
		performLog(commandString, responseString, commandMetaData);
	}

	private void performLog(final String commandString,
			final String responseString, final CommandMetaData commandMetaData) {
		final StringBuilder sb = new StringBuilder(1000);
		sb.append("ServerTxId:");
		sb.append(commandMetaData
				.get(DNSAPIClientCommandMetaData.SERVER_TRANSACTION_ID));
		sb.append(" ClientTxId:");
		sb.append(commandMetaData
				.get(DNSAPIClientCommandMetaData.CLIENT_TRANSACTION_ID));
		sb.append(" Command:");
		sb.append(commandString);
		sb.append(" Response:");
		sb.append(responseString);
		sb.append(" ");
		sb.append(commandMetaData
				.get(StopwatchCommandInterceptor.COMMAND_TIME_MS));
		sb.append("ms");
		transactionLog.info(sb.toString());
	}

	private String messageToJson(final String message) {
		final StringBuilder sb = new StringBuilder();
		sb.append("{ \"error\" : { \"message\" : \"");
		sb.append(message.replace('"', '\''));
		sb.append("\" } }");
		return sb.toString();
	}

	private String getObjectString(final Object object) {
		String objectString;
		try {
			objectString = writer.writeValueAsString(object);
		} catch (final JsonProcessingException e) {
			objectString = "Error formating object "
					+ object.getClass().getSimpleName();
			log.error(objectString, e);
			e.printStackTrace();
		}
		return objectString;
	}
}
