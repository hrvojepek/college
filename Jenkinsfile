import groovy.json.JsonSlurper;

node{
    stage 'Stop Build, Build, Test and Package'
    env.PATH = "/usr/share/maven/bin:${env.PATH}"
    git url: "https://github.com/hrvojepek/college.git"
	sh 'curl -X POST http://vmi87509.contabo.host:10000/shutdown || true'
	// sh 'curl -X POST http://vmi87509.contabo.host:10000/job/AutoServis/lastBuild/stop || true'
	def jobname = env.JOB_NAME
    def buildnum = env.BUILD_NUMBER.toInteger()

    def job = Jenkins.instance.getItemByFullName(jobname)
     for (build in job.builds) {
         if (!build.isBuilding()) { continue; }
         if (buildnum == build.getNumber().toInteger()) { continue; println "equals" }
        build.doStop();
    }
    // workaround, taken from https://github.com/jenkinsci/pipeline-examples/blob/master/pipeline-examples/gitcommit/gitcommit.groovy
    def commitid = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
    def workspacePath = pwd()
    sh "echo ${commitid} > ${workspacePath}/expectedCommitid.txt"
    sh "mvn clean package -Dcommitid=${commitid}"
}

node{
    stage 'Stop, Deploy and Start'
    // shutdown
    sh 'curl -X POST http://vmi87509.contabo.host:10000/shutdown || true'
    // copy file to target location
    sh 'cp target/*.jar /tmp/'
    // start the application
    sh 'nohup java -jar /tmp/*.jar &'
    // wait for application to respond
    sh 'while ! httping -qc1 http://vmi87509.contabo.host:10000 ; do sleep 1 ; done'
}

node{
    stage 'Smoketest'
    def workspacePath = pwd()
    sh "curl --retry-delay 10 --retry 5 http://vmi87509.contabo.host:10000/info -o ${workspacePath}/info.json"
    if (deploymentOk()){
        return 0
    } else {
        return 1
    }
}


def deploymentOk(){
    def workspacePath = pwd()
    expectedCommitid = new File("${workspacePath}/expectedCommitid.txt").text.trim()
    actualCommitid = readCommitidFromJson()
    println "expected commitid from txt: ${expectedCommitid}"
    println "actual commitid from json: ${actualCommitid}"
    return expectedCommitid == actualCommitid
}

def readCommitidFromJson() {
    def workspacePath = pwd()
    def slurper = new JsonSlurper()
    def json = slurper.parseText(new File("${workspacePath}/info.json").text)
    def commitid = json.app.commitid
    return commitid
}

def cancelRunning() {
        sh "echo 'Cancel running builds'"
        def numCancels = 0;
        for (job in this.hudson.instance.items) {
            for (build in job.builds) {
                if (build == this.build) { continue; } // don't cancel ourself!
                if (!build.hasProperty('causes')) { continue; }
                if (!build.isBuilding()) { continue; }
                for (cause in build.causes) {
                    if (!cause.hasProperty('upstreamProject')) { continue; }
                    if (cause.upstreamProject == this.upstreamProject &&
                            cause.upstreamBuild == this.upstreamBuild) {
                        this.printer.println('Stopping ' + build.toString());
                        build.doStop();
                        this.printer.println(build.toString() + ' stopped.');
                        numCancels++;
                        break;
                    }
                }
            }
        }
    }
