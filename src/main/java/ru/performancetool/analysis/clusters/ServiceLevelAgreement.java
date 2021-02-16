package ru.performancetool.analysis.clusters;

import lombok.Getter;
import ru.performancetool.analysis.data.PurifiedData;
import ru.performancetool.analysis.data.PurifiedDataElement;
import ru.performancetool.analysis.utilities.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ServiceLevelAgreement {

    @Getter
    private Map<String, Boolean> agreements = new HashMap<>();
    private Function<PurifiedDataElement, Boolean> function;

    public ServiceLevelAgreement(Function<PurifiedDataElement, Boolean> function) {
        this.function = function;
    }

    public static ServiceLevelAgreement handle(String functionFile) throws IOException {
        Function<PurifiedDataElement, Boolean> function = Utils.loadFunction(functionFile);
        ServiceLevelAgreement serviceLevelAgreement = new ServiceLevelAgreement(function);
        return serviceLevelAgreement;
    }

    public void fillAgreement(PurifiedDataElement purifiedDataElement) {
        agreements.put(purifiedDataElement.getName(), function.apply(purifiedDataElement));
    }

    public boolean accept(PurifiedData purifiedData) {
        purifiedData.getResultDataElementsList().forEach(
                this::fillAgreement
        );
        return this
                .getAgreements()
                .values()
                .stream()
                .reduce((boolean1, boolean2) -> boolean1 && boolean2)
                .orElse(false);
    }
}
