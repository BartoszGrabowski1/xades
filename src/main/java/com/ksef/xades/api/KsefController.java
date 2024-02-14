package com.ksef.xades.api;


import lombok.RequiredArgsConstructor;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

@RestController
@RequiredArgsConstructor
public class KsefController {

    @GetMapping("/ksef-sign-xml")
    public void signXml() throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // ścieżki do certów
        String certificatePath = "certificate.pem";
        String privateKeyPath = "private_key.pem";

        // Wczytywanie certyfikatu X.509
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        FileInputStream certInputStream = new FileInputStream(certificatePath);
        X509Certificate certificate = (X509Certificate) certFactory.generateCertificate(certInputStream);
        certInputStream.close();

        // Wczytywanie klucza prywatnego
        PemReader pemReader = new PemReader(new FileReader(privateKeyPath));
        PemObject pemObject = pemReader.readPemObject();
        byte[] keyBytes = pemObject.getContent();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = kf.generatePrivate(spec);
        pemReader.close();

        // Proces podpisywania tutaj na xades'a
        XmlSigner xmlSigner = new XadesXmlSigner();
        File signedFile = new File("out/signed.xml");
        xmlSigner.sign(new File("test_xml_to_sign.xml"), signedFile, privateKey, certificate);
        System.out.println("Signed file saved in: " + signedFile.getAbsolutePath());
    }
}
