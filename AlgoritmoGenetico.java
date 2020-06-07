package binario;

import java.util.Random;

/**
 * Classe para manipulação do Algoritmo Genético.
 * 
 * @author Matheus Braga
 */
public class AlgoritmoGenetico {

    private static double taxaCrossover;
    private static double taxaMutacao;

    private static String solucao;

    /**
     * Método criador de uma nova população. Verifica se o elitismo é utilizado.
     * Cria os novos indivíduos e retorna a nova população.
     * 
     * @param populacao       (Populacao)
     * @param utilizaElitismo (boolean)
     * @return Populacao
     */
    public static Populacao criaNovaGeracao(Populacao populacao, boolean utilizaElitismo) {
        Random r = new Random();

        // cria a instância da nova população com o mesmo tamanho da original
        Populacao novaPopulacao = new Populacao(populacao.getTamPopulacao());

        // se for utilizado elitismo, passa o melhor indivíduo da geração atual
        if (utilizaElitismo) {
            novaPopulacao.setIndividuo(populacao.getIndividuo(0));
        }

        // insere novos indivíduos na nova população, até atingir o tamanho máximo
        while (novaPopulacao.getNumIndividuos() < novaPopulacao.getTamPopulacao()) {

            // faz a selecao por torneio para os 2 pais
            Individuo[] paisSelecionados = selecaoTorneio(populacao);

            Individuo[] filhosGerados = new Individuo[2];

            // verifica a taxa de crossover, se sim realiza o crossover,
            // se não, mantém os pais selecionados para a próxima geração
            if (r.nextDouble() <= taxaCrossover) {
                filhosGerados = crossover(paisSelecionados[1], paisSelecionados[0]);
            } else {
                filhosGerados[0] = new Individuo(paisSelecionados[0].getCromossomo());
                filhosGerados[1] = new Individuo(paisSelecionados[1].getCromossomo());
            }

            // adiciona dois filhos na nova geração
            novaPopulacao.setIndividuo(filhosGerados[0]);
            novaPopulacao.setIndividuo(filhosGerados[1]);
        }

        // ordena a nova população
        novaPopulacao.ordenaPopulacao();
        return novaPopulacao;
    }

    /**
     * Método de seleção por torneio. Recebe uma população e retorna os 2 melhores
     * indivíduos.
     * 
     * @param populacao (Populacao)
     * @return individuos (Individuo[])
     */
    public static Individuo[] selecaoTorneio(Populacao populacao) {
        Random r = new Random();
        Populacao popTemp = new Populacao(2);

        // seleciona 2 indivíduos aleatoriamente na população
        popTemp.setIndividuo(populacao.getIndividuo(r.nextInt(populacao.getTamPopulacao())));
        popTemp.setIndividuo(populacao.getIndividuo(r.nextInt(populacao.getTamPopulacao())));

        // ordena a população para deixar o melhor individuo para seleção
        popTemp.ordenaPopulacao();

        Individuo[] melhoresPais = new Individuo[2];

        // seleciona os 2 melhores individuos da população temporária
        melhoresPais[0] = popTemp.getIndividuo(0);
        melhoresPais[1] = popTemp.getIndividuo(1);

        return melhoresPais;
    }

    /**
     * Método que recupera a taxa de cruzamento (crossover).
     * 
     * @return double
     */
    public static double getTaxaCrossover() {
        return taxaCrossover;
    }

    /**
     * Método que seta a taxa de cruzamento (crossover).
     * 
     * @param taxa (double)
     * @return void
     */
    public static void setTaxaCrossover(double taxaCrossover) {
        AlgoritmoGenetico.taxaCrossover = taxaCrossover;
    }

    /**
     * Método que recupera a taxa de mutação.
     * 
     * @return double
     */
    public static double getTaxaMutacao() {
        return taxaMutacao;
    }

    /**
     * Método que seta a taxa de mutação.
     * 
     * @param taxa (double)
     * @return void
     */
    public static void setTaxaMutacao(double taxaMutacao) {
        AlgoritmoGenetico.taxaMutacao = taxaMutacao;
    }

    /**
     * Método que realiza o cruzamento (crossover) de 2 pais e retorna 2 novos
     * indivíduos. Cruzamento com corte em 2 pontos aleatórios.
     * 
     * @param pai1 (Individuo)
     * @param pai2 (Individuo)
     * @return individuos (Individuo[])
     */
    private static Individuo[] crossover(Individuo pai1, Individuo pai2) {
        Random r = new Random();

        // sorteia o ponto de corte
        int pontoCorte1 = r.nextInt((pai1.getCromossomo().length() / 2) - 2) + 1;
        int pontoCorte2 = r.nextInt((pai1.getCromossomo().length() / 2) - 2) + pai1.getCromossomo().length() / 2;

        Individuo[] filhos = new Individuo[2];

        // pega os genes dos pais
        String genePai1 = pai1.getCromossomo();
        String genePai2 = pai2.getCromossomo();

        String geneFilho1;
        String geneFilho2;

        // realiza o corte,
        geneFilho1 = genePai1.substring(0, pontoCorte1);
        geneFilho1 += genePai2.substring(pontoCorte1, pontoCorte2);
        geneFilho1 += genePai1.substring(pontoCorte2, genePai1.length());
        geneFilho2 = genePai2.substring(0, pontoCorte1);
        geneFilho2 += genePai1.substring(pontoCorte1, pontoCorte2);
        geneFilho2 += genePai2.substring(pontoCorte2, genePai2.length());

        // cria o novo indivíduo com os genes dos pais
        filhos[0] = new Individuo(geneFilho1);
        filhos[1] = new Individuo(geneFilho2);

        return filhos;

    }

    /**
     * Método que recupera a solução.
     * 
     * @return String
     */
    public static String getSolucao() {
        return solucao;
    }

    /**
     * Método que seta a solução.
     * 
     * @param solucao (String)
     * @return void
     */
    public static void setSolucao(String solucao) {
        AlgoritmoGenetico.solucao = solucao;
    }

}
