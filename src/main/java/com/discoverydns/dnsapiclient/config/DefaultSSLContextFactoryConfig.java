package com.discoverydns.dnsapiclient.config;

/**
 * Mandatory config bean for the {@link com.discoverydns.dnsapiclient.internal.DefaultSSLContextFactory} class.
 * This configuration enables to point to path-accessible keystore and trustore resources.
 * @author Arnaud Dumont
 */
public interface DefaultSSLContextFactoryConfig {
    /**
     * @return The path to the SSL KeyStore file
     */
    public String getKeyStorePath();

    /**
     * @return The type of SSL KeyStore file (e.g. "JKS" or "PKCS12")
     */
    public String getKeyStoreType();

    /**
     * @return The password of the SSL KeyStore file
     */
    public String getKeyStorePassword();

    /**
     * @return The password of the Key contained in the SSL KeyStore file
     */
    public String getKeyStoreKeyPassword();

    /**
     * @return The path to the SSL TrustStore file
     */
    public String getTrustStorePath();

    /**
     * @return The type of SSL TrustStore file (e.g. "JKS" or "PKCS12")
     */
    public String getTrustStoreType();

    /**
     * @return The password of the SSL TrustStore file
     */
    public String getTrustStorePassword();
}
