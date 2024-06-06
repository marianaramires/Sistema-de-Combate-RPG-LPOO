abstract class Personagem {
    private String nomeTipo;
    private double saude;
    private double forca;
    private double destreza;
    Arma arma;

    public Personagem(String nomeTipo, double saude, double forca, double destreza, Arma arma) {
        this.nomeTipo = nomeTipo;

        if (saude < 0) {
            this.saude = 0;
        } else if (saude > 10) {
            this.saude = 10;
        } else {
            this.saude = saude;
        }

        if (forca < 0) {
            this.forca = 0;
        } else if (forca > 10) {
            this.forca = 10;
        } else {
            this.forca = forca;
        }

        if (destreza < 0) {
            this.destreza = 0;
        } else if (destreza > 10) {
            this.destreza = 10;
        } else {
            this.destreza = destreza;
        }

        this.arma = arma;
    }

    public void printStatus() {
        if(this.estaMorto()){
            System.out.printf("%s [Morreu, Forca: %d, Destreza: %d, %s]\n", this.nomeTipo, 
            this.forca, this.destreza, this.arma.getNome());
        } else{
            System.out.printf("%s [Saude: $d, Forca: %d, Destreza: %d, %s]\n", this.nomeTipo, 
            this.saude, this.forca, this.destreza, this.arma.getNome());
        }
    }

    public void atacar(Personagem b){
        if (this.estaMorto()){
            System.out.printf("O %s não consegue atacar, pois está morto.\n", this.nomeTipo);
        } else{
            System.out.printf("O %s ataca o %s com %s.\n", this.nomeTipo, b.nomeTipo, this.arma.getNome());

            if(b.estaMorto()){
                System.out.printf("Pare! O %s já está morto!\n", b.nomeTipo);
            } else{
                if(this.destreza > b.destreza){ // ATAQUE BEM SUCEDIDO - nao eh defendido
                    double dano = this.calculaDano();
                    b.recebeDano(dano);
                    System.out.printf("O ataque foi efetivo com %d pontos de dano!\n", dano);
                } else if(this.destreza < b.destreza){ // ATAQUE MAL SUCEDIDO - defendido e revidado por B
                    double dano = b.calculaDano();
                    this.recebeDano(dano);
                    System.out.printf("O ataque foi inefetivo e revidado com %d pontos de dano!\n", dano);
                } else if (this.destreza == b.destreza){ // ATAQUE DEFENDIDO
                    System.out.println("O ataque foi defendido, ninguem se machucou!\n");
                }
            }

            this.printStatus();
            b.printStatus();
        }    
    }

    private double calculaDano() {
        return this.forca * this.arma.getModDano();
    }

    private void recebeDano(double pontosDano) {
        this.saude = this.saude - pontosDano;
    }

    private boolean estaMorto() {
        if (saude < 1.0) {
            return true;
        } else {
            return false;
        }
    }
}