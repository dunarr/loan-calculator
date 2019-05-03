import java.security.InvalidParameterException;

public class Loan {
    private int ammount;
    private int duration;
    private float rate;
    private int monthly;

    /**
     * Méthode pour calculer le taux mensuel
     *
     * @param rate (float) => taux fixe du prêt
     * @return tm =>taux mensuel utilisé dans les autres formules
     */

    private float getTm(float rate) {
        return (float) Math.pow(1 + rate, 1f / 12f) - 1;
    }

    /**
     * Méthode pour calculer le capital empruntable
     *
     * @param duration (int)=> durée de l'emprunt en mois
     * @param monthly (int)=> mensualité
     * @param rate (float) => taux fixe du prêt
     * @return ammount
     */

    public int getAmmount(int duration, int monthly, float rate) {

        //Vérification des paramètres

        if (duration <= 0) {
            throw new InvalidParameterException("duration must be positive");
        }
        if (monthly <= 0) {
            throw new InvalidParameterException("monthly must be positive");
        }
        if (rate <= 0) {
            throw new InvalidParameterException("rate must be positive");

        // fin de la vérification

        }
        float tm = getTm(rate);  //Reprise de la valeur de la fonction getTm
        this.ammount = Math.round((float) (monthly * (   //Méthode de calcul du capital empruntable
                1 - 1 /
                        Math.pow(1 + tm, duration)

        ) / tm));
        this.duration = duration;
        this.monthly = monthly;
        this.rate = rate;
        return this.ammount;
    }

    /**
     * Méthode de calcul de la durée de l'emprunt en mois
     *
     * @param ammount (int) => capital emprunté
     * @param monthly (int)=> mensualité
     * @param rate (float) => taux fixe du prêt
     * @return duration
     */

    public int getDuration(int ammount, int monthly, float rate) {

        //Vérification des paramètres

        if (monthly <= 0) {
            throw new InvalidParameterException("monthly must be positive");
        }
        if (rate <= 0) {
            throw new InvalidParameterException("rate must be positive");
        }
        if (ammount <= 0) {
            throw new InvalidParameterException("ammount must be positive");
        }

        //Fin de la vérification

        float tm = getTm(rate);  //Reprise de la valeur de la fonction getTm
        this.duration = Math.round((float) (Math.log(monthly / (monthly - ammount * tm)) / Math.log(1 + tm))); //Méthode de calcul de la durée
        if(this.duration == 0){
            throw new InvalidParameterException("you can't repay a loan with these parameters");
        }

            this.ammount = ammount;
        this.monthly = monthly;
        this.rate = rate;
        return this.duration;
    }

    /**
     * Méthode de calcul de la mensualité
     *
     * @param ammount (int) => capital emprunté
     * @param duration (int)=> durée de l'emprunt en mois
     * @param rate (float) => taux fixe du prêt
     * @return monthly
     */


    public int getMonthly(int ammount, int duration, float rate) {

        //Vérification des paramètres

        if (duration <= 0) {
            throw new InvalidParameterException("duration must be positive");
        }
        if (rate <= 0) {
            throw new InvalidParameterException("rate must be positive");
        }
        if (ammount <= 0) {
            throw new InvalidParameterException("ammount must be positive");
        }

        //Fin de la vérification

        float tm = getTm(rate);  //Reprise de la valeur de la fonction getTm

        this.monthly = Math.round((float) (ammount * tm / (1 - 1 / Math.pow(1 + tm, duration)))); //Méthode de calcul de la mensualité
        this.ammount = ammount;
        this.duration = duration;
        this.rate = rate;
        return this.monthly;
    }

    /**
     * Calcul du montant total à payer
     *
     * @return
     */

    public float getTotal() {
        return monthly * duration;
    }
}
