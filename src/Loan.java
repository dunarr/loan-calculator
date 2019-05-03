import java.security.InvalidParameterException;

public class Loan {
    private int ammount;
    private int duration;
    private float rate;
    private int monthly;


    private float getTm(float rate) {
        return (float) Math.pow(1 + rate, 1f / 12f) - 1;
    }

    public int getAmmount(int duration, int monthly, float rate) {
        if (duration <= 0) {
            throw new InvalidParameterException("duration must be positive");
        }
        if (monthly <= 0) {
            throw new InvalidParameterException("monthly must be positive");
        }
        if (rate <= 0) {
            throw new InvalidParameterException("rate must be positive");
        }
        float tm = getTm(rate);
        this.ammount = Math.round((float) (monthly * (
                1 - 1 /
                        Math.pow(1 + tm, duration)

        ) / tm));
        this.duration = duration;
        this.monthly = monthly;
        this.rate = rate;
        return this.ammount;
    }

    public int getDuration(int ammount, int monthly, float rate) {
        if (monthly <= 0) {
            throw new InvalidParameterException("monthly must be positive");
        }
        if (rate <= 0) {
            throw new InvalidParameterException("rate must be positive");
        }
        if (ammount <= 0) {
            throw new InvalidParameterException("ammount must be positive");
        }
        float tm = getTm(rate);
        this.duration = Math.round((float) (Math.log(monthly / (monthly - ammount * tm)) / Math.log(1 + tm)));
        if(this.duration == 0){
            throw new InvalidParameterException("you can't repay a loan with these parameters");
        }

            this.ammount = ammount;
        this.monthly = monthly;
        this.rate = rate;
        return this.duration;
    }

    public int getMonthly(int ammount, int duration, float rate) {
        if (duration <= 0) {
            throw new InvalidParameterException("duration must be positive");
        }
        if (rate <= 0) {
            throw new InvalidParameterException("rate must be positive");
        }
        if (ammount <= 0) {
            throw new InvalidParameterException("ammount must be positive");
        }
        float tm = getTm(rate);

        this.monthly = Math.round((float) (ammount * tm / (1 - 1 / Math.pow(1 + tm, duration))));
        this.ammount = ammount;
        this.duration = duration;
        this.rate = rate;
        return this.monthly;
    }

    public float getTotal() {
        return monthly * duration;
    }
}
