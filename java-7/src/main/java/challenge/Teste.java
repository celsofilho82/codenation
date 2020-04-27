package challenge;

import static java.util.concurrent.ThreadLocalRandom.current;

public class Teste {

	public static void main(String[] args) {
		
		Estacionamento estacionamento = new Estacionamento();
		
		Motorista ada = Motorista.builder().withNome("Ada").withIdade(20)
                .withPontos(3)
                .withHabilitacao("1231")
                .build();

        Carro carroBranco = Carro.builder().withCor(Cor.BRANCO).
                withPlaca("123")
                .withMotorista(ada)
                .build();

        estacionamento.estacionar(carroBranco);

        for (int indice = 1; indice <= 11; indice++) {

            Motorista motorista = Motorista.builder()
                    .withNome("Motorista " + indice)
                    .withIdade(20)
                    .withPontos(3)
                    .withHabilitacao(Long.toString(current().nextLong()))
                    .build();

            Carro carro = Carro.builder().withCor(Cor.BRANCO).
                    withPlaca("123")
                    .withMotorista(motorista)
                    .build();

            estacionamento.estacionar(carro);
        }
        
        System.out.println("Quantidade de carros estacionados " + estacionamento.carrosEstacionados());

	}

}
