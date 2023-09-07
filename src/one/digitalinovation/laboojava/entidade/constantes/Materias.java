
package one.digitalinovation.laboojava.entidade.constantes;

public enum Materias {

    M2(2), 
    
    M5(3),
    
    M10(5);

     private double mult;

  
    Materias(double mult) {
        this.mult = mult / 100;
    }

    public double getFator() {
        return mult;
    }

}
