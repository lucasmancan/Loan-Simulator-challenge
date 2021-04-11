package encargo;

import java.time.temporal.ChronoUnit;

import static java.lang.String.format;

public enum FrequenciaRecorrencia {
    DIA, MES, ANO;

    public ChronoUnit toChronoUnit() {
        ChronoUnit chronoUnit = null;

        switch (this) {
            case ANO:
                chronoUnit = ChronoUnit.YEARS;
                break;
            case DIA:
                chronoUnit = ChronoUnit.DAYS;
                break;
            case MES:
                chronoUnit = ChronoUnit.MONTHS;
                break;
            default:
                throw new IllegalArgumentException(format("There's no time unit equivalent to %s", this.name()));
        }

        return chronoUnit;
    }
}
