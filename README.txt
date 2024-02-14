Tutaj masz fragment repo odnośnie podpisywania dokumentów podpisem xades.

W projekcie znajduje się już:
klucz prywatny: private_key.pem
samopodpisany certyfikat: certificate.pem
oraz plik do podpisania: text_xml_to_sign
Oraz folder ksef api pliki w którym masz wzór dokumentu do podpisu dokumentacje oraz plik yaml do dokumentacji swagger ksefa

Aby podpisać plik możesz skorzystać ze swaggera uruchomiając prosty endpoint get do wykonania podpisu, który następnie znajdziesz w folderze out.

Cały kod w miejscach, które uznałem za kluczowe oraz podmieniłem względem pierwowzoru o którym rozmawialiśmy opisałem komentarzami.
