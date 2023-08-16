package com.qa.nbaWarriors.Reporting;

import cucumber.runtime.StepDefinitionMatch;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;

public class CustomFormatter implements Reporter {
    @Override
    public void before(Match match, Result result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void result(Result result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void after(Match match, Result result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void match(Match match) {
        // TODO Auto-generated method stub
        ThreadLocalStepDefinitionMatch.set((StepDefinitionMatch)match);
    }

    @Override
    public void embedding(String mimeType, byte[] data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void write(String text) {
        // TODO Auto-generated method stub

    }
}
