package ru.performancetool.analysis.data;

import javafx.util.Pair;
import lombok.Data;
import lombok.val;

import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

import static ru.performancetool.analysis.data.MetricsDescription.MetricsTypes.*;

@Data
public class MetricsDescription {

    /**
     * metricsDescription - name and type
     * max size 12
     */
    @Size(max = 12)
    private List<Pair<String, Class<?>>> metricsDescription;

    public MetricsDescription(Pair<String, Class<?>>... metrics) {
        metricsDescription = Arrays.asList(metrics);
    }

    public MetricsTypes getMetricsTypes() {
        return metricsDescription.stream()
                .map(pair -> {
                    val cls = pair.getValue();
                    if (cls.equals(Integer.class)) {
                        return ALL_INT;
                    } else if (cls.equals(Double.class)) {
                        return ALL_DOUBLE;
                    }
                    return ANY;
                })
                .reduce((metricsType, metricsType2) -> {
                    if (!metricsType.equals(metricsType2)) {
                        return ANY;
                    }
                    if (metricsType.equals(ALL_INT)) {
                        return ALL_INT;
                    }
                    if (metricsType.equals(ALL_DOUBLE)) {
                        return ALL_DOUBLE;
                    }
                    return ANY;
                })
                .orElse(ANY);
    }

    public enum MetricsTypes {
        ANY(Object.class), ALL_INT(int.class), ALL_DOUBLE(double.class);

        private Class cls;

        MetricsTypes(Class cls) {
            this.cls = cls;

        }

        public static MetricsTypes getMetricsTypesFromShortCase(String shortCase) {
            switch (shortCase) {
                case "int":
                    return ALL_INT;
                case "dou":
                    return ALL_DOUBLE;
                default:
                    return ANY;
            }
        }

        public Class getCls() {
            return cls;
        }
    }
}
