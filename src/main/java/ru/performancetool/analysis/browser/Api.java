package ru.performancetool.analysis.browser;

import java.util.List;

public interface Api {

    void getHealth();

    void uploadResult(String path);

    List showArchive();

    Object showTestResult(String testUUID);

    Object getTestResult(boolean compute, String testUUID);
}
