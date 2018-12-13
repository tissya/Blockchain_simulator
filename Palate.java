import org.apache.commons.math3.distribution.ParetoDistribution;

class Palate{

    public  static void main(String[] args){
    ParetoDistribution d = new ParetoDistribution();
    double r = d.sample();

    //System.out.println(r);
    //System.out.println(d.density(0));
    //System.out.println(d.density(2));
    //System.out.println(d.density(15));
    //System.out.println(d.density(30));
    //System.out.println(d.density(300));

    for(int i = 0; i <= 20; i++){
        System.out.println( i +" : " + d.density(i) * 2000);
    }
    }
}