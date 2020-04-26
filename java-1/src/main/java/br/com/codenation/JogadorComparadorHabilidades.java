package br.com.codenation;

import java.util.Comparator;

public class JogadorComparadorHabilidades implements Comparator<Jogador> {

	@Override
	public int compare(Jogador j1, Jogador j2) {
		
		if (j1.getNivelHabilidade().equals(j2.getNivelHabilidade())) {

            return (int) (j1.getId() - j2.getId());

        } else {

            return j2.getNivelHabilidade().compareTo(j1.getNivelHabilidade());

        }
		
	}
}


