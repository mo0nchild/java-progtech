package application.models;

public interface Procentable {
    public Float countPr(int pr) throws ProcentException;
    public Float countSum(float sum, float pr) throws ProcentException;

    public Integer countSumrnd(int pr) throws ProcentException;
    public Float getValue();
    public void setValue(Float value);
}
