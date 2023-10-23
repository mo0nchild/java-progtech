package application.models;

public class Procent implements Procentable {

    private Float value = 0.f;

    public Procent(float value) {
        super();
        this.value = value;
    }
    public Procent() { this(0.f); }

    @Override
    public final Float getValue() { return this.value; }

    @Override
    public final void setValue(Float value) { this.value = value; }

    @Override
    public Float countPr(int pr) throws ProcentException {
        if (pr <= 0) throw new ProcentException("countPr", "Неверное значение");
        return this.value * pr / 100.f;
    }

    @Override
    public Float countSum(float sum, float pr) throws ProcentException {
        if (pr <= 0) throw new ProcentException("countSum", "Неверное значение");
        return sum * pr / 100.f;
    }

    @Override
    public Integer countSumrnd(int pr) throws ProcentException {
        if (pr <= 0) throw new ProcentException("countSumrnd", "Неверное значение");
        return Math.round(this.value * pr / 100.f);
    }
}
