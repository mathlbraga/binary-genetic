package binario;

import java.util.Random;

/**
 * Classe para criação de um indivíduo.
 * 
 * @author Matheus Braga
 */
public final class Individuo {

    private final Random rand = new Random();
    private final char[] binario = "01".toCharArray();
    private int aptidao = 0;
    String cromossomo;

    /**
     * Método construtor de um indivíduo com um número de genes.
     * 
     * @param numeroDeGenes (int)
     * @return void
     */
    public Individuo(int numGenes) {
        this.cromossomo = "";
        for (int i = 0; i < numGenes; i++) {
            this.cromossomo += geraGene();
        }
        geraAptidao();
    }

    /**
     * Método construtor de um indivíduo com um cromossomo definido.
     * 
     * @param cromossomo (String)
     * @return void
     */
    public Individuo(String cromossomo) {
        this.cromossomo = cromossomo;
        Random r = new Random();
        StringBuilder gene = new StringBuilder();
        // cria um gene aleatório para a mutação
        if (r.nextDouble() <= AlgoritmoGenetico.getTaxaMutacao()) {
            gene.append(cromossomo);
            int posAleatoria = r.nextInt(cromossomo.length());
            for (int i = 0; i < gene.length(); i++) {
                if (i == posAleatoria) {
                    if (String.valueOf(cromossomo.charAt(i)).equals("0")) {
                        gene.setCharAt(i, "1".charAt(0));
                    } else {
                        gene.setCharAt(i, "0".charAt(0));
                    }
                }
            }

            this.cromossomo = gene.toString();
        }
        geraAptidao();
    }

    /**
     * Método para criação de um gene (0 ou 1) para o indivíduo.
     * 
     * @return String
     */
    public String geraGene() {
        StringBuilder gene = new StringBuilder();
        int ch = rand.nextInt(binario.length);
        gene.append(binario[ch]);
        return gene.toString();
    }

    /**
     * Método que recupera um indivíduo (cromossomo).
     * 
     * @return String
     */
    public String getCromossomo() {
        return this.cromossomo;
    }

    /**
     * Método que recupera uma aptidão de um indivíduo.
     * 
     * @return int
     */
    public int getAptidao() {
        return aptidao;
    }

    /**
     * Método que seta uma aptidão de um indivíduo.
     * 
     * @param aptidao (int)
     * @return void
     */
    public void setAptidao(int aptidao) {
        this.aptidao = aptidao;
    }

    /**
     * Método que verifica o quão apto é um indivíduo e seta a aptidão.
     * 
     * @return void
     */
    public void geraAptidao() {
        String solucao = AlgoritmoGenetico.getSolucao();
        for (int i = 0; i < solucao.length(); i++) {
            if (solucao.charAt(i) == cromossomo.charAt(i)) {
                aptidao++;
            }
        }
    }

}
