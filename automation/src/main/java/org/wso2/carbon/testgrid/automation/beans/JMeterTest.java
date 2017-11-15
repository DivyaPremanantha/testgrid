/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.testgrid.automation.beans;

import org.wso2.carbon.testgrid.automation.TestAutomationException;
import org.wso2.carbon.testgrid.automation.executor.TestExecutor;
import org.wso2.carbon.testgrid.automation.executor.TestExecutorFactory;
import org.wso2.carbon.testgrid.common.Deployment;
import org.wso2.carbon.testgrid.common.constants.TestGridConstants;

import java.util.List;

/**
 * This class is responsible for Executing the JMeter scripts for a single test.
 *
 * @since 1.0.0
 */
public class JMeterTest extends Test {

    private List<String> jMeterScripts;
    private TestExecutor testExecutor;

    public JMeterTest() throws TestAutomationException {
        testExecutor = TestExecutorFactory.getTestExecutor(TestGridConstants.TEST_TYPE_JMETER);
    }

    private List<String> getjMeterScripts() {
        return jMeterScripts;
    }

    public void setjMeterScripts(List<String> jMeterScripts) {
        this.jMeterScripts = jMeterScripts;
    }

    /**
     * @param testLocation the jmeter tests location as a String.
     * @param deployment   Deployment mapping information.
     * @throws TestAutomationException When there is an execution error.
     */
    @Override
    public void execute(String testLocation, Deployment deployment) throws TestAutomationException {
        testExecutor.init(testLocation, getTestName());
        for (String script : this.getjMeterScripts()) {
            testExecutor.execute(script, deployment);
        }
    }
}
