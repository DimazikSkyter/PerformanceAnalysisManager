package ru.performancetool.analysis.handler;

import ru.performancetool.analysis.clusters.ServiceLevelAgreement;
import ru.performancetool.analysis.clusters.Strategy;
import ru.performancetool.analysis.data.DataPoint;
import ru.performancetool.analysis.data.PurifiedData;
import ru.performancetool.analysis.data.ResultData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class DataAnalyserFlow {


    private final DataAnalyserFlowProperty property;
    private List<DataPoint> dataPoints = new ArrayList<>();
    private Map<Strategy, Predicate<PurifiedData>> plan;

    public DataAnalyserFlow(String name,
                            boolean needSave,
                            DataAnalyserFlowProperty property,
                            Map<Strategy, Predicate<PurifiedData>> plan) {
        this.property = property;
        this.plan = plan;
    }

    public ResultData run(PurifiedData purifiedData) throws IOException {
        ServiceLevelAgreement sla = ServiceLevelAgreement.handle(property.getSlaFunctionFileName());
        purifiedData.setSLACompleted(sla.accept(purifiedData));

        ResultData resultData = plan.entrySet().stream().filter(strategyPredicateEntry ->
                strategyPredicateEntry.getValue().test(purifiedData)
        )
                .map(strategyPredicateEntry -> strategyPredicateEntry.getKey().run(dataPoints, purifiedData))
                .filter(Objects::nonNull)
                .reduce(ResultData::joinAndReturnThis)
                .orElseThrow(() ->
                    new RuntimeException("Exception on handle result")
                );
        resultData.apply(dataPoints);
        return resultData;
    }
}
