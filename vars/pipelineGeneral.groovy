def call(){
    
    pipeline{

        agent any

        tools{
            nodejs 'NodeJS18'
        }
        
        environment{
            projectName = "${env.GIT_URL_1}".replaceAll('.+/(.+)\\.git', '$1')toLowerCase()
        }

        stages{
            stage('Fase 1: Proceso de construcción') {
                steps {
                    script {
                        def cloneapp = new org.devops.lb_buildartefacto()
                        cloneapp.clone()
                        def buildapp = new org.devops.lb_buildartefacto()
                        buildapp.install()
                    }
                }
            }

            stage('Fase 1: Análisis de Sonarqube'){
                steps{
                    script{
                        def test = new org.devops.lb_analisissonarqube()
                        test.runTest()
                        def analisysSonarqube = new org.devops.lb_analisissonarqube()
                        analisysSonarqube.analisys("${projectName}")
                    }
                }
            }
        }
    }
}
