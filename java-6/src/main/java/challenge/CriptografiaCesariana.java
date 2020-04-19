package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {

        if(texto.isEmpty()){
            throw new IllegalArgumentException("É obrigatório informar um texto como parâmetro");
        }

        String textoMinusculo = texto.toLowerCase();
        StringBuilder textoCifrado = new StringBuilder();

        for (int i = 0; i < textoMinusculo.length(); i++) {
            if(textoMinusculo.charAt(i) >= 97 && textoMinusculo.charAt(i) <= 122 ){
                int letraASCII = ((int) textoMinusculo.charAt(i)) + 3;
                textoCifrado.append((char) letraASCII);
            } else {
                int letraASCII = ((int) textoMinusculo.charAt(i));
                textoCifrado.append((char) letraASCII);
            }    
        }
        return textoCifrado.toString();
    }

    @Override
    public String descriptografar(String texto) {

        if(texto.isEmpty()){
            throw new IllegalArgumentException("É obrigatório informar um texto como parâmetro");
        }

        String textoMinusculo = texto.toLowerCase();
        StringBuilder textoCifrado = new StringBuilder();

        for (int i = 0; i < textoMinusculo.length(); i++) {
            if(textoMinusculo.charAt(i) >= 97 && textoMinusculo.charAt(i) <= 122 ){
                int letraASCII = ((int) textoMinusculo.charAt(i)) - 3;
                textoCifrado.append((char) letraASCII);
            } else {
                int letraASCII = ((int) textoMinusculo.charAt(i));
                textoCifrado.append((char) letraASCII);
            }    
        }
        return textoCifrado.toString();
    }

}
