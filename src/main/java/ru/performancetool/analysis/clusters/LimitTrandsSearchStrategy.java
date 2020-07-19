package ru.performancetool.analysis.clusters;


import javafx.util.Pair;
import ru.performancetool.analysis.data.PurifiedData;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class LimitTrandsSearchStrategy extends Strategy {

    /**
     *  Work only in success test.
     * */

    /**
     * Needed already cleary result set
     */
    private PurifiedData resultSet;
    private List<String> independenceMetrics;
    private List<String> ignoringMetrics;
    private Pair<Instant, Instant> startAndFinishPeriod;
    private Map<Integer, String> layers;

}
