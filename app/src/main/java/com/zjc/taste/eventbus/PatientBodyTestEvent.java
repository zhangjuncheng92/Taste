package com.zjc.taste.eventbus;

import java.util.Map;

/**
 * Created by 96999 on 2017/8/4.
 */

public class PatientBodyTestEvent {
    private Map<String, Integer> scoreMap;

    public Map<String, Integer> getScoreMap() {
        return scoreMap;
    }

    public void setScoreMap(Map<String, Integer> scoreMap) {
        this.scoreMap = scoreMap;
    }

    public PatientBodyTestEvent(Map<String, Integer> scoreMap) {
        this.scoreMap = scoreMap;
    }
}
