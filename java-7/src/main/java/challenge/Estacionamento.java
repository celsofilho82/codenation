package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
	
	List<Carro> vagas = new ArrayList<>();

    public void estacionar(Carro carro) {
    	
    	verificandoRestricoes(carro);
    	
    	if(adicionaCarroNaVaga(carro)) {
    		
    	}else {
    		if(verificaDisponibilidadeDeVagas(carro)) {
    			
    		} else throw new EstacionamentoException("Estacionamento lotado!");
    	}	   
    }
    
    public int carrosEstacionados() {
        return vagas.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return vagas.contains(carro);
    }
    
    private void verificandoRestricoes(Carro carro) {
    	if(carro.getMotorista() == null || 
    			carro.getMotorista().getIdade() < 18 || 
    			carro.getMotorista().getPontos() > 20) 
    		throw new EstacionamentoException("Esse veiculo possuí restrições para estacionar!");
    }
    
    private boolean adicionaCarroNaVaga(Carro carro) {
    	if(carrosEstacionados() < 10) {
    		vagas.add(carro);
    		return true;
    	}
    	return false;
    }
    
    private boolean verificaDisponibilidadeDeVagas(Carro carro) {
    	for(Carro car : vagas) {
    		if(car.getMotorista().getIdade() < 55) {
    			 vagas.remove(car);
    			 vagas.add(carro);
    			 return true;
    		}
    	}
    	return false;
    }
    
}
