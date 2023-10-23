package application.domen;

import application.models.ProcentException;

public interface Calculatable {
    public Float calculate() throws ProcentException;
}
