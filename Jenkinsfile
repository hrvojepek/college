pipeline {
    agent any
    stages {
        stage('SCM Checkout') {
            steps {
                checkout scm
                stopBuilds()
            }
        }

        stage('Build') {
            steps {
                script {
                    sh "/usr/share/maven/bin/mvn clean install -Dmaven.test.failure.ignore=true"
                    junit '**/target/*-reports/*.xml'
                }
            }
        }


        stage('Deploy SNAPSHOT') {
            when {
                branch 'master'
            }
            steps {
                configFileProvider([configFile(fileId: 'maven-settings-with-deploy-snapshot', variable: 'MAVEN_SETTINGS')]) {
                    script {
                        sh "/usr/share/maven/bin/mvn deploy -s $MAVEN_SETTINGS -DskipTests"
                    }
                }
            }
        }

        stage('Release') {
            when {
                branch 'master'
            }
            steps {
                    // shutdown
                    sh 'curl -X POST http://vmi87509.contabo.host:10000/shutdown || true'
                    // copy file to target location
                    sh 'cp target/*.jar /tmp/'
                    // start the application
                    sh 'nohup java -jar /tmp/*.jar &'
                    // wait for application to respond
                    sh 'while ! httping -qc1 http://vmi87509.contabo.host:10000 ; do sleep 1 ; done'
            }
        }
    }
}

    def stopBuilds() {
            sh "echo 'Stop running builds'"
        	def jobname = env.JOB_NAME
            def buildnum = env.BUILD_NUMBER.toInteger()

            def job = Jenkins.instance.getItemByFullName(jobname)
             for (build in job.builds) {
                 if (!build.isBuilding()) { continue; }
                 if (buildnum == build.getNumber().toInteger()) { continue; println "equals" }
                build.doStop();
            }
    }