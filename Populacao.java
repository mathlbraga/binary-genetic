package binario;

/**
 * Classe para criação de uma População.
 * 
 * @author Matheus Braga
 */
public class Populacao {

    private Individuo[] individuos;
    private int tamPopulacao;

    /**
     * Método construtor de uma população com indivíduos aleatórios.
     * 
     * @param tamanhoCromossomo (int)
     * @param tamanhoPopulacao  (int)
     * @return void
     */
    public Populacao(int tamCromossomo, int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
        this.individuos = new Individuo[tamPopulacao];
        for (int i = 0; i < this.individuos.length; i++) {
            this.individuos[i] = new Individuo(tamCromossomo);
        }
    }

    /**
     * Método construtor de uma população com indivíduos nulos.
     * 
     * @param tamanhoPopulacao (int)
     * @return void
     */
    public Populacao(int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
        this.individuos = new Individuo[tamPopulacao];
        for (int i = 0; i < this.individuos.length; i++) {
            this.individuos[i] = null;
        }
    }

    /**
     * Método que recupera um indivíduo da população.
     * 
     * @param index (int)
     * @return Individuo
     */
    public Individuo getIndividuo(int index) {
        return individuos[index];
    }

    /**
     * Método que seta um indivíduo em um índice da população.
     * 
     * @param index     (int)
     * @param individuo (Individuo)
     * @return void
     */
    public void setIndividuos(int index, Individuo individuo) {
        this.individuos[index] = individuo;
    }

    /**
     * Método que seta um indivíduo no primeiro índice disponível da população.
     * 
     * @param individuo (Individuo)
     * @return void
     */
    public void setIndividuo(Individuo individuo) {
        for (int i = 0; i < individuos.length; i++) {
            if (individuos[i] == null) {
                individuos[i] = individuo;
                return;
            }
        }
    }

    /**
     * Método que recupera o tamanho da população.
     * 
     * @return int
     */
    public int getTamPopulacao() {
        return tamPopulacao;
    }

    /**
     * Método que seta o tamanho da população.
     * 
     * @param tamanhoPopulacao (int)
     * @return void
     */
    public void setTamPopulacao(int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
    }

    /**
     * Método que recupera o número de indivíduos não nulos (existentes).
     * 
     * @return int
     */
    public int getNumIndividuos() {
        int num = 0;
        for (int i = 0; i < individuos.length; i++) {
            if (individuos[i] != null) {
                num++;
            }
        }
        return num;
    }

    /**
     * Método que ordena os indivíduos pelas aptidões.
     * 
     * @return void
     */
    public void ordenaPopulacao() {
        boolean trocou = true;
        while (trocou) {
            trocou = false;
            for (int i = 0; i < individuos.length - 1; i++) {
                if (individuos[i].getAptidao() < individuos[i + 1].getAptidao()) {
                    Individuo temp = individuos[i];
                    individuos[i] = individuos[i + 1];
                    individuos[i + 1] = temp;
                    trocou = true;
                }
            }
        }
    }
}
