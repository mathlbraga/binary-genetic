package binario;

/**
 *
 * @author Matheus Braga
 */
public class ExecutaAG {

    public static void main(String[] args) throws InterruptedException {

        Populacao populacao = null;
        int numGenes = 26; // numero de genes no cromossomo
        int tamPopulacao = 60; // numero de individuos na populacao
        int numMaxGeracoes = 100; // limite de geracoes
        int geracao = 0; // geracao atual

        AlgoritmoGenetico.setSolucao("11111111111111111111111111"); // individuo que se deseja encontrar
        AlgoritmoGenetico.setTaxaCrossover(0.7); // taxa de crossover em 70%
        AlgoritmoGenetico.setTaxaMutacao(0.01); // taxa de mutação em 1%
        boolean elitismo = true; // será utilizado elitismo

        while (geracao < numMaxGeracoes) {
            geracao++;
            if (populacao == null) {
                // cria uma nova populacao aleatoria
                populacao = new Populacao(numGenes, tamPopulacao);
            } else {
                // cria uma nova populacao com os melhores individuos selecionados
                populacao = AlgoritmoGenetico.criaNovaGeracao(populacao, elitismo);
            }
            System.out.print("Geração: " + geracao + " | ");
            System.out.print("Melhor indivíduo da geração: " + populacao.getIndividuo(0).getCromossomo());
            System.out.println(" | Aptidão: " + populacao.getIndividuo(0).getAptidao());
        }
    }
}
