package com.ksef.xades.api;

import org.w3c.dom.Document;

import java.io.File;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public interface XmlSigner {

    File sign(Document xmlToSign, File destinationFile, PrivateKey privateKey, X509Certificate certificate) throws XmlSigningException;
    File sign(File xmlToSign,File destinationFile, PrivateKey privateKey, X509Certificate certificate) throws XmlSigningException;
}
