package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	List<Jogador> jogadores = new ArrayList<Jogador>();
	List<Time> times = new ArrayList<Time>();
	
	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		
		if(timeExiste(id)) { throw new IdentificadorUtilizadoException(); }
		
		times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		
		if(jogadorExiste(id)) { throw new IdentificadorUtilizadoException(); }
		
		if(!(timeExiste(idTime))) { throw new TimeNaoEncontradoException(); }
		
		jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		
		if(!(jogadorExiste(idJogador))) { throw new JogadorNaoEncontradoException(); }
		
		Jogador jogador = buscarJogador(idJogador);
		
		Time time = buscarTime(jogador.getIdTime());
		
		time.setCapitao(idJogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		
		if(!(timeExiste(idTime))) { throw new TimeNaoEncontradoException(); }
		
		Time time = buscarTime(idTime);
		
		if(time.getCapitao() == null) { throw new CapitaoNaoInformadoException(); }
		
		return time.getCapitao();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		
		if(!(jogadorExiste(idJogador))) { throw new JogadorNaoEncontradoException(); }
		
		Jogador jogador = buscarJogador(idJogador);
		
		return jogador.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		
		if(!(timeExiste(idTime))) { throw new TimeNaoEncontradoException(); }
		
		Time time = buscarTime(idTime);
		
		return time.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		
		if(!(timeExiste(idTime))) { throw new TimeNaoEncontradoException(); }
		
		List<Long> jogadoresPorTime = new ArrayList<Long>();
		
		for(Jogador jogador: jogadores) {
			
			if(jogador.getIdTime().equals(idTime)) { jogadoresPorTime.add(jogador.getId()); }
		}
		
		jogadoresPorTime.sort(Comparator.naturalOrder());
		
		return jogadoresPorTime;
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		
		if(!(timeExiste(idTime))) { throw new TimeNaoEncontradoException(); }
		
		List<Jogador> listaJogadores = new ArrayList<Jogador>();
		
		for(Jogador jogador : jogadores) {
			
			if(jogador.getIdTime().equals(idTime)) { listaJogadores.add(jogador); }
		}
		
		listaJogadores.sort(new JogadorComparadorHabilidades());
		
		return listaJogadores.get(0).getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		
		if(!(timeExiste(idTime))) { throw new TimeNaoEncontradoException(); }
		
		LocalDate dataNascimento = LocalDate.now();
		
		Long jogadorMaisVelhoTime = 0L;
		
		List<Jogador> listaJogadores = new ArrayList<Jogador>();
		
		for(Jogador jogador : jogadores) {
			
			if(jogador.getIdTime().equals(idTime)) { listaJogadores.add(jogador); }
		}
		
		for(Jogador jogador : listaJogadores) {
			
			int dataComparacao = jogador.getDataNascimento().compareTo(dataNascimento);
			
			if(dataComparacao < 0) {
				
				dataNascimento = jogador.getDataNascimento();
				
				jogadorMaisVelhoTime = jogador.getId();
			}
		}
		
		return jogadorMaisVelhoTime;
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		
		List<Long> listaTimesId = new ArrayList<Long>();
		
		if(times.isEmpty()) return listaTimesId;
		
		for(Time time : times) { listaTimesId.add(time.getId()); }
		
		listaTimesId.sort(Comparator.naturalOrder());
		
		return listaTimesId;
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		
		if(!(timeExiste(idTime))) { throw new TimeNaoEncontradoException(); }
		
		BigDecimal maiorSalario = BigDecimal.ZERO;
		
		Long idJogadorMaiorSalario = 0L;
		
		List<Jogador> listaJogadores = new ArrayList<Jogador>();
		
		for(Jogador jogador : jogadores) {
			
			if(jogador.getIdTime().equals(idTime)) { listaJogadores.add(jogador); }
		}
		
		for(Jogador jogador : listaJogadores) {
			
			int salarioComparacao = maiorSalario.compareTo(jogador.getSalario());
			
			if(salarioComparacao < 0) {
				
				maiorSalario = jogador.getSalario();
				
				idJogadorMaiorSalario = jogador.getId();
			}
			
			if(salarioComparacao == 0) {
				
				if(idJogadorMaiorSalario > jogador.getId()){
					
					idJogadorMaiorSalario = jogador.getId();
					
					maiorSalario = jogador.getSalario();
				}
			}	
		}
		
		return idJogadorMaiorSalario;
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		
		if(!(jogadorExiste(idJogador))) { throw new JogadorNaoEncontradoException(); }
		
		BigDecimal salario = BigDecimal.ZERO;
		
		for(Jogador jogador : jogadores) {
			
			if(jogador.getId().equals(idJogador)) {
				
				salario = jogador.getSalario();
				
				break;
			}
		}
		
		return salario;
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		
		List<Long> topJogadoresId = new ArrayList<Long>();
		
		if(jogadores.isEmpty()) { return topJogadoresId; }
		
		jogadores.sort(new JogadorComparadorHabilidades());
		
		for(int i = 0; i < top; i++ ) { topJogadoresId.add(jogadores.get(i).getId()); }
		
		return topJogadoresId;
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		
		if(!(timeExiste(timeDaCasa)) || !(timeExiste(timeDeFora))) { throw new TimeNaoEncontradoException(); }
		
		Time timeMandante = new Time();
		
		Time timeAdversario = new Time();
		
		timeMandante = buscarTime(timeDaCasa);
		
		timeAdversario = buscarTime(timeDeFora);
		
		
		if(timeMandante.getCorUniformePrincipal().equals(timeAdversario.getCorUniformePrincipal())) {
			
			return timeAdversario.getCorUniformeSecundario();
		}
		
		
		return timeAdversario.getCorUniformePrincipal();
	}
	
	// ---------------- helpers -----------------------//
	
	private boolean timeExiste(Long id) {
		for(Time time : times) {
			if (time.getId().equals(id)) return true;
		}
		return false;
	}
	
	private boolean jogadorExiste(Long id) {
		for(Jogador jogador: jogadores) {
			if(jogador.getId().equals(id)) return true;
		}
		return false;
	}
	
	private Jogador buscarJogador(Long id) {
		for(Jogador jogador: jogadores) {
			if(jogador.getId().equals(id)) return jogador;
			}
		throw new JogadorNaoEncontradoException();
	}
	
	private Time buscarTime(Long id) {
		for(Time time: times) {
			if(time.getId().equals(id)) return time;	
		}
		throw new TimeNaoEncontradoException();
	}
	
}



