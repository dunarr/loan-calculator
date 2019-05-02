public class Loan {
    private int ammount;
    private int duration;
    private float rate;
    private int monthly;


    private float getTm(float rate){
        return (float) Math.pow(1 + rate, 1f / 12f) - 1;
    }

    public int getAmmount(int duration, int monthly, float rate) {
        float tm = getTm(rate);
        return Math.round((float)(monthly * (
                1 - (
                        Math.pow(1 + tm,12f*duration/12)
                )
        )/tm));
    }

    public int getDuration(int ammount, int monthly, float rate) {
        float tm = getTm(rate);
    }

    public int getMonthly(int ammount, int duration, float rate) {

    }

    public float getTotal() {
        return 0f;
    }
}
