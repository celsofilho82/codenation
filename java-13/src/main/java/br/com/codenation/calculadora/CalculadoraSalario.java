package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		//Use o Math.round apenas no final do método para arredondar o valor final.
		//Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-

    double salarioMinimo = 1045.00;

    if(salarioBase < 0 || salarioBase < salarioMinimo ){
      return 0;
    }

    return Math.round(calcularIRRF(salarioBase));
	}


	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {

    double valorINSS = 0;

    if(salarioBase <= 1500.00){
      valorINSS = salarioBase * 0.08;
    } else if (salarioBase <= 4000.00) {
      valorINSS = salarioBase * 0.09;
    } else {
      valorINSS = salarioBase * 0.11;
    }
		return salarioBase - valorINSS;
	}

  private double calcularIRRF(double salarioBase) {

    double salario = calcularInss(salarioBase);

    double valorIRRF = 0;

    if(salario > 3000.00 && salario <= 6000.00 ){

      valorIRRF = salario * 0.075;
    }
    else if (salario > 6000.00){

      valorIRRF = salario * 0.15;
    }

    return salario - valorIRRF;
  }

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar!
*/
