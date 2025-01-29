package org.devops

def runTest(){
    sh 'npm test'
}

def analisys(projectGitName){
    def scannerHome = tool 'sonar-scanner'
    withSonarQubeEnv('sonar-scanner') {
        sh "${scannerHome}/bin/sonar-scanner -X \
            -Dsonar.projectKey=${projectGitName} \
            -Dsonar.projectName=${projectGitName} \
            -Dsonar.sources=${env.SOURCE} \
            -Dsonar.tests=src/__test__ \
            -Dsonar.exclusions='**/*.test.js' \
            -Dsonar.testExecutionReportPaths=./test-report.xml \
            -Dsonar.javascript.lcov.reportPaths=./coverage/lcov.info"
    }  
}
