package com.discoverydns.dnsapiclient.config;

/**
 * Mandatory config bean for the {@link com.discoverydns.dnsapiclient.DefaultTransactionLogHandler} class.
 * This configuration enables to point to path-accessible log file resources, with a rotation pattern.
 * @author Arnaud Dumont
 */
public interface DefaultTransactionLogHandlerConfig {

    /**
     * @return The path to the transaction log file
     */
    public String getTransactionLogFile();

    /**
     * @return The rotation pattern for the transaction log files, which will be gzipped if the pattern ends in '.gz'
     */
    public String getTransactionLogFileRotationPattern();

    /**
     * @return The maximum number of archive files to keep. '0' means 'keep all'.
     */
    public int getMaxTransactionLogFileVersions();
}
